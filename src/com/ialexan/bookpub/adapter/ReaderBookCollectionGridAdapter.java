package com.ialexan.bookpub.adapter;

import java.util.ArrayList;

import com.ialexan.bookpub.R;
import com.ialexan.bookpub.imageFromURL.ImageLoader;
import com.ialexan.bookpub.model.Book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ReaderBookCollectionGridAdapter extends BaseAdapter {

    private Context mContext;

    private ArrayList<Book> mBookList;

    public ImageLoader imageLoader;

    private LayoutInflater mInflater;

    private class ViewHolder {

        public ImageView imageView;
    }

    public ReaderBookCollectionGridAdapter( Context context,
        ArrayList<Book> mBookList )
    {
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
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

        if( mBookList != null && position >= 0 && position < getCount() ){ return mBookList.get( position ); }

        return null;
    }

    @Override
    public long getItemId( int position )
    {
        if( mBookList != null && position >= 0 && position < getCount() ){ return mBookList.get(
            position )
            .getId(); }

        return 0;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent )
    {

        View view = convertView;
        ViewHolder viewHolder;

        if( view == null )
        {
            view = mInflater.inflate(
                R.layout.item_reader_book_collection_grid, parent, false );

            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById( R.id.grid_image );

            view.setTag( viewHolder );
        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }

        // Get the specific Book's cover page

        // ImageView image = (ImageView) view.getImageViewCoverPage();

//        Toast.makeText(mContext,mContext.getString( R.string.ImageURL )
//            .toString() + mBookList.get( position ).getCover().getId() , Toast.LENGTH_LONG).show();
        
        imageLoader.DisplayImage( mContext.getString( R.string.ImageURL )
            .toString() + mBookList.get( position ).getCoverThumbnail().getId(),
            viewHolder.imageView );

        return view;
    }

}
