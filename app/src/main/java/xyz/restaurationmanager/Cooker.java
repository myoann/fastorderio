package xyz.restaurationmanager;

import java.io.Serializable;

/**
 * Created by Yoann on 1/10/2016.
 */
public class Cooker implements Serializable {
    String apikey;
    String email;
    String name;
    String gcmkey;
    boolean active;

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGcmkey() {
        return gcmkey;
    }

    public void setGcmkey(String gcmkey) {
        this.gcmkey = gcmkey;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
