package com.example.practic491;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practic491.services.HttpService;

public class ServerSignUpActivity extends AppCompatActivity implements View.OnClickListener {
    EditText name;
    EditText surname;
    EditText email;
    Button signUpConfirm;
    DBUsers dbUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.server_activity_sign_up);
        name = findViewById(R.id.serverName);
        surname = findViewById(R.id.serverSurname);
        email = findViewById(R.id.serverEmail);
        signUpConfirm = findViewById(R.id.serverSignUpConfirm);
        signUpConfirm.setOnClickListener(this);
        dbUsers = new DBUsers(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, HttpService.class);
        stopService(intent);
    }

    @Override
    public void onClick(View v) {
        if (name.getText().toString().isEmpty()
                || surname.getText().toString().isEmpty()
                || email.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.emptyField, Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, HttpService.class);
        intent.putExtra("type", HttpService.SIGNUP_REQUEST_TYPE);
        intent.putExtra("name", name.getText().toString());
        intent.putExtra("surname", surname.getText().toString());
        intent.putExtra("email", email.getText().toString());
        startService(intent);
    }
}
