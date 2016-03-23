package com.example.jared.smart_bandage_android;


/**
 * Created by Me on 2016-03-12.
 */
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SendData {
    private EditText bandageID;
    private String tempValue = "3";
    private String humidityValue;
    // TODO add in moisture later if that ends up working...


    public void insert(){
        String bandageIDstring = "1234";
        insertToDatabase(bandageIDstring,tempValue);
    }

    private void insertToDatabase(final String bID, final String temp){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramUsername = params[0];
                String paramAddress = params[1];



                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("bandage_id", "123"));
                nameValuePairs.add(new BasicNameValuePair("temp_data", "asdsadasd"));

                //nameValuePairs.add(new BasicNameValuePair("humidity", humid));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://www.jaredcuglietta.ca/insert_db.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);
                    Log.i("help", response.getStatusLine().toString());
                    HttpEntity entity = response.getEntity();
                    Log.i("help", entity.toString());

                } catch (ClientProtocolException e) {
                    Log.i("help", "helpp me");

                } catch (IOException e) {
                    Log.i("help1", e.getMessage());

                }
                return "success";
            }
/*
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
                textViewResult.setText("Inserted");
            }
            */
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(bID, temp);
    }


}