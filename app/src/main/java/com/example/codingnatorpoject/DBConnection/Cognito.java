package com.example.codingnatorpoject.DBConnection;

// example code from...
// https://cryptiot.de/programming/adding-aws-cognito-sign-in-and-sign-up-to-android-app/

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.*;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.*;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.*;
import com.amazonaws.regions.Regions;
import static  android.content.ContentValues.TAG;

import java.util.Objects;

public class Cognito {
    // ############################################################# Information about Cognito Pool
    private String poolID = "ap-northeast-2_jec1sIwqC";
    private String clientID = "71r9rffgpecdfvuutoi6fta41j";
    private String clientSecret = "1g953ttk3qa2b5v8e2io3qj8u0j6rl34gtv41gnfm5l0pbut9e3j";
    private Regions awsRegion = Regions.AP_NORTHEAST_2;         // Place your Region
    // ############################################################# End of Information about Cognito Pool

    private CognitoUserPool userPool;
    private CognitoUserAttributes userAttributes;       // Used for adding attributes to the user
    private Context appContext;
    public static String userId;
    public static String email;
    public static String nickname;
    public static String pw;
    private String userPassword;                        // Used for Login
    public static boolean recentConfirmation = false;
    public static boolean waitingResponse = false;

    public Cognito(Context context){
        appContext = context;
        userPool = new CognitoUserPool(context, this.poolID, this.clientID, this.clientSecret, this.awsRegion);
        userAttributes = new CognitoUserAttributes();
    }
    public void signUpInBackground(String id, String password){
        userPool.signUpInBackground(id, password, this.userAttributes, null, signUpCallback);
        userId = id;
        //userPool.signUp(userId, password, this.userAttributes, null, signUpCallback);
    }
    SignUpHandler signUpCallback = new SignUpHandler() {
        @Override
        public void onSuccess(CognitoUser cognitoUser, boolean userConfirmed, CognitoUserCodeDeliveryDetails cognitoUserCodeDeliveryDetails) {
            // Sign-up was successful
            Log.d(TAG, "Sign-up success");
            Toast.makeText(appContext,"Sign-up success", Toast.LENGTH_LONG).show();
            // Check if this user (cognitoUser) needs to be confirmed
            if(!userConfirmed) {
                // This user must be confirmed and a confirmation code was sent to the user
                // cognitoUserCodeDeliveryDetails will indicate where the confirmation code was sent
                // Get the confirmation code from user
            }
            else {
                //Toast.makeText(appContext,"Error: User Confirmed before", Toast.LENGTH_LONG).show();
                // The user has already been confirmed
            }
        }
        @Override
        public void onFailure(Exception exception) {
            Toast.makeText(appContext,"Sign-up failed", Toast.LENGTH_LONG).show();
            Log.d(TAG, "Sign-up failed: " + exception);
        }
    };
    public void confirmUser(String userId, String code){
        recentConfirmation = false; waitingResponse = false;
        CognitoUser cognitoUser =  userPool.getUser(userId);
        cognitoUser.confirmSignUpInBackground(code,false, confirmationCallback);
        //cognitoUser.confirmSignUp(code,false, confirmationCallback);
    }
    // Callback handler for confirmSignUp API
    GenericHandler confirmationCallback = new GenericHandler() {

        @Override
        public void onSuccess() {
            // User was successfully confirmed
            Toast.makeText(appContext,"User Confirmed", Toast.LENGTH_LONG).show();
            recentConfirmation = true; waitingResponse = true;
            UserManager.users.add(new Pair<>(email, new Pair<>(nickname, new Pair<>(pw, 0))));
            /*
            class userCreator extends AsyncTask<String, Void, String> {
                String msg = "";
                @Override
                protected String doInBackground(String... strings) {

                    try {
                        URL reqUrl = new URL( "https://71cyxe4ifa.execute-api.ap-northeast-2.amazonaws.com/default/createuserdata");
                        HttpURLConnection conn = (HttpURLConnection) reqUrl.openConnection();

                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Content-Type", "application/json; utf-8");
                        //conn.setRequestProperty("Accept", "**");

                        conn.setDoOutput(true);

                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
                        bw.write(strings[0]);
                        bw.flush();
                        bw.close();

                        //conn.connect();

                        String msg = conn.getResponseMessage();

                        Log.e("createUser_Response", msg);
                    }
                    catch (Exception e) { Log.e(e.toString(), "in createUser"); }

                    return msg;
                }
            }

            new userCreator().execute(
                    "{ \"id\": \"" + userId + "\", " +
                    "\"nickname\": \"" + nickname + "\"}"
            );
            */
        }

        @Override
        public void onFailure(Exception exception) {
            waitingResponse = true;
            // User confirmation failed. Check exception for the cause.
            exception.printStackTrace();
        }

    };
    public void addAttribute(String key, String value){
        userAttributes.addAttribute(key, value);
    }

    public boolean userLogin(String userId, String password){
        CognitoUser cognitoUser =  userPool.getUser(userId);
        try {
            this.userPassword = PasswordManager.encrypt(password);
        } catch (PasswordManager.InvalidPasswordException e) {
            return false;
        }

        cognitoUser.getSessionInBackground(authenticationHandler);

        for (int i = 0, z = UserManager.users.size(); i < z; i++) {
            Pair<String, Pair<String, Pair<String, Integer>>> tmp = UserManager.users.get(i);
            Log.i("userLogin", "Comparing " + tmp.first);

            // if id matched
            if (tmp.first.equals(userId)) {
                // if password is wrong
                if (!Objects.equals(tmp.second.second.first, this.userPassword)) return false;

                new DatabaseConnector(appContext).updateData(userId, "lastLoginStamp", DatabaseConnector.timeStamp());
                UserManager.setUser(userId);
                return true;
            }
        }

        UserManager.logout();
        return false;
    }
    // Callback handler for the sign-in process
    AuthenticationHandler authenticationHandler = new AuthenticationHandler() {
        @Override
        public void authenticationChallenge(ChallengeContinuation continuation) {

        }
        @Override
        public void onSuccess(CognitoUserSession userSession, CognitoDevice newDevice) {
            Toast.makeText(appContext,"Sign in success", Toast.LENGTH_LONG).show();

        }
        @Override
        public void getAuthenticationDetails(AuthenticationContinuation authenticationContinuation, String userId) {
            // The API needs user sign-in credentials to continue
            AuthenticationDetails authenticationDetails = new AuthenticationDetails(userId, userPassword, null);
            // Pass the user sign-in credentials to the continuation
            authenticationContinuation.setAuthenticationDetails(authenticationDetails);
            // Allow the sign-in to continue
            authenticationContinuation.continueTask();
        }
        @Override
        public void getMFACode(MultiFactorAuthenticationContinuation multiFactorAuthenticationContinuation) {
            // Multi-factor authentication is required; get the verification code from user
            //multiFactorAuthenticationContinuation.setMfaCode(mfaVerificationCode);
            // Allow the sign-in process to continue
            //multiFactorAuthenticationContinuation.continueTask();
        }
        @Override
        public void onFailure(Exception exception) {
            // Sign-in failed, check exception for the cause
            //Toast.makeText(appContext,"Sign in Failure", Toast.LENGTH_LONG).show();
        }
    };

    public String getPoolID() {
        return poolID;
    }

    public void setPoolID(String poolID) {
        this.poolID = poolID;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public Regions getAwsRegion() {
        return awsRegion;
    }

    public void setAwsRegion(Regions awsRegion) {
        this.awsRegion = awsRegion;
    }
}