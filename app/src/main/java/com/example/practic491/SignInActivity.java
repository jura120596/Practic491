package com.example.practic491;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.practic491.services.HttpService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    EditText email;
    EditText password;
    Button signIn;
    SharedPreferences sharedPreferences;
    protected static final String TOKEN = "TOKEN";
    protected BroadcastReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password_login);
        signIn = findViewById(R.id.signIn);
        signIn.setOnClickListener(this);
        initReceiver(); //функция ниже
    }

    private void initReceiver()
    {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                try {
                    System.out.println(intent.getStringExtra(HttpService.INFO));
                    JSONObject json = new JSONObject(intent.getStringExtra(HttpService.INFO));
                    json = json.getJSONObject("data");
                    String token = json.getString("access_token");

                    sharedPreferences = getSharedPreferences("Auth",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(TOKEN, token);
                    editor.apply();

                } catch (JSONException e) {
                    Toast.makeText(SignInActivity.this, "Wrong JSON format", Toast.LENGTH_LONG).show();
                }
            }
        };

        registerReceiver(receiver, new IntentFilter(HttpService.CHANNEL));
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, HttpService.class);
        intent.putExtra("type", HttpService.SIGNIN_REQUEST_TYPE);
        intent.putExtra("email", email.getText().toString());
        intent.putExtra("password", password.getText().toString());
        startService(intent);
    }
}
