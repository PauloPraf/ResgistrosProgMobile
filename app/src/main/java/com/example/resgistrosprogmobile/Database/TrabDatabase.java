package com.example.resgistrosprogmobile.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.resgistrosprogmobile.Entities.AlunoEnt;
import com.example.resgistrosprogmobile.Entities.CursoEnt;

@Database(entities = {AlunoEnt.class, CursoEnt.class},version = 1)
public abstract class TrabDatabase extends RoomDatabase {
}
