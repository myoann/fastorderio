package xyz.restaurationmanager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    String url = "http://92.243.14.22:1337/menu/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        AQuery aq = new AQuery(this);
        final ArrayList<Menu> listeMenus = new ArrayList<Menu>();
        aq.ajax(url, JSONArray.class, new AjaxCallback<JSONArray>() {
            @Override
            public void callback(String url, JSONArray json, AjaxStatus status) {
                ListView lst = (ListView) findViewById(R.id.list_menus);
                if (json != null) {
                    //successful ajax call, show status code and json content

                    for (int i = 0; i < json.length(); i++) {
                        try {
                            JSONObject o = json.getJSONObject(i);
                            Menu m = new Menu();
                            m.setId(o.getString("id"));
                            m.setPrice(Integer.valueOf(o.getString("price")));
                            listeMenus.add(m);
                        } catch (JSONException e) {

                            continue;
                        }
                    }
                    MenuItemAdapter adapter = new MenuItemAdapter(MenuActivity.this, listeMenus);
                    lst.setAdapter(adapter);
                } else {
                    //ajax error, show error code
                    Log.d("ListeMenus", "Ajax Error");
                }
            }
        });

    }


}
