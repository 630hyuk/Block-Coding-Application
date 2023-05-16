package com.example.codingnatorpoject.DBConnection;

import java.time.LocalDateTime;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.UserData;

/* Connector for Database
 * 2023.04.23 : Currently can be only connected to UserData
 */
public class DatabaseConnector {


    // parameter should be applicationContext or getApplicationContext()
    // in fragment? activity.applicationContext or getActivity().getApplicationContext()
    public DatabaseConnector(Context context) {
        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(context);
            Log.i("DatabaseConnector", "Initialized Amplify");
        }
        catch (AmplifyException e) {
            Log.e("DatabaseConnector", "Amplify Initialize failure", e);
        }
    }

    // Update the item with same id (calls overloaded method with item's id)
    public void updateUserData(UserData itemToUpdate, String field, String updateTo) {
        UserData.Builder builder = itemToUpdate.copyOfBuilder();

        builder.setAttribute(field, updateTo);

        Amplify.DataStore.save(
                builder.build(),
                success -> Log.i("Amplify", "Item updated: " + success.item().getAttribute(field)),
                error -> Log.e("Amplify", "Could not save item to DataStore", error)
        );
    }


    // we can update with primary key, not knowing whole item's information
    public void updateUserData(String id, String field, String updateTo) {
        updateUserData(getUserList("id", id).get(0), field, updateTo);
    }

    public void deleteUserData(UserData itemToDelete) {
        Amplify.DataStore.delete(
                itemToDelete,
                success -> Log.i("Amplify", "Item deleted: " + success.item().getId()),
                error -> Log.e("Amplify", "Could not delete item ", error)
        );
    }

    public void deleteUserData(String field, String target) {
        ArrayList<UserData> list = getUserList(field, target);

        for (UserData item : list) {
            deleteUserData(item);
        }
    }

    public ArrayList<UserData> getUserList() {
        ArrayList<UserData> dataList = new ArrayList<>();

        Amplify.DataStore.query(
                UserData.class,
                items -> {
                    while (items.hasNext()) {
                        UserData item = items.next();
                        dataList.add(item);
                        Log.i("Amplify", "Id " + item.getId());
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );

        return dataList;
    }

    public ArrayList<UserData> getUserList(String field, String toFind) {
        ArrayList<UserData> dataList = new ArrayList<>();

        Amplify.DataStore.query(
                UserData.class,
                items -> {
                    while (items.hasNext()) {
                        UserData item = items.next();
                        if (item.compare(field, toFind)) {
                            dataList.add(item);
                            Log.i("Amplify", "Id " + item.getId());
                        }
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );

        return dataList;
    }


    /* << easily use current timestamp in type: Temporal.Datetime >>
     * Calls Local*DateTime.now().toString()
     * and then changes the format which is appropriate to Temporal.DateTime
     */
    private static Temporal.DateTime now() {
        return new Temporal.DateTime(LocalDateTime.now().toString().substring(0,23) + "Z");
    }

}
