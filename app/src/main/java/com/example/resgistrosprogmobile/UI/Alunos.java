package com.example.resgistrosprogmobile.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.resgistrosprogmobile.Database.TrabDatabase;
import com.example.resgistrosprogmobile.Entities.AlunoEnt;
import com.example.resgistrosprogmobile.Entities.CursoEnt;
import com.example.resgistrosprogmobile.R;

import java.util.List;

public class Alunos extends AppCompatActivity {

    TrabDatabase db;
    ListView listViewAlunos;
    ArrayAdapter<AlunoEnt> listAlunoAdapter;
    List<AlunoEnt> listAluno;
    Button bttAddAluno;
    Intent edtIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alunos);

        db = TrabDatabase.getDatabase(getApplicationContext());
        listViewAlunos = findViewById(R.id.listViewAlunos);
        bttAddAluno = findViewById(R.id.btn_novo_aluno);
        bttAddAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AlunoForm.class));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        edtIntent = new Intent(this, AlunoForm.class);
        listarAlunos();
    }

    private void listarAlunos() {
        listAluno = db.alunoModel().getAll();
        if(listAluno.size() == 0) {
            Toast.makeText(getApplicationContext(), "Não existem alunos criados. Por favor, crie um", Toast.LENGTH_LONG).show();
        }
        listAlunoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listAluno);
        listViewAlunos.setAdapter(listAlunoAdapter);
        listViewAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlunoEnt alunoSelect = listAluno.get(position);
                edtIntent.putExtra("alunoSelecionadoId", alunoSelect.getAlunoid());
                startActivity(edtIntent);
            }
        });

    }
}