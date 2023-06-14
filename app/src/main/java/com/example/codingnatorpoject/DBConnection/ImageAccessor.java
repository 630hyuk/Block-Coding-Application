package com.example.codingnatorpoject.DBConnection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;

import java.io.InputStream;
import java.net.URL;

public class ImageAccessor {

    // parameter should be applicationContext or getApplicationContext()
    // in fragment? activity.applicationContext or getActivity().getApplicationContext()
    public ImageAccessor(Context context) {
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

    public String getFileUrl(int stage, int chapter, int pn) {
        return "https://codingnator-image-storage05625-staging.s3.ap-northeast-2.amazonaws.com/public/stage" + stage +
                "/chapter" + chapter +
                "/" + pn + ".png";
    }

    public static Bitmap getBitmap(String url) {
        final Bitmap[] res = {null};
        class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
            @Override
            protected Bitmap doInBackground(String... strings) {
                try {
                    InputStream is = new URL(strings[0]).openStream();
                    res[0] = BitmapFactory.decodeStream(is);
                }
                catch (Exception e) {
                    Log.e("ImageAccessor", "OMG");
                    e.printStackTrace();
                    Log.e("ImageAccessor", e.getMessage());
                }
                return null;
            }

        }

        new ImageDownloader().execute(url);

        return res[0];
    }


}
