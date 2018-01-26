package com.example.uzair.contact;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uzair.contact.Database.DBHelper;

public class AddContact extends AppCompatActivity{
    DBHelper DB_Helper;
    EditText input_name, input_ph, input_email;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        DB_Helper = new DBHelper(this);
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        assign_Ids();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!input_name.getText().toString().isEmpty() || !input_ph.getText().toString().isEmpty() || !input_email.getText().toString().isEmpty())
                {

                    long rid = DB_Helper.insert(input_name.getText().toString(), input_ph.getText().toString(), input_email.getText().toString());
                    Cursor b = DB_Helper.read(rid);
                    String[] rows = new String[b.getCount()];
                    while (b.moveToNext()) {
                        rows[b.getPosition()] = "ID: " + b.getInt(b.getColumnIndex(DBHelper.ID))
                                + "\n" + "NAME: " + b.getString(b.getColumnIndex(DBHelper.NAME));
                    }
//
                    b.close();

                    finish();

                }
                else
                {



                    // set title
                   alert.setTitle("Empty Fields!");

                    // set dialog message
                    alert
                            .setMessage("Please Fill all the fields")
                            .setCancelable(true);

                    // create alert dialog
                    AlertDialog alertDialog = alert.create();

                    // show it
                    alertDialog.show();


                }




            }
        });

    }

    void assign_Ids()
    {
        input_name = (EditText) findViewById(R.id.input_name);
        input_ph = (EditText) findViewById(R.id.input_ph);
        input_email = (EditText) findViewById(R.id.input_email);
        save = (Button)findViewById(R.id.save_button);

    }

}

