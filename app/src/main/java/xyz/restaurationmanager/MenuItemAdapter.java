package xyz.restaurationmanager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Florian on 13/12/2015.
 */
public class MenuItemAdapter extends BaseAdapter {
    Context context;
    ArrayList<Menu> listeMenus;

    MenuItemAdapter(Context context, ArrayList<Menu> listeMenus){
        this.context = context;
        this.listeMenus = listeMenus;
    }
    @Override
    public int getCount() {
        return listeMenus.size();
    }

    @Override
    public Object getItem(int position) {
        return listeMenus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        MenuViewHolder viewHolder = new MenuViewHolder();
        if(v==null) {
            v = View.inflate(context, R.layout.content_list_menu, null);
            viewHolder.titreMenu = (TextView) v.findViewById(R.id.titre_menu);
            viewHolder.prixMenu = (TextView) v.findViewById(R.id.prix_menu);
            viewHolder.boutonAccesMenu = (Button) v.findViewById(R.id.boutton_acces_menu);
            v.setTag(viewHolder);
        }else{
            viewHolder = (MenuViewHolder) v.getTag();
        }

        viewHolder.titreMenu.setText("Menu n°" + (position + 1));
        viewHolder.prixMenu.setText(this.listeMenus.get(position).getPrice()+" euros");
        viewHolder.boutonAccesMenu.setTag(this.listeMenus.get(position).getId());
        viewHolder.boutonAccesMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DisplayMenu.class);
                intent.putExtra("id", v.getTag() + "");
                context.startActivity(intent);

            }
        });
        return v;
    }

    class MenuViewHolder{
        TextView titreMenu;
        TextView prixMenu;
        Button boutonAccesMenu;
    }
}
