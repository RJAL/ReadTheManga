package com.example.ruilopes.readthemanga;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import java.util.concurrent.ExecutionException;


public class MangaResults extends Fragment {

    public MangaResults() {
        // Required empty public constructor


    }


    TextView tvTitle;
    TextView tvStatus;
    TextView tvSinopse;
    TextView tvScore;
    Button btnFav;
    ImageView imageView;
    View view;
    private JSONObject jarray;
    Spinner sp;
    Button btnSave;
    DataBase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = (View) inflater.inflate(R.layout.fragment_manga_results, container, false);

        tvTitle = (TextView) view.findViewById(R.id.textViewTitle);
        tvStatus = (TextView) view.findViewById(R.id.textViewStatus);
        tvSinopse = (TextView) view.findViewById(R.id.textViewSinopse);
        tvScore = (TextView) view.findViewById(R.id.textViewScore);
        btnFav = (Button) view.findViewById(R.id.buttonFavorite);
        imageView = (ImageView) view.findViewById(R.id.imageView3);
        sp = (Spinner) view.findViewById(R.id.spinnerStatus);
        btnSave = (Button) view.findViewById(R.id.buttonSave);
        db = new DataBase(getActivity());
        db.getWritableDatabase();

        Bundle b = getArguments();
        final int id = b.getInt("id");
        final String title = b.getString("title");

        String link = "";

        String xml = null;
        try {
            xml = new MangaParser().execute(title).get();
            jarray = XML.toJSONObject(xml);
            for (int i = 0; i < jarray.getJSONObject("manga").getJSONArray("entry").length(); i++) {
                if (id == jarray.getJSONObject("manga").getJSONArray("entry").getJSONObject(i).getInt("id")){
                    tvTitle.setText("Title: " + title);
                    tvStatus.setText("Status: " + jarray.getJSONObject("manga").getJSONArray("entry").getJSONObject(i).getString("status"));
                    link = jarray.getJSONObject("manga").getJSONArray("entry").getJSONObject(i).getString("image");
                    tvSinopse.setText("Sinopse: " + Html.fromHtml(jarray.getJSONObject("manga").getJSONArray("entry").getJSONObject(i).getString("synopsis")));
                    tvScore.setText("Score: " + jarray.getJSONObject("manga").getJSONArray("entry").getJSONObject(i).getString("score"));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            imageView.setImageBitmap(new MangaImage().execute(link).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        final String[] str = {"Completed", "Reading", "Plan to Read"};

        sp.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, str));

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addFavorite(id, title);
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addStatus(id, title, str[sp.getSelectedItemPosition()]);
            }
        });


        return view;
    }
}
