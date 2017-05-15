package com.conformiz.milkconsumerapp.network;

public interface INetworkListener<T> {

	void onPreExecute();
	void onPostExecute(T result);
	void doInBackground();
}
