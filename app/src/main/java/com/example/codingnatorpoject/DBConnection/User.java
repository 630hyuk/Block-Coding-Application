package com.example.codingnatorpoject.DBConnection;

import android.os.AsyncTask;
import android.util.Log;

import androidx.work.Data;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class User {
    static String nickname;
    static String email;
    static String progress;
    static byte[][] stars;
    static int totalStars;

    private static String json;
    private static DatabaseConnector db = new DatabaseConnector();

    // this method may take about 0.5 seconds
    public static boolean setUser(String id) {
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
                        logout(); return null;
                    }

                    obj = obj.getJSONObject("body");
                    progress = obj.getString("progress");
                    nickname = obj.getString("nickname");
                    totalStars = obj.getInt("stars");

                }
                catch (Exception e) { Log.e(e.toString(), "in getuserdata"); }

                return null;
            }
        }

        new UserGetter().execute(email);
        try { Thread.sleep(500); }
        catch (Exception e) { e.printStackTrace();}

        // if failed to find user
        if (email == null) return false;

        // set Array: stars
        decodeProgress();
        return true;
    }

    public static void logout() {
        db.updateProgress(email, progress);
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
            String tmp = progress.substring(i*4, i*4+4);

            for (int j = 0; j < 3; j++) {
                byte toDecode = (byte)tmp.charAt(j);
                stars[i][j*3 + 2] = (byte)(toDecode & 0b00000011);
                toDecode >>= 2;
                stars[i][j*3 + 1] = (byte)(toDecode & 0b00000011);
                toDecode >>= 2;
                stars[i][j*3] = (byte)(toDecode & 0b00000011);
            }
            stars[i][9] = (byte)(tmp.charAt(3) - '0');
        }
    }

    private static void encodeProgress() {
        StringBuilder newProgress = new StringBuilder();

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {
                byte toEncode = stars[i][j*3];
                toEncode <<= 2;
                toEncode |= stars[i][j*3+1];
                toEncode <<= 2;
                toEncode |= stars[i][j*3+2];
                newProgress.append((char)toEncode);
            }
            newProgress.append((char)stars[i][9]);
        }

        progress = newProgress.toString();
    }
}
