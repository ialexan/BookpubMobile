package com.ialexan.bookpub.fragment;

import com.ialexan.bookpub.R;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TabFragment extends Fragment {

    public static final int STORE_STATE = 1;

    public static final int BOOK_COLLECTION_STATE = 2;

    public static int mTabState;

    private View view;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState )
    {


        // Grab the tab buttons from the layout and attach event handlers. The
        // code just uses standard
        // buttons for the tab widgets. These are bad tab widgets, design
        // something better, this is just
        // to keep the code simple.
        // Button storeViewTab = (Button) view.findViewById(
        // R.id.button_store_view );
        // Button bookCollectionViewTab = (Button) view.findViewById(
        // R.id.button_my_books_collection_view );

        if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT )
        {
            view = inflater.inflate( R.layout.fragment_tab, container, false );


            if (mTabState!=BOOK_COLLECTION_STATE){

               
            }

        }
        else
        {

            view = inflater.inflate( R.layout.fragment_tab, container, false );

            // bar.setCustomView(view, new ActionBar.LayoutParams(
            // LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
            // Gravity.RIGHT));
        }




        return view;
    }

    public void goToStoreView()
    {
        // mTabState keeps track of which tab is currently displaying its
        // contents.
        // Perform a check to make sure the list tab content isn't already
        // displaying.



        if( mTabState != STORE_STATE )
        {
            // Update the mTabState
            mTabState = STORE_STATE;

            // Fragments have access to their parent Activity's FragmentManager.
            // You can
            // obtain the FragmentManager like this.
            FragmentManager fm = getFragmentManager();

            if( fm != null )
            {
                // Perform the FragmentTransaction to load in the list tab
                // content.
                // Using FragmentTransaction#replace will destroy any Fragments
                // currently inside R.id.fragment_content and add the new
                // Fragment
                // in its place.
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace( R.id.fragment_content, new StoreListFragment() );
                ft.commit();
            }
        }
    }

    public void goToMyLibrary()
    {
        // See gotoListView(). This method does the same thing except it loads
        // the grid tab.
        if( mTabState != BOOK_COLLECTION_STATE )
        {


            mTabState = BOOK_COLLECTION_STATE;

            FragmentManager fm = getFragmentManager();

            if( fm != null )
            {
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace( R.id.fragment_content,
                    new ReaderBookCollectionGridFragment() ); // change to
                // BookCollectionListFragment()
                ft.commit();
            }
        }
    }

}
