package xyz.restaurationmanager;

/**
 * Created by Yoann on 11/24/2015.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import android.app.ListFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class ListProducts extends AppCompatActivity {

    AQuery aq;
    String url = "http://92.243.14.22:1337/product/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_products);
        AQuery aq = new AQuery(this);
        final ArrayList<Product> listProducts = new ArrayList<Product>();

        aq.ajax(url, JSONArray.class, new AjaxCallback<JSONArray>() {
            @Override
            public void callback(String url, JSONArray json, AjaxStatus status) {
                ListView lst = (ListView) findViewById(R.id.list_products);
                if (json != null) {
                    //successful ajax call, show status code and json content

                    for (int i = 0; i < json.length(); i++) {
                        try {
                            JSONObject o = json.getJSONObject(i);
                            Product p = new Product();
                            if (!o.getString("name").isEmpty() && !o.getString("price").isEmpty() && !o.getString("createdAt").isEmpty()) {
                                p.setName(o.getString("name"));
                                p.setDescription(o.getString("description"));
                                p.setPrice(o.getString("price"));
                                p.setCalories(o.getString("calories"));
                                p.setType(o.getString("type"));
                                p.setPicture(o.getString("picture"));
                                p.setDiscount(o.getString("discount"));
                                p.setCreatedAt(o.getString("createdAt"));
                                p.setUpdatedAt(o.getString("updatedAt"));
                                p.setId(o.getString("id"));
                            } else {
                                throw new JSONException("Valeur vide ou nulle");
                            }

                            listProducts.add(p);
                        } catch (JSONException e) {

                            continue;
                        }
                    }
                    ProductItemAdapter adapter = new ProductItemAdapter(ListProducts.this, listProducts);
                    lst.setAdapter(adapter);
                } else {
                    //ajax error, show error code
                    Log.d("ListProducts", "Ajax Error");
                }
            }
        });
    }
}

