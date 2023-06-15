package com.example.codingnatorpoject.DBConnection;

import android.os.AsyncTask;
import android.util.Log;

import androidx.work.Data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class User {
    static String nickname;
    static String email;
    static String progress;
    static byte[][] stars;
    static int totalStars;

    private static String json;
    private static DatabaseConnector db = new DatabaseConnector();

    // this method may take about a second
    public static boolean setUser(String id) {
        Log.i("setUser", "Trying with" + id);
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
                        logout(false); return null;
                    }

                    obj = obj.getJSONObject("body");
                    progress = obj.getString("progress");
                    Log.e("asdf", progress);
                    nickname = obj.getString("nickname");
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
        try { Thread.sleep(10000); }
        catch (Exception e) { e.printStackTrace();}

        // if failed to find user
        if (email == null) return false;

        return true;
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
        if (tmp < newStar) {
            stars[stage-1][chapter-1] = newStar;
            totalStars += (newStar - tmp);
        }
        encodeProgress();
        db.updateProgress(email, progress);
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
