package xyz.restaurationmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListProduct extends AppCompatActivity {

    AQuery aq;
    String url = "http://92.243.14.22:1337/product/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        AQuery aq = new AQuery(this);
        final ArrayList<Product> listProducts = new ArrayList<Product>();
        aq.ajax(url, JSONArray.class, new AjaxCallback<JSONArray>() {
            @Override
            public void callback(String url, JSONArray json, AjaxStatus status) {
                ListView lst = (ListView) findViewById(R.id.list_product);
                if (json != null) {
                    //successful ajax call, show status code and json content

                    for (int i = 0; i < json.length(); i++) {
                        try {
                            JSONObject o = json.getJSONObject(i);
                            Product p = new Product();
                            if (!o.getString("name").isEmpty() && !o.getString("price").isEmpty() && !o.getString("createdAt").isEmpty()) {
                                p.setName(o.getString("name"));
                                p.setPrice(o.getString("price"));
                                p.setCreatedAt(o.getString("createdAt"));

                                if (!o.getString("description").isEmpty()) {
                                    p.setDescription(o.getString("description"));
                                } else {
                                    p.setDescription("");
                                }
                                if (!o.getString("calories").isEmpty()) {
                                    p.setCalories(o.getString("calories"));
                                } else {
                                    p.setCalories("");
                                }
                                if (!o.getString("type").isEmpty()) {
                                    p.setType(o.getString("type"));
                                } else {
                                    p.setType("");
                                }
                                if (!o.getString("picture").isEmpty()) {
                                    p.setPicture(o.getString("picture"));
                                } else {
                                    p.setPicture("");
                                }
                                if (!o.getString("discount").isEmpty()) {
                                    p.setDiscount(o.getString("discount"));
                                } else {
                                    p.setDiscount("");
                                }
                                if (!o.getString("description").isEmpty()) {
                                    p.setDescription(o.getString("description"));
                                } else {
                                    p.setDescription("");
                                }
                                if (!o.getString("updatedAt").isEmpty()) {
                                    p.setUpdatedAt(o.getString("updatedAt"));
                                } else {
                                    p.setUpdatedAt("");
                                }
                                if (!o.getString("id").isEmpty()) {
                                    p.setId(o.getString("id"));
                                } else {
                                    p.setId("");
                                }
                            } else {
                                continue;
                            }

                            listProducts.add(p);
                        } catch (JSONException e) {

                            continue;
                        }
                    }
                    ProductItemAdapter adapter = new ProductItemAdapter(ListProduct.this, listProducts);
                    lst.setAdapter(adapter);
                } else {
                    //ajax error, show error code
                    Log.d("ListAccounts", "Ajax Error");
                }
            }
        });
    }
}