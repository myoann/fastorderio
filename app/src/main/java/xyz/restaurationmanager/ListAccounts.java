package xyz.restaurationmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListAccounts extends AppCompatActivity {

    AQuery aq;
    String url = "http://92.243.14.22/person/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_accounts);

        Log.d("tkt", "on est la");

        AQuery aq = new AQuery(this);
        aq.ajax(url, JSONObject.class, this, "jsonCallback");

    }

    public void jsonCallback(String url, JSONObject json, AjaxStatus status){

        if(json != null){
            //successful ajax call
            Log.d("biatch", json.toString());
        }else{
            //ajax error
            Log.d("ListAccounts", "Ajax Error");
        }

    }



}
