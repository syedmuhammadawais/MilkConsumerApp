package com.conformiz.milkconsumerapp.network;

import android.content.Context;
import android.util.Log;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicHeader;
//import org.apache.http.protocol.HTTP;
//import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.net.URL;

import static java.net.Proxy.Type.HTTP;

/**
 * Created by Lenovo on 7/28/2016.
 */
public class DeleteBodyRequest implements IRequest {

    private String mAction;
    Context context;
    private String bodyData = "";

    public DeleteBodyRequest(Context pContext, String action) {
        context = pContext;
        mAction = action;
    }
    public DeleteBodyRequest(Context pContext, String action, String bodyData) {
        context = pContext;
        mAction = action;
        this.bodyData = bodyData;
    }

    @Override
    public String doRequest() {

        try {

//            Log.i("do",""+bodyData);
//           // Log.d("debug", "BODY: " + bodyData);
//
//            URL url = new URL(mAction);
//            Log.d("debug", "URL " + url);
//
//
//            HttpClient httpClient = new DefaultHttpClient();
//            HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(url.toString());
//            httpDelete.setHeader("Content-type","application/json");
//            StringEntity se = new StringEntity(bodyData);
//            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
//
//            if(!bodyData.equals("")) {
//                httpDelete.setEntity(se);
//                Log.d("debug", " entity set ");
//
//            } else {
//                httpDelete.setEntity(new StringEntity("{}", HTTP.UTF_8));
//                Log.d("debug", " entity NOT set ");
//
//            }
//
//            HttpResponse httpResponse = httpClient.execute(httpDelete);
//            HttpEntity entity = httpResponse.getEntity();
//            final String response = EntityUtils.toString(entity);
//            Log.d("debug", "Response: " + response);
//            return response;
        } catch (final Exception e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            System.gc();
        }





        return null;
    }

//    @NotThreadSafe
//    class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
//        public static final String METHOD_NAME = "DELETE";
//        public String getMethod() { return METHOD_NAME; }
//
//        public HttpDeleteWithBody(final String uri) {
//            super();
//            setURI(URI.create(uri));
//        }
//        public HttpDeleteWithBody(final URI uri) {
//            super();
//            setURI(uri);
//        }
//        public HttpDeleteWithBody() { super(); }
//    }
}