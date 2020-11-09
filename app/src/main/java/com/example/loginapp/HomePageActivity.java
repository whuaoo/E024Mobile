package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomePageActivity extends AppCompatActivity {
    private TextView ecGPA;
    private TextView epGPA;
    private TextView eaccCredits;
    private TextView esemCredits;
    private TextView eWelcome;
    private TextView estudentType;
    private Button eCalc;

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
        ecGPA.setText("Cumulative GPA: " + mPreferences.getString("cGPA"," "));
        epGPA = findViewById(R.id.tvpGPA);
        epGPA.setText("Projected GPA: " + mPreferences.getString("pGPA"," "));
        eaccCredits = findViewById(R.id.tvaccCredits);
        eaccCredits.setText("Accumulated Credits: " + mPreferences.getString("accCredits"," "));
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
    }

    public void csbrowser(View view){
        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse
                ("http://www.eee.ntu.edu.sg/Programmes/ProspectiveStudents/UG/IEM/Curriculum/Pages/Curriculum-Structure.aspx"));
        startActivity(browserIntent);
    }
}