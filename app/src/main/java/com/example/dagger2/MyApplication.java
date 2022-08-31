package com.example.dagger2;


import android.app.Application;

import com.example.dagger2.di.DaggerRetroComponent;
import com.example.dagger2.di.RetroComponent;
import com.example.dagger2.di.RetroModule;

public class MyApplication extends Application {


    private RetroComponent retroComponent;

    @Override
    public void onCreate(){

        super.onCreate();

        retroComponent = DaggerRetroComponent.builder()
                .retroModule(new RetroModule())
                .build();
    }
    public RetroComponent getRetroComponent(){
        return retroComponent;
    }
}
