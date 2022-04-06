package com.example.mvvmproject;

import android.app.Application;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mvvmproject.databinding.FragmentFirstBinding;
import com.example.mvvmproject.databinding.FragmentLoginPageBinding;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;


public class LoginPage extends Fragment {

    private FragmentLoginPageBinding binding;
    private Button buttonLogIn;
    private TextInputLayout username;
    private TextInputLayout password;
    private UserViewModel userViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginPageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        buttonLogIn = view.findViewById(R.id.login);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getAllLogedUsers().observe(this, new Observer<List<LogInUser>>() {
            @Override
            public void onChanged(List<LogInUser> logInUsers) {
                if (!logInUsers.isEmpty()){
                    NavHostFragment.findNavController(LoginPage.this)
                            .navigate(R.id.action_LogIn_to_FirstFragment);
                }
            }
        });

        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validatePassword() | !validateUsername()) {
                    return;}

                else {
                    login(username,password);
                }
            }
        });



        return view;

    }

    public void login(TextInputLayout username, TextInputLayout password){
        String un = username.getEditText().getText().toString().trim();
        String pw = password.getEditText().getText().toString().trim();

        LogInUser logInUser = new LogInUser(un,pw);
        userViewModel.insert(logInUser);
        NavHostFragment.findNavController(LoginPage.this)
                .navigate(R.id.action_LogIn_to_FirstFragment);
    }

    private boolean validateUsername() {
        String usernameInput = username.getEditText().getText().toString().trim();

        if (usernameInput.isEmpty()) {
            username.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(usernameInput).matches()) {
            username.setError("Email is invalid");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = password.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            password.setError("Field can't be empty");
            return false;
        } else if (passwordInput.length() < 6) {
            password.setError("Password should be at least 6 digits");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}