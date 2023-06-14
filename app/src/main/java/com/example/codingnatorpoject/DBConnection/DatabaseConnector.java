package com.example.codingnatorpoject.DBConnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.model.temporal.Temporal;

/* Connector for Database
 * 2023.04.23 : Currently can be only connected to UserData
 * 2023.05.16 : removed methods : createUserData, login
 * 2023.05.20 : using AsyncTask, which is Deprecated, but doesn't matter
 */
public class DatabaseConnector {
    Context context;
    private String defaultUrl = "https://71cyxe4ifa.execute-api.ap-northeast-2.amazonaws.com/default/";

    public DatabaseConnector() {
        this.context = null;
    }

    // parameter should be applicationContext or getApplicationContext()
    // in fragment? activity.applicationContext or getActivity().getApplicationContext()
    public DatabaseConnector(Context context) {
        this.context = context;
        try {
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(context);
            Log.i("DatabaseConnector", "Initialized Amplify");
        }
        catch (Amplify.AlreadyConfiguredException e) {
            // haha, ignore that
        }
        catch (AmplifyException e) {
            Log.e("DatabaseConnector", "Amplify Initialize failure", e);
        }
    }


    /* example of post req */
    public void uploadQuestion(String id, String content, String hint, boolean isOX, String[] cands, String answer, String explanation, String image) {

        // AsyncTask??
        class Uploader extends AsyncTask<String, Void, String> {
            String msg = "";
            @Override
            protected String doInBackground(String... strings) {

                try {
                    URL reqUrl = new URL(defaultUrl + "uploadquestion");
                    HttpURLConnection conn = (HttpURLConnection) reqUrl.openConnection();

                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json; utf-8");
                    conn.setRequestProperty("Accept", "*/*");

                    conn.setDoOutput(true);
                    conn.getOutputStream().write(strings[0].getBytes());

                    conn.connect();

                    String msg = conn.getResponseMessage();
                    Log.e("qUpload_Response", msg);
                }
                catch (Exception e) { Log.e(e.toString(), "in uploadQuestion"); }

                return msg;
            }
        }

        String jsonFormat = "{ \"id\": \"%s\", " +
                "\"content\": \"%s\", \"hint\": \"%s\", \"isOX\": \"%s\", " +
                "\"cand1\": \"%s\", \"cand2\": \"%s\", \"cand3\": \"%s\", \"cand4\": \"%s\", " +
                "\"answer\": \"%s\", \"explanation\": \"%s\", \"image\": \"%s\" }";

        if (isOX) {
            new Uploader().execute(String.format(jsonFormat, id, content, hint, "true",
                    cands[0], cands[1], "null", "null",
                    answer, explanation, image));
        }
        else {
            new Uploader().execute(String.format(jsonFormat, id, content, hint, "false",
                    cands[0], cands[1], cands[2], cands[3],
                    answer, explanation, image));
        }

    }


    public void updateProgress(String userId, String progress) {
        class Updater extends AsyncTask<String, Void, String> {
            String msg = "";
            @Override
            protected String doInBackground(String... strings) {

                try {
                    URL reqUrl = new URL( defaultUrl + "updatedata");
                    HttpURLConnection conn = (HttpURLConnection) reqUrl.openConnection();

                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json; utf-8");
                    //conn.setRequestProperty("Accept", "*/*");

                    conn.setDoOutput(true);

                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
                    bw.write(strings[0]);
                    bw.flush();
                    bw.close();

                    //conn.connect();

                    String msg = conn.getResponseMessage();

                    Log.e("updateProg_Response", msg);
                }
                catch (Exception e) { Log.e(e.toString(), "in updateProg"); }

                return msg;
            }
        }

        Log.i("DBC", "Saving user progress");
        String jsonFormat = "{ \"table_name\": \"UserData-764tmacrt5dobnsevpovqnmw4u-staging\", " +
                "\"id\": \"%s\", " +
                "\"field\": \"progress\", " +
                "\"expected\": \"%s\"}";

        new Updater().execute(String.format(jsonFormat, userId, progress));
    }

    /* << easily use current timestamp in type: Temporal.Datetime >>
     * Calls Local*DateTime.now().toString()
     * and then changes the format which is appropriate to Temporal.DateTime
     */
    private static Temporal.DateTime now() {
        return new Temporal.DateTime(LocalDateTime.now().toString().substring(0,23) + "Z");
    }

}
