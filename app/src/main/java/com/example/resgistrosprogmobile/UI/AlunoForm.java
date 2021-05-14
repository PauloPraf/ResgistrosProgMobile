package com.example.resgistrosprogmobile.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.resgistrosprogmobile.Database.TrabDatabase;
import com.example.resgistrosprogmobile.Entities.AlunoEnt;
import com.example.resgistrosprogmobile.Entities.CursoEnt;
import com.example.resgistrosprogmobile.R;

import java.util.List;

public class AlunoForm extends AppCompatActivity {
    TrabDatabase db;
    AlunoEnt dbAluno;
    List<CursoEnt> cursos;
    ArrayAdapter<CursoEnt> cursosAdapter;
    Spinner spnCursos;
    EditText edtNome, edtCpf, edtEmail, edtTelefone;
    Button btn_salvar, btn_excluir, btn_novo_curso;
    int dbAlunoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_form);

        db = TrabDatabase.getDatabase(getApplicationContext());
        btn_salvar = findViewById(R.id.btnCriar);
        edtNome = findViewById(R.id.edtNomeAluno);
        edtCpf = findViewById(R.id.edtCpf);
        edtEmail = findViewById(R.id.edtEmail);
        edtTelefone = findViewById(R.id.edtTel);
        btn_excluir = findViewById(R.id.btnDeletar);
        spnCursos = findViewById(R.id.spnCursos);
        btn_novo_curso = findViewById(R.id.btnCriarCursoAluno);
        dbAlunoId = getIntent().getIntExtra("alunoSelecionadoId", -1);
        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarAluno(view);
            }
        });
        btn_excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                excluirAluno(view);
            }
        });
        btn_novo_curso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CursoForm.class));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(dbAlunoId != -1)
            preencherCampos();
        else
            btn_excluir.setVisibility(View.GONE);
        preencherCursos();
    }

    private void preencherCampos() {
        dbAluno = db.alunoModel().get(dbAlunoId);
        edtNome.setText(dbAluno.getNomeAluno());
        edtCpf.setText(dbAluno.getCpf());
        edtEmail.setText(dbAluno.getEmail());
        edtTelefone.setText(dbAluno.getTelefone());
        btn_salvar.setText("Salvar");
    }

    private void preencherCursos() {
        cursos = db.cursoModel().getAll();
        if(cursos.size() == 0)
            Toast.makeText(getApplicationContext(), "Não existem cursos cadastrados. Por favor, cadastre um", Toast.LENGTH_LONG).show();
        cursosAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cursos);
        spnCursos.setAdapter(cursosAdapter);
        if(dbAluno != null) {
            int pos = cursosAdapter.getPosition(db.cursoModel().get(dbAluno.getCursoid()));
            spnCursos.setSelection(pos);
        }
    }

    public void salvarAluno(View view) {
        if(edtNome.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "O nome é obrigatório", Toast.LENGTH_SHORT).show();
            return;
        }

        if(edtCpf.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "O cpf é obrigatório", Toast.LENGTH_SHORT).show();
            return;
        }

        if(edtEmail.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "O Email é obrigatório", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!edtEmail.getText().toString().matches("([a-zA-Z0-9]+)@[a-z]{2,5}.[a-z]{2,4}")) {
            Toast.makeText(getApplicationContext(), "Informe um email válido", Toast.LENGTH_SHORT).show();
            return;
        }

        if(edtTelefone.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "O telefone é obrigatório", Toast.LENGTH_SHORT).show();
            return;
        }

        CursoEnt curso = (CursoEnt) spnCursos.getSelectedItem();

        if(curso == null) {
            Toast.makeText(getApplicationContext(), "Selecione um curso", Toast.LENGTH_SHORT).show();
            return;
        }

        AlunoEnt aluno = new AlunoEnt(curso.getCursoid(), edtNome.getText().toString(), edtCpf.getText().toString(), edtEmail.getText().toString(), edtTelefone.getText().toString());

        if(dbAluno != null) {
            db.alunoModel().updateCurso(curso.getCursoid(), dbAlunoId);
            db.alunoModel().updateName(edtNome.getText().toString(), dbAlunoId);
            db.alunoModel().updateCpf(edtCpf.getText().toString(), dbAlunoId);
            db.alunoModel().updateEmail(edtEmail.getText().toString(), dbAlunoId);
            db.alunoModel().updateTelefone(edtTelefone.getText().toString(), dbAlunoId);
            Toast.makeText(getApplicationContext(), "Aluno atualizado com sucesso", Toast.LENGTH_LONG).show();
        } else {
            db.alunoModel().insertAll(aluno);
            Toast.makeText(getApplicationContext(), "Aluno criado com sucesso", Toast.LENGTH_LONG).show();
        }

        finish();
    }

    public void excluirAluno(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Excluir aluno")
                .setMessage("Tem certeza que deseja excluir este aluno?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluir();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void excluir() {
        db.alunoModel().delete(dbAluno);
        Toast.makeText(getApplicationContext(), "Aluno excluído com sucesso", Toast.LENGTH_LONG).show();
        finish();
    }
}