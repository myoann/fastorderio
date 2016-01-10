package xyz.restaurationmanager;

import android.util.Log;

import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.Transformer;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Florian on 13/12/2015.
 */
public class Menu  implements Transformer, Serializable {
    String id;
    int price;
    int discount;
    String createdAt;
    String updatedAt;


    String server;
    String cooker;
    ArrayList<String> items;
    public Menu(){

    }
    public Menu(String id, int price, int discount, String createdAt, String updatedAt, String server, String cooker, ArrayList<String> items) {
        this.id = id;
        this.price = price;
        this.discount = discount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.server = server;
        this.cooker = cooker;
        this.items = items;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCooker() {
        return cooker;
    }

    public void setCooker(String cooker) {
        this.cooker = cooker;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id="+id+
                "price=" + price +
                ", discount=" + discount +
                ", server='" + server + '\'' +
                ", cooker='" + cooker + '\'' +
                ", items='" + items + '\'' +
                '}';
    }

    @Override
    public <T> T transform(String url, Class<T> type, String encoding, byte[] data, AjaxStatus status) {
        Gson g = new Gson();
        Log.d("type", type.toString());
        return g.fromJson(new String(data), type);
    }
}
