package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add extends AppCompatActivity {
    Button btn;
    EditText date, location, title, description;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        location = findViewById(R.id.location_input);
        title = findViewById(R.id.title_input);
        description = findViewById(R.id.description_input);
        date = findViewById(R.id.date_input);
        btn = findViewById(R.id.add);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("events");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Location = location.getText().toString().trim();
                String Date = date.getText().toString().trim();
                String Title = title.getText().toString().trim();
                String Description = description.getText().toString().trim();

                if (Location.isEmpty()) {
                    location.setError("Location is required!");
                    location.requestFocus();
                    return;
                }

                if (Title.isEmpty()) {
                    title.setError("Title is required!");
                    title.requestFocus();
                    return;
                }

                if (Description.isEmpty()) {
                    description.setError("Description is required!");
                    description.requestFocus();
                    return;
                }

                if (!isValidDateFormat(Date)) {
                    date.setError("Invalid date format!");
                    date.requestFocus();
                    return;
                }

                Event event = new Event(Title, Date, Location, Description);
                reference.child(Title).setValue(event);
                Toast.makeText(add.this, "Event added successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(add.this, findadd.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean isValidDateFormat(String date) {

        String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(19|20)\\d\\d$";
        return date.matches(regex);
    }
}
