package com.conformiz.milkconsumerapp.network;

import android.content.Context;
import android.util.Log;


import com.conformiz.milkconsumerapp.utils.Constants;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class PostRequest implements IRequest {

    private String bodyData;
    private String action;
    Context context;

    public PostRequest(Context pContext, String pAction, String data) {
        context = pContext;
        bodyData = data;
        action = pAction;
    }

    @Override
    public String doRequest() {

        try {
            String URL = Constants.URL_BASE_URL+action;
            if(action.contains("interpayafrica") || action.contains("milk")){
                URL = action;
            }
           // String baseURL = Utility.getInstance().getBaseURL(context);
            Log.d("debug", "URL: " + "baseURL "+ URL);
            Log.d("debug", "BODY: " + bodyData);
            URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(30000);
            conn.setConnectTimeout(30000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(bodyData);

            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();
            String response = "";

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line = "";
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
                br.close();
            }
            Log.d("debug", "RESPONSE: " + response);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
