package com.example.ruilopes.readthemanga;


public class MangaAttributes {

    //Criação dos Atributos
    private int id;
    private String title;
    private int chapters;
    private int chap_read;
    private int volumes;
    private int vol_read;
    private double score;
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

    public int getChapters() {
        return chapters;
    }

    public void setChapters(int chapters) {
        this.chapters = chapters;
    }

    public int getChap_read() {
        return chap_read;
    }

    public void setChap_read(int chap_read) {
        this.chap_read = chap_read;
    }

    public int getVolumes() {
        return volumes;
    }

    public void setVolumes(int volumes) {
        this.volumes = volumes;
    }

    public int getVol_read() {
        return vol_read;
    }

    public void setVol_read(int vol_read) {
        this.vol_read = vol_read;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
