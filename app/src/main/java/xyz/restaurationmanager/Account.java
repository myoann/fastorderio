package xyz.restaurationmanager;

import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.Transformer;
import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by Yoann on 10/27/2015.
 */
public class Account implements Transformer, Serializable {
    String email;
    String password;
    String telephone;
    String prenom;
    String nom;
    String createdby;
    String sexe;
    String connected;
    String createdAt;
    String updatedAt;
    String id;

    public Account() {}

    public Account(String email, String password, String telephone, String prenom, String nom, String createdby, String sexe, String connected, String createdAt, String updatedAt, String id) {
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.prenom = prenom;
        this.nom = nom;
        this.createdby = createdby;
        this.sexe = sexe;
        this.connected = connected;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return telephone;
    }

    public void setPhone(String telephone) {
        this.telephone = telephone;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCreatedBy() {
        return createdby;
    }

    public void setCreatedBy(String createdby) {
        this.createdby = createdby;
    }

    public String getSexType() {
        return sexe;
    }

    public void setSexType(String sexe) {
        this.sexe = sexe;
    }

    public String getConnected() {
        return connected;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getId() {
        return id;
    }


    @Override
    public <T> T transform(String url, Class<T> type, String encoding, byte[] data, AjaxStatus status) {
        Gson g = new Gson();
        return g.fromJson(new String(data), type);
    }
}
