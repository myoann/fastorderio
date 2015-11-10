package xyz.restaurationmanager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {



    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    private View mProgressView;
    private View mRegisterFormView;

    AQuery aq;
    String url = "http://92.243.14.22/person/login/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("On create RegisterActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the register form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.editTextLoginEmail);
        mPasswordView = (EditText) findViewById(R.id.editTextLoginPassword);




        //Button enregistrerInscription = (Button) findViewById(R.id.buttonEnregistrerInscription);
        Button enregistrerConnexion = (Button) findViewById(R.id.login_button);
        enregistrerConnexion.setOnClickListener(this);


        mRegisterFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    @Override
    public void onClick(View v) {
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        Log.d("EMAIL : ", email);
        Log.d("PASS : ", password);

        aq = new AQuery(v);

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.


            GsonTransformer t = new GsonTransformer();

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("email",email);
            params.put("password",password);
            aq = new AQuery(v);
            aq.transformer(t).ajax(url, params, Success.class, new AjaxCallback<Success>() {
                public void callback(String url, Success success, AjaxStatus status) {
                    Gson gson = new Gson();
                    Account obj = success.getUser();
                    String isSuccess = gson.toJson(success.getSuccess());
                    if (isSuccess.contentEquals(isSuccess)) {
                        Intent intent = new Intent(LoginActivity.this, NavigationDrawerActivity.class);
                        //to pass :
                        intent.putExtra("user", obj);
                        Log.d("Login Activity", "connexion");
                        // to retrieve object in second Activity
                        startActivity(intent);
                    } else {
                        Log.d("NOOON", " pas connecte");
                    }
                }
            });
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

}
