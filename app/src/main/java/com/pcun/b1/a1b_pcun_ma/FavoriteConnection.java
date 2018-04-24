package com.pcun.b1.a1b_pcun_ma;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.pcun.b1.a1b_pcun_ma.type.FavoriteInput;

import javax.annotation.Nonnull;

import okhttp3.OkHttpClient;

public class FavoriteConnection {
    private final String TAG = "debug_lines";
    private ApolloClient apolloClient;
    private OkHttpClient okHttpClient;
    private static final String URL = "http://35.196.104.239:3307/graphql";

    public FavoriteConnection() {
        this.okHttpClient = new OkHttpClient.Builder().build();
        this.apolloClient = ApolloClient.builder()
                .serverUrl(URL)
                .okHttpClient(okHttpClient)
                .build();
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

    public void favoriteById(int id, final AppCompatActivity context) {
        FavoriteById favoriteById = FavoriteById.builder().id(id).build();

        apolloClient.query(
                favoriteById
        ).enqueue(new ApolloCall.Callback<FavoriteById.Data>() {
            @Override
            public void onResponse(@Nonnull final Response<FavoriteById.Data> response) {
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