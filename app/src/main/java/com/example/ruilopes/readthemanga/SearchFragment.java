package com.example.ruilopes.readthemanga;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


//Fragmento relacionado com a pesquisa do manga
public class SearchFragment extends Fragment {

    public SearchFragment() {
        // Required empty public constructor
    }


    EditText insertManga;
    Button searchManga;
    ListView mangaList;
    View view;
    private JSONObject jsonArray;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = (View) inflater.inflate(R.layout.fragment_search, container, false);

        insertManga = (EditText) view.findViewById(R.id.search_txt);
        searchManga = (Button) view.findViewById(R.id.search_btn);
        mangaList = (ListView) view.findViewById(R.id.mangaListSearch);


        searchManga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = insertManga.getText().toString();
                ArrayList<String> list = new ArrayList<>();

                try {
                    String xml = new MangaParser().execute(insertManga.getText().toString()).get();
                    jsonArray = XML.toJSONObject(xml);
                    for (int i = 0; i < jsonArray.getJSONObject("manga").getJSONArray("entry").length(); i++){
                        list.add("Title: " + jsonArray.getJSONObject("manga").getJSONArray("entry").getJSONObject(i).getString("title") +
                        "\n" + "Status: " + jsonArray.getJSONObject("manga").getJSONArray("entry").getJSONObject(i).getString("status") +
                        "\t" + "Score: " + jsonArray.getJSONObject("manga").getJSONArray("entry").getJSONObject(i).getString("score")+ "\n");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                mangaList.setAdapter(new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, list));
            }
        });


        mangaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                try {
                    bundle.putInt("id", jsonArray.getJSONObject("manga").getJSONArray("entry").getJSONObject(i).getInt("id"));
                    bundle.putString("title", jsonArray.getJSONObject("manga").getJSONArray("entry").getJSONObject(i).getString("title"));

                    MangaResults fragment = new MangaResults();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.main_fragmentLayout, fragment).addToBackStack(null).commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
}