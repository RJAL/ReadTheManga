package com.example.ruilopes.readthemanga;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class SearchFragment extends Fragment {

    public SearchFragment() {
        // Required empty public constructor
    }

    EditText insertManga;
    Button searchManga;
    ListView mangaList;
    View view;
    TextView tv;
    private JSONObject jsonArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = (View) inflater.inflate(R.layout.fragment_search, container, false);

        insertManga = (EditText) view.findViewById(R.id.search_txt);
        searchManga = (Button) view.findViewById(R.id.search_btn);
        mangaList = (ListView) view.findViewById(R.id.mangaListSearch);
        tv = (TextView) view.findViewById(R.id.textView2);

        searchManga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = insertManga.getText().toString();
                ArrayList<String> list = new ArrayList<>();
                try {
                    jsonArray = new JSONObject(new MangaParser().execute("asd").get());
                    /*for (int i = 0; i < jsonArray.length(); i++){
                        list.add("Title: " + jsonArray.getJSONObject(i).getString("title") );
                    }*/
                    tv.setText("alias: " + jsonArray.getString("alias") + "artist:" + jsonArray.getString("artist")
                    + "categories" + jsonArray.getJSONArray("categories").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                //mangaList.setAdapter(new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, list));
            }
        });

        return view;
    }
}