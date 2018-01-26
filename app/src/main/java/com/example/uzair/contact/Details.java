package com.example.uzair.contact;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.uzair.contact.Database.DBHelper;

public class Details extends AppCompatActivity {


    Button call, msg, delete, update;
    TextView tvPhone,tvName,tvEmail;

    DBHelper DB_Helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        DB_Helper = new DBHelper(this);

        final int id  = getIntent().getIntExtra("id",0);
        final String name  = getIntent().getStringExtra("name");
        final String phone  = getIntent().getStringExtra("phone");
        final String email  = getIntent().getStringExtra("email");

        assign_Ids();

        tvName.setText(name);
        tvPhone.setText(phone);
        tvEmail.setText(email);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+ tvPhone.getText()));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);



            }
        });

        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:" + Uri.encode(tvPhone.getText().toString())));
                startActivity(intent);


            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), UpdateContact.class);
                i.putExtra("id",id);
                i.putExtra("name",tvName.getText());
                i.putExtra("phone",tvPhone.getText());
                i.putExtra("email",tvEmail.getText());
                startActivity(i);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DB_Helper.delete(id);
                finish();


            }
        });

    }

    void assign_Ids()
    {
        delete = (Button) findViewById(R.id.details_delete_icon);
        update = (Button) findViewById(R.id.details_Update_icon);
        msg = (Button) findViewById(R.id.details_message_icon);
        call = (Button) findViewById(R.id.call_icon_details);
        tvPhone = (TextView)findViewById(R.id.tv_phone_number_details);
        tvName = (TextView)findViewById(R.id.tv_name_details);
        tvEmail = (TextView)findViewById(R.id.tv_email_details);
    }
}
