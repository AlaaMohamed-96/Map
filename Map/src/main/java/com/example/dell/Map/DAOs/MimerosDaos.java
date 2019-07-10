package com.example.dell.projectx.DAOs;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.example.dell.projectx.model.Memory;
import java.util.List;

@Dao
public interface MimerosDaos {

    @Insert
    void InsertMemory(Memory memory);

    @Update
    void UpdateMemory(Memory memory);
    @Delete
    void DeleteMemory(Memory memory);



    @Query("delete from Memory where id=:id")
    void DeleteNoteByTitiel(int id);

    @Query("select * from Memory")
    List<Memory> getAllNote();

    @Query("select * from Memory where id=:id")
    List<Memory>SearchNoteByTitel(int id);



}
