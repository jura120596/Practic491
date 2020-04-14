package com.example.practic491;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button signUpButton;
    Button signInButton;
    Button serverSignUpButton;
    Button usersListButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpAction();
            }
        });
        usersListButton = findViewById(R.id.usersListButton);
        usersListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usersAction();
            }
        });
        signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInAction();
            }
        });
        serverSignUpButton = findViewById(R.id.serverSignUpButton);
        serverSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serverSignUpAction();
            }
        });
    }

    public void signUpAction()
    {
        startActivity(new Intent(this, DBSignUpActivity.class));
    }

    public void usersAction()
    {
        startActivity(new Intent(this, UsersListActivity.class));
    }
    public void serverSignUpAction()
    {
        startActivity(new Intent(this, ServerSignUpActivity.class));
    }
    public void signInAction()
    {
        startActivity(new Intent(this, SignInActivity.class));
    }
}
