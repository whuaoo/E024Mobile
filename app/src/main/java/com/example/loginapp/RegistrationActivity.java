package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationActivity extends AppCompatActivity {
    //create more variables here
    private EditText eRegName;
    private EditText eRegPassword;
    private EditText eRegcGPA;
    private EditText eRegpGPA;
    private EditText eRegaccCredits;
    private EditText eRegsemCredits;
    private Button eRegister;
    public String regUsername;
    public SharedPreferences mPreferences;
    public String sharedPrefFile = "com.example.android.hellosharedprefs";
    public static Credentials credentials;
    public static Profile profile;
    public Document newstudent;
    private String TAG = "Dynamodb_register";

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
        mPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);
        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regUsername = eRegName.getText().toString();
                String regPassword = eRegPassword.getText().toString();
                Float regcGPA = Float.parseFloat(eRegcGPA.getText().toString());
                Float regpGPA = Float.parseFloat(eRegpGPA.getText().toString());
                Integer regaccCredits = Integer.parseInt(eRegaccCredits.getText().toString());
                Integer regsemCredits = Integer.parseInt(eRegsemCredits.getText().toString());
                newstudent = new Document();
                newstudent.put("username",regUsername);
                newstudent.put("pass",regPassword);
                newstudent.put("cur_gpa",regcGPA);
                newstudent.put("proj_gpa",regpGPA);
                newstudent.put("accum_credits",regaccCredits);
                newstudent.put("sem_credit",regsemCredits);
                newstudent.put("Type","JC Student");



                RegitserAsyncTask regitserAsyncTaskk = new RegitserAsyncTask();

                if(validate(regUsername, regPassword)){
                    credentials = new Credentials(regUsername, regPassword);
                    profile = new Profile(regcGPA, regpGPA, regaccCredits, regsemCredits);
                    regitserAsyncTaskk.execute();


                }
            }
        });
    }

    private class RegitserAsyncTask extends AsyncTask<Void, Void, Document> {
        @Override
        protected Document doInBackground(Void... params) {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(RegistrationActivity.this);
            Log.d(TAG, "databases content"+newstudent.toString());
            databaseAccess.create(newstudent);
            return databaseAccess.getByName(regUsername);
        }

        @Override
        protected void onPostExecute(Document document) {

            JSONObject student = null;
            try {
                student = new JSONObject(document.toString());
                JSONObject username = new JSONObject(student.get("username").toString());
                JSONObject cgpa = new JSONObject(student.get("cur_gpa").toString());
                JSONObject pgpa = new JSONObject(student.get("proj_gpa").toString());
                JSONObject acccredits = new JSONObject(student.get("accum_credits").toString());
                JSONObject semcredits = new JSONObject(student.get("sem_credit").toString());
                JSONObject type = new JSONObject(student.get("Type").toString());
                //save student's info into sharedpreference

                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                preferencesEditor.putString("username", username.getString("value"));
                preferencesEditor.putString("cGPA", cgpa.getString("value"));
                preferencesEditor.putString("pGPA", pgpa.getString("value"));
                preferencesEditor.putString("accCredits", acccredits.getString("value"));
                preferencesEditor.putString("semCredits", semcredits.getString("value"));
                preferencesEditor.putString("type", type.getString("value"));
                preferencesEditor.apply();
                startActivity(new Intent(RegistrationActivity.this, HomePageActivity.class));
                Toast.makeText(RegistrationActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }



        }

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