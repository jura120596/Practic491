package com.example.practic491;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class UsersListActivity extends AppCompatActivity {
    UserAdapter userAdapter;
    DBUsers dbUsers;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        listView = findViewById(R.id.listView);
        dbUsers = new DBUsers(this);
        userAdapter = new UserAdapter(this, dbUsers.selectAll());
        listView.setAdapter(userAdapter);
    }
}
