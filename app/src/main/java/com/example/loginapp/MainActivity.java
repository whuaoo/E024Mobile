package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String TAG = "Login_Demo";
    private TextView textView;
    private EditText eName;
    private EditText ePassword;
    private Button eLogin,UserProfile;
    private TextView eAttemptsInfo,eRegister;


    private int counter = 5;
    private String inputPassword,inputName;

    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.android.hellosharedprefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.btnLogin);
        eAttemptsInfo = findViewById(R.id.tvAttemptsInfo);
        eRegister = findViewById(R.id.tvRegister);
        mPreferences = getSharedPreferences(
                sharedPrefFile, MODE_PRIVATE);

        //register button function
        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });

        //login button function
        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inputName = eName.getText().toString();
                inputPassword = ePassword.getText().toString();
                Log.d(TAG, "User input content "+ inputPassword);

                if(inputName.isEmpty() || inputPassword.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please enter details correctly!", Toast.LENGTH_SHORT).show();
                }else{

                    ValidateUserAsyncTask LoginUserTask = new ValidateUserAsyncTask();
                    LoginUserTask.execute();
                    //if userinfo returned equals to inputpass, flag is true
                    //else falg is false



                }
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();


    }

    private class ValidateUserAsyncTask extends AsyncTask<String, Void, Document> {
        @Override
        protected Document doInBackground(String... params) {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(MainActivity.this);
            Log.d(TAG, "Send Username content "+ inputName);
            return databaseAccess.getByName(inputName);

        }

        @Override
        protected void onPostExecute(Document document) {

            JSONObject student = null;
            try {
                student = new JSONObject(document.toString());
                JSONObject password = new JSONObject(student.get("pass").toString());
                JSONObject cgpa = new JSONObject(student.get("cur_gpa").toString());
                JSONObject pgpa = new JSONObject(student.get("proj_gpa").toString());
                JSONObject acccredits = new JSONObject(student.get("accum_credits").toString());
                JSONObject semcredits = new JSONObject(student.get("sem_credit").toString());
                JSONObject type = new JSONObject(student.get("Type").toString());
                //save student's info into sharedpreference
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                preferencesEditor.putString("username", inputName);
                preferencesEditor.putString("cGPA", cgpa.getString("value"));
                preferencesEditor.putString("pGPA", pgpa.getString("value"));
                preferencesEditor.putString("accCredits", acccredits.getString("value"));
                preferencesEditor.putString("semCredits", semcredits.getString("value"));
                preferencesEditor.putString("type", type.getString("value"));
                preferencesEditor.apply();

                Log.d(TAG, "inputPassword content "+ inputPassword);
                Log.d(TAG, "retrieved password content "+ password.getString("value"));
                if(inputPassword.equals(password.getString("value"))) {

                    Log.d(TAG, "Login success");
                        Toast.makeText(MainActivity.this, "Log in successful!", Toast.LENGTH_SHORT).show();
                        // Add the code to go to new activity
                        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                        startActivity(intent);

                }
                else {
                    counter--;
                    Toast.makeText(MainActivity.this, "Incorrect password!", Toast.LENGTH_SHORT).show();
                    eAttemptsInfo.setText("No. of attempts remaining: " + counter);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
}