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
import android.widget.TextView;

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
import java.util.Objects;

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
    TextView eTextNbArticles;
    TextView eTextReduc;
    TextView eTextPrixTotal;
    TextView tEntrees;
    TextView tPlats;
    TextView tDesserts;
    Double reducTotal;
    Double prixTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("On create AddMenuActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);
        final List<Product> cart = ListProduct.getCart();

        this.eTextNbArticles = (TextView)(this.findViewById(R.id.nbArticles));
        this.eTextNbArticles.setText(String.valueOf(cart.size()));

        reducTotal = 0.0;
        prixTotal = 0.0;
        Double prixTotalReduit = 0.0;
        int nbEntrees = 0;
        int nbPlats = 0;
        int nbDesserts = 0;

        for(int i = 0; i<cart.size();i++) {
            if ((cart.get(i).getType().equalsIgnoreCase("Entrée")) || (cart.get(i).getType().equalsIgnoreCase("Appéritif"))) {
                nbEntrees += 1;
            } else if ((cart.get(i).getType().equalsIgnoreCase("Plat")) || (cart.get(i).getType().equalsIgnoreCase("Plat "))) {
                nbPlats += 1;
            } else if (cart.get(i).getType().equalsIgnoreCase("Dessert")) {
                nbDesserts += 1;
            }
            reducTotal += cart.get(i).getDiscount();
            prixTotal += cart.get(i).getPrice();
        }
        prixTotalReduit = prixTotal - reducTotal;

        this.eTextReduc = (TextView)(this.findViewById(R.id.reduction));
        this.eTextReduc.setText(String.valueOf(reducTotal) + " €");

        this.eTextPrixTotal = (TextView)(this.findViewById(R.id.prixtotal));
        this.eTextPrixTotal.setText(String.valueOf(prixTotalReduit) + " €");

        this.tEntrees = (TextView)(this.findViewById(R.id.entrees));
        if (nbEntrees > 1) {
            this.tEntrees.setText(nbEntrees + " Entrées & Aperos");
        } else {
            this.tEntrees.setText(nbEntrees + " Entrée ou Apero");
        }

        this.tPlats = (TextView)(this.findViewById(R.id.plats));
        if (nbPlats > 1) {
            this.tPlats.setText(nbPlats + " Plats");
        } else {
            this.tPlats.setText(nbPlats + " Plat");
        }

        this.tDesserts = (TextView)(this.findViewById(R.id.desserts));
        if (nbDesserts > 1) {
            this.tDesserts.setText(nbDesserts + " Desserts");
        } else {
            this.tDesserts.setText(nbDesserts + " Dessert");
        }

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
        aq = new AQuery(v);
        boolean cancel = false;
        View focusView = null;

        JSONObject json = new JSONObject();

            GsonTransformer t = new GsonTransformer();
        //Map<String, Object> params = new HashMap<String, Object>();
        try {
            json.put("price", this.prixTotal);
            json.put("discount", this.reducTotal);
            json.put("server", "56843162e913666b573e6c2f");
            json.put("cooker", "568d0432a222d88f44e8c5c1");
            JSONArray itemArray = this.getItemsCommande();
            Log.d("itemArray",itemArray.toString());
            json.put("items",itemArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        aq.post(url, json, JSONObject.class, new AjaxCallback<JSONObject>() {

            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {

                Log.d("res", "->" + json);

            }
        });

    }
    public JSONArray getItemsCommande() throws JSONException {
        JSONArray items = new JSONArray();
        final List<Product> cart = ListProduct.getCart();
        for(int i = 0; i<cart.size();i++) {
            Log.d("ici","**"+cart.get(i).toString());
            JSONObject item = new JSONObject();
            item.put("id",cart.get(i).getId());
            items.put(item);
        }
        return items;
    }
}
