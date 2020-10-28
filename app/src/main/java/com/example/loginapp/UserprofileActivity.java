package com.example.loginapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;

public class UserprofileActivity extends AppCompatActivity {
    private String TAG = "DynamoDb_Demo";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        Button buttonGetItem = findViewById(R.id.button3);
        textView = findViewById(R.id.textView);
        buttonGetItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Getting all devices...");
                textView.setText("Getting all devices...");
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
        protected void onPostExecute(Document documents) {
        }

    }
}