package com.example.resgistrosprogmobile.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CursoEnt {
    @PrimaryKey(autoGenerate = true)
    private int Cursoid;
    private String nomeCurso;
    private int qtdeHoras;
}
