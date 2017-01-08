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
        String t = "";
        try {
            Scanner scan;
            url = new URL("http://www.mangaeden.com/api/manga/4e70ea0ec092255ef7004a28");

            scan = new Scanner(url.openStream());
            while (scan.hasNext()){
                STR += scan.nextLine();
            }
            scan.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return STR;
    }
}