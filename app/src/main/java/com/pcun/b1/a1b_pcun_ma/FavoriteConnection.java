package com.pcun.b1.a1b_pcun_ma;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pcun.b1.a1b_pcun_ma.type.FavoriteInput;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import okhttp3.OkHttpClient;

public class FavoriteConnection {
    private final String TAG = "debug_lines";
    private ApolloClient apolloClient;
    private OkHttpClient okHttpClient;
    private static final String URL = "http://35.196.104.239/graphql"; //jonv3
    //private static final String URL = "http://104.196.29.186/graphiql";   //camilov2
//    private static final String URL = "http://35.196.104.239:3307/graphql"; //pruebami

    private FavoriteAdapter favoriteAdapter;
    private ListView listView;

    public FavoriteConnection() {
        this.okHttpClient = new OkHttpClient.Builder().build();
        this.apolloClient = ApolloClient.builder()
                .serverUrl(URL)
                .okHttpClient(okHttpClient)
                .build();
    }
    public FavoriteAdapter getFavoriteAdapter() {
        return favoriteAdapter;
    }

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

    //public void favoriteById(int id, final AppCompatActivity context) {
    public int favoriteById(int id, final AppCompatActivity context) {
        FavoriteById favoriteById = FavoriteById.builder().id(id).build();

        apolloClient.query(
                FavoriteById.builder().id(id).build()
        ).enqueue(new ApolloCall.Callback<FavoriteById.Data>() {
            @Override
            public void onResponse(@Nonnull final Response<FavoriteById.Data> response) {
                final ArrayList<FavoriteById.FavoriteById1> data = new ArrayList<>(response.data().FavoriteById());
                Log.d(TAG, "REQUEST SUCCEED!");
                if (response.data() != null) {
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listView = (ListView) context.findViewById(android.R.id.list);
                            favoriteAdapter = new FavoriteAdapter(context, data);
                            listView.setAdapter(favoriteAdapter);
                            //int txtResponse = ((GlobalData) context.getApplication()).getCurrentUser();
                            TextView txtResponse = (TextView) context.findViewById(R.id.textView);
                            txtResponse.setText(response.data().toString());
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
/*
    public void showList(int id){
        final ArrayList<AllPoints.AllPoint> data;
        listView = (ListView)favoriteById(((GlobalData) context.getApplication()).getCurrentUser();).findViewById(R.id.nav_favourite);
        favoriteAdapter = new FavoriteAdapter(context, data);
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
    }
*/
    public void createFavorite(int user_id, int place_id, String comment) {
        FavoriteInput favoriteInput = FavoriteInput.builder()
                .user_id(user_id)
                .place_id(place_id)
                .comment(comment)
                .build();

        CreateFavorite createFavorite = CreateFavorite.builder().favoriteInput(favoriteInput).build();

        apolloClient.mutate(
                createFavorite
        ).enqueue(new ApolloCall.Callback<CreateFavorite.Data>() {
            @Override
            public void onResponse(@Nonnull Response<CreateFavorite.Data> response) {
                Log.d(TAG, "REQUEST SUCCEED!");
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                Log.d(TAG, "REQUEST FAILED...");
                Log.d(TAG, e.toString());
            }
        });
    }

    public void updateFavorite(int id, int user_id, int place_id, String comment) {
        FavoriteInput favoriteInput = FavoriteInput.builder()
                .user_id(user_id)
                .place_id(place_id)
                .comment(comment)
                .build();

        UpdateFavorite updateFavorite = UpdateFavorite.builder()
                .id(id).favoriteInput(favoriteInput).build();

        apolloClient.mutate(
                updateFavorite
        ).enqueue(new ApolloCall.Callback<UpdateFavorite.Data>() {
            @Override
            public void onResponse(@Nonnull Response<UpdateFavorite.Data> response) {
                Log.d(TAG, "REQUEST SUCCEED!");
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                Log.d(TAG, "REQUEST FAILED...");
                Log.d(TAG, e.toString());
            }
        });
    }

    public void deleteFavorite(int id) {
        DeleteFavorite deleteFavorite = DeleteFavorite.builder().id(id).build();
        apolloClient.mutate(
                deleteFavorite
        ).enqueue(new ApolloCall.Callback<DeleteFavorite.Data>() {
            @Override
            public void onResponse(@Nonnull Response<DeleteFavorite.Data> response) {
                Log.d(TAG, "REQUEST SUCCEED!");
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                Log.d(TAG, "REQUEST FAILED...");
                Log.d(TAG, e.toString());
            }
        });
    }
}