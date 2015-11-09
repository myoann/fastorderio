package xyz.restaurationmanager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Florian on 08/11/2015.
 */
public class AccountItemAdapter extends BaseAdapter {

    private Context context;
    public List<Account> acounts;

    public AccountItemAdapter(Context context, List<Account> acounts) {
        this.context = context;
        this.acounts = acounts;
    }

    @Override
    public int getCount() {
        return acounts.size();
    }

    @Override
    public Object getItem(int arg0) {
        return acounts.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        View v = convertView;

        AccountViewHolder viewHolder = null;
        if(v==null){
            v = View.inflate(context, R.layout.content_list_accounts, null);
            viewHolder = new AccountViewHolder();
            viewHolder.nom_prenom= (TextView)v.findViewById(R.id.title_name_firstname);
           // viewHolder.date_creation= (TextView)v.findViewById(R.id.txt_date_inscription);
            v.setTag(viewHolder);
        }
        else{
            viewHolder = (AccountViewHolder) v.getTag();
        }
        Account account = acounts.get(position);
        viewHolder.nom_prenom.setText(account.getPrenom());
        viewHolder.date_creation.setText(account.getCreatedAt());
        return v;
    }

    class AccountViewHolder{
        TextView nom_prenom;
        TextView date_creation;
    }
}
