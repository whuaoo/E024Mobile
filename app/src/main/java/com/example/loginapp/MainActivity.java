package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.util.Log;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Primitive;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String TAG = "DynamoDb_Demo";
    private TextView textView;
    private EditText eName;
    private EditText ePassword;
    private Button eLogin,UserProfile;
    private TextView eAttemptsInfo;

    private String Username = "Admin";
    private String Password = "12345678";

    boolean isValid = false;
    private int counter = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.btnLogin);
        eAttemptsInfo = findViewById(R.id.tvAttemptsInfo);
        UserProfile = findViewById(R.id.btn_userprofile);


        UserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Directing to User Profile page!", Toast.LENGTH_SHORT).show();

                // Add the code to go to new activity
                Intent intent = new Intent(MainActivity.this, UserprofileActivity.class);
                startActivity(intent);
            }});
        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputName = eName.getText().toString();
                String inputPassword = ePassword.getText().toString();

                if(inputName.isEmpty() || inputPassword.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please enter details correctly!", Toast.LENGTH_SHORT).show();
                }else{

                    isValid = validate(inputName, inputPassword);
                    if(!isValid){
                        counter--;
                        Toast.makeText(MainActivity.this, "Incorrect password!", Toast.LENGTH_SHORT).show();
                        eAttemptsInfo.setText("No. of attempts remaining: " + counter);

                        if(counter == 0){
                            eLogin.setEnabled(false);
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Log in successful!", Toast.LENGTH_SHORT).show();

                        // Add the code to go to new activity
                        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                        startActivity(intent);
                    }


                }
            }
        });
    }
    private boolean validate(String name,String password){
        if(name.equals(Username) && password.equals(Password)){
            return true;
        }
        return false;
    }
}