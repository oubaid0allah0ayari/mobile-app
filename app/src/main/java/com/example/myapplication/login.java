package com.example.myapplication;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    EditText loginemail,loginpassword;
    Button loginbtn;

    FirebaseDatabase database;
    DatabaseReference reference;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView btn = findViewById(R.id.textViewSignUp);
        loginemail =findViewById(R.id.title_input);
        loginpassword =findViewById(R.id.date_input);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        loginbtn =findViewById(R.id.btnlogin);

        String savedUsername = sharedPreferences.getString("username", "");
        String savedPassword = sharedPreferences.getString("password", "");
        loginemail.setText(savedUsername);
        loginpassword.setText(savedPassword);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateemail() | !validatepassword()){
                    Toast.makeText(login.this,"Invalid username or password",Toast.LENGTH_LONG).show();

                }else{
                    checkuser(loginemail.getText().toString());
                }

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public Boolean validateemail(){
        String val=loginemail.getText().toString();
        if(val.isEmpty()){
            loginemail.setError("email cannot be empty");
            return false ;
        }else {
            loginemail.setError(null);
            return true ;
        }
    }
    public Boolean validatepassword(){
        String val=loginpassword.getText().toString();
        if(val.isEmpty()){
            loginpassword.setError("password cannot be empty");
            return false ;
        }else {
            loginpassword.setError(null);
            return true ;
        }
    }
    public void checkuser(String username){
        String userUsername=loginpassword.getText().toString().trim();
        String userPassword=loginemail.getText().toString().trim();
        reference.orderByChild("name").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Helper helper = snapshot.getValue(Helper.class);
                        if (helper != null) {
                            String Username = helper.getName();
                            String Password = helper.getPassword();
                            if(userUsername.equals(Username) && userPassword.equals(Password)){


                            }else{
                                loginemail.setError("Invalid username or password");
                                Intent intent = new Intent(login.this, findadd.class);
                                startActivity(intent);

                            }
                        }else{
                            Toast.makeText(login.this, "User not found", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}