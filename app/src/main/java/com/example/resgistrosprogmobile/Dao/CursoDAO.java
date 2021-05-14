package com.example.resgistrosprogmobile.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.resgistrosprogmobile.Entities.CursoEnt;

import java.util.List;

@Dao
public interface CursoDAO {

    @Query("SELECT * FROM CursoEnt WHERE Cursoid = :id LIMIT 1")
    CursoEnt get(int id);

    @Query("SELECT * FROM CursoEnt")
    List<CursoEnt> getAll();

    @Insert
    void insertAll(CursoEnt... cursoEnt);

    @Query("UPDATE CursoEnt SET nomeCurso =:name WHERE Cursoid == :Cursoid")
    void updateName(String name, int Cursoid);

    @Query("UPDATE CursoEnt SET qtdeHoras =:qtdeHoras WHERE Cursoid == :Cursoid")
    void updateCarga(int qtdeHoras, int Cursoid);

    @Query("SELECT count(1) FROM CursoEnt JOIN AlunoEnt ON (AlunoEnt.Cursoid =:cursoid)")
    int qntAlunos(int cursoid);

    @Delete
    void delete(CursoEnt cursos);
}
