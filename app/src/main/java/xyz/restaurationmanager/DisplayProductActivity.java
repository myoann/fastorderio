package xyz.restaurationmanager;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class DisplayProductActivity extends AppCompatActivity {

    private String urlProduct = "http://92.243.14.22:1337/product/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AQuery aq = new AQuery(this);
        String url = urlProduct+getIntent().getSerializableExtra("id");
        aq.ajax(url, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {
                try {
                    Log.d("name",json.getString("name"));
                    TextView tvName = (TextView) findViewById(R.id.name_product);
                    tvName.setText(json.getString("name"));
                    Log.d("description", json.getString("description"));
                    TextView tvDesc = (TextView) findViewById(R.id.description_product);
                    tvDesc.setText(json.getString("description"));
                    Log.d("price", json.getString("price"));
                    TextView tvPrice = (TextView) findViewById(R.id.price_product);
                    tvPrice.setText("Prix : " + json.getString("price")+ "$  ");
                    Log.d("calories", json.getString("calories"));
                    TextView tvCalories = (TextView) findViewById(R.id.calories_product);
                    tvCalories.setText("Calories : "+ json.getString("calories")+ "cal  ");
                    Log.d("type", json.getString("type"));
                    TextView tvType = (TextView) findViewById(R.id.type_product);
                    tvType.setText(json.getString("type"));
                    Log.d("picture", json.getString("picture"));
                    ImageView tvPicture = (ImageView) findViewById(R.id.image_product);
                    tvPicture.setImageURI(Uri.parse(json.getString("picture")));
                    Log.d("discount", json.getString("discount"));
                    TextView tvDiscount = (TextView) findViewById(R.id.discount_product);
                    tvDiscount.setText("Réduction : "+json.getString("discount")+"$  ");
                    Log.d("createdAt",json.getString("createdAt"));
                    Log.d("updatedAt",json.getString("updatedAt"));
                    Log.d("id",json.getString("id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }

}
