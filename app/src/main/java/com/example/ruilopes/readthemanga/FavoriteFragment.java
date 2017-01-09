package com.example.ruilopes.readthemanga;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;


public class FavoriteFragment extends Fragment {


    public FavoriteFragment() {
        // Required empty public constructor
    }

    ListView lv;
    View view;
    DataBase db;
    ArrayList<MangaAttributes> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = (View) inflater.inflate(R.layout.fragment_favorite, container, false);

        lv = (ListView) view.findViewById(R.id.listViewFavorite);
        db = new DataBase(getActivity());
        db.getWritableDatabase();

        list = db.favMangas();

        ArrayList<String> favlist = new ArrayList<>();

        for(int i = 0; i < list.size(); i++ ){
            favlist.add("Title: " + list.get(i).getTitle());
        }

        lv.setAdapter(new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, favlist));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", list.get(i).getId());
                bundle.putString("title", list.get(i).getTitle());

                MangaResults fragment = new MangaResults();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.main_fragmentLayout, fragment).addToBackStack(null).commit();
            }
        });

        return view;
    }
}
