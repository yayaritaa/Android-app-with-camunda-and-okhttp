package com.journaldev.okhttp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

import android.content.SharedPreferences;

//ctrl+alt+l to format code
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtString;
    Button asynchronousGet;
    EditText name, password;
    String Sname, Spassword;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;

    public String url = "http://bps.isiforge.tn:8080/engine-rest/process-definition?latest=true";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asynchronousGet = (Button) findViewById(R.id.asynchronousGet);
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        asynchronousGet.setOnClickListener(this);
        txtString = (TextView) findViewById(R.id.txtString);
    }


    void run(final String sn, final String sp) throws IOException {

        OkHttpClient client = new OkHttpClient.Builder()
                .authenticator(new Authenticator() {
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        if (response.request().header("Authorization") != null) {
                            return null; // Give up, we've already attempted to authenticate.
                        }

                        String credential = Credentials.basic(sn, sp);
                        return response.request().newBuilder()
                                .header("Authorization", credential)
                                .build();
                    }
                })
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();
                LoginActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = new Intent(LoginActivity.this, ListProssActivity.class);
                        intent.putExtra("EXTRA_ID", myResponse);
                        startActivity(intent);

                    }
                });
                LoginActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtString.setText(myResponse);

                    }
                });

            }
        });
    }

    @Override
    public void onClick(View view) {
        Sname = name.getText().toString();
        ;
        Spassword = password.getText().toString();
        switch (view.getId()) {
            case R.id.asynchronousGet:
                try {
                    sharedpreferences = getSharedPreferences(MyPREFERENCES, 0);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    // editor.putString(url, url);
                    editor.putString("sn", Sname);
                    editor.putString("sp", Spassword);
                    editor.commit();
                   // Toast.makeText(getApplicationContext(), "name should be    " + Sname, Toast.LENGTH_SHORT).show();
                   // Toast.makeText(getApplicationContext(), "password should be    " + Spassword, Toast.LENGTH_SHORT).show();
                    run(Sname, Spassword);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

        }


    }
}



