package com.example.practic491;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    EditText name;
    EditText surname;
    EditText email;
    EditText password;
    Button signUpConfirm;
    DBUsers dbUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signUpConfirm = findViewById(R.id.signUpConfirm);
        signUpConfirm.setOnClickListener(this);
        dbUsers = new DBUsers(this);
    }

    @Override
    public void onClick(View v) {
        if (name.getText().toString().isEmpty()
                || surname.getText().toString().isEmpty()
                || email.getText().toString().isEmpty()
                || password.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.emptyField, Toast.LENGTH_SHORT).show();
            return;
        }
        if (dbUsers.insert(name.getText().toString(), surname.getText().toString(),
                email.getText().toString(), password.getText().toString()) > 0) {
            Toast.makeText(this, R.string.successMessage, Toast.LENGTH_SHORT).show();
            name.setText("");
            surname.setText("");
            email.setText("");
            password.setText("");
        } else {
            Toast.makeText(this, R.string.errorMessage, Toast.LENGTH_SHORT).show();
        }
    }
}
