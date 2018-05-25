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

public class FavoriteAdapter extends ArrayAdapter<FavoriteById.FavoriteById1> implements View.OnClickListener {
    final String TAG = "debug_line";
    //private ArrayList<AllFavorites.AllFavorite> dataset;
    private ArrayList<FavoriteById.FavoriteById1> dataset;
    Context mContext;

    private static class ViewHolder {
        TextView txtPlaceId;
        TextView txtComment;
        ImageView info;
    }
/*
    public FavoriteAdapter(Context context, ArrayList<AllFavorites.AllFavorite> data) {
        //super(context, R.layout.point_item, data);
        super(context, R.layout.favorite_item);
        this.dataset = data;
        this.mContext = context;
    }*/
public FavoriteAdapter(Context context, ArrayList<FavoriteById.FavoriteById1> data) {
    //super(context, R.layout.point_item, data);
    super(context, R.layout.favorite_item);
    this.dataset = data;
    this.mContext = context;
}

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick method...");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //AllFavorites.AllFavorite favorite = getItem(position);
        FavoriteById.FavoriteById1 favorite = getItem(position);
        //AllPoints.AllPoint point = getItem(position);
        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.favorite_item, parent, false);
            viewHolder.txtPlaceId = (TextView) convertView.findViewById(R.id.lugar_id);
            viewHolder.txtComment = (TextView) convertView.findViewById(R.id.comentario_f);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.txtPlaceId.setText(favorite.place_id());
        viewHolder.txtComment.setText(favorite.comment());


        return convertView;
    }

}

