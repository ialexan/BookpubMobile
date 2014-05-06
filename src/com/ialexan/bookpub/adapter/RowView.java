package com.ialexan.bookpub.adapter;

import com.ialexan.bookpub.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RowView extends LinearLayout {

    private TextView titleView;

    private TextView authorView;

    private TextView ratingView;

    private ImageView imageViewCoverPage;

    public RowView( Context context )
    {
        super( context );

        // Inflate the xml resource
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        layoutInflater.inflate( R.layout.list_row, this );

        // Capture the TextViews to the member variables
        this.titleView = (TextView) findViewById( R.id.title_view );
        this.authorView = (TextView) findViewById( R.id.author_view );
        this.ratingView = (TextView) findViewById( R.id.rating_view );

        this.imageViewCoverPage = (ImageView) findViewById( R.id.image_view_coverpage );
    }

    public void setTitleView( String titleText )
    {
        this.titleView.setText( titleText );
    }

    public void setAuthorView( String authorText )
    {
        this.authorView.setText( authorText );
    }

    public void setRatingView( String ratingText )
    {
        this.ratingView.setText( ratingText );
    }

    public ImageView getImageViewCoverPage()
    {
        return imageViewCoverPage;
    }

    public void setImageViewCoverPage( ImageView imageViewCoverPage )
    {
        this.imageViewCoverPage = imageViewCoverPage;
    }

}
