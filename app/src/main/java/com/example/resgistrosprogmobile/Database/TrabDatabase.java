package com.example.resgistrosprogmobile.Database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.resgistrosprogmobile.Dao.AlunoDAO;
import com.example.resgistrosprogmobile.Dao.CursoDAO;
import com.example.resgistrosprogmobile.Entities.AlunoEnt;
import com.example.resgistrosprogmobile.Entities.CursoEnt;

@Database(entities = {AlunoEnt.class, CursoEnt.class},version = 1)
public abstract class TrabDatabase extends RoomDatabase {

    private static TrabDatabase INSTANCE;

    public static TrabDatabase getDatabase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder
                    (context.getApplicationContext(),
                     TrabDatabase.class,
                     "Controle de Cursos").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public abstract AlunoDAO alunoModel();
    public abstract CursoDAO cursoModel();
}
