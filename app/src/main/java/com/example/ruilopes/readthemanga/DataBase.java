package com.example.ruilopes.readthemanga;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;


//Classe da base de dados
public class DataBase extends SQLiteOpenHelper{

    private static final String log = "DataBase";

    //Nome da Base de Dados e Versão
    private static final String db_name = "MangaDataBase";
    private static final int db_version = 1;

    //Criação da Tabela e Colunas
    private static final String manga_table = "manga";
    private static final String id = "id";
    private static final String title = "title";
    private static final String status = "status";
    private static final String favorite = "favorite";

    public DataBase(Context context) {
        super(context, db_name, null, db_version);
    }

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, db_name, null, db_version);
    }


    //Query Statements
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("..........", "--MANGA_TABLE_CREATED--");

        String manga_table_create = "CREATE TABLE " + manga_table + " ( " + id + " INTEGER PRIMARY KEY, " +
                title + " TEXT, " + favorite + " BOOLEAN, " + status + " TEXT)";

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
                title + ", " + favorite + ", " + status + ") VALUES (" +
                mangaAttributes.getId() + ", '" + mangaAttributes.getTitle() + "', '" + mangaAttributes.getStatus() + "');";

        database.execSQL(addQueryStatement);
        database.close();
    }


    public void updateManga(MangaAttributes mangaAttributes){
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQueryStatement = "UPDATE " + manga_table + " SET " + status + " = '" +
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
            manga.setStatus(c.getString(2));
        }
        return mangaList;
    }


    public ArrayList<MangaAttributes> favMangas() {
        ArrayList<MangaAttributes> mangaList = new ArrayList<>();
        String selectQueryStatement = "SELECT * FROM " + manga_table + " WHERE "+ favorite + " = 1 ;";

        SQLiteDatabase sqldatabase = this.getWritableDatabase();
        Cursor c = sqldatabase.rawQuery(selectQueryStatement, null);

        while (c.moveToNext()){
            MangaAttributes manga = new MangaAttributes();
            manga.setId(c.getInt(0));
            manga.setTitle(c.getString(1));
            mangaList.add(manga);
        }
        return mangaList;
    }


    public void test() {
        ArrayList<MangaAttributes> mangaList = new ArrayList<>();
        String selectQueryStatement = "SELECT * FROM " + manga_table + " WHERE "+ favorite + " = 1 ;";
        SQLiteDatabase sqldatabase = this.getWritableDatabase();
        Cursor c = sqldatabase.rawQuery(selectQueryStatement, null);
        while (c.moveToNext()){
            Log.d("id: ", c.getInt(0)+"");
            Log.d("name: ", c.getString(1));
        }
    }


    public void addFavorite(int i, String name){
        SQLiteDatabase database = this.getWritableDatabase();
        String ifQueryStatement = "SELECT 1 FROM " + manga_table + " WHERE " + id + " = " + i + ";";
        Cursor c = database.rawQuery(ifQueryStatement, null);
        String addFavQuery = "";
        if (c.getCount()==0){
            addFavQuery = "INSERT INTO " + manga_table + " (" + id + ", " +
                    title + ", " + favorite + ") VALUES (" + i + ", '" + name + "', 1);";
        }
        else {
            addFavQuery = "UPDATE " + manga_table + " SET " + favorite + " = 1 WHERE "
                    + id + " = " + i + ";";
        }
        database.execSQL(addFavQuery);
        database.close();
        test();
    }


    public void addStatus(int i, String name, String stats){
        SQLiteDatabase database = this.getWritableDatabase();
        String statusQuery = "SELECT 1 FROM " + manga_table + " WHERE " + id + " = " + i + ";";
        Cursor c = database.rawQuery(statusQuery, null);
        String addStatusQuery = "";
        if (c.getCount()==0){
            addStatusQuery = "INSERT INTO " + manga_table + " (" + id + ", " + title
                    + ", " + status + ") VALUES (" + i + ", '" + name + "', '" + stats + "');";
        }
        else {
            addStatusQuery = "UPDATE " + manga_table + " SET " + status + " = '" + stats + "' WHERE "
                    + id + " = " + i + ";";
        }
        database.execSQL(addStatusQuery);
        database.close();
    }


    public ArrayList<MangaAttributes> mylist() {
        ArrayList<MangaAttributes> mangaList = new ArrayList<>();
        String selectQueryStatement = "SELECT * FROM " + manga_table + " WHERE "+ status + " != '';";

        SQLiteDatabase sqldatabase = this.getWritableDatabase();
        Cursor c = sqldatabase.rawQuery(selectQueryStatement, null);

        while (c.moveToNext()){
            MangaAttributes manga = new MangaAttributes();
            manga.setId(c.getInt(0));
            manga.setTitle(c.getString(1));
            manga.setStatus(c.getString(3));
            mangaList.add(manga);
        }
        return mangaList;
    }
}