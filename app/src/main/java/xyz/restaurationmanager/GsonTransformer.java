package xyz.restaurationmanager;

import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.Transformer;
import com.google.gson.Gson;

/**
 * Created by Yoann on 10/27/2015.
 */
public class GsonTransformer implements Transformer {

    public <T> T transform(String url, Class<T> type, String encoding, byte[] data, AjaxStatus status) {
        Gson g = new Gson();
        return g.fromJson(new String(data), type);
    }
}

