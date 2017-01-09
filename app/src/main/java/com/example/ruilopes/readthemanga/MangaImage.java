package com.example.ruilopes.readthemanga;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;


public class MangaImage extends AsyncTask<String, Void, Bitmap> {


    @Override
    protected Bitmap doInBackground(String... strings) {
        String urldisplay = strings[0];
        Bitmap bmap = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            bmap = BitmapFactory.decodeStream(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmap;
    }
}
