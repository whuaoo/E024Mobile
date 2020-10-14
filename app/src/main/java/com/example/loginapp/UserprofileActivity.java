package com.example.loginapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Primitive;
import java.util.List;

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
    private class GetAllItemsAsyncTask extends AsyncTask<Void, Void, List<Document>> {
        @Override
        protected List<Document> doInBackground(Void... params) {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(UserprofileActivity.this);
            Log.d(TAG, "databases content"+databaseAccess.getAllItems().toString());
            return databaseAccess.getAllItems();
        }

        @Override
        protected void onPostExecute(List<Document> documents) {
        }

    }
}