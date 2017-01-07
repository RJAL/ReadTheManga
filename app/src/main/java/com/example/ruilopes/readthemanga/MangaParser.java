package com.example.ruilopes.readthemanga;

import android.os.AsyncTask;
import java.net.URL;
import java.util.Scanner;

public class MangaParser extends AsyncTask<String, Void, String>{

    //Parsing Para Obter Leitura do URL
    @Override
    protected String doInBackground(String... strings) {

        String STR = "";
        URL url = null;

        try {
            Scanner scan;

            url = new URL("https://myanimelist.net/api/manga/search.xml?q=" + strings[0]);

            scan = new Scanner(url.openStream());
            while (scan.hasNext())
                STR += scan.nextLine();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }


        return STR;
    }
}