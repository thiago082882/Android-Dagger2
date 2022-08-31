package com.example.dagger2.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.dagger2.MyApplication;
import com.example.dagger2.di.RetroServiceInterface;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {

@Inject
    RetroServiceInterface mService;


    private MutableLiveData<RecyclerList> liveDataList;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        ((MyApplication)application).getRetroComponent().inject(MainActivityViewModel.this );

        //Inicializando a lista

        liveDataList= new MutableLiveData<>();
    }
    public  MutableLiveData<RecyclerList> getLiveDataListObserver(){
        return liveDataList;
    }

    // Chamada da API

    public void makeApiCall(){
        Call<RecyclerList> call = mService.getDataFromAPI("newyork");
        call.enqueue(new Callback<RecyclerList>() {
            @Override
            public void onResponse(Call<RecyclerList> call, Response<RecyclerList> response) {

                if(response.isSuccessful()){
                    liveDataList.postValue(response.body());
                } else {
                    liveDataList.postValue(null);
                }

            }

            @Override
            public void onFailure(Call<RecyclerList> call, Throwable t) {

                liveDataList.postValue(null);

            }
        });

    }
}
