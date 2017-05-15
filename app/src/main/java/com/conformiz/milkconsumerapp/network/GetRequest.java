package com.conformiz.milkconsumerapp.network;

import android.content.Context;
import android.util.Log;


import com.conformiz.milkconsumerapp.utils.Constants;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetRequest implements IRequest {

    private String mAction;
    private String mParams;
    Context context;

    public GetRequest(Context pContext, String action, String data) {
        context = pContext;
        mAction = action;
        mParams = data;
    }

    @Override
    public String doRequest() {

        //String serverUrl = Utility.getInstance().getBaseURL(context) + mAction;
        String serverUrl = Constants.URL_BASE_URL+mAction;
        if (mParams != null) {
            serverUrl += "/" + mParams;
        }

        try {
//            serverUrl = URLEncoder.encode(serverUrl, "UTF-8");
            Log.d("debug", "URL = " + serverUrl);
            URL url = new URL(serverUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(30000);
            conn.setConnectTimeout(30000);
            conn.setRequestProperty("Content-Type", "application/json");

            Log.d("debug", "Response Code: " + conn.getResponseCode());
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String response = IOUtils.toString(in, "UTF-8");
            Log.d("debug", "Response: " + response);
            in.close();

            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
