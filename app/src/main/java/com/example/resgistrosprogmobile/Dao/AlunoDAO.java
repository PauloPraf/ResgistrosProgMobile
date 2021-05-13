package com.example.resgistrosprogmobile.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.resgistrosprogmobile.Entities.AlunoEnt;

import java.util.List;

@Dao
public interface AlunoDAO {

    @Query("SELECT * FROM AlunoEnt WHERE Alunoid = :id LIMIT 1")
    AlunoEnt get(int id);

    @Query("SELECT * FROM AlunoEnt")
    List<AlunoEnt> getAll();

    @Query("SELECT * FROM AlunoEnt WHERE Alunoid IN (:alunoentId)")
    List<AlunoEnt> loadAllByIds(int[] alunoentId);

    @Query("SELECT * FROM AlunoEnt WHERE nomeAluno LIKE :name LIMIT 1")
    AlunoEnt findByName(String name);

    @Query("UPDATE AlunoEnt SET cursoId =:cursoID WHERE Alunoid == :alunoentId")
    void updateCurso(int cursoID, int alunoentId);

    @Query("UPDATE AlunoEnt SET nomeAluno =:name WHERE Alunoid == :alunoentId")
    void updateName(String name, int alunoentId);

    @Query("UPDATE AlunoEnt SET cpf =:cpf WHERE Alunoid == :alunoentId")
    void updateCpf(String cpf, int alunoentId);

    @Query("UPDATE AlunoEnt SET email =:email WHERE Alunoid == :alunoentId")
    void updateEmail(String email, int alunoentId);

    @Query("UPDATE AlunoEnt SET telefone =:telefone WHERE Alunoid == :alunoentId")
    void updateTelefone(String telefone, int alunoentId);

    @Insert
    void insertAll(AlunoEnt... aluno);

    @Delete
    void delete(AlunoEnt aluno);
}
