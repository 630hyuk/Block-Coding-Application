package com.example.codingnatorpoject.DBConnection;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageAccessor {

    // parameter should be applicationContext or getApplicationContext()
    // in fragment? activity.applicationContext or getActivity().getApplicationContext()
    public ImageAccessor(Context context) {
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

    public String getFileUrl(int stage, int chapter, int pn) {

/*      Nah, we don't need this actually
        class urlGetter extends AsyncTask<Integer, Void, String> {

            @Override
            protected String doInBackground(Integer... params) {
                try {
                    URL reqUrl = new URL("https://b59648vra0.execute-api.ap-northeast-2.amazonaws.com/default/getFileUrl?" +
                            "stage=" + params[0] +
                            "&chapter=" + params[1] +
                            "&pn=" + params[2]);
                    HttpURLConnection conn = (HttpURLConnection) reqUrl.openConnection();

                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Content-Type", "application/json; utf-8");
                    conn.setRequestProperty("Accept", "");

                    conn.connect();
                    Log.e("asdf", "here");
                    Log.e("asdf", conn.getResponseMessage());
                    return conn.getResponseMessage();
                } catch (Exception e) {
                    Log.e(e.toString(), "in getFileUrl");
                    return null;
                }
            }

        }

        return new urlGetter().doInBackground(stage, chapter, pn);
*/
        return "https://codingnator-image-storage05625-staging.s3.ap-northeast-2.amazonaws.com/public/stage" + stage +
                "/chapter" + chapter +
                "/" + pn + ".png";
    }

}
