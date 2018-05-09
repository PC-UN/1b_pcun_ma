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

public class DisposalPointAdapter extends ArrayAdapter<AllDisposalPointsBasic.AllDisposalPoint> {
    final String TAG = "debug_lines";
    private ArrayList<AllDisposalPointsBasic.AllDisposalPoint> dataset;
    Context mContext;

    private static class ViewHolder {
        TextView txt_disposal_point_name;
        TextView txt_city;
        TextView txt_department;
        TextView txt_residue_category;
        TextView txt_contact_person;
        TextView txt_email;
        ImageView info;
    }

    public DisposalPointAdapter(Context context, ArrayList<AllDisposalPointsBasic.AllDisposalPoint> data) {
        super(context, R.layout.disposal_point_item, data);
        this.dataset = data;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AllDisposalPointsBasic.AllDisposalPoint disposalPoint = getItem(position);
        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.disposal_point_item, parent, false);
            viewHolder.txt_disposal_point_name = (TextView) convertView.findViewById(R.id.disposal_point_name);
            viewHolder.txt_city = (TextView) convertView.findViewById(R.id.city);
            viewHolder.txt_department = (TextView) convertView.findViewById((R.id.department));
            viewHolder.txt_residue_category = (TextView) convertView.findViewById(R.id.residue_category);
            viewHolder.txt_contact_person = (TextView) convertView.findViewById(R.id.contact_person);
            viewHolder.txt_email = (TextView) convertView.findViewById(R.id.email);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.txt_disposal_point_name.setText(disposalPoint.disposal_point_name());
        viewHolder.txt_city.setText(disposalPoint.city());
        viewHolder.txt_department.setText(disposalPoint.department());
        viewHolder.txt_residue_category.setText(disposalPoint.residue_category());
        viewHolder.txt_contact_person.setText(disposalPoint.contact_person());
        viewHolder.txt_email.setText(disposalPoint.email());

        return convertView;
    }
}
