package midas.mirror;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<CalListItem> items;
    private LayoutInflater inflater;
    private ViewHolder holder;


    public ListAdapter(Context context,int layout, ArrayList<CalListItem> items){
        this.context = context;
        this.layout =layout;
        this.items = items;
        this.inflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(CalListItem item){
        items.add(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       //convertView -> 항목 하나에 들어가는 layout을 말함
        if (convertView== null) {
            convertView = inflater.inflate(layout,parent,false);
            holder = new ViewHolder();

            holder.title = convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(items.get(position).getTitle());
        return convertView;
    }

    class ViewHolder{
        TextView title;
    }
}
