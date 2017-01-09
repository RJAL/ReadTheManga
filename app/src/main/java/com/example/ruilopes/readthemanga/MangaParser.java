package com.example.ruilopes.readthemanga;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Scanner;


public class MangaParser extends AsyncTask<String, Void, String>{

    //Parsing Para Obter Leitura do URL
    @Override
    protected String doInBackground(String... strings) {
        String STR = "";
        URL url = null;
        String t = "";
        StringBuilder res = new StringBuilder();

        try {
            Authenticator.setDefault(new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("Comp_Mobile", "comp_movel123".toCharArray());
                }
            });
            HttpURLConnection connection = (HttpURLConnection)  new URL("https://myanimelist.net/api/manga/search.xml?q="+strings[0]).openConnection();
            connection.setUseCaches(false);
            connection.setRequestMethod("GET");
            connection.connect();
            Log.d("----CODE"+connection.getResponseCode(), "-----");

            InputStream stream = new BufferedInputStream(connection.getInputStream());
            BufferedReader read = new BufferedReader(new InputStreamReader(stream));

            String line= "";

            while((line = read.readLine())!=null){
                res.append(line);
                Log.d("----------::::", line);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return res.toString();
    }
}