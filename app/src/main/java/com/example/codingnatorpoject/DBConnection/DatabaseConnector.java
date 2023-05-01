package com.example.codingnatorpoject.DBConnection;

import java.time.LocalDateTime;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.UserData;

/* Connector for Database
 * 2023.04.23 : Currently can be only connected to UserData
 */
public class DatabaseConnector {

    /*
    // parameter should be applicationContext or getApplicationContext()
    // in fragment? activity.applicationContext or getActivity().getApplicationContext()
    DatabaseConnector(Context context) {
        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(context);

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }
    }
    */

    // Called when a user just registered.
    // don't have to fill timestamps
    public void createUserData(String email, String pw, String nickname, String progress, String phone) {
        UserData item;
        try {
            item = UserData.builder()
                    .email(email)
                    .pw(PasswordManager.encrypt(pw))
                    .nickname(nickname)
                    .progress(progress)
                    .registerStamp(now())
                    .phone(phone)
                    .build();
        }
        catch (PasswordManager.InvalidPasswordException e) {
            Log.e("DatabaseConnector", "Invalid Password: " + pw);
            return;
        }

        Amplify.DataStore.save(
                item,
                success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
                error -> Log.e("Amplify", "Could not save item to DataStore", error)
        );

        // A new method for same? work
        /*
        Amplify.API.mutate(
                ModelMutation.create(item),
                response -> Log.i("Amplify", "Added item with id: " + response.getData().getId()),
                error -> Log.e("Amplify", "Create failed", error)
        );
        */

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

    // returns UserData if email & pw matches.
    // else, return null
    public UserData login(String email, String pw) {
        UserData res;
        try {
            res = getUserList("email", email).get(0);
        }
        catch (NullPointerException e) {
            return null;
        }

        if (!res.getPw().equals(pw)) return null;

        updateUserData(res, "lastLoginStamp", now().toString());
        return res;
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

/*
    // code from..
    // https://docs.amplify.aws/lib/graphqlapi/query-data/q/platform/android/#list-subsequent-pages-of-items
    public void queryFirstPage() {
        query(ModelQuery.list(UserData.class, ModelPagination.limit(1_000)));
    }


    private static void query(GraphQLRequest<PaginatedResult<UserData>> request) {
        Amplify.API.query(
                request,
                response -> {
                    if (response.hasData()) {
                        for (UserData data : response.getData()) {
                            Log.d("Amplify", data.getId());
                        }
                        if (response.getData().hasNextResult()) {
                            query(response.getData().getRequestForNextResult());
                        }
                    }
                },
                failure -> Log.e("Amplify", "Query failed.", failure)
        );
    }
*/



    /* << easily use current timestamp in type: Temporal.Datetime >>
     * Calls Local*DateTime.now().toString()
     * and then changes the format which is appropriate to Temporal.DateTime
     */
    private static Temporal.DateTime now() {
        return new Temporal.DateTime(LocalDateTime.now().toString().substring(0,23) + "Z");
    }

}
