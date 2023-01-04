package com.example.curdoperation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,contact,dob;
    Button create1;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.editTextTextPersonName);
        contact =(EditText) findViewById(R.id.editTextTextPersonName4);
        dob = (EditText) findViewById(R.id.editTextTextPersonName5);
        create1 = (Button) findViewById(R.id.button5);
        Button update = (Button)findViewById(R.id.button6);
        Button delete = (Button)findViewById(R.id.button8);
        Button read = (Button)findViewById(R.id.button7);
        DB=new DBHelper(this);
        create1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT=name.getText().toString();
                String contactTXT=contact.getText().toString();
                String dobTXT=dob.getText().toString();
                Boolean checkinsertdata = DB.insertuserdatas(nameTXT,contactTXT,dobTXT);
                if(checkinsertdata == true)
                {
                    Toast.makeText(MainActivity.this, "data inserted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "failed to insert", Toast.LENGTH_SHORT).show();
                }

            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT=name.getText().toString();
                String contactTXT=contact.getText().toString();
                String dobTXT=dob.getText().toString();
                Boolean checkupdatedata = DB.updateuserdatas(nameTXT,contactTXT,dobTXT);
                if(checkupdatedata == true)
                {
                    Toast.makeText(MainActivity.this, "data updated", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "failed to update", Toast.LENGTH_SHORT).show();
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT=name.getText().toString();

                Boolean checkdeletedata = DB.deleteuserdatas(nameTXT);
                if(checkdeletedata == true)
                {
                    Toast.makeText(MainActivity.this, "row deleted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "failed to delete row", Toast.LENGTH_SHORT).show();
                }

            }
        });






        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getdata();
                if(res.getCount()==0)
                {
                    Toast.makeText(MainActivity.this, "no datas found", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext())
                {
                    buffer.append("name:"+res.getString(0)+"\n");
                    buffer.append("contact:"+res.getString(1)+"\n");
                    buffer.append("dob:"+res.getString(2)+"\n\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("user DEtails");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });







    }
}