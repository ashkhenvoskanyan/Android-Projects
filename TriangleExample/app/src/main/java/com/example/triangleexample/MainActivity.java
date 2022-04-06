package com.example.triangleexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView a,b,c;
    private EditText firstValue, secondValue, thirdValue;
    private int fSide;
    private int sSide;
    private int thSide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = findViewById(R.id.aSide);
        b = findViewById(R.id.bSide);
        c = findViewById(R.id.cSide);
        firstValue = findViewById(R.id.firstSideNum);
        secondValue = findViewById(R.id.secondSideNum);
        thirdValue = findViewById(R.id.thirdSideNum);
        button = findViewById(R.id.enter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkEmpty(firstValue) && checkEmpty(secondValue) && checkEmpty(thirdValue)) {
                    fSide = Integer.parseInt(firstValue.getText().toString());
                    sSide = Integer.parseInt(secondValue.getText().toString());
                    thSide = Integer.parseInt(thirdValue.getText().toString());
                    if (checkCorrectness(fSide, sSide, thSide)) {
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("a", fSide);
                        intent.putExtra("b", sSide);
                        intent.putExtra("c", thSide);

                        startActivity(intent);
                    } else
                        Toast.makeText(getApplicationContext(), "The triangle is invalid", Toast.LENGTH_LONG).show();

                }
            }
        });

    }


    private boolean checkEmpty(EditText value) {
        if (value.getText().toString().equals("")) {
            value.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon, 0, 0, 0);
            return false;
        }
        return true;


    }

    public boolean checkCorrectness(int a, int b, int c) {
        if (a == 0 || b == 0 || c == 0)
            return false;

        else if (a + b >= c && a + c >= b && b + c >= a)
            return true;

        else
            return false;
    }




}