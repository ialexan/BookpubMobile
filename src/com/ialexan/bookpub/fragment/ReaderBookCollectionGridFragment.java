package com.ialexan.bookpub.fragment;

import java.io.InputStream;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ialexan.bookpub.R;
import com.ialexan.bookpub.activity.EbookActivity;
import com.ialexan.bookpub.adapter.ReaderBookCollectionGridAdapter;
import com.ialexan.bookpub.httpConnection.HttpRemoteConnection;
import com.ialexan.bookpub.model.Book;
import com.ialexan.bookpub.model.BooksJson;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class ReaderBookCollectionGridFragment extends Fragment {

    private GridView mGridView;

    private ReaderBookCollectionGridAdapter mGridAdapter;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState )
    {
        View view = inflater.inflate(
            R.layout.fragment_reader_book_collection_grid, container, false );

        // Store a pointer to the GridView that powers this grid fragment.
        mGridView = (GridView) view.findViewById( R.id.grid_view );

        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState )
    {
        super.onActivityCreated( savedInstanceState );
        Activity activity = getActivity();

        if( activity != null )
        {
            // Create an instance of the custom adapter for the GridView. A
            // static array of location data
            // is stored in the Application sub-class for this app. This data
            // would normally come
            // from a database or a web service.

            // ArrayList of the users book reading List that will be passes to
            // the adapter
            ArrayList<Book> bookList = new ArrayList<Book>();;


            try
            {

                HttpRemoteConnection httpConn = new HttpRemoteConnection();

                InputStream  inputStream = httpConn.getStreamFromUrl(activity.getString( R.string.LibraryURL ).toString(),null );
                
                ObjectMapper mapper = new ObjectMapper();
                // The webservice for Library books returns Json Object in the form of {"books":[{book1},{book2},... ]} 
                BooksJson books = mapper.readValue(inputStream, BooksJson.class);
                
                bookList = books.get("books");

            }
            catch( Exception e )
            {
                Log.e( "log_tag", "Error Parsing Data" + e.toString() );
            }

            
            // Now pass the activity and the bookList to the adapter
            mGridAdapter = new ReaderBookCollectionGridAdapter(activity,bookList );

            if( mGridView != null )
            {
                mGridView.setAdapter( mGridAdapter );
            }

            // Setup our onItemClickListener to emulate the onListItemClick()
            // method of ListFragment.
            mGridView.setOnItemClickListener( new OnItemClickListener() {

                @Override
                public void onItemClick( AdapterView<?> parent, View view,
                    int position, long id )
                {
                    onGridItemClick( (GridView) parent, view, position, id );
                }

            } );
        }
    }

    public void onGridItemClick( GridView g, View v, int position, long id )
    {
        Activity activity = getActivity();

        if( activity != null )
        {
            // LocationModel locationModel = (LocationModel)
            // mGridAdapter.getItem(position);

            // Display a simple Toast to demonstrate that the click event is
            // working. Notice that Fragments have a
            // getString() method just like an Activity, so that you can quickly
            // access your localized Strings.
            // Toast.makeText(activity, getString(R.string.toast_item_click) +
            // locationModel.address, Toast.LENGTH_SHORT).show();
            Toast.makeText( activity, "Book", Toast.LENGTH_SHORT ).show();
            
            Intent intent = new Intent( activity, EbookActivity.class );
//            intent.putExtra( book_ID, book.getId().toString() );  pass the bookId
            startActivity( intent );

        }
    }

}
