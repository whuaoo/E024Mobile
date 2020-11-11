package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomePageActivity extends AppCompatActivity {
    private String TAG = "HomePage_Demo";
    private TextView ecGPA;
    private TextView epGPA;
    private TextView eaccCredits;
    private TextView esemCredits;
    private TextView eWelcome;
    private TextView estudentType;
    private Button eCalc, Logout;

    private String sharedPrefFile = "com.example.android.hellosharedprefs";
    SharedPreferences mPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);
        eWelcome = findViewById(R.id.tvWelcome);
        eWelcome.setText("Welcome " + mPreferences.getString("username"," "));
        ecGPA = findViewById(R.id.tvcGPA);
        eaccCredits = findViewById(R.id.tvaccCredits);

        Log.d(TAG, "Flag "+ mPreferences.getString("flag", " " ));

        if (mPreferences.getString("flag", " ").equals("true")){
            Double sem_credits =  Double.valueOf(mPreferences.getString("sem_credits"," "));
            Double sem_gpa = Double.valueOf(mPreferences.getString("sem_gpa"," "));
            Double accCredits = Double.valueOf(mPreferences.getString("accCredits"," "));
            Double old_cgpa = Double.valueOf(mPreferences.getString("cGPA"," "));
            Double new_cgpa = (sem_credits *sem_gpa + accCredits*old_cgpa)/(sem_credits+accCredits);

            Log.d(TAG, "New Cumulative GPA: "+String.format("%.2f", new_cgpa));
            ecGPA.setText("New Cumulative GPA: " + String.format("%.2f", new_cgpa));
            eaccCredits.setText("New Accumulated Credits: " + Double.toString(sem_credits+accCredits));
        }
        else{
            ecGPA.setText("Cumulative GPA: " + mPreferences.getString("cGPA"," "));
            eaccCredits.setText("Accumulated Credits: " + mPreferences.getString("accCredits"," "));
        }

        epGPA = findViewById(R.id.tvpGPA);
        epGPA.setText("Projected GPA: " + mPreferences.getString("pGPA"," "));


        esemCredits = findViewById(R.id.tvsemCredits);
        esemCredits.setText("Credits this semester: " + mPreferences.getString("semCredits"," "));
        estudentType = findViewById(R.id.tvstudentType);
        estudentType.setText("Student type: " + mPreferences.getString("type"," "));

        eCalc = findViewById(R.id.btnCalc);
        eCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this, GPACalculatorActivity.class);
                startActivity(intent);
            }
        });
        Logout = findViewById(R.id.btnLogout);
    }
    public void logout(View view){
        Toast.makeText(HomePageActivity.this, "You have successfully logged out, please log in again", Toast.LENGTH_SHORT).show();
        // Add the code to go to new activity
        Intent intent = new Intent(HomePageActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void csbrowser(View view){
        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse
                ("http://www.eee.ntu.edu.sg/Programmes/ProspectiveStudents/UG/IEM/Curriculum/Pages/Curriculum-Structure.aspx"));
        startActivity(browserIntent);
    }
}