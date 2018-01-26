package com.example.uzair.contact;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.uzair.contact.Database.DBHelper;

public class UpdateContact extends AppCompatActivity {

    EditText et1, et2, et3, et4;

    DBHelper DB_Helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        DB_Helper = new DBHelper(this);

        final int id  = getIntent().getIntExtra("id",0);
        final String name  = getIntent().getStringExtra("name");
        final String phone  = getIntent().getStringExtra("phone");
        final String email  = getIntent().getStringExtra("email");

        et1 = (EditText) findViewById(R.id.update_name);
        et2 = (EditText) findViewById(R.id.update_ph);
        et3 = (EditText) findViewById(R.id.update_email);

        et1.setText(name);
        et2.setText(phone);
        et3.setText(email);

        DB_Helper.update(id,et1.getText().toString(),et2.getText().toString(),et3.getText().toString());


    }
}
