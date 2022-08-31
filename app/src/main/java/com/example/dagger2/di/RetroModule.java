package com.example.dagger2.di;


import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetroModule {

    private String baseURL = "https://api.github.com/search/";//repositories?q=newyork

    // criando uma função dentro do modulo que retornará a instância da interface

    @Provides
    @Singleton
    public RetroServiceInterface getRetroServiceInterface(Retrofit retrofit) {
        return retrofit.create(RetroServiceInterface.class);
    }

    @Singleton
    @Provides
    public Retrofit getRetrofitInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
