package com.example.codingnatorpoject.DBConnection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class QuestionRepository {

    static final String[] fields = {"content", "hint", "isOX", "cand1", "cand2", "cand3", "cand4", "answer", "explanation", "image"};
    public static final String id_format = "%d-%d-%d";

    static HashMap<String, HashMap<String, String>> questions;
    static HashMap<String, Bitmap> images;
    static boolean isDownloaded = false;

    public QuestionRepository(Context context) throws JSONException {
        class QGetter extends AsyncTask<Integer, Void, String> {

            @Override
            protected String doInBackground(Integer... params) {
                String json;
                try {
                    int stage = params[0], chapter = params[1], pn = params[2];
                    URL reqUrl = new URL(
                            "https://71cyxe4ifa.execute-api.ap-northeast-2.amazonaws.com/default/getQuestionData?" +
                                    "stage=" + stage +
                                    "&chapter=" + chapter +
                                    "&pn=" + pn);

                    HttpsURLConnection conn = (HttpsURLConnection) reqUrl.openConnection();

                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Content-Type", "application/json; utf-8");
                    conn.addRequestProperty("User-Agent", "Mozilla/5.0");
                    conn.setRequestProperty("Accept", "*/*");

                    //conn.setDoOutput(true);

                    conn.connect();

                    Log.i("getQuestion", conn.getResponseMessage());

                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));

                    StringBuffer sb = new StringBuffer();   // Why not Stringbuilder? because it's asynctask.
                    String tmp;
                    while ((tmp = br.readLine()) != null) sb.append(tmp);

                    br.close();

                    json = sb.toString();

                    String key = String.format(id_format, stage, chapter, pn);

                    Log.i("asdf", json);
                    JSONObject obj = new JSONObject(json);
                    int statusCode = obj.getInt("statusCode");

                    if (statusCode != 200) {
                        questions.put(key, null);
                        return null;
                    }

                    questions.put(key, new HashMap<>());
                    obj = obj.getJSONObject("body");

                    HashMap<String, String> qObj = (HashMap) questions.get(key);
                    for (String field : fields) {
                        //Log.i("asdf", field + ": " + obj.getString(field));
                        qObj.put(field, obj.getString(field));
                    }

                    Log.i("QuestionRepository", key+" completed");
                }
                catch (Exception e) {
                    Log.e("in QuestionRepository()",e.toString()); e.printStackTrace();
                }

                return null;
            }

        }

        class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
            // strings[0] : key "1-1-1"
            @Override
            protected Bitmap doInBackground(String... strings) {
                try {
                    String key = strings[0];
                    //Log.i("asdf", questions.get(key).toString());
                    HttpsURLConnection conn = (HttpsURLConnection)new URL(questions.get(key).get("image")).openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    images.put(key, BitmapFactory.decodeStream(is));
                    if (images.get(key) != null) Log.i("nullCheck", images.get(key).toString());
                }
                catch (Exception e) {
                    e.printStackTrace();
                    Log.e("ImageAccessor", e.getMessage());
                }
                return null;
            }

        }


        if (!isDownloaded) {

            questions = new HashMap<>();
            images = new HashMap<>();

            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 10; j++) {

                    for (int k = 1, z = (j == 10 ? 10 : 3); k <= z; k++) {
                        new QGetter().execute(i,j,k);
                        //Log.i("QuestionRepository", key+" completed");
                    }

                } // end of for(j) - chapter

            } // end of for(i) - stage

            try {Thread.sleep(7000);
            }
            catch (Exception e) { e.printStackTrace();}

            for (int i = 1; i <= 3; i++)
                for (int j = 1; j <= 10; j++)
                    for (int k = 1, z = (j == 10 ? 10 : 3); k <= z; k++) {
                        new ImageDownloader().execute(String.format(id_format,i,j,k));
                    }

        }



        isDownloaded = true;
    }

    // stage, chapter, pn을 전달하면 문제 데이터를 HashMap 타입으로 반환
    // ex) get(1, 1, 1) -> {"content" : "해당 블록은 캐릭터가 바라보는 방향으로 30만큼 움직이나요?", "isOX" : "true", ...}
    public HashMap<String, String> get(int stage, int chapter, int pn) {
        return questions.get(String.format(id_format, stage, chapter, pn));
    }

    public Bitmap getImage(int stage, int chapter, int pn) {
        return images.get(String.format(id_format, stage, chapter, pn));
    }

    public boolean getIsDownloaded() { return isDownloaded; }
}
