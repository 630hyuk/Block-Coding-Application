package com.example.codingnatorpoject.DBConnection;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;

import android.util.Log;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.UserData;

/* Connector for Database
 * 2023.04.23 : Currently can be only connected to UserData
 */
public class DatabaseConnector {

    // there's no id, registerStamp, lastLoginStamp
    // cuz they must be managed strictly by system, not by user
    static String[] fields = {"email", "pw", "nickname", "progress", "phone"};

    DatabaseConnector() {
        // todo?
    }

    public boolean login(String email, String pw) {
        // todo: return true if matched
        return false;
    }

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
    }

    // Update the item with same id (calls overloaded method with item's id)
    public void updateUserData(UserData itemToUpdate, String field, String updateTo) {
        UserData.BuildStep builder = itemToUpdate.copyOfBuilder();

        switch (field) {
            case "email":
                builder.email(updateTo);
                break;
            case "pw":
                try {
                    // Update 대상 패스워드의 암호화 여부를 감지
                    builder.pw(PasswordManager.isEncrypted(updateTo) ? updateTo : PasswordManager.encrypt(updateTo));
                }
                catch (PasswordManager.InvalidPasswordException e) {
                    Log.e("DatabaseConnector", "Invalid Password: " + updateTo);
                }
                break;
            case "nickname":
                builder.nickname(updateTo);
                break;
            case "progress":
                builder.progress(updateTo);
                break;
            case "phone":
                builder.phone(updateTo);
                break;
            default:
                Log.e("DatabaseConnector", "No such field named with " + field);
                break;
        }

        Amplify.DataStore.save(
                builder.build(),
                success -> Log.i("Amplify", "Item updated: " + success.item().getId()),
                error -> Log.e("Amplify", "Could not save item to DataStore", error)
        );
    }

/*
    // we can update with primary key, not knowing whole item's information
    public void updateUserData(String id, String field, String updateTo) {
        UserData.BuildStep builder = UserData.builder().id(id);
        // todo: edit this after query

        Amplify.DataStore.save(
                item,
                success -> Log.i("Amplify", "Item updated: " + success.item().getId()),
                error -> Log.e("Amplify", "Could not save item to DataStore", error)
        );
    }


    public UserData selectUserData(String field, String toFind) {
        Amplify.DataStore.query(
                UserData.class,
                items -> {
                    while (items.hasNext()) {
                        UserData item = items.next();
                        Log.i("Amplify", "Id " + item.getId());
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
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
