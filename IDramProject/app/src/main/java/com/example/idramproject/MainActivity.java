package com.example.idramproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    LinearLayoutManager linearLayoutManager;
    List<ImagesClass> imagesList;
    List<ButtonClass> buttonList;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        textView =(TextView) findViewById(R.id.seeAll);

        textView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        initData();
        initRecyclerView();
        initData1();
        initRecyclerView1();
    }

    private void initData() {

        imagesList = new ArrayList<>();

        imagesList.add(new ImagesClass(R.drawable.idram1, "GIVE YOURSELF A TREAT"));
        imagesList.add(new ImagesClass(R.drawable.idram2, "10 x 1 MLN AMD FROM IDRAM"));
        imagesList.add(new ImagesClass(R.drawable.idram3, "ROCKET LINE 0%"));
        imagesList.add(new ImagesClass(R.drawable.idram4, "TRANSFERS TO RUSSIA"));
        imagesList.add(new ImagesClass(R.drawable.idram5, "NEW PARTNERS"));

    }

    @SuppressLint("NotifyDataSetChanged")
    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ImagesAdapter(imagesList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initData1() {

        buttonList = new ArrayList<>();

        buttonList.add(new ButtonClass(R.drawable.circle, R.drawable.ic_baseline_phone_android_24, "Mobile connection"));
        buttonList.add(new ButtonClass(R.drawable.circle, R.drawable.ic_baseline_add_card_24, "To bank card"));
        buttonList.add(new ButtonClass(R.drawable.circle, R.drawable.ic_baseline_account_balance_24, "To bank account"));
        buttonList.add(new ButtonClass(R.drawable.circle, R.drawable.ic_baseline_local_parking_24, "Parking payment"));
        buttonList.add(new ButtonClass(R.drawable.circle, R.drawable.ic_baseline_live_tv_24, "TV"));
        buttonList.add(new ButtonClass(R.drawable.circle, R.drawable.ic_baseline_local_fire_department_24, "Gas"));
        buttonList.add(new ButtonClass(R.drawable.circle, R.drawable.ic_baseline_wifi_24, "Internet"));
        buttonList.add(new ButtonClass(R.drawable.circle, R.drawable.ic_baseline_opacity_24, "Water"));
        buttonList.add(new ButtonClass(R.drawable.circle, R.drawable.ic_baseline_add_card_24, "Property tax"));
        buttonList.add(new ButtonClass(R.drawable.circle, R.drawable.ic_baseline_more_horiz_24, "Other"));


    }

    @SuppressLint("NotifyDataSetChanged")
    private void initRecyclerView1() {
        recyclerView2 = findViewById(R.id.recyclerView2);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView2.setLayoutManager(linearLayoutManager);
        adapter = new ButtonAdapter(buttonList);
        recyclerView2.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}