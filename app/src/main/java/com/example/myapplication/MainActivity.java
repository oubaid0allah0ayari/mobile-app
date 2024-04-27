package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Helper;
import com.example.myapplication.R;
import com.example.myapplication.findadd;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText email, name, password, passwordConfirm;
    TextView login;
    FirebaseDatabase database;
    DatabaseReference reference;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");


        email = findViewById(R.id.title_input);
        name = findViewById(R.id.inputUsername);
        password = findViewById(R.id.date_input);
        passwordConfirm = findViewById(R.id.inputConformPassword);
        register = findViewById(R.id.btnRegister);
        login = findViewById(R.id.textViewSignUp);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, login.class));
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate()) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields correctly!", Toast.LENGTH_LONG).show();
                    return;
                }

                String rName = name.getText().toString().trim();
                String rEmail = email.getText().toString().trim();
                String rPassword = password.getText().toString().trim();
                String rPasswordConfirm = passwordConfirm.getText().toString().trim();

                if (rPassword.equals(rPasswordConfirm)) {
                    Helper helper = new Helper(rEmail, rPassword, rName);
                    reference.child(rName).setValue(helper);
                    Toast.makeText(MainActivity.this, "Registration successful!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, findadd.class));
                } else {
                    Toast.makeText(MainActivity.this, "Passwords do not match!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public Boolean validate() {
        String valEmail = email.getText().toString().trim();
        String valName = name.getText().toString().trim();
        String valPassword = password.getText().toString().trim();
        String valPasswordConfirm = passwordConfirm.getText().toString().trim();


        String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";


        String passwordPattern = "^(?=.*[0-9]).{8,}$";

        if (valEmail.isEmpty() || valName.isEmpty() || valPassword.isEmpty() || valPasswordConfirm.isEmpty()) {

            if (valEmail.isEmpty()) {
                email.setError("Email is required");
            }
            if (valName.isEmpty()) {
                name.setError("Username is required");
            }
            if (valPassword.isEmpty()) {
                password.setError("Password is required");
            }
            if (valPasswordConfirm.isEmpty()) {
                passwordConfirm.setError("Please confirm password");
            }
            return false;
        } else if (!valEmail.matches(emailPattern)) {

            email.setError("Invalid email format");
            return false;
        } else if (!valPassword.matches(passwordPattern)) {
            password.setError("Password must be at least 8 characters long with at least one uppercase letter, one number, and one special character");
            return false;
        }
        return true;
    }

}
