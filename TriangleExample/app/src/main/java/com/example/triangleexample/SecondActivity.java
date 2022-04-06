package com.example.triangleexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        p = findViewById(R.id.perimeterValue);

        int a = getIntent().getIntExtra("a", 0);
        int b = getIntent().getIntExtra("b",0);
        int c = getIntent().getIntExtra("c",0);

        int perimeter = a + b + c;

        p.setText(" " + perimeter);


    }
}