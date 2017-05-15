package com.conformiz.milkconsumerapp.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

public class NetworkThread<T> extends AsyncTask<IRequest, Integer, T> {
    private INetworkListener<T> mNetworkListener;

    private Gson mGson;
    private Class<T> mType;

    private Context context;

    public NetworkThread(Context context, Class<T> type) {
        mGson = new Gson();
        mType = type;
        this.context = context;
    }

    public NetworkThread(Context context, Class<T> type, INetworkListener<T> pNetworkListener) {
        mGson = new Gson();
        mType = type;
        mNetworkListener = pNetworkListener;
        this.context = context;
    }

    public void setNetworkListener(INetworkListener<T> mNetworkListener) {
        this.mNetworkListener = mNetworkListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mNetworkListener != null) {
            mNetworkListener.onPreExecute();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(T result) {
        super.onPostExecute(result);
        if (mNetworkListener != null) {
            mNetworkListener.onPostExecute(result);
        }

        if (!isOnline()) {
            if(context!=null)
            Toast.makeText(this.context, "Please connect to internet.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected T doInBackground(IRequest... params) {
        if (!isOnline()) {
            return null;
        }
        if (mNetworkListener != null) {
            mNetworkListener.doInBackground();
        }

        String serverResponse = params[0].doRequest();
        Log.e("SERVER-RESPONSE", serverResponse + "");
        try {
            return mGson.fromJson(serverResponse, mType);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isOnline() {
        if(context!=null){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();}
        return false;
    }
}
