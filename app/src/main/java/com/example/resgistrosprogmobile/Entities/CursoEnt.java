package com.example.resgistrosprogmobile.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CursoEnt {
    @PrimaryKey(autoGenerate = true)
    private int Cursoid;
    private String nomeCurso;
    private int qtdeHoras;

    public CursoEnt(int Cursoid, String nomeCurso, int qtdeHoras) {
        this.Cursoid = Cursoid;
        this.nomeCurso = nomeCurso;
        this.qtdeHoras = qtdeHoras;
    }

    public int getCursoid() {
        return Cursoid;
    }

    public void setCursoid(int cursoid) {
        Cursoid = cursoid;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public int getQtdeHoras() {
        return qtdeHoras;
    }

    public void setQtdeHoras(int qtdeHoras) {
        this.qtdeHoras = qtdeHoras;
    }

    @Override
    public String toString() {
        return "#" + getCursoid() + " " + getNomeCurso();
    }
}
