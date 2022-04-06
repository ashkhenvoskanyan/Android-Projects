package com.example.mvvmproject;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mvvmproject.databinding.FragmentSecondBinding;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class SecondFragment<FragmentSecondBinding> extends Fragment {


    private FragmentSecondBinding binding;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Button buttonSave;
    private TextInputLayout name_surname;
    private TextInputLayout phone_number;
    private String date;
    private UserViewModel userViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_second, container, false);

        initDatePicker();
        dateButton = root.findViewById(R.id.dataPickerButton);
        buttonSave = root.findViewById(R.id.button_save);
        name_surname = root.findViewById(R.id.username);
        phone_number = root.findViewById(R.id.phoneNumber);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);


        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(), android.R.style.Theme_DeviceDefault_Dialog, dateSetListener, year, month, day);

                dialog.show();
            }
        });


        return root;
    }

    private void initDatePicker() {
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "OnDateSet : dd/mm/yyyy:  " + day +"/" + month + "/" + year);
                date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };
    }

    private String makeDateString(int day, int month, int year) {

        return month + "/" + day + "/" + year;
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (saveAndSendUser()) {
                    NavHostFragment.findNavController(SecondFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FirstFragment);
                }

            }
        });
    }

//    private void edit() {
//        name_surname.getEditText().setText(getArguments().getString("username"));
//
//        //userViewModel.update(user);
//    }

    private boolean saveAndSendUser() {
        String name = name_surname.getEditText().getText().toString().trim();
        String phone = phone_number.getEditText().getText().toString().trim();
        User user = new User(name, phone, date);

        if (name.isEmpty()) {
            name_surname.setError("Field can't be empty");
            return false;
        } else if (phone.isEmpty()) {
            phone_number.setError("Field can't be empty");
            return false;
        } else if (phone.length() < 9) {
            phone_number.setError("Phone number should be 9 character");
            return false;
        } else {
            userViewModel.insert(user);
            return true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}