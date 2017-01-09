package com.example.ruilopes.readthemanga;


public class MangaAttributes {

    //Criação dos Atributos
    private int id;
    private String title;
    private boolean favorite;
    private String status;


    //Encapsulamento dos Atributos: Refactor - Encapsulate Fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}