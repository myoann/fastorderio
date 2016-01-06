package xyz.restaurationmanager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Yoann on 11/24/2015.
 */
public class ProductItemAdapter extends BaseAdapter {

    private Context context;
    public List<Product> products;
    public int positionA;
    private int image_cercle_contact;
    private int image_cercle_contact1;

    public ProductItemAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int arg0) {
        return products.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return Long.getLong(products.get(arg0).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        View v = convertView;
        this.positionA = position;
        ProductViewHolder viewHolder = null;
        if(v==null){
            v = View.inflate(context, R.layout.content_list_product, null);
            viewHolder = new ProductViewHolder();
            viewHolder.name= (TextView)v.findViewById(R.id.title_name_firstname);

            v.setTag(viewHolder);
        }
        else{
            viewHolder = (ProductViewHolder) v.getTag();
        }
        Product product = products.get(position);
        viewHolder.name.setText(product.getName());

        return v;
    }

    class ProductViewHolder{
        TextView name;
        TextView price;
    }
}
