package xyz.restaurationmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddMenuActivity extends AppCompatActivity implements View.OnClickListener {
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView1;
    private EditText mPasswordView2;
    private EditText mPhoneView;
    private EditText mFirstNameView;
    private EditText mLastNameView;
    private RadioButton mMasculin;
    private RadioButton mFeminin;

    private View mProgressView;
    private View mRegisterFormView;

    AQuery aq;
    String url = "http://92.243.14.22:1337/menu/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("On create AddMenuActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        // Set up the register form.
        //mEmailView = (AutoCompleteTextView) findViewById(R.id.editTextRegisterEmail);

        //Button enregistrerInscription = (Button) findViewById(R.id.buttonEnregistrerInscription);
        Button enregistrerMenu = (Button) findViewById(R.id.addANewMenu);
        enregistrerMenu.setOnClickListener(this);

        mRegisterFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    @Override
    public void onClick(View v) {
        //String price = mEmailView.getText().toString();
        Double price = 302.0;
        Double discount = 37.5;
        String server = "7531";
        String cooker = "642";
        ArrayList<String> items = new ArrayList<String>();
       /* items.add("564113af6c7e6511333f616c");
        items.add("564113af6c7e6511333f6172");
        items.add("564113af6c7e6511333f6165");*/

        aq = new AQuery(v);

        boolean cancel = false;
        View focusView = null;
    /*
        // Check for not null server
        if (TextUtils.isEmpty(server)) {
            //mFirstNameView.setError(getString(R.string.error_field_required));
            //focusView = mFirstNameView;
            cancel = true;
        }
        // Check for not null cooker
        if (TextUtils.isEmpty(cooker)) {
            //mLastNameView.setError(getString(R.string.error_field_required));
            //focusView = mLastNameView;
            cancel = true;
        }

        // Check for items to exist
        if (items.size() == 0) {
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {*/
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.


            GsonTransformer t = new GsonTransformer();

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("price", 322);
            params.put("discount", 12);
            params.put("server",server);
            params.put("cooker",cooker);

            JSONArray itemsArray = new JSONArray();
            JSONObject anItem = new JSONObject();
            anItem.put("id", (String) " okokook");


            //JSONArray itemss = new JSONArray()
           /* HashMap<String, Object> itemss = new HashMap<String, Object>();
            for(int i = 0; i<items.size();i++) {
                try {
                    itemsArray.put(i, anItem);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }*/


            params.put("items", itemsArray);


        //params.put("items", itemss);


            aq = new AQuery(v);
            aq.transformer(t).ajax(url, params, Menu.class, new AjaxCallback<Menu>() {
                public void callback(String url, Menu menu, AjaxStatus status) {
                    Gson gson = new Gson();
                    Log.d("GSON status", status.getMessage() + status.getError());
                    Log.d("GSON Object:", gson.toJson(menu));
                }
            });
            //startActivity(new Intent(AddMenuActivity.this, NavigationDrawerActivity.class));


            /*aq.post(url, obj, JSONObject.class, new AjaxCallback<JSONObject>() {
                public void callback(String url, JSONObject json, AjaxStatus status) {
                    Log.d("Réponse", "" + json);
                }
            });*/

        //}
    }
}
