package com.ialexan.bookpub.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.ialexan.bookpub.R;
import com.ialexan.bookpub.fragment.TabFragment;

public class StoreAndCollectionActivity extends FragmentActivity {

    private FragmentManager fm;

    private TabFragment tabFragment;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        // getActionBar().setDisplayHomeAsUpEnabled( true );
        ActionBar bar = getActionBar();
        bar.setDisplayShowTitleEnabled( true );

        // Notice how there is not very much code in the Activity. All the
        // business logic for
        // rendering tab content and even the logic for switching between tabs
        // has been pushed
        // into the Fragments. This is one example of how to organize your
        // Activities with Fragments.
        // This benefit of this approach is that the Activity can be reorganized
        // using layouts
        // for different devices and screen resolutions.
        if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT )
        {
            setContentView( R.layout.activity_store_and_collection );
        }
        else{
            setContentView( R.layout.activity_store_collection_landscape );

        }

        // Grab the instance of TabFragment that was included with the layout
        // and have it
        // launch the initial tab.
        fm = getSupportFragmentManager();
        tabFragment = (TabFragment) fm.findFragmentById( R.id.fragment_tab );


        // Get the message from the intent
        Intent intent = getIntent();
        if (intent.getStringExtra( "WhereAreYouComingFrom" ).equals( "FromBookActivity" ))
        {
            tabFragment.goToMyLibrary();    
        }
        else{

            tabFragment.goToStoreView();
        }

        // if statement to not interfere between the library and the store
        if (TabFragment.mTabState == TabFragment.STORE_STATE){
            tabFragment.goToStoreView();
        }
        else{
            tabFragment.goToMyLibrary(); 
        }

    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.activity_store_and_collection, menu );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        switch( item.getItemId() )
        {
            case R.id.menu_store:
                tabFragment.goToStoreView();
                return true;

            case R.id.menu_library:
                tabFragment.goToMyLibrary();
                return true;

            case R.id.menu_search:

                return true;

            default:
                return super.onOptionsItemSelected( item );
        }
    }

    public void clickTopRated( View view ){
        ImageView topRatedImage = (ImageView) findViewById( R.id.TopRatedTabImage );
        topRatedImage.setBackgroundColor( Color.parseColor("#3D93DD") );
        
        ImageView recommendatioTabImage  = (ImageView) findViewById( R.id.RecommendationTabImage );
        recommendatioTabImage.setBackgroundColor( Color.parseColor("#000000") );
    }

    public void clickTopRecommendation( View view ){
        ImageView recommendatioTabImage  = (ImageView) findViewById( R.id.RecommendationTabImage );
        recommendatioTabImage.setBackgroundColor( Color.parseColor("#3D93DD") );
        
        ImageView topRatedImage = (ImageView) findViewById( R.id.TopRatedTabImage );
        topRatedImage.setBackgroundColor( Color.parseColor("#000000") );
    }

}
