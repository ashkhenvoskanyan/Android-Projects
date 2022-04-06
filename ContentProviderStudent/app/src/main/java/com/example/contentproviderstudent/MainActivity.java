package com.example.contentproviderstudent;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Mani Activity", "onCreate");
    }

    protected void onStart(){
        super.onStart();
        Log.i("Main Activity", "onStart");
    }

    protected void onResume(){
        super.onResume();
        Log.i("Main Activity", "onResume");
    }

    public void onClickAddName(View view ){
        ContentValues values = new ContentValues();
        values.put(StudentsProvider.NAME, ((EditText) findViewById(R.id.name)).getText().toString());
        values.put(StudentsProvider.GRADE,((EditText) findViewById(R.id.grade)).getText().toString());

        Uri uri = getContentResolver().insert(StudentsProvider.CONTENT_URI, values);
        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
    }

    @SuppressLint("Range")
    public void onClickRetrieveStudents(View view){
        String URL = "content://com,example.MyApplication.StudentsProvider";

        Uri students = Uri.parse(URL);
        Cursor c = getContentResolver().query(students,null,null,null,null);

        String myStudents = null;

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            myStudents = myStudents + c.getString(c.getColumnIndex(StudentsProvider.ID)) + ", " + c.getString(c.getColumnIndex(StudentsProvider.NAME)) + ", " + c.getString(c.getColumnIndex(StudentsProvider.GRADE));
        }
        Toast.makeText(this,myStudents,Toast.LENGTH_SHORT).show();
    }

    protected void onPause(){
        super.onPause();
        Log.i("Main Activity", "onPause");
    }

    protected void onStop(){
        super.onStop();
        Log.i("Main Activity", "onStop");
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.i("Main Activity", "onDestroy");
    }
}
