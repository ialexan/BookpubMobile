package com.ialexan.bookpub.activity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ialexan.bookpub.R;
import com.ialexan.bookpub.httpConnection.HttpRemoteConnection;
import com.ialexan.bookpub.model.ServiceResponse;

public class MainActivity extends Activity {

    @SuppressLint("NewApi")
    @Override
    public void onCreate( Bundle savedInstanceState )
    {// This is called when the activity is created
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        StrictMode.enableDefaults();
    }

   
    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {// Creates the menu on top of the screen
        getMenuInflater().inflate( R.menu.activity_main, menu );
        return true;
    }

    
    // Called when the user clicks the Register 
    public void sendToRegister( View view )
    {
        Intent intent = new Intent( this, RegisterActivity.class );
        startActivity( intent );
    }

    
    // Called when the user clicks the Login 
    public void sendToStoreAndLibrary( View view )
    {
        try
        {
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            EditText editTextEmail = (EditText) findViewById( R.id.TextEmail );
            String email = editTextEmail.getText().toString();

            EditText editTextPassword = (EditText) findViewById( R.id.TextPassword );
            String password = editTextPassword.getText().toString();

            params.add( new BasicNameValuePair( "j_username", email ) );
            params.add( new BasicNameValuePair( "j_password", password ) );
            params.add( new BasicNameValuePair( "mobile", "mobile" ));

            HttpRemoteConnection httpConn = new HttpRemoteConnection();

            InputStream  inputStream = httpConn.getStreamFromUrl( getString( R.string.LoginURL ),params );
            
            ObjectMapper mapper = new ObjectMapper();
            // The webservice for loging returns Json Object in the form of {"success":true,"message":""} if logged in 
            // and {"success":false,"message":""} when can't login
            ServiceResponse resultData = mapper.readValue(inputStream, ServiceResponse.class);
            
          
            if( resultData.isSuccess() )
            { // Login is successful
               // Toast.makeText( getApplicationContext(), "Logged In", Toast.LENGTH_SHORT ).show();
                
              Intent intent = new Intent( this, StoreAndCollectionActivity.class );
              intent.putExtra( "WhereAreYouComingFrom", "MainActivity" ); 
              startActivity( intent );
            }
            else
            { // Login Failed, renter email and password
                Toast.makeText( getApplicationContext(), "Email or Password invalid", Toast.LENGTH_SHORT ).show();
            }
            

        }
        catch( Exception e )
        {
            Log.e( "log_tag", "Error Parsing Data" + e.toString() );
        }


    }

}
