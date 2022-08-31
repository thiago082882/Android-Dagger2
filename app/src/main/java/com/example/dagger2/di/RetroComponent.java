package com.example.dagger2.di;


import com.example.dagger2.model.MainActivityViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RetroModule.class})

public interface RetroComponent {

    public void inject (MainActivityViewModel mainActivityViewModel);


}
