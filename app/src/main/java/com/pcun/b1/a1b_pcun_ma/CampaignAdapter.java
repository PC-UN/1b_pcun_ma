package com.pcun.b1.a1b_pcun_ma;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CampaignAdapter extends ArrayAdapter<AllCampaign.AllCampaign1> {

    final String TAG = "debug_lines";
    private ArrayList<AllCampaign.AllCampaign1> dataset;
    Context mContext;

    private static class ViewHolder {
        TextView txt_name;
        TextView txt_city;
        TextView txt_address;
        TextView txt_create_date;
        TextView txt_start_date;
        TextView txt_end_date;
        TextView txt_status;
        TextView txt_program;
        ImageView info;
    }
    public CampaignAdapter(Context context, ArrayList<AllCampaign.AllCampaign1> data) {
        super(context, R.layout.campaign_item, data);
        this.dataset = data;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AllCampaign.AllCampaign1 campaign = getItem(position);
        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.campaign_item, parent, false);
            viewHolder.txt_name = (TextView) convertView.findViewById(R.id.name_campaign);
            viewHolder.txt_city = (TextView) convertView.findViewById(R.id.city_campaign);
            viewHolder.txt_address = (TextView) convertView.findViewById((R.id.address_campaign));
            viewHolder.txt_create_date = (TextView) convertView.findViewById(R.id.create_date);
            viewHolder.txt_start_date = (TextView) convertView.findViewById(R.id.start_date);
            viewHolder.txt_end_date = (TextView) convertView.findViewById(R.id.end_date);
            viewHolder.txt_status = (TextView) convertView.findViewById(R.id.status);
            viewHolder.txt_program = (TextView) convertView.findViewById(R.id.program);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.txt_name.setText(campaign.name());
        viewHolder.txt_city.setText(campaign.city());
        viewHolder.txt_address.setText(campaign.address());
        viewHolder.txt_create_date.setText(campaign.created_date());
        viewHolder.txt_start_date.setText(campaign.start_date());
        viewHolder.txt_end_date.setText(campaign.end_date());
        viewHolder.txt_status.setText(campaign.status());
        viewHolder.txt_program.setText(campaign.program());

        return convertView;
    }
}
