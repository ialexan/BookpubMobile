package com.ialexan.bookpub.httpConnection;

import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class HttpRemoteConnection {

    protected static DefaultHttpClient httpClient = new DefaultHttpClient();

    private InputStream inputStream = null;

    public HttpRemoteConnection()
    {
    }
    
    public InputStream getStreamFromUrl( String url, List<NameValuePair> params )
    {// Making HTTP request

        try
        {

            HttpPost httpPost = new HttpPost( url );

            if( params != null )
            {
                httpPost.setEntity( new UrlEncodedFormEntity( params ) );
            }
            
            HttpResponse httpResponse = httpClient.execute( httpPost );
            HttpEntity httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();

        }
        catch( Exception e )
        {
            Log.e( "log_tag", "Error in http connection " + e.toString() );
        }

        // return Input Stream
        return inputStream;
    }

}
