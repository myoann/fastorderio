package xyz.restaurationmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DisplayMenu extends AppCompatActivity {

    private String urlMenu = "http://92.243.14.22:1337/menu/";
    private String urlUsers = "http://92.243.14.22:1337/person/";
    private String urlProduits = "http://92.243.14.22:1337/product/";
    private Account cuisinier;
    private Account serveur;
    private int position;
    private int tailleProduits;
    private ArrayList<Product> listeProduits = new ArrayList<Product>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String idMenu = (String)intent.getSerializableExtra("id");
        AQuery aq = new AQuery(this);
        if(idMenu!=null){

            setContentView(R.layout.activity_display_menu);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
           // setSupportActionBar(toolbar);
            //Requete AJAX pour obtenir les infos sur le menu courant

            String url = this.urlMenu+idMenu;
            aq.ajax(url, JSONObject.class, new AjaxCallback<JSONObject>() {
                @Override
                public void callback(String url, JSONObject json, AjaxStatus status) {
                    try {
                        String prixMenu = (String) json.getString("price");
                        String reductionMenu = (String) json.getString("discount");
                        TextView tvPrix = (TextView) findViewById(R.id.price_menu_text);
                        TextView tvReduction = (TextView) findViewById(R.id.discount_menu_text);
                        tvPrix.setText("Prix : " + prixMenu + "$  ");
                        tvReduction.setText("Réduction : " + reductionMenu + "$");
                        DisplayMenu.this.serveur = new Account();
                        DisplayMenu.this.cuisinier = new Account();
                        JSONObject c = json.getJSONObject("server");
                        JSONObject c2 = json.getJSONObject("cooker");
                       /* DisplayMenu.this.serveur.setId(c.getString("id"));
                        DisplayMenu.this.cuisinier.setId(c2.getString("id"));
                        DisplayMenu.this.requeteAjaxGetServeur();
                        DisplayMenu.this.requeteAjaxGetCuisinier();*/
                        DisplayMenu.this.requeteAjaxGetProduits(json.getJSONArray("items"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        }else{
            startActivity(new Intent(DisplayMenu.this, MenuActivity.class));
        }


    }

    public void requeteAjaxGetServeur(){
        //Log.d("objet serveur 2", DisplayMenu.this.serveur.toString());
        //Requete Ajax pour obtenir les infos sur le serveur courant
        AQuery aq = new AQuery(this);
        String url = this.urlUsers+DisplayMenu.this.serveur.getId();
        Log.d("urlAppel",url);
        aq.ajax(url, Account.class, new AjaxCallback<Account>() {
                @Override
                public void callback(String url, Account a, AjaxStatus status) {
                    DisplayMenu.this.serveur = a;
                }
        });
    }
    public void requeteAjaxGetCuisinier(){
        //Requete Ajax pour obtenir les infos sur le cuisinier courant
        AQuery aq = new AQuery(this);
        String url = this.urlUsers+DisplayMenu.this.cuisinier.getId();
        Log.d("urlAppel",url);
        aq.ajax(url, Account.class, new AjaxCallback<Account>() {
            @Override
            public void callback(String url, Account a, AjaxStatus status) {
                DisplayMenu.this.cuisinier = a;
            }
        });
    }

    public void requeteAjaxGetProduits(JSONArray jsonArray){
        this.tailleProduits = jsonArray.length();
        this.position = 0;
        for (int i=0;i<jsonArray.length();i++){
            try {
                JSONObject o = jsonArray.getJSONObject(i);
                String idProduit = o.getString("id");
                AQuery aq = new AQuery(this);
                String url = this.urlProduits+idProduit;
                Log.d("urlAppel", url);
                aq.ajax(url, JSONObject.class, new AjaxCallback<JSONObject>() {
                    @Override
                    public void callback(String url, JSONObject o, AjaxStatus status) {
                        Product p = new Product();
                        try {
                            p.setId(o.getString("id"));
                            p.setName(o.getString("name"));
                            p.setType(o.getString("type"));
                            DisplayMenu.this.listeProduits.add(p);
                            // Log.d("produit", p.toString());
                            if(DisplayMenu.this.position==DisplayMenu.this.tailleProduits-1){
                                DisplayMenu.this.afficherListe();
                            }
                            DisplayMenu.this.position++;
                        } catch (JSONException e) {

                            e.printStackTrace();
                        }

                    }
                });


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
    public void afficherListe(){
        Log.d("Liste des produits",this.listeProduits.toString());
        TableLayout tEntrees = (TableLayout) findViewById(R.id.layout_entrees);
        TableRow tr = new TableRow(this);
        TextView titre = new TextView(this);
        titre.setText("Entrées");
        titre.setTextSize(30);
        tEntrees.addView(titre);
        TableLayout tPlats = (TableLayout) findViewById(R.id.layout_plats);
        titre = new TextView(this);
        titre.setText("Plats");
        titre.setTextSize(30);
        tPlats.addView(titre);
        TableLayout tDesserts = (TableLayout) findViewById(R.id.layout_desserts);
        titre = new TextView(this);
        titre.setText("Desserts");
        titre.setTextSize(30);
        tDesserts.addView(titre);
        int nbEntrees = 0;
        int nbPlats = 0;
        int nbDesserts = 0;
        for(int i = 0; i<this.listeProduits.size(); i++){
            Product p = this.listeProduits.get(i);
            TextView t = new TextView(getApplicationContext());
            t.setText(p.getName());
            t.setTextSize(17);
            t.setTextColor (getResources().getColor(R.color.colorPrimaryDark));
            tr = new TableRow(this);
            tr.addView(t);
            if("Entrée".equals(p.getType()) || "Appéritif".equals(p.getType())){
                tEntrees.addView(tr);
                nbEntrees++;
            }else if("Plat ".equals(p.getType())){
                tPlats.addView(tr);
                nbPlats++;
            }else{
                tDesserts.addView(tr);
                nbDesserts++;
            }
        }
        if(nbEntrees==0){
            TextView t = new TextView(getApplicationContext());
            t.setText("Aucune entrée");
            t.setTextSize(17);
            t.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            tr = new TableRow(this);
            tr.addView(t);
            tEntrees.addView(tr);
        }
        if(nbPlats==0){
            TextView t = new TextView(getApplicationContext());
            t.setText("Aucun plat");
            t.setTextSize(17);
            t.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            tr = new TableRow(this);
            tr.addView(t);
            tPlats.addView(tr);
        }
        if(nbDesserts==0){
            TextView t = new TextView(getApplicationContext());
            t.setText("Aucun dessert");
            t.setTextSize(17);
            t.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            tr = new TableRow(this);
            tr.addView(t);
            tDesserts.addView(tr);
        }
    }
}
