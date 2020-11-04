package com.example.loginapp;

import android.content.ClipData;
import android.content.Context;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.document.Table;
import com.amazonaws.mobileconnectors.dynamodbv2.document.UpdateItemOperationConfig;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Primitive;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;

import java.util.List;
import java.util.UUID;

public class DatabaseAccess {
    private static final String COGNITO_POOL_ID = "ap-southeast-1:bc4ee47c-2035-4b45-971c-0b6e7e4e8b87";
    private static final Regions MY_REGION = Regions.AP_SOUTHEAST_1;
    private AmazonDynamoDBClient dbClient;
    private Table dbTable;
    private Context context;
    private final String DYNAMODB_TABLE = "User";
    CognitoCachingCredentialsProvider credentialsProvider;


    private static volatile DatabaseAccess instance;
    private DatabaseAccess (Context context) {
        this.context =context;
        credentialsProvider = new CognitoCachingCredentialsProvider (context, COGNITO_POOL_ID, MY_REGION);
        dbClient = new AmazonDynamoDBClient(credentialsProvider);
        dbClient.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_1));
        dbTable = Table.loadTable(dbClient, DYNAMODB_TABLE);
    }
    public static synchronized DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }
    //register student
    public void create(Document memo) {
        if (!memo.containsKey("userId")) {
            memo.put("userId", credentialsProvider.getCachedIdentityId());
        }
        //if (!memo.containsKey("noteId")) {
         //   memo.put("noteId", UUID.randomUUID().toString());
      //  }
        if (!memo.containsKey("creationDate")) {
            memo.put("creationDate", System.currentTimeMillis());
        }
        dbTable.putItem(memo);
    }
    //update student's profile
    public void update(Document memo) {
        Document document = dbTable.updateItem(memo, new UpdateItemOperationConfig().withReturnValues(ReturnValue.ALL_NEW));
    }

    //get student's profile by userID
    public Document getById(int user_id) {
        return  dbTable.getItem(new Primitive(user_id));
    }

    //get all student's profile
    public List<Document> getAllItems() {

        return dbTable.query(new Primitive(credentialsProvider.getCachedIdentityId())).getAllResults();
    }

}
