package com.pcun.b1.a1b_pcun_ma;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import okhttp3.OkHttpClient;

public class DisposalPointConnection {
    private final String TAG = "debug_lines";
    private ApolloClient apolloClient;
    private OkHttpClient okHttpClient;
    // private static final String URL = "http://35.196.104.239:3307/graphql";
    private static final String URL = "http://10.0.2.2:5000/graphql";

    private DisposalPointAdapter disposalPointAdapter;
    private ListView listView;

    public DisposalPointConnection() {
        this.okHttpClient = new OkHttpClient.Builder().build();
        this.apolloClient = ApolloClient.builder()
                .serverUrl(URL)
                .okHttpClient(okHttpClient)
                .build();
    }

    public static class Marker {
        public double lat;
        public double lon;
        public String contactPerson;
        public String disposalPointName;
        public String eMail;

        public Marker(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
            this.contactPerson = "";
            this.disposalPointName = "";
            this.eMail = "";
        }
    }

    /*
     we assume that s has the following format:
        (1.2345678, -9.8765432)
    */
    public Marker parseLocation(String s) {
        String strNum = "";
        double lat = 0;
        double lon = 0;
        for(int i = 1; i < s.length() - 1; i++) {
            if(s.charAt(i) == ',' || s.charAt(i) == ' ') {
                if(strNum != "")
                    lon = new Double(strNum).doubleValue();
                strNum = "";
                continue;
            }
            strNum += s.charAt(i);
        }
        lat = new Double(strNum).doubleValue();
        return new Marker(lat, lon);
    }

    public int allPointsBasic(final Activity context) {
        apolloClient.query(
                AllDisposalPointsBasic.builder().build()
        ).enqueue(new ApolloCall.Callback<AllDisposalPointsBasic.Data>() {
            @Override
            public void onResponse(@Nonnull final Response<AllDisposalPointsBasic.Data> response) {
                Log.d(TAG, "REQUEST SUCCEED!");
                if(response.data() != null) {
                    final ArrayList<AllDisposalPointsBasic.AllDisposalPoint> data = new ArrayList<>(response.data().allDisposalPoints());

                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listView = (ListView) context.findViewById(android.R.id.list);
                            disposalPointAdapter = new DisposalPointAdapter(context, data);

                            listView.setAdapter(disposalPointAdapter);
                            listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        String location = data.get(position).location();
                                        Marker marker = parseLocation(location);
                                        Intent intent = new Intent(context, FragmentMapActivity.class);
                                        intent.putExtra("latitude", marker.lat);
                                        intent.putExtra("longitude", marker.lon);
                                        context.startActivity(intent);
                                    }
                                }
                            );
                        }
                    });
                }
                else {
                    Log.d(TAG, "Connection with stats-ms missed or DB empty...");
                }

            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                Log.d(TAG, "REQUEST FAILED...");
                Log.d(TAG, e.toString());
            }
        });
        return 0;
    }

    public DisposalPointAdapter getDisposalPointAdapter() {
        return disposalPointAdapter;
    }

    public void pointById(int id, final AppCompatActivity context) {
        PointById pointById = PointById.builder().id(id).build();

        apolloClient.query(
                pointById
        ).enqueue(new ApolloCall.Callback<PointById.Data>() {
            @Override
            public void onResponse(@Nonnull final Response<PointById.Data> response) {
                Log.d(TAG, "REQUEST SUCCEED!");
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView txtResponse = (TextView) context.findViewById(R.id.textView);
                        txtResponse.setText(response.data().toString());
                    }
                });
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                Log.d(TAG, "REQUEST FAILED...");
                Log.d(TAG, e.toString());
            }
        });
    }

    public void pointByName(String name, final AppCompatActivity context) {
        PointByName pointByName = PointByName.builder().name(name).build();

        apolloClient.query(
                pointByName
        ).enqueue(new ApolloCall.Callback<PointByName.Data>() {
            @Override
            public void onResponse(@Nonnull final Response<PointByName.Data> response) {
                Log.d(TAG, "REQUEST SUCCEED!");
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView txtResponse = (TextView) context.findViewById(R.id.textView);
                        txtResponse.setText(response.data().toString());
                    }
                });
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                Log.d(TAG, "REQUEST FAILED...");
                Log.d(TAG, e.toString());
            }
        });
    }

    public void pointByPosition(double latitude_upper, double latitude_lower,
                                double longitude_upper, double longitude_lower,
                                final AppCompatActivity context) {

        PointByPosition pointByPosition = PointByPosition.builder()
                .latitude_upper(latitude_upper).latitude_lower(latitude_lower)
                .longitude_upper(longitude_upper).longitude_lower(longitude_lower)
                .build();

        apolloClient.query(
                pointByPosition
        ).enqueue(new ApolloCall.Callback<PointByPosition.Data>() {
            @Override
            public void onResponse(@Nonnull final Response<PointByPosition.Data> response) {
                Log.d(TAG, "REQUEST SUCCEED!");
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView txtResponse = (TextView) context.findViewById(R.id.textView);
                        txtResponse.setText(response.data().toString());
                    }
                });
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                Log.d(TAG, "REQUEST FAILED...");
                Log.d(TAG, e.toString());
            }
        });
    }


}
