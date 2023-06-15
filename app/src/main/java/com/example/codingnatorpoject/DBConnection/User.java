package com.example.codingnatorpoject.DBConnection;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import androidx.work.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class User {
    static String nickname = null;
    static String email = null;
    static String progress = null;
    static byte[][] stars = null;
    static int totalStars = 0;

    public static ArrayList<Pair<String, Pair<String, Integer>>> users;
    private static String json;
    private static DatabaseConnector db = new DatabaseConnector();
    private static boolean isDownloaded = false;

    public static void getUserList(boolean forceUpdate) {
        if (forceUpdate) isDownloaded = false;
        getUserList();
    }

    public static void getUserList() {
        users = new ArrayList<>();
        class UserScanner extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                try {
                    URL reqUrl = new URL(
                            "https://71cyxe4ifa.execute-api.ap-northeast-2.amazonaws.com/default/scanuserdata"
                    );
                    HttpURLConnection conn = (HttpURLConnection) reqUrl.openConnection();

                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Content-Type", "application/json; utf-8");
                    conn.addRequestProperty("User-Agent", "Mozilla/5.0");
                    conn.setRequestProperty("Accept", "*/*");

                    conn.connect();

                    // read from connection stream
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuffer sb = new StringBuffer();
                    String tmp;
                    while((tmp = br.readLine()) != null) sb.append(tmp);
                    br.close();

                    // deal with json
                    json = sb.toString();
                    JSONObject obj = new JSONObject(json);
                    if (obj.getInt("statusCode") != 200) {
                        Log.e("scanUser", "Error");
                        return null;
                    }

                    JSONArray arr = obj.getJSONArray("body");
                    for (int i = 0, z = arr.length(); i < z; i++) {
                        JSONObject tmpObj = arr.getJSONObject(i);
                        Log.i("getUserList", tmpObj.toString());

                        String id = tmpObj.getString("id");
                        String nickname = tmpObj.getString("nickname");
                        int stars;

                        try {
                            stars = tmpObj.getInt("stars");
                        }
                        catch (JSONException e) {stars = 0;}

                        users.add(new Pair<>(id, new Pair<>(nickname, stars)));
                    }

                    try {
                        totalStars = obj.getInt("stars");
                    }
                    catch(JSONException e) {
                        totalStars = 0;
                    }
                }
                catch (Exception e) { Log.e("in scanuserdata", e.toString()); }

                isDownloaded = true;
                return null;
            }

            @Override
            protected void onPostExecute(String str) {
                super.onPostExecute(str);
            }
        }

        if (!isDownloaded) {
            new UserScanner().execute("");

            while (!isDownloaded) {
                try { Thread.sleep(100); }
                catch (Exception e) { e.printStackTrace(); }
            };
        }
    }

    // this method may take about a second
    public static boolean setUser(String id) {
        Log.i("setUser", "Trying with <" + id + ">");
        stars = new byte[3][10];

        email = id;

        class UserGetter extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                try {
                    URL reqUrl = new URL(
                            "https://71cyxe4ifa.execute-api.ap-northeast-2.amazonaws.com/default/getuserdata?" +
                                    "id=" + params[0]
                    );
                    HttpURLConnection conn = (HttpURLConnection) reqUrl.openConnection();

                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Content-Type", "application/json; utf-8");
                    conn.addRequestProperty("User-Agent", "Mozilla/5.0");
                    conn.setRequestProperty("Accept", "*/*");

                    conn.connect();

                    // read from connection stream
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuffer sb = new StringBuffer();
                    String tmp;
                    while((tmp = br.readLine()) != null) sb.append(tmp);
                    br.close();

                    // deal with json
                    json = sb.toString();
                    JSONObject obj = new JSONObject(json);
                    if (obj.getInt("statusCode") != 200) {
                        Log.e("setUser", "User not found");
                        logout(); return null;
                    }

                    obj = obj.getJSONObject("body");
                    nickname = obj.getString("nickname");

                    // two try-catch codes are usually not used.
                    try {
                        progress = obj.getString("progress");
                    }
                    catch(JSONException e) {
                        Log.e("User.getUser", "Oh, no");
                        Log.e("User.getUser", String.valueOf(progress));
                        progress = "000000000000000000000000000000";
                    }

                    try {
                        totalStars = obj.getInt("stars");
                    }
                    catch(JSONException e) {
                        totalStars = 0;
                    }

                    // set Array: stars
                    decodeProgress();
                }
                catch (Exception e) { Log.e("in getuserdata", e.toString()); }

                return null;
            }
        }

        new UserGetter().execute(email);
        try { Thread.sleep(500); }
        catch (Exception e) { e.printStackTrace();}

        // if failed to find user
        return email != null;
    }

    public static void logout() {
        logout(false);
    }

    public static void logout(boolean update) {
        if (update) db.updateProgress(email, progress);
        nickname = null;
        email = null;
        progress = null;
        stars = null;
        totalStars = 0;
    }

    public static int getStarAt(int stage, int chapter) {
        return stars[stage-1][chapter-1];
    }

    // if newStar is higher than the record before,
    // update the data of stars[][] and totalStars
    public static void updateStarAt(int stage, int chapter, byte newStar) {
        byte tmp = stars[stage-1][chapter-1];
        byte resultStar = newStar;

        if (chapter == 10) {
            resultStar = (byte)(
                    (newStar < 3) ? 0 :
                    (newStar < 7) ? 1 :
                    (newStar < 10) ? 2 : 3
            );
        }

        if (tmp < resultStar) {
            stars[stage-1][chapter-1] = resultStar;
            totalStars += (resultStar - tmp);
        }
        encodeProgress();
        db.updateProgress(email, progress, totalStars);
    }

    private static void decodeProgress() {
        if (progress == null) {
            Log.e("decodeProgress", "need Login");
            return;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                char tmp = progress.charAt(10*i + j);
                stars[i][j] = (byte)(tmp - '0');
            }
        }
    }

    private static void encodeProgress() {
        StringBuilder newProgress = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                char tmp = (char)(stars[i][j] + '0');
                newProgress.append(tmp);
            }
        }

        progress = newProgress.toString();
    }
}
