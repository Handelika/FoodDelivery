package com.handelika.fooddelivery.apiCall;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by w7 on 16.07.2017.
 */

public class ServiceGenerator {

    public static String baseUrl = "https://aboneservis.kalmiyazilim.com.tr/abone.asmx/";

    public static Retrofit retrofit;

    OkHttpClient client;

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder()
                    .addInterceptor(new LoggingInterceptor())
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS);

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson));


    public static <S> S createService(Class<S> serviceClass) {

        builder.client(httpClient.build());
        retrofit = builder.build();
        return retrofit.create(serviceClass);

    }

}

class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        Log.d("OkHttp", String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        Log.d("OkHttp", String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}