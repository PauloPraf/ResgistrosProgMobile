package com.example.resgistrosprogmobile.Entities;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.resgistrosprogmobile.Database.TrabDatabase;

@Entity(foreignKeys = @ForeignKey(entity = CursoEnt.class,
        parentColumns = "Cursoid", childColumns = "Cursoid"))
public class AlunoEnt {
    @PrimaryKey(autoGenerate = true)
    private int Alunoid;
    private int Cursoid;
    private String nomeAluno;
    private String cpf;
    private String email;
    private String telefone;

    public AlunoEnt(int Cursoid, String nomeAluno, String cpf, String email, String telefone) {
        this.Cursoid = Cursoid;
        this.nomeAluno = nomeAluno;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public int getAlunoid() {
        return Alunoid;
    }

    public void setAlunoid(int alunoid) {
        Alunoid = alunoid;
    }

    public int getCursoid() {
        return Cursoid;
    }

    public void setCursoid(int cursoid) {
        Cursoid = cursoid;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        TrabDatabase db = TrabDatabase.INSTANCE;
        CursoEnt curso = db.cursoModel().get(getCursoid());
        return "#" + getAlunoid() + " - " + getNomeAluno() + " - " + curso.getNomeCurso();
    }
}
