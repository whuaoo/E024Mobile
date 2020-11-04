package com.example.loginapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;



public class UserprofileActivity extends AppCompatActivity {
    private String TAG = "DynamoDb_Demo";
    private TextView username_display;
    private TextView usertype_display;
    private TextView usergpa_display;
    private TextView accum_credits;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        Button buttonGetItem = findViewById(R.id.button3);
        username_display = findViewById(R.id.textView);
        usertype_display = findViewById(R.id.textView2);
        usergpa_display = findViewById(R.id.textView3);
        accum_credits = findViewById(R.id.textView4);

        buttonGetItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Getting all devices...");
                username_display.setText("Getting all devices...");
                GetAllItemsAsyncTask getAllDevicesTask = new GetAllItemsAsyncTask();
                getAllDevicesTask.execute();
            }
        });
    }
    private class GetAllItemsAsyncTask extends AsyncTask<Void, Void, Document> {
        @Override
        protected Document doInBackground(Void... params) {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(UserprofileActivity.this);
            Log.d(TAG, "databases content"+databaseAccess.getById(2).toString());
            return databaseAccess.getById(2);
        }

        @Override
        protected void onPostExecute(Document document) {


            JSONObject student = null;
            try {
                student = new JSONObject(document.toString());
                JSONObject user_name = new JSONObject( student.get("user_name").toString());
                username_display.setText(user_name.getString("value"));

                JSONObject student_type = new JSONObject( student.get("Type").toString());
                usertype_display.setText(student_type.getString("value"));

                JSONObject student_gpa = new JSONObject( student.get("proj_gpa").toString());
                usergpa_display.setText(student_gpa.getString("value"));

                JSONObject student_credits = new JSONObject( student.get("accum_credits").toString());
                accum_credits.setText(student_credits.getString("value"));


            } catch (JSONException e) {
                e.printStackTrace();
            }

                // usertype_display.setText(student.get("type").toString());
                // usergpa_display.setText(student.get("proj_gpa").toString());
                //  accum_credits.setText(student.get("accum_credits").toString());


        }

    }
}