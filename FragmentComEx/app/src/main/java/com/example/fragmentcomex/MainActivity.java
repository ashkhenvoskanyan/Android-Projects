package com.example.fragmentcomex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Fragment1.Listener1, Fragment2.Listener2 {

    private Fragment1 fragment1;
    private Fragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_1, fragment1)
                .replace(R.id.frame_2, fragment2)
                .commit();
    }

    @Override
    public void sendData1(CharSequence input) {
        fragment2.updateText(input);
    }

    @Override
    public void sendData2(CharSequence input) {
        fragment1.updateText(input);
    }
}