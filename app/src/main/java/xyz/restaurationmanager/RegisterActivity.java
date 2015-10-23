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
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {



    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView1;
    private EditText mPasswordView2;
    private EditText mPhoneView;
    private EditText mFirstNameView;
    private EditText mLastNameView;

    private View mProgressView;
    private View mRegisterFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("On create RegisterActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Set up the register form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.editTextRegisterEmail);
        mPasswordView1 = (EditText) findViewById(R.id.editTextRegisterMotDePasse1);
        mPasswordView2 = (EditText) findViewById(R.id.editTextRegisterMotDePasse2);
        mPhoneView = (EditText) findViewById(R.id.editTextRegisterTelephone);
        mFirstNameView = (EditText) findViewById(R.id.editTextRegisterPrenom);
        mLastNameView = (EditText) findViewById(R.id.editTextRegisterNom);

        String email = mEmailView.getText().toString();
        String password1 = mPasswordView1.getText().toString();
        String password2 = mPasswordView2.getText().toString();
        String phone = mPhoneView.getText().toString();
        String firstName = mFirstNameView.getText().toString();
        String lastName = mLastNameView.getText().toString();


        Button enregistrerInscription = (Button) findViewById(R.id.buttonEnregistrerInscription);

        enregistrerInscription.setOnClickListener(this);

        mRegisterFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }
}
