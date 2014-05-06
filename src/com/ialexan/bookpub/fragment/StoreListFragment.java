package com.ialexan.bookpub.fragment;

import java.io.InputStream;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ialexan.bookpub.R;
import com.ialexan.bookpub.activity.BookActivity;
import com.ialexan.bookpub.adapter.StoreListAdapter;
import com.ialexan.bookpub.httpConnection.HttpRemoteConnection;
import com.ialexan.bookpub.model.Book;
import com.ialexan.bookpub.model.BooksJson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class StoreListFragment extends ListFragment {

    // ListFragment is a very useful class that provides a simple ListView
    // inside of a Fragment.
    // This class is meant to be sub-classed and allows you to quickly build up
    // list interfaces
    // in bookpub app.

    
    public final static String book_ID = "bookId";

    @Override
    public void onActivityCreated( Bundle savedInstanceState )
    {
        super.onActivityCreated( savedInstanceState );

        Activity activity = getActivity();

        if( activity != null )
        {
            
            ArrayList<Book> bookList = new ArrayList<Book>();
            
            try
            {
                
                HttpRemoteConnection httpConn = new HttpRemoteConnection();
                
                InputStream  inputStream = httpConn.getStreamFromUrl( getString( R.string.BooksURL ),null );
     
                ObjectMapper mapper = new ObjectMapper();
                // The webservice for books returns Json Object in the form of {"books":[{book1},{book2},... ]} 
                BooksJson books = mapper.readValue(inputStream, BooksJson.class);
           
                bookList = books.get("books");
             
                
            }
            catch( Exception e )
            {
                Log.e( "log_tag", "Error Parsing Data" + e.toString() );
            } 
                  

            // Create the adapter and pass the activity and the data coming from
            // the server
            StoreListAdapter storeListAdapter = new StoreListAdapter(activity,bookList );

            setListAdapter( storeListAdapter );

        }
    }

    // Clicking on a single row in the list
    @Override
    public void onListItemClick( ListView l, View v, int position, long id )
    {
        Activity activity = getActivity();

        if( activity != null )
        {

            // Toast.makeText(activity, getString(R.string.toast_item_click) +
            // locationModel.address, Toast.LENGTH_SHORT).show();

            StoreListAdapter listAdapter = (StoreListAdapter) getListAdapter();

            Book book = (Book) listAdapter.getItem( position );
            
            Intent intent = new Intent( activity, BookActivity.class );
            intent.putExtra( book_ID, book.getId().toString() );
            startActivity( intent );

        }
    }

}
