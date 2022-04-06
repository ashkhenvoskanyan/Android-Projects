package com.example.loginpage;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "detailActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.employeedetails);
        Log.d(TAG, "onCreate: start");
        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Log.d(TAG,"getIncomingIntent: checking for incoming intents.");
        if(getIntent().hasExtra("image") && getIntent().hasExtra("name"));{
            Log.d(TAG, "getIncomingIntent: found");
            int image = getIntent().getIntExtra("image",0);
            String name = getIntent().getStringExtra("name");
            setImage(image,name);
        }
    }

    private void setImage(int imageUrl, String name){
        TextView name1 = findViewById(R.id.detailsName);
        name1.setText(name);
        ImageView image = findViewById(R.id.detailsImage);
        image.setImageResource(imageUrl);

    }


}
