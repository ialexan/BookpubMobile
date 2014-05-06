package com.ialexan.bookpub.activity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ialexan.bookpub.R;
import com.ialexan.bookpub.httpConnection.HttpRemoteConnection;
import com.ialexan.bookpub.model.ServiceResponse;
import com.ialexan.bookpub.model.ServiceResponseJson;
import com.ialexan.bookpub.util.MD5;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {

    DatePickerFragment frag;

    Calendar now;

    EditText editTextFirstnameInput;

    EditText editTextLastnameInput;

    EditText editTextEmail;

    EditText editTextPassword;

    Button btnBirthday;

    Button btnRegister;

    @SuppressLint("NewApi")
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );
        getActionBar().setDisplayHomeAsUpEnabled( true );

        now = Calendar.getInstance();

        // Entering the birthday Field
        btnBirthday = (Button) findViewById( R.id.birthday_button );
        btnBirthday.setOnClickListener( new View.OnClickListener() {

            public void onClick( View view )
            {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show( getFragmentManager(), "datePicker" );
            }
        } );
    }


    // Called when the user clicks the Register, if successful it will redirect to store and library activity 
    public void sendToStoreAndLibrary( View view )
    {

        editTextFirstnameInput = (EditText) findViewById( R.id.firstname_input );
        String firstName = editTextFirstnameInput.getText().toString();

        editTextLastnameInput = (EditText) findViewById( R.id.lastname_input );
        String lastName = editTextLastnameInput.getText().toString();

        editTextEmail = (EditText) findViewById( R.id.email_input );
        String email = editTextEmail.getText().toString();

        editTextPassword = (EditText) findViewById( R.id.password_input );
        String password = editTextPassword.getText().toString();

        String birthday = btnBirthday.getText().toString();

        try
        {
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            // Change password to MD5 encryption
            MD5 md5 = new MD5( password );

            params.add( new BasicNameValuePair( "firstName", firstName ) );
            params.add( new BasicNameValuePair( "lastName", lastName ) );
            params.add( new BasicNameValuePair( "email", email ) );
            params.add( new BasicNameValuePair( "password", md5.getEncryptedValue() ) );
            params.add( new BasicNameValuePair( "birthday", birthday ) );

            HttpRemoteConnection httpConn = new HttpRemoteConnection();

            InputStream  inputStream = httpConn.getStreamFromUrl( getString( R.string.RegisterURL ), params );

            ObjectMapper mapper = new ObjectMapper();
            // The webservice for Register returns Json Object in the form of {"success":true,"message":""} when registered
            // and {"success":false,"message":"User already exists."} when not valid registered
            ServiceResponseJson resultData = mapper.readValue(inputStream, ServiceResponseJson.class);

            ServiceResponse response = resultData.get( "result" );


            if( response.isSuccess() )
            { // Registration is successful

                Toast.makeText( getApplicationContext(),"Congratulations you are registered", Toast.LENGTH_LONG ).show();

                //                Intent intent = new Intent( this, MainActivity.class );
                //                startActivity( intent ); 

                goToLogin(email,password);


            }
            else
            { // Registration Failed
                Toast.makeText( getApplicationContext(), "Invalid Entry!",Toast.LENGTH_LONG ).show();
            }

        }
        catch( Exception e )
        {
            Log.e( "log_tag", "Error Parsing Data " + e.toString() );
        }





    }

    private void goToLogin(String email, String password)
    {

        try{

            //send a request for a login 
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add( new BasicNameValuePair( "j_username", email ) );
            params.add( new BasicNameValuePair( "j_password", password ) );
            params.add( new BasicNameValuePair( "mobile", "mobile" ));

            HttpRemoteConnection httpConnection = new HttpRemoteConnection();

            InputStream inputStream = httpConnection.getStreamFromUrl( getString( R.string.LoginURL ),params );

            ObjectMapper mapper = new ObjectMapper();

            ServiceResponse resultData = mapper.readValue(inputStream, ServiceResponse.class);

            if(resultData.isSuccess()){
                Intent intent = new Intent( this, StoreAndCollectionActivity.class );
                intent.putExtra( "WhereAreYouComingFrom", "MainActivity" ); 
                startActivity( intent );    
            }
            else{
                Toast.makeText( getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT ).show();
            }

        }
        catch( Exception e )
        {
            Log.e( "log_tag", "Error Parsing Data " + e.toString() );
        }

    }


    @SuppressLint("NewApi")
    public class DatePickerFragment extends DialogFragment implements
    DatePickerDialog.OnDateSetListener {

        @SuppressLint("NewApi")
        @Override
        public Dialog onCreateDialog( Bundle savedInstanceState )
        {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get( Calendar.YEAR );
            int month = c.get( Calendar.MONTH );
            int day = c.get( Calendar.DAY_OF_MONTH );

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog( getActivity(), this, year, month, day );
        }

        public void onDateSet( DatePicker view, int year, int month, int day )
        {
            // Do something with the date chosen by the user

            String birthday = String.valueOf( month + 1 ) + "/"
            + String.valueOf( day ) + "/" + String.valueOf( year );

            btnBirthday.setText( birthday );

        }
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.activity_register, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        switch( item.getItemId() )
        {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask( this );
                return true;
        }
        return super.onOptionsItemSelected( item );
    }

}
