package com.ialexan.bookpub.adapter;

import java.util.ArrayList;

import com.ialexan.bookpub.R;
import com.ialexan.bookpub.imageFromURL.ImageLoader;
import com.ialexan.bookpub.model.Book;
import com.ialexan.bookpub.model.User;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class StoreListAdapter extends BaseAdapter {

    private Context mContext;

    private ArrayList<Book> mBookList;

    public ImageLoader imageLoader;

    public StoreListAdapter( Context context, ArrayList<Book> mBookList )
    {
        mContext = context;
        this.mBookList = mBookList;
        imageLoader = new ImageLoader( context.getApplicationContext() );
    }

    @Override
    public int getCount()
    {
        return mBookList.size();
    }

    @Override
    public Object getItem( int position )
    {
        return mBookList.get( position );
    }

    @Override
    public long getItemId( int position )
    {
        return mBookList.get( position ).getId();
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent )
    {

        RowView view = null;

        if( convertView == null )
        {
            // Create a new view
            view = new RowView( this.mContext );
            // This area is for properties that are all the same

        }
        else
        {
            // Use the convertView
            view = (RowView) convertView;
        }

        // This area is for properties that are different for each view

        //Set the cover image for each book
        ImageView image = (ImageView) view.getImageViewCoverPage();
        imageLoader.DisplayImage( mContext.getString( R.string.ImageURL )
            .toString() + mBookList.get( position ).getCoverThumbnail().getId(), image );

        
        
        // Set the title for each row
        view.setTitleView( mBookList.get( position ).getTitle() );

        // Set the Author for each row
        String authors = "";
        for (User author : mBookList.get( position ).getAuthors()){
            authors += author.getFirstName() + " " + author.getLastName() + "  "; 
        }
        view.setAuthorView(authors);

        // ----------------------------

         

        return view;
    }

}
