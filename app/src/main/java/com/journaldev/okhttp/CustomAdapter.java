package com.journaldev.okhttp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class CustomAdapter extends BaseAdapter {
    private List<Process> lesProcessus;
    private Activity activity;
    private LayoutInflater inflater;

    public CustomAdapter(List<Process> lesProcessus, Activity activity) {
        this.lesProcessus = lesProcessus;
        this.activity = activity;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override

    public int getCount() {
        return lesProcessus.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public String getProssId(int i) {
        return lesProcessus.get(i).getId();
    }

    public String getProssName(int i) {
        return lesProcessus.get(i).getName();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.each_list_item, parent, false);

            holder = new ViewHolder();

            holder.nameView = (TextView) convertView.findViewById(R.id.nameView);
            holder.keyView = (TextView) convertView.findViewById(R.id.keyView);
            holder.idview=(TextView) convertView.findViewById(R.id.idview);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        Process process = lesProcessus.get(position);

        holder.nameView.setText(process.getName());
        holder.keyView.setText(process.getKey());
        holder.idview.setText(process.getId());

        return convertView;
    }

    public class ViewHolder {

        TextView nameView;
        TextView keyView;
        TextView idview;


    }

}
