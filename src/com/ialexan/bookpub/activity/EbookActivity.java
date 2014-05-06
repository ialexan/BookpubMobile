package com.ialexan.bookpub.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.ialexan.bookpub.R;
import com.ialexan.bookpub.httpConnection.HttpRemoteConnection;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.TOCReference;
import nl.siegmann.epublib.epub.EpubReader;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


public class EbookActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);

        AssetManager assetManager = getAssets();
        try {

            File file = new File("2000002.epub");
            URL server = new URL("http://10.0.2.2:8080/bookpub/service/epubFile/2000002");


            HttpURLConnection connection = (HttpURLConnection)server.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.addRequestProperty("Accept","image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/epub, application/msword, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/x-shockwave-flash, */*");
            connection.addRequestProperty("Accept-Language", "en-us,zh-cn;q=0.5");
            connection.addRequestProperty("Accept-Encoding", "gzip, deflate");

            connection.connect();
            InputStream is = connection.getInputStream();


            OutputStream os = new FileOutputStream(file.getName());
            byte[] buffer = new byte[1024];
            int byteReaded = is.read(buffer);
            while(byteReaded != -1)
            {
              os.write(buffer,0,byteReaded);
              byteReaded = is.read(buffer);
            }
           os.close();
          
           InputStream epubInputStream = assetManager
           .open("2000002.epub");
           
            Log.i("SAHAG", "HELOOOOOOOOOOOOOOOOOOOOOOOO: ");

            // Load Book from inputStream
            Book book = (new EpubReader()).readEpub(epubInputStream);

            // Log the book's authors
            Log.i("epublib", "author(s): " + book.getMetadata().getAuthors());

            // Log the book's title
            Log.i("epublib", "title: " + book.getTitle());

            System.out.println( "The book title is " + book.getTitle() );

            Log.i("SAHAG", "HELOOOOOOOOOOOOOOOOOOOOOOOO AGAINNNNNNNNNNNNNNNNNNNN: ");

            // Log the book's coverimage property
            //          Bitmap coverImage = BitmapFactory.decodeStream(book.getCoverImage()
            //              .getInputStream());
            //          Log.i("epublib", "Coverimage is " + coverImage.getWidth() + " by "
            //              + coverImage.getHeight() + " pixels");

            // Log the tale of contents
            logTableOfContents(book.getTableOfContents().getTocReferences(), 0);
        } catch (IOException e) {
            System.out.println( "Could not connect");
            Log.e("epublib", e.getMessage());
        }



        //        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void logTableOfContents(List<TOCReference> tocReferences, int depth) {
        if (tocReferences == null) {
            return;
        }
        for (TOCReference tocReference : tocReferences) {
            StringBuilder tocString = new StringBuilder();
            for (int i = 0; i < depth; i++) {
                tocString.append("\t");
            }
            tocString.append(tocReference.getTitle());
            Log.i("epublib", tocString.toString());

            logTableOfContents(tocReference.getChildren(), depth + 1);
        }
    }

    //    @Override
    //    public boolean onCreateOptionsMenu(Menu menu) {
    //        getMenuInflater().inflate(R.menu.activity_ebook, menu);
    //        return true;
    //    }
    //
    //    
    //    @Override
    //    public boolean onOptionsItemSelected(MenuItem item) {
    //        switch (item.getItemId()) {
    //            case android.R.id.home:
    //                NavUtils.navigateUpFromSameTask(this);
    //                return true;
    //        }
    //        return super.onOptionsItemSelected(item);
    //    }

}
