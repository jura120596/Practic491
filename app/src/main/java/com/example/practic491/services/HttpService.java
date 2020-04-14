package com.example.practic491.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

import com.example.practic491.helpers.ParameterStringBuilder;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HttpService extends Service {
    public static final String CHANNEL = "HTTP_SERVICE";
    public static final String INFO = "INFO";
    public static final int SIGNIN_REQUEST_TYPE = 1;
    public static final int SIGNUP_REQUEST_TYPE = 2;

    @Override
    public void onCreate() {
        // сообщение о создании службы
        Toast.makeText(this, "Service created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // сообщение о запуске службы
        Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();
        int type = intent.getIntExtra("type", 0);
        if (type == SIGNUP_REQUEST_TYPE) {
            SignUpTask t = new SignUpTask();
            t.execute(intent.getStringExtra("name"),
                    intent.getStringExtra("surname"),
                    intent.getStringExtra("email"));
        } else if (type == SIGNIN_REQUEST_TYPE){
            SignInTask t = new SignInTask();
            t.execute(intent.getStringExtra("email"),
                    intent.getStringExtra("password"));
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        //сообщение об остановке службы
        Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //поток работы с сетью
    private class SignUpTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String aVoid) {
            Intent i = new Intent(CHANNEL); // интент для отправки ответа
            i.putExtra(INFO, aVoid); // добавляем в интент данные
            sendBroadcast(i); // рассылаем
        }

        @Override
        protected String doInBackground(String... args) {
            String result = "";
            try {
                //загружаем данные
                URL url = new URL("http://sdsdsd34.ngrok.io/api/auth/employee/signup");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");

                Map<String, String> parameters = new HashMap<>();
                parameters.put("role", 1 + "");
                parameters.put("name", args[0]);
                parameters.put("surname", args[1]);
                parameters.put("email", args[2]);//name=NAME&surname=SURNAME&email=EMAIL\
                System.out.println(args[0] + args[1] + args[2]);
                con.setDoOutput(true);
                DataOutputStream out = new DataOutputStream(con.getOutputStream());
                out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
                out.flush();
                out.close();

                int status = con.getResponseCode();
                System.out.println("RESPONSE " + status);

                Scanner in = new Scanner((InputStream) con.getInputStream());
                result = in.nextLine();
                in.close();

            } catch (Exception e) {
                result = e.toString();
            }
            return result;
        }
    }
    private class SignInTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String aVoid) {
            Intent i = new Intent(CHANNEL); // интент для отправки ответа
            i.putExtra(INFO, aVoid); // добавляем в интент данные
            sendBroadcast(i); // рассылаем
        }

        @Override
        protected String doInBackground(String... args) {
            String result = "";
            try {
                //загружаем данные
                URL url = new URL("http://sdsdsd34.ngrok.io/api/auth/login");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                Map<String, String> parameters = new HashMap<>();
                parameters.put("email", args[0]);
                parameters.put("password", args[1]);
                System.out.println(args[0] + args[1]);
                con.setDoOutput(true);
                DataOutputStream out = new DataOutputStream(con.getOutputStream());
                out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
                out.flush();
                out.close();

                int status = con.getResponseCode();
                System.out.println("RESPONSE " + status);

                Scanner in = new Scanner((InputStream) con.getInputStream());
                result = in.nextLine();
                in.close();
                System.out.println(result);

            } catch (Exception e) {
                result = e.toString();
            }
            return result;
        }
    }
}
