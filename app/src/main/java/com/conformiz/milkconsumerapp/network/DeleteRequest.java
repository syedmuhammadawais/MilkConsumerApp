package com.conformiz.milkconsumerapp.network;

import android.content.Context;
import android.util.Log;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeleteRequest implements IRequest {

    private String mAction;
    Context context;

    public DeleteRequest(Context pContext, String action) {
        context = pContext;
        mAction = action;
    }

    @Override
    public String doRequest() {

        try {
            URL url = new URL(mAction);
            Log.d("debug", "URL: " + url + mAction);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
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
