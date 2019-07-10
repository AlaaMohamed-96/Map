package com.example.dell.projectx.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Memory {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo
    String description;
    @ColumnInfo
    double lang;
    @ColumnInfo
    double lat;
    @ColumnInfo
    String photo;
    @ColumnInfo
    String data;


    public Memory(){

    }



    @Ignore
    public Memory( String description, double lang, double lat, String data) {
        this.description = description;
        this.lang = lang;
        this.lat = lat;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLang() {
        return lang;
    }

    public void setLang(double lang) {
        this.lang = lang;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
