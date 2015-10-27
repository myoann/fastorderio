package xyz.restaurationmanager;

import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.Transformer;
import com.google.gson.Gson;

/**
 * Created by Yoann on 10/27/2015.
 */
public class Account implements Transformer {
    String email;
    String password1;
    String password2;
    String phone;
    String firstName;
    String lastName;
    String createdBy;
    String sexType;

    public Account() {}

    public Account(String email, String password1, String password2, String phone, String firstName, String lastName, String createdBy, String sexType) {
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdBy = createdBy;
        this.sexType = sexType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getSexType() {
        return sexType;
    }

    public void setSexType(String sexType) {
        this.sexType = sexType;
    }

    @Override
    public <T> T transform(String url, Class<T> type, String encoding, byte[] data, AjaxStatus status) {
        Gson g = new Gson();
        return g.fromJson(new String(data), type);
    }
}
