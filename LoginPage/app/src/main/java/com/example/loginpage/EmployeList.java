package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class EmployeList extends AppCompatActivity {

    EmployeeAdapter employeeAdapter;
    ArrayList<EmployeeInfo> employeeInfos = new ArrayList<>();
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employe_list);

        EditText search = findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                filter(editable  .toString());

            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

            employeeInfos.add(new EmployeeInfo("Nicol Green", R.drawable.image1));
            employeeInfos.add( new EmployeeInfo("Lena Smith", R.drawable.image9));
            employeeInfos.add(new EmployeeInfo("Lucy White", R.drawable.image3));
            employeeInfos.add(new EmployeeInfo("Alexia King", R.drawable.image4));
            employeeInfos.add( new EmployeeInfo("Nelly Cook", R.drawable.image5));
            employeeInfos.add(new EmployeeInfo("Tom Taylor", R.drawable.image6));
            employeeInfos.add(new EmployeeInfo("Joanna Wood", R.drawable.image7));
            employeeInfos.add(new EmployeeInfo("Ruby Lee", R.drawable.image8));


         employeeAdapter = new EmployeeAdapter(employeeInfos,EmployeList.this);
        recyclerView.setAdapter(employeeAdapter);
    }

    private void filter(String text){
        ArrayList<EmployeeInfo> filteredList = new ArrayList<>();

        for (EmployeeInfo item : employeeInfos){
            if (item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }


       employeeAdapter.filterList(filteredList);
    }
}