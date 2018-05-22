package com.pcun.b1.a1b_pcun_ma;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

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
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import okhttp3.OkHttpClient;

import com.pcun.b1.a1b_pcun_ma.FragmentMapActivity;

public class PointConnection {
    private final String TAG = "debug_lines";
    private ApolloClient apolloClient;
    private OkHttpClient okHttpClient;
    private static final String URL = "http://35.196.104.239/graphql"; //jonv3
    //private static final String URL = "http://104.196.29.186/graphiql";   //camilov2
   // private static final String URL = "http://35.185.71.134/graphql";


    private PointAdapter pointAdapter;
    private ListView listView;

    GoogleMap mGoogleMap4;

    public PointConnection() {
        this.okHttpClient = new OkHttpClient.Builder().build();
        this.apolloClient = ApolloClient.builder()
                .serverUrl(URL)
                .okHttpClient(okHttpClient)
                .build();
    }


    public PointAdapter getPointAdapter() {
        return pointAdapter;
    }


    /*
    public void allFavorites(final AppCompatActivity context) {
        apolloClient.query(
                AllFavorites.builder().build()
        ).enqueue(new ApolloCall.Callback<AllFavorites.Data>() {
            @Override
            public void onResponse(@Nonnull final Response<AllFavorites.Data> response) {

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

    */
    public int allPoints(final Activity context) {
        apolloClient.query(
                AllPoints.builder().build()
        ).enqueue(new ApolloCall.Callback<AllPoints.Data>() {
            @Override
            public void onResponse(@Nonnull final Response<AllPoints.Data> response) {
                Log.d(TAG, "REQUEST SUCCEED!");
                final ArrayList<AllPoints.AllPoint> data = new ArrayList<>(response.data().allPoints());
                if(response.data() != null) {
                    context.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            listView = (ListView) context.findViewById(android.R.id.list);
                            pointAdapter = new PointAdapter(context, data);
                            listView.setAdapter(pointAdapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                                    Intent intent = new Intent(context, FragmentMapActivity.class);
                                                                    intent.putExtra("from", 1);

                                                                    double lat = intent.getDoubleExtra("latitude", data.get(position).latitude());
                                                                    double lon = intent.getDoubleExtra("longitude", data.get(position).longitude());
                                                                    Log.d(TAG, "lat" + lat + "lon " + lon);


                                                                    MarkerOptions markerOptions = new MarkerOptions();
                                                                    markerOptions.position(new LatLng(lat, lon));
                                                                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

                                                                    //context.startActivity(intent);
                                                                    FragmentMapActivity.mGoogleMap.addMarker(markerOptions);
                                                                    FragmentMapActivity.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 12));


                                                                    String msg = new String("draw marker at: " + (new Double(lat).toString()) + " " + (new Double(lon).toString()));
                                                                    //context.startActivity(intent);
                                                                }
                                                            }
                            );
                        }
                    });
                } else {
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
