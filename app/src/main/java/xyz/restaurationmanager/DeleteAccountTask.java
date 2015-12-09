package xyz.restaurationmanager;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Florian on 24/11/2015.
 */
public class DeleteAccountTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        URL url = null;
        try {
            url = new URL("http://92.243.14.22:1337/person/"+params[0]);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestProperty("Content-Type", "application/xml");
            httpCon.setRequestMethod("DELETE");
            httpCon.connect();
            Log.d("Code réponse", "" + httpCon.getResponseCode());
            //return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "en cours";
    }
}
