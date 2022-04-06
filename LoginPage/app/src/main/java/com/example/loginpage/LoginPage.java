package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;

public class LoginPage extends AppCompatActivity {

    private TextInputLayout username;
    private TextInputLayout password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
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
            password.setError("Password is too short");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }


    public void confirmInput(View v) {

        if (!validatePassword() | !validateUsername()) {
            return;
        } else {
            Intent intent = new Intent(LoginPage.this, EmployeList.class);
            startActivity(intent);
        }

    }
}


