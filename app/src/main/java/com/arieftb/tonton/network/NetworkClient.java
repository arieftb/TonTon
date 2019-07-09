/*
 * Developed by arieftb on 7/8/19 2:22 PM.
 * Last Modified 7/8/19 2:22 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.network;

import com.arieftb.tonton.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {

    private String baseUrl;

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public NetworkEndPoint getApiService() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(NetworkEndPoint.class);
    }
}
