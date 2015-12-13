package xyz.restaurationmanager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

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
            viewHolder.libelleMenu = (TextView) v.findViewById(R.id.libellé_menu);
            v.setTag(viewHolder);
        }else{
            viewHolder = (MenuViewHolder) v.getTag();
        }
        viewHolder.libelleMenu.setText(this.listeMenus.get(position).getPrice()+" euros");
        return v;
    }

    class MenuViewHolder{
        TextView libelleMenu;
    }
}
