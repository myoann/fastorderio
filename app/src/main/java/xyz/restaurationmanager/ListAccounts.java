package xyz.restaurationmanager;

import android.app.ListFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListAccounts extends AppCompatActivity  {

    AQuery aq;
    String url = "http://92.243.14.22/person/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_accounts);

        AQuery aq = new AQuery(this);
        final ArrayList<Account> listAccounts = new ArrayList<Account>();
        aq.ajax(url, JSONArray.class, new AjaxCallback<JSONArray>() {
            @Override
            public void callback(String url, JSONArray json, AjaxStatus status) {
                if(json != null){
                    //successful ajax call, show status code and json content

                        for (int i = 0; i < json.length(); i++) {
                            try {
                                JSONObject o = json.getJSONObject(i);
                                Account a = new Account();
                                a.setNom(o.getString("nom"));
                                a.setPrenom(o.getString("prenom"));
                                a.setConnected(o.getString("connected"));
                                a.setCreatedAt(o.getString("createdAt"));

                                listAccounts.add(a);
                            } catch (JSONException e) {
                                continue;
                            }
                        }

                    for (int j = 0; j<listAccounts.size(); j++) {
                        Log.d("biatchList", listAccounts.get(j).toString());
                    }

                } else {
                    //ajax error, show error code
                    Log.d("ListAccounts", "Ajax Error");
                }
            }
        });
    }
}
