package com.pcun.b1.a1b_pcun_ma;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FavoriteAdapter extends ArrayAdapter<AllPoints.AllPoint> implements View.OnClickListener {
    final String TAG = "debug_line";
    private ArrayList<FavoriteById.FavoriteById1> dataset;
    Context mContext;

    private static class ViewHolder {
        TextView txtCategory;
        TextView txtName;
        TextView txtContact;
        TextView txtEmail;
        // TextView txtLatitude;
        // TextView txtLongitude;
        ImageView info;
    }

    public FavoriteAdapter(Context context, ArrayList<FavoriteById.FavoriteById1> data) {
        //super(context, R.layout.point_item, data);
        super(context, R.layout.point_item);
        this.dataset = data;
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick method...");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AllPoints.AllPoint point = getItem(position);
        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.point_item, parent, false);
            viewHolder.txtCategory = (TextView) convertView.findViewById(R.id.category);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.txtContact = (TextView) convertView.findViewById(R.id.contact);
            viewHolder.txtEmail = (TextView) convertView.findViewById(R.id.email);
            // viewHolder.txtLatitude = (TextView) convertView.findViewById(R.id.latitude);
            // viewHolder.txtLongitude = (TextView) convertView.findViewById(R.id.longitude);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.txtCategory.setText(point.category());
        viewHolder.txtName.setText(point.name());
        viewHolder.txtContact.setText(point.contact());
        viewHolder.txtEmail.setText(point.email());
        // viewHolder.txtLatitude.setText(((Double) point.latitude()).toString());
        // viewHolder.txtLatitude.setText(((Double) point.longitude()).toString());
        // viewHolder.info.setTag(position);

        return convertView;
    }

}

