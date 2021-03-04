package com.developerputra.myuserapigithub;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {

    private static class ViewHolder {
        TextView username;
        TextView company;
        TextView location;
        ImageView img;
    }

    private ArrayList<UserItems> DataList = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;
    private String final_overview1, final_overview2;

    public UserAdapter(Context context) {
        this.context = context;
        mInflater    = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<UserItems> items){
        DataList = items;
        notifyDataSetChanged();
    }

    public void addItem(final UserItems item) {
        DataList.add(item);
        notifyDataSetChanged();
    }

    public void clearData(){
        DataList.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (DataList == null) return 0;
        return DataList.size();
    }

    @Override
    public UserItems getItem(int position) {
        return DataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            holder           = new ViewHolder();
            convertView      = mInflater.inflate(R.layout.item_user, null);
            holder.username  = (TextView)convertView.findViewById(R.id.username);
            holder.company   = (TextView)convertView.findViewById(R.id.company);
            holder.location  = (TextView)convertView.findViewById(R.id.location);
            holder.img       = (ImageView)convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.username.setText(DataList.get(position).getUsername());

        String company = DataList.get(position).getCompany();

        if(TextUtils.isEmpty(company)){
            final_overview1 = "No data";
        }
        else {
            final_overview1 = company;
        }
        holder.company.setText(final_overview1);

        String location = DataList.get(position).getLocation();

        if(TextUtils.isEmpty(location)){
            final_overview2 = "No data";
        }
        else {
            final_overview2 = location;
        }
        holder.location.setText(final_overview2);

        Picasso.get().load("http://image.tmdb.org/t/p/w154/" + DataList.get(position).getImg()).placeholder(context.getResources().getDrawable(R.drawable.pictures)).error(context.getResources().getDrawable(R.drawable.pictures)).into(holder.img);
        return convertView;
    }

}
