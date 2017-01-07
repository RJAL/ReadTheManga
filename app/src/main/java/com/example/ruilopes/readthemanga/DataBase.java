package com.example.ruilopes.readthemanga;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper{

    //Nome da Base de Dados e Versão
    private static final String db_name = "MangaDataBase";
    private static final int db_version = 1;

    //Criação da Tabela e Campos
    private static final String manga_table = "manga";
    private static final String id = "id";


    private String title;
    private int chapters;
    private int chap_read;
    private int volumes;
    private int vol_read;
    private double score;
    private String state;

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
