package com.journaldev.okhttp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;

public class PostActivity extends AppCompatActivity {
    public String mykey, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        String postBody = this.getIntent().getExtras().getString("postBody");
        mykey = this.getIntent().getExtras().getString("keyy");
        id = this.getIntent().getExtras().getString("ID");

        SharedPreferences prefs = getSharedPreferences("MyPrefs", 0);
        String restoredName = prefs.getString("sn", null);
        String restoredPass = prefs.getString("sp", null);
        try {
            run(postBody, restoredName, restoredPass, mykey);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //SendJsonDataToServer(postBody,restoredName ,restoredPass, id);


    }

    void run(String postBody, String name, String password, String mykey) throws IOException {

        Log.v("**********", "post");
        //String postBody = jArr.toString();
        Log.v("**********", "affichage de postBody *********************  " + postBody);
        Log.v("**********", "the id *********************  " + id);


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://bps.isiforge.tn:8080/engine-rest/process-definition/key/"+mykey+"/submit-form")
                .addHeader("Authorization", Credentials.basic(name, password))
                .post(RequestBody.create(
                        MediaType.parse("application/json; charset=utf-8"), postBody))
                .build();
        Log.v("**********", "the request *********************  " + request.toString());

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final Response myResponse = response;
                PostActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        int resp = myResponse.code();
                        if (resp == 200) {
                            Toast.makeText(getApplicationContext(), " Le post est réussit ", Toast.LENGTH_SHORT).show();

                        } else {

                            Toast.makeText(getApplicationContext(), " Le post a échoué "+resp , Toast.LENGTH_SHORT).show();


                        }

                    }
                });


            }
        });
       /* ****************
        Call call = client.newCall(request);
        Response response = null;
        try {
            Log.v("**********", "execute");
            response = call.execute();
            Log.v("**********", "response est" + response);
        } catch (IOException e) {
            e.printStackTrace();
        }


        int resp = response.code();
        if (resp == 204) {
            Toast.makeText(getApplicationContext(), " Le post est réussit ", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(getApplicationContext(), " Le post a échoué ", Toast.LENGTH_SHORT).show();


        }

    }*/
    }
}

/*
    private void SendJsonDataToServer(String postBody, String name, String password, String id) {

        Log.v("**********", "d5al lel post houni");
        //String postBody = jArr.toString();
        Log.v("**********", "affichage de postBody *********************  " + postBody);
        Log.v("**********", "the id *********************  " + id);


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("Authorization", Credentials.basic(name, password))
                .post(RequestBody.create(
                        MediaType.parse("text/x-markdown; charset=utf-8"), postBody))
                .build();


        Call call = client.newCall(request);
        Response response = null;
        try {
            Log.v("**********", "execute");
            response = call.execute();
            Log.v("**********", "response est" + response);
        } catch (IOException e) {
            e.printStackTrace();
        }


        int resp = response.code();
        if (resp == 204) {
            Toast.makeText(getApplicationContext(), " Le post est réussit ", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(getApplicationContext(), " Le post a échoué ", Toast.LENGTH_SHORT).show();


        }

    }
}

*/
