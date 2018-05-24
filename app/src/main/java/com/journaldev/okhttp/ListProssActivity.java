package com.journaldev.okhttp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class ListProssActivity extends AppCompatActivity {

    Gson gson;
    ListView listview;

    CustomAdapter adapter;
    TextView txtID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listview = (ListView) findViewById(R.id.listview);

        Bundle extras = getIntent().getExtras();

        //if i have result
        if (extras != null) {
            String datas = extras.getString("EXTRA_ID");
            System.out.print(datas);
            if (datas != null) {
                Type type = new TypeToken<List<Process>>() {
                }.getType();
                gson = new Gson();
                final List<Process> plspross = (List<Process>) gson.fromJson(datas, type);
                adapter = new CustomAdapter(plspross, this);
                listview.setAdapter(adapter);

                //lorsque on click sur un item de la liste

                final Context context = this;
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    //parent: The view where the selection happens -- in your case, it's the ListView
                    //view: The selected view (row) within the ListView
                    //position: The position of the row in the adapter
                    //id: The row id of the selected item
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // 1
                        Process selectedPross = plspross.get(position);

                        // 2
                        Intent detailIntent = new Intent(context, FormActivity.class);

                        // 3
                        detailIntent.putExtra("ID", selectedPross.getId());
                        detailIntent.putExtra("keyy",selectedPross.getKey());

                        // 4
                        startActivity(detailIntent);
                    }

                });

            }

        }
    }


}
