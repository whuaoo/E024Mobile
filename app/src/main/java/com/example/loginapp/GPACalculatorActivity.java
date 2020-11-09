package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GPACalculatorActivity extends AppCompatActivity{
    Button btn;
    EditText e1, e2, e3, e4, e5, f1, f2, f3, f4, f5;
    TextView tView, tViewWarning;
    int count = 0;
    View view;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_a_calculator);

        view = this.getWindow().getDecorView();
        view.setBackgroundResource(R.color.blue);

        btn = (Button) findViewById(R.id.b);
        e1 = (EditText) findViewById(R.id.e1);
        e2 = (EditText) findViewById(R.id.e2);
        e3 = (EditText) findViewById(R.id.e3);
        e4 = (EditText) findViewById(R.id.e4);
        e5 = (EditText) findViewById(R.id.e5);
        f1 = (EditText) findViewById(R.id.f1);
        f2 = (EditText) findViewById(R.id.f2);
        f3 = (EditText) findViewById(R.id.f3);
        f4 = (EditText) findViewById(R.id.f4);
        f5 = (EditText) findViewById(R.id.f5);
        tView = (TextView) findViewById(R.id.t);
        tViewWarning = (TextView) findViewById(R.id.warning);
        tView.setBackgroundColor(Color.TRANSPARENT);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float g1, g2, g3, g4, g5, h1, h2, h3, h4, h5;
                if (count % 2 == 0) {
                    if (e1.getText().toString().equals("") && e2.getText().toString().equals("") && e3.getText().toString().equals("") && e4.getText().toString().equals("") && e5.getText().toString().equals("")) {
                        tViewWarning.setText("Please Enter your Grades");
                        tViewWarning.setTextColor(Color.rgb(255,0,0));

                    } else {
                        if (e1.getText().toString().equals("") || f1.getText().toString().equals(""))
                        {
                            g1 = 0; h1 = 0;}
                        else {
                            g1 = Float.parseFloat(e1.getText().toString());
                            h1 = Float.parseFloat(f1.getText().toString());}

                        if (e2.getText().toString().equals("") || f2.getText().toString().equals(""))
                        {
                            g2 = 0; h2 = 0;}
                        else {
                            g2 = Float.parseFloat(e2.getText().toString());
                            h2 = Float.parseFloat(f2.getText().toString());}

                        if (e3.getText().toString().equals("") || f3.getText().toString().equals(""))
                        {
                            g3 = 0; h3 = 0;}
                        else {
                            g3 = Float.parseFloat(e3.getText().toString());
                            h3 = Float.parseFloat(f3.getText().toString());}

                        if (e4.getText().toString().equals("") || f4.getText().toString().equals(""))
                        {
                            g4 = 0; h4 = 0;}
                        else {
                            g4 = Float.parseFloat(e4.getText().toString());
                            h4 = Float.parseFloat(f4.getText().toString());}

                        if (e5.getText().toString().equals("") || f5.getText().toString().equals(""))
                        {
                            g5 = 0; h5 = 0;}
                        else {
                            g5 = Float.parseFloat(e5.getText().toString());
                            h5 = Float.parseFloat(f5.getText().toString());}


                        float average = g1*h1 + g2*h2 + g3*h3 + g4*h4 + g5*h5;
                        average = average / (h1 + h2 + h3 + h4 + h5);
                        if (average <= 3) {
                            tView.setText(Float.toString(average));
                            view.setBackgroundResource(R.color.red);
                        } else if (average > 3 && average < 4) {
                            tView.setText(Float.toString(average));
                            view.setBackgroundResource(R.color.yellow);
                        } else if (average >= 4 && average <= 5) {
                            tView.setText(Float.toString(average));
                            view.setBackgroundResource(R.color.green);
                        }
                        count++;
                        btn.setText("Clear Form");
                        tViewWarning.setText("");
                    }

                } else {
                    e1.setText("");
                    e2.setText("");
                    e3.setText("");
                    e4.setText("");
                    e5.setText("");
                    f1.setText("");
                    f2.setText("");
                    f3.setText("");
                    f4.setText("");
                    f5.setText("");

                    tView.setText("");
                    view.setBackgroundResource(R.color.blue);
                    btn.setText("Compute GPA");
                    count++;
                }

            }
        });

    }
}