package com.filmscout.nasha.filmscout.api;

import android.app.Application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.filmscout.nasha.filmscout.api.Converter.EnumConverter;
import com.filmscout.nasha.filmscout.app.AppScope;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class ApiModule {
    private static final long DISK_CACHE_SIZE = 50 * 1024 * 1024; //50MB

    public static final String BACKDROP_URL = "http://image.tmdb.org/t/p/w1280/";
    public static final String POSTER_URL = "http://image.tmdb.org/t/p/w600/";
    public static final String API_URL = "https://api.themoviedb.org/3/";
    public static final String MOVIE = "movie/";
    public static final String DISCOVER = "discover/movie/";
    public static final String SEARCH_MOVIE = "search/movie/";
    public static final String MPAA_RATING = "certification/movie/list";
    public static final String GENRE = "genre/movie/list";
    public static final String PERSON = "person/";
    public static final String CONFIGURATION = "configuration/";

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Application app){
        File cacheDir = new File(app.getCacheDir(), "http");
        return new OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .cache(new okhttp3.Cache(cacheDir, DISK_CACHE_SIZE))
                .build();
    }

    @Provides
    @Singleton
    JacksonConverterFactory provideJacksonConverterFactory() {
        ObjectMapper objectMapper = new ObjectMapper();
        return JacksonConverterFactory
                .create(objectMapper);
    }

    @Provides
    @Named("filmScoutDB")
    @Singleton
    public Retrofit proveRetroFitAdapter(JacksonConverterFactory jacksonConverterFactory,
                                         OkHttpClient okHttpClient){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = okHttpClient.newBuilder()
                .addInterceptor(interceptor)
                .build();
        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(jacksonConverterFactory)
                .addConverterFactory(new EnumConverter())
                .build();
    }

    @Provides
    @Singleton
    public MovieApiService provideMovieApiService(@Named("filmScoutDB") Retrofit restAdapter){
        return restAdapter.create(MovieApiService.class);
    }


}
