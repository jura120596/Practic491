package com.example.practic491;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private ArrayList<User> usersArray;

    public UserAdapter(Context ctx, ArrayList<User> arr) {
        mLayoutInflater = LayoutInflater.from(ctx);
        setArrayMyData(arr);
    }

    public ArrayList<User> getArrayMyData() {
        return usersArray;
    }

    public void setArrayMyData(ArrayList<User> arrayMyData) {
        this.usersArray = arrayMyData;
    }

    public int getCount() {
        return usersArray.size();
    }

    public Object getItem(int position) {

        return position;
    }

    public long getItemId(int position) {
        User user = usersArray.get(position);
        if (user != null) {
            return user.getId();
        }
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView = mLayoutInflater.inflate(R.layout.user_item, null);

        TextView name = (TextView) convertView.findViewById(R.id.name_item);
        TextView surname = (TextView) convertView.findViewById(R.id.surname_item);
        TextView email = (TextView) convertView.findViewById(R.id.email_item);


        User user = usersArray.get(position);
        name.setText(user.getName());
        surname.setText(user.getSurname());
        email.setText(user.getEmail());

        return convertView;
    }
}
