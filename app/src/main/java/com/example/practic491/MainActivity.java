package com.example.practic491;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button signUpButton;
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
    }

    public void signUpAction()
    {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    public void usersAction()
    {
        startActivity(new Intent(this, UsersListActivity.class));
    }
}
