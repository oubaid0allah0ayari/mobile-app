package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class findadd extends AppCompatActivity {


    ImageView mylist;
    ImageView addevent;
    ImageView findevent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findadd);
        mylist = findViewById(R.id.mylist);
        addevent = findViewById(R.id.addevent);
        findevent =findViewById(R.id.findevent);


        if( addevent!= null){
            addevent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(findadd.this, com.example.myapplication.add.class);
                    startActivity(intent);
                }
            });
        }else{
            Toast.makeText(findadd.this, "User not found", Toast.LENGTH_SHORT).show();
        }
        findevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(findadd.this, find.class);
                startActivity(intent);
            }
        });
        mylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(findadd.this, mylist.class);
                startActivity(intent);
            }
        });
    }
}