package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {
    //create more variables here
    private EditText eRegName;
    private EditText eRegPassword;
    private EditText eRegcGPA;
    private EditText eRegpGPA;
    private EditText eRegaccCredits;
    private EditText eRegsemCredits;
    private Button eRegister;

    public static Credentials credentials;
    public static Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        eRegName = findViewById(R.id.etRegName);
        eRegPassword = findViewById(R.id.etRegPassword);
        eRegister = findViewById(R.id.btnRegister);
        eRegcGPA = findViewById(R.id.etRegcGPA);
        eRegpGPA = findViewById(R.id.etRegpGPA);
        eRegaccCredits = findViewById(R.id.etRegaccCredits);
        eRegsemCredits = findViewById(R.id.etRegsemCredits);

        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regUsername = eRegName.getText().toString();
                String regPassword = eRegPassword.getText().toString();
                Float regcGPA = Float.parseFloat(eRegcGPA.getText().toString());
                Float regpGPA = Float.parseFloat(eRegpGPA.getText().toString());
                Integer regaccCredits = Integer.parseInt(eRegaccCredits.getText().toString());
                Integer regsemCredits = Integer.parseInt(eRegsemCredits.getText().toString());

                if(validate(regUsername, regPassword)){
                    credentials = new Credentials(regUsername, regPassword);
                    profile = new Profile(regcGPA, regpGPA, regaccCredits, regsemCredits);
                    startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                    Toast.makeText(RegistrationActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }




    private boolean validate(String username, String password){
        if(username.isEmpty() || password.length() < 8){
            Toast.makeText(this, "Please enter all the details, password should be at least 8 characters!",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}