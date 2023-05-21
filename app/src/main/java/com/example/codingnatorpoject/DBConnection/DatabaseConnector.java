package com.example.codingnatorpoject.DBConnection;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.AWSDataStorePlugin;

/* Connector for Database
 * 2023.04.23 : Currently can be only connected to UserData
 * 2023.05.16 : removed methods : createUserData, login
 * 2023.05.20 : using AsyncTask, which is Deprecated, but doesn't matter
 */
public class DatabaseConnector {
    Context context;

    // parameter should be applicationContext or getApplicationContext()
    // in fragment? activity.applicationContext or getActivity().getApplicationContext()
    public DatabaseConnector(Context context) {
        this.context = context;
        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
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
                    URL reqUrl = new URL("https://1pwn3dii83.execute-api.ap-northeast-2.amazonaws.com/default/uploadquestion");
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
            new Uploader().execute(String.format(jsonFormat, id, content, hint, isOX ? "true" : "false",
                    cands[0], cands[1], "null", "null",
                    answer, explanation, image));
        }
        else {
            new Uploader().execute(String.format(jsonFormat, id, content, hint, isOX ? "true" : "false",
                    cands[0], cands[1], cands[2], cands[3],
                    answer, explanation, image));
        }


    }


    /* << easily use current timestamp in type: Temporal.Datetime >>
     * Calls Local*DateTime.now().toString()
     * and then changes the format which is appropriate to Temporal.DateTime
     */
    private static Temporal.DateTime now() {
        return new Temporal.DateTime(LocalDateTime.now().toString().substring(0,23) + "Z");
    }

}
