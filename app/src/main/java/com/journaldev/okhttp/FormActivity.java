package com.journaldev.okhttp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.ObjectUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.security.Identity;
import java.util.ArrayList;
import java.util.Iterator;

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

import java.lang.Object;
import java.util.List;

public class FormActivity extends AppCompatActivity {


    TextView textView;
    LinearLayout linear;
    Button Button;
    String textViewTag, editTextTag;
    List<TextView> tvList = new ArrayList<>();
    List<EditText> etList = new ArrayList<>();
    public String mykey;
    public String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        // textView = (TextView) findViewById(R.id.textView);
        Button = (Button) findViewById(R.id.button);

        String ID = this.getIntent().getExtras().getString("ID");
        mykey = this.getIntent().getExtras().getString("keyy");
        SharedPreferences prefs = getSharedPreferences("MyPrefs", 0);
        String restoredName = prefs.getString("sn", null);
        String restoredPass = prefs.getString("sp", null);

        try {
            // Toast.makeText(getApplicationContext(), "BECH NA3MEL RUN TAWA welyéééé", Toast.LENGTH_SHORT).show();

            run(restoredName, restoredPass, ID);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    void run(final String sn, final String sp, final String id) throws IOException {

        String url = "http://bps.isiforge.tn:8080/engine-rest/process-definition/" + id + "/form-variables";
        OkHttpClient client = new OkHttpClient.Builder()
                .authenticator(new Authenticator() {
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        //Toast.makeText(getApplicationContext(), " f west auth", Toast.LENGTH_SHORT).show();
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
                FormActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //textView.setText(myResponse);
                        int j = 0;
                        int i = 0;
                        try {

                            JSONObject jsonObject = new JSONObject(myResponse);
                            Iterator<?> keys = jsonObject.keys();

                            while (keys.hasNext()) {
                                String key = (String)keys.next();
                                String value = (String)jsonObject.getString(key);

                                Log.v("**********", "**********");
                                Log.v("category key", key);
                                Log.v("category value", value);

                                LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayout2);

                                // add text view
                                TextView tv = new TextView(FormActivity.this);
                                tv.setId(i + 1);
                                tv.setText(key);
                                ll.addView(tv);
                                tvList.add(tv);


                                // add edit text
                                EditText et = new EditText(FormActivity.this);
                                JSONObject insideObject = new JSONObject(value);
                                String placeholder = (String) insideObject.get("type");
                                String placeholder2 = (String) insideObject.get("value");

                                et.setId(j + 1);
                                et.setHint(placeholder);
                                et.setMinLines(1);
                                et.setMaxLines(3);
                                ll.addView(et);
                                etList.add(et);


                                Log.v("**********", "**********");
                                Log.v("category key", key);
                                Log.v("category value", value);


                            }
                            //button behaviour
                            Button.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    Log.v("**********", "button clicked***********************");
                                    //public void makeJSON(){
                                    JSONArray jArr = new JSONArray();
                                    JSONObject jObj = new JSONObject();
                                    String index1;
                                    String index2, index22, index11;


                                    try {
                                        for (int k = 0; k < tvList.size(); k++) {

                                            index1 = tvList.get(k).getText().toString();
                                            index2 = etList.get(k).getText().toString();
                                            JSONObject job = new JSONObject();
                                            job.put("type", "String");
                                            job.put("value", index2);
                                            job.put("valueInfo", "");


                                            Log.v("**********", "Let's see     " + index1 + "   index2    " + index2);
                                            //EditText ed = (EditText) fin
                                            //String text = ed.getText().toString();
                                            jObj.put(index1, job);
                                            //Toast.makeText(getApplicationContext(), "jobj heya"+, Toast.LENGTH_SHORT).show();
                                            Log.v("**********", "jObj " + jObj.toString());

                                        }

                                        jArr.put(jObj);

                                        if (jArr.length() > 0) {
                                            Log.v("**********", "tast");
                                            String postBody = jObj.toString();
                                            Intent detailIntent = new Intent(FormActivity.this, PostActivity.class);

                                            // 3
                                            detailIntent.putExtra("postBody", postBody);
                                            detailIntent.putExtra("keyy",mykey);
                                            detailIntent.putExtra("Id",ID);
                                            // 4
                                            startActivity(detailIntent);
                                           // SendJsonDataToServer(jArr, sn, sp, mykey);
                                            //call to async class


                                        } else {
                                            Log.v("**********", "try again");

                                        }

                                    } catch (Exception e) {
                                        System.out.println("Error:" + e);
                                    }


                                    // }

                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });


            }
        });
    }

}



