package com.example.uzair.contact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.uzair.contact.Adapters.DataAdapter;
import com.example.uzair.contact.Database.DBHelper;
import com.example.uzair.contact.Models.ContactInfo;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  {

    DataAdapter dataAdapter;
    RecyclerView recyclerView;
    DBHelper DB_Helper;
    Button addNew_button;

    ArrayList<ContactInfo> ci;
    TextView tv_no_record;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB_Helper = new DBHelper(this);
        assign_Ids();
        tv_no_record.setVisibility(View.INVISIBLE);
        addNew_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), AddContact.class);
                        startActivity(i);

                    }
                }
        );


        recyclerView = (RecyclerView) findViewById(R.id.recyler_view_main);
        ci = DB_Helper.getAll();
        if (ci.size() <= 0) {
            tv_no_record.setVisibility(View.VISIBLE);

        } else {
            tv_no_record.setVisibility(View.INVISIBLE);
            dataAdapter = new DataAdapter(this, ci);

            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(llm);

            recyclerView.setAdapter(dataAdapter);

        }
    }

    void assign_Ids() {
        addNew_button = (Button) findViewById(R.id.addContact_button);
        tv_no_record = (TextView)findViewById(R.id.no_value_tv);

    }


}


