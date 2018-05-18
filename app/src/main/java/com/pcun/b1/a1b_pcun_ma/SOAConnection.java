package com.pcun.b1.a1b_pcun_ma;

import com.apollographql.apollo.ApolloClient;

import okhttp3.OkHttpClient;

public class SOAConnection {
    private final String TAG = "debug_lines";
    private ApolloClient apolloClient;
    private OkHttpClient okHttpClient;
    private static final String URL = "http://35.185.71.134/graphql";

    public SOAConnection() {
        this.okHttpClient = new OkHttpClient.Builder().build();
        this.apolloClient = ApolloClient.builder()
                .serverUrl(URL)
                .okHttpClient(okHttpClient)
                .build();
    }
}
