package com.example.resgistrosprogmobile.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AlunoEnt {
    @PrimaryKey(autoGenerate = true)
    private int Alunoid;
    private int Cursoid;
    private String nomeAluno;
    private String cpf;
    private String email;
    private String telefone;

}
