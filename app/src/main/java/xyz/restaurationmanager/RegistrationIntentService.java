/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.restaurationmanager;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";
    private static final String[] TOPICS = {"global"};

    public RegistrationIntentService() {
        super(TAG);
    }
    SharedPreferences sharedPreferences;

    @Override
    protected void onHandleIntent(Intent intent) {
         sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        try {
            // [START register_for_gcm]
            // Initially this call goes out to the network to retrieve the token, subsequent calls
            // are local.
            // R.string.gcm_defaultSenderId (the Sender ID) is typically derived from google-services.json.
            // See https://developers.google.com/cloud-messaging/android/start for details on this file.
            // [START get_token]
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            // [END get_token]
            //AIzaSyBNy7W34fFGy8oWrtD7q-O7tXwTC0LW6o4
            Log.i(TAG, "GCM Registration Token: " + token);

            // TODO: Implement this method to send any registration to your app's servers.
            sendRegistrationToServer(token);

            // You should store a boolean that indicates whether the generated token has been
            // sent to your server. If the boolean is false, send the token to your server,
            // otherwise your server should have already received the token.
            sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, true).apply();
            // [END register_for_gcm]
        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
            // If an exception happens while fetching the new token or updating our registration data
            // on a third-party server, this ensures that we'll attempt the update at a later time.
            sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false).apply();
        }
    }

    void onComplete(){
        sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, true).apply();
        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(QuickstartPreferences.REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    public void jsonCallback(String url, JSONObject json, AjaxStatus status){
        System.out.println(json.toString());
        if(json != null){
          onComplete();
        }else{
            //ajax error
        }

    }

    /**
     * Persist registration to third-party servers.
     *
     * Modify this method to associate the user's GCM registration token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
        System.out.println(token);
        //String name=getString(R.string.cooker_name),  email=getString(R.string.cooker_email), api_key=getString(R.string.api_key).equals("$$")?"AIzaSyBNy7W34fFGy8oWrtD7q-O7tXwTC0LW6o4":getString(R.string.api_key);
        String name="Massa Moise Ltd.",  email="massa-moise@cooker.com", api_key="AIzaSyCoUwkiSQv4UxSgQc0wWH0wy0_YaKltyRk".equals("$$")?"AIzaSyCoUwkiSQv4UxSgQc0wWH0wy0_YaKltyRk":"AIzaSyCoUwkiSQv4UxSgQc0wWH0wy0_YaKltyRk";
        AQuery aq = new AQuery(this);
        Map<String, Object> params = new HashMap<>();
        params.put("name", "Massa Moise Ltd");
        params.put("email", email);
        params.put("gcmkey", token);
        params.put("apikey", "AIzaSyCoUwkiSQv4UxSgQc0wWH0wy0_YaKltyRk");
        aq.ajax("http://92.243.14.22:1337/cooker", params, JSONObject.class,this,"jsonCallback");
    }

}
