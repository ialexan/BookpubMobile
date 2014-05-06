package com.ialexan.bookpub.activity;

import java.io.InputStream;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ialexan.bookpub.R;
import com.ialexan.bookpub.fragment.StoreListFragment;
import com.ialexan.bookpub.httpConnection.HttpRemoteConnection;
import com.ialexan.bookpub.imageFromURL.ImageLoader;
import com.ialexan.bookpub.model.Book;
import com.ialexan.bookpub.model.BookJson;
import com.ialexan.bookpub.model.ServiceResponse;
import com.ialexan.bookpub.model.ServiceResponseJson;
import com.ialexan.bookpub.model.User;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BookActivity extends Activity {

    private Book book;

    private ImageLoader imageLoader;

    private ObjectMapper mapper = new ObjectMapper();


    @SuppressLint({ "NewApi" })
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT )
        {
            setContentView( R.layout.activity_book );

        }
        else
        {
            setContentView( R.layout.activity_book_landscape_view );
        }




        ImageView imageViewCoverPage = (ImageView) findViewById( R.id.image_book_view_coverpage );
        TextView titleView = (TextView) findViewById( R.id.book_title_view );
        TextView authorView = (TextView) findViewById( R.id.book_author_view );
        TextView priceView = (TextView) findViewById( R.id.book_price_view );
        TextView descriptionView = (TextView) findViewById( R.id.book_descpription_review_content_view );

        // Get the message from the intent
        Intent intent = getIntent();
        String bookId = intent.getStringExtra( StoreListFragment.book_ID );

        try
        {

            HttpRemoteConnection httpConn = new HttpRemoteConnection();

            InputStream  inputStream = httpConn.getStreamFromUrl( getString( R.string.BookURL ) + bookId , null );


            // The webservice for books returns Json Object in the form of {"books":[{book1},{book2},... ]} 
            BookJson bookJson = mapper.readValue(inputStream, BookJson.class);

            book = bookJson.get("book"); 

            imageLoader = new ImageLoader( getApplicationContext() );

            imageLoader.DisplayImage( getString( R.string.ImageURL ).toString()
                + book.getCover().getId(), imageViewCoverPage );


            // Set Title of the book
            titleView.setText( book.getTitle() );
            
            // Set the authors of the book            
            String authors = "";
            for (User author : book.getAuthors()){
                authors += author.getFirstName() + " " + author.getLastName() + "  "; 
            }
            authorView.setText( authors);
            
            // Set the price of the book
            priceView.setText( "$" + book.getPrice() );
            
            // Set the description of the book
            descriptionView.setText( book.getDescription() );




        }
        catch( Exception e )
        {
            Log.e( "log_tag", "Error Parsing Data." + e.toString() );
        }


    }

    //puchaseBook gets called up when the user clicks on buy
    public void purchaseBook( View v )
    {

        try
        {

            HttpRemoteConnection httpConnection = new HttpRemoteConnection();

            InputStream  inputStream = httpConnection.getStreamFromUrl(getString( R.string.PurchaseURL ).toString()+book.getId(), null );

            // The webservice for books returns Json Object in the form of {"success":true,"messgage":""} 
            ServiceResponseJson resultData = mapper.readValue(inputStream, ServiceResponseJson.class);

            ServiceResponse response = resultData.get( "result" );
            
            if( response.isSuccess() )
            { // Purchasing the book was successful
                Toast.makeText( getApplicationContext(),"You bought the book", Toast.LENGTH_SHORT ).show();

                Intent intent = new Intent( this, StoreAndCollectionActivity.class );
                intent.putExtra( "WhereAreYouComingFrom", "FromBookActivity" );                
                startActivity( intent );
                
                
                
            }
            else
            { // Didn't purchase the book
                Toast.makeText( getApplicationContext(), "Couldn't buy the book", Toast.LENGTH_SHORT ).show();
            }


        }
        catch( Exception e )
        {
            Log.e( "log_tag", "Error Parsing Data." + e.toString() );
        }

    }





    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.activity_book, menu );
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
