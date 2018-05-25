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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pcun.b1.a1b_pcun_ma.type.CampaignInput;

import javax.annotation.Nonnull;

import okhttp3.OkHttpClient;
import java.util.ArrayList;
import java.util.List;

public class CampaignConnection {


    private CampaignAdapter campaignAdapter;
    private final String TAG = "debug_lines";
    private ApolloClient apolloClient;
    private OkHttpClient okHttpClient;
    private static final String URL = "http://35.196.104.239/graphql";


    private ListView listView;

    public CampaignConnection() {
        this.okHttpClient = new OkHttpClient.Builder().build();
        this.apolloClient = ApolloClient.builder()
                .serverUrl(URL)
                .okHttpClient(okHttpClient)
                .build();
    }

    public CampaignAdapter getCampaignAdapter() {

        return campaignAdapter;
    }
    public int allCampaign(final Activity context) {
        apolloClient.query(
                AllCampaign.builder().build()
        ).enqueue(new ApolloCall.Callback<AllCampaign.Data>() {
            @Override
            public void onResponse(@Nonnull final Response<AllCampaign.Data> response) {
                Log.d(TAG, "REQUEST SUCCEED!");
                final ArrayList<AllCampaign.AllCampaign1> data = new ArrayList<>(response.data().allCampaigns());
                if(response.data() != null) {
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listView = (ListView) context.findViewById(android.R.id.list);
                            campaignAdapter = new CampaignAdapter(context, data);
                            listView.setAdapter(campaignAdapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                                    Intent intent = new Intent(context, FragmentMapActivity.class);
                                                                    intent.putExtra("from", 1);






                                                                    context.startActivity(intent);

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

/*
    public void allCampaign(final AppCompatActivity context) {
        apolloClient.query(
                AllCampaign.builder().build()
        ).enqueue(new ApolloCall.Callback<AllCampaign.Data>() {
            @Override
            public void onResponse(@Nonnull final Response<AllCampaign.Data> response) {

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
    public void campaignById(String id, final AppCompatActivity context) {
        CampaignById campaignById = CampaignById.builder().id(id).build();

        apolloClient.query(
                campaignById
        ).enqueue(new ApolloCall.Callback< CampaignById.Data>() {
            @Override
            public void onResponse(@Nonnull final Response< CampaignById.Data> response) {
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

    public CampaignAdapter getCampaingAdapter() {
        return campaignAdapter;
    }

    public void createCampaign(String _id, String name, String city, String address, String created_date, String start_date, String end_date, String status, String program) {
        CampaignInput campaignInput = CampaignInput.builder()
                .name(name)
                .city(city)
                .address(address)

                .created_date(created_date)
                .start_date(start_date)
                .end_date(end_date)
                .status(status)
                .program(program)
                .build();

        CreateCampaign createCampaign = CreateCampaign.builder().campaign(campaignInput).build();

        apolloClient.mutate(
                createCampaign
        ).enqueue(new ApolloCall.Callback<CreateCampaign.Data>() {
            @Override
            public void onResponse(@Nonnull Response<CreateCampaign.Data> response) {
                Log.d(TAG, "campana creada!");
                Log.d(TAG, response.data().toString());
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                Log.d(TAG, "creacion fallida");
                Log.d(TAG, e.toString());
            }
        });
    }

    /*

    public void updateCampaign(String _id, String name, String city, String address, int ubication[], String created_date, String start_date, String end_date, String status, String program) {
        CampaignInput campaignInput = CampaignInput.builder()
                .name(name)
                .city(city)
                .address(address)
                .ubication(ubication)
                .created_date(created_date)
                .start_date(start_date)
                .end_date(end_date)
                .status(status)
                .program(program)
                .build();

        UpdateCampaign updateCampaign = UpdateCampaign.builder()
                .id(id).campaignInput(campaignInput).build();

        apolloClient.mutate(
                updateCampaign
        ).enqueue(new ApolloCall.Callback<UpdateCampaign.Data>() {
            @Override
            public void onResponse(@Nonnull Response<UpdateCampaign.Data> response) {
                Log.d(TAG, "REQUEST SUCCEED!");
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                Log.d(TAG, "REQUEST FAILED...");
                Log.d(TAG, e.toString());
            }
        });
    }

    public void deleteCampaign(String id) {
        DeleteCampaign deleteCampaign = DeleteCampaign.builder().id(id).build();
        apolloClient.mutate(
                deleteCampaign
        ).enqueue(new ApolloCall.Callback<DeleteCampaign.Data>() {
            @Override
            public void onResponse(@Nonnull Response<DeleteCampaign.Data> response) {
                Log.d(TAG, "REQUEST SUCCEED!");
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                Log.d(TAG, "REQUEST FAILED...");
                Log.d(TAG, e.toString());
            }
        });*/

}
