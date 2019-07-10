package com.example.dell.projectx;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.dell.projectx.DAOs.MimerosDaos;
import com.example.dell.projectx.model.Memory;


@Database(entities = {Memory.class},version = 1,exportSchema = false)
public abstract class MemoryDataBase extends RoomDatabase {

    private static final String DATABASE_NAME = "memorDataBase";
    private static MemoryDataBase dataBase;
    public abstract MimerosDaos mimerosDaos();

    public static MemoryDataBase getInstance(Context context){
        if (dataBase==null){
            dataBase=
            Room.databaseBuilder(context,
                    MemoryDataBase.class,
                    DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return dataBase;
    }
}
