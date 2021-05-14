package com.example.resgistrosprogmobile.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.resgistrosprogmobile.Database.TrabDatabase;
import com.example.resgistrosprogmobile.Entities.CursoEnt;
import com.example.resgistrosprogmobile.R;

import java.sql.SQLClientInfoException;

public class CursoForm extends AppCompatActivity {
    TrabDatabase db;
    CursoEnt dbCurso;
    EditText edtNome, qntHoras;
    Button btn_salvar, btn_excluir;
    int dbCursoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso_form);

        db = TrabDatabase.getDatabase(getApplicationContext());
        btn_salvar = findViewById(R.id.btn_salvar_curso);
        edtNome = findViewById(R.id.edtNome);
        qntHoras = findViewById(R.id.edtCargaHoraria);
        btn_excluir = findViewById(R.id.btn_excluir_curso);
        dbCursoId = getIntent().getIntExtra("cursoSelecionadoId", -1);
        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarCurso(view);
            }
        });
        btn_excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                excluirCurso(view);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(dbCursoId != -1)
            preencherCampos();
        else
            btn_excluir.setVisibility(View.GONE);
    }

    private void preencherCampos() {
        dbCurso = db.cursoModel().get(dbCursoId);
        edtNome.setText(dbCurso.getNomeCurso());
        qntHoras.setText("" + dbCurso.getQtdeHoras());
        btn_salvar.setText("Salvar");
    }

    public void salvarCurso(View view) {
        String horas = qntHoras.getText().toString();
        boolean isInt = true;

        try {
            Integer.parseInt(horas);
        } catch(Exception e) {
            isInt = false;
        }

        if(edtNome.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "O nome é obrigatório", Toast.LENGTH_SHORT).show();
            return;
        }

        if(qntHoras.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "A carga horária é obrigatória", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!isInt) {
            Toast.makeText(getApplicationContext(), "A carga horária deve ser um inteiro", Toast.LENGTH_SHORT).show();
            return;
        }

        CursoEnt curso = new CursoEnt(edtNome.getText().toString(), Integer.parseInt(horas));

        if(dbCurso != null) {
            db.cursoModel().updateName(edtNome.getText().toString(), dbCursoId);
            db.cursoModel().updateCarga(Integer.parseInt(horas), dbCursoId);
            Toast.makeText(getApplicationContext(), "Curso atualizado com sucesso", Toast.LENGTH_LONG).show();
        } else {
            db.cursoModel().insertAll(curso);
            Toast.makeText(getApplicationContext(), "Curso criado com sucesso", Toast.LENGTH_LONG).show();
        }

        finish();
    }

    public void excluirCurso(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Excluir curso")
                .setMessage("Tem certeza que deseja excluir este curso?")
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
        //db.cursoModel().delete(dbCurso);
        //Toast.makeText(getApplicationContext(), "Curso excluído com sucesso", Toast.LENGTH_LONG);show();
        //finish();

        try {
            db.cursoModel().delete(dbCurso);
            Toast.makeText(getApplicationContext(), "Curso excluído com sucesso", Toast.LENGTH_LONG).show();
        } catch(SQLiteConstraintException e) {
            Toast.makeText(getApplicationContext(), "O curso possui alunos matriculados", Toast.LENGTH_LONG).show();
        }
        finish();
    }
}