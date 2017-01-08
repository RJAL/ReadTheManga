package com.example.ruilopes.readthemanga;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper{

    private static final String log = "DataBase";

    //Nome da Base de Dados e Versão
    private static final String db_name = "MangaDataBase";
    private static final int db_version = 1;

    //Criação da Tabela e Colunas
    private static final String manga_table = "manga";
    private static final String id = "id";
    private static final String title = "title";
    private static final String chapters = "chapters";
    private static final String chap_read = "chap_read";
    private static final String volumes = "volumes";
    private static final String vol_read = "vol_read";
    private static final String score = "score";
    private static final String status = "status";



    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("..........", "--MANGA_TABLE_CREATED--");

        String manga_table_create = "CREATE TABLE " + manga_table + " ( " + id + " INTEGER PRIMARY KEY, " +
                title + " TEXT, " + chapters + " INTEGER, " + chap_read + " INTEGER, " + volumes + " INTEGER, " +
                vol_read + " INTEGER, " + score + " INTEGER " + status + " TEXT)";

        sqLiteDatabase.execSQL(manga_table_create);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + manga_table);
        onCreate(sqLiteDatabase);
    }


    public void addManga(MangaAttributes mangaAttributes){
        SQLiteDatabase database = this.getWritableDatabase();
        String addQueryStatement = "INSERT INTO " + manga_table + " (" + id + ", " +
                title + ", " + chapters + ", " + chap_read + ", " + volumes + ", " +
                vol_read + ", " + score + ", " + status + ") VALUES (" +
                mangaAttributes.getId() + ", '" + mangaAttributes.getTitle() + "', '" + mangaAttributes.getChapters()
                + "','" + mangaAttributes.getChap_read() + "','" + mangaAttributes.getVolumes() + "','" +
                mangaAttributes.getVol_read() + "', '" + mangaAttributes.getScore() + "', '" +
                mangaAttributes.getStatus() + "');";

        database.execSQL(addQueryStatement);
        database.close();
    }


    public void updateManga(MangaAttributes mangaAttributes){
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQueryStatement = "UPDATE " + manga_table + " SET " + chap_read + " = '"
                + mangaAttributes.getChap_read() + "', " + vol_read + " = '"
                + mangaAttributes.getVol_read() + "', " + status + " = '" +
                mangaAttributes.getStatus() + "' WHERE " + id + " = " + mangaAttributes.getId() + ";";

        database.execSQL(updateQueryStatement);
        database.close();
    }


    public  void removeManga(MangaAttributes mangaAttributes){
        SQLiteDatabase database = this.getWritableDatabase();
        String removeQueryStatement = "DELETE FROM " + manga_table + " WHERE " + id + " = " + mangaAttributes.getId() + ";";

        database.execSQL(removeQueryStatement);
        database.close();
    }


    public ArrayList<MangaAttributes> mangas() {
        ArrayList<MangaAttributes> mangaList = new ArrayList<>();
        String selectQueryStatement = "SELECT * FROM " + manga_table + ";";

        SQLiteDatabase sqldatabase = this.getWritableDatabase();
        Cursor c = sqldatabase.rawQuery(selectQueryStatement, null);

        while (c.moveToNext()){
            MangaAttributes manga = new MangaAttributes();
            manga.setId(c.getInt(0));
            manga.setTitle(c.getString(1));
            manga.setChapters(c.getInt(2));
            manga.setChap_read(c.getInt(3));
            manga.setVolumes(c.getInt(4));
            manga.setVol_read(c.getInt(5));
            manga.setScore(c.getInt(6));
            manga.setStatus(c.getString(7));
        }
        return mangaList;
    }
}