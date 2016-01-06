package xyz.restaurationmanager;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class DisplayMenu extends FragmentActivity {

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String idMenu = intent.getStringExtra("id");
        Log.d("toto","ici"+idMenu);

        if(idMenu!=null){
            this.url="http://92.243.14.22:1337/menu/"+idMenu;
            setContentView(R.layout.activity_display_menu);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
           // setSupportActionBar(toolbar);

            //Requete AJAX
            GsonTransformer t = new GsonTransformer();
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", idMenu);
            AQuery aq = new AQuery(this);
            aq.transformer(t).ajax(url, params, Success.class, new AjaxCallback<Success>() {
                public void callback(String url, Menu menu, AjaxStatus status) {
                    Gson gson = new Gson();
                    Double prix = gson.fromJson(menu.getPrice()+"",Double.class);

                }
            });
        }else{
            startActivity(new Intent(DisplayMenu.this,MenuActivity.class));
        }


    }

}
