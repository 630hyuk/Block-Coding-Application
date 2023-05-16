package com.example.codingnatorpoject.DBConnection;

import android.content.Context;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

import java.io.File;

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
        catch (AmplifyException e) {
            Log.e("DatabaseConnector", "Amplify Initialize failure", e);
        }
    }

    private void uploadFile(String key, File file) {
        Amplify.Storage.uploadFile(key, file,
                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + key),
                error -> Log.e("MyAmplifyApp", "Upload failed", error)
        );
    }

    private void downloadFile(String key, File file) {
        Amplify.Storage.downloadFile(key, file,
                result -> Log.i("MyAmplifyApp", "Successfully downloaded: " + key),
                error -> Log.e("MyAmplifyApp", "Download failed", error)
        );
    }

    private void getFileUrl(String key) {
        Amplify.Storage.getUrl(
                key,
                result -> Log.i("MyAmplifyApp", "Successfully generated: " + result.getUrl()),
                error -> Log.e("MyAmplifyApp", "URL generation failure", error)
        );
    }

    private void deleteFile(String filename) {
        Amplify.Storage.remove(filename,
                result -> Log.i("MyAmplifyApp", "Successfully removed: " + result.getKey()),
                error -> Log.e("MyAmplifyApp", "Remove failure", error)
        );
    }
}
