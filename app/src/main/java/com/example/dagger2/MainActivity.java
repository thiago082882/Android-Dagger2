package com.example.dagger2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Observable;
import android.os.Bundle;
import android.widget.Toast;

import com.example.dagger2.model.MainActivityViewModel;
import com.example.dagger2.model.RecyclerList;

public class MainActivity extends AppCompatActivity {


    RecyclerViewAdapter recyclerViewAdapter;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        getData();

    }

    private void initRecyclerView() {

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void getData(){
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.getLiveDataListObserver().observe(this, new Observer<RecyclerList>() {
            @Override
            public void onChanged(RecyclerList recyclerList) {
                if(recyclerList != null ){
                    recyclerViewAdapter.setListData(recyclerList.getItems());
                    recyclerViewAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(MainActivity.this, "Não foi possível buscar dados...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.makeApiCall();
    }

}