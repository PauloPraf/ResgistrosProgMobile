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
import com.example.resgistrosprogmobile.Entities.CursoEnt;
import com.example.resgistrosprogmobile.R;

import java.util.List;

public class Cursos extends AppCompatActivity {
    TrabDatabase db;
    ListView listViewCurso;
    ArrayAdapter<CursoEnt> listCursoAdapter;
    List<CursoEnt> listCurso;
    Button bttAddCurso;
    Intent edtIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);

        db = TrabDatabase.getDatabase(getApplicationContext());
        listViewCurso = findViewById(R.id.listCurso);
        bttAddCurso = findViewById(R.id.btt_add_curso);
        bttAddCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CursoForm.class));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        edtIntent = new Intent(this, CursoForm.class);
        listarCursos();
    }

    private void listarCursos() {
        listCurso = db.cursoModel().getAll();
        if(listCurso.size() == 0) {
            Toast.makeText(getApplicationContext(), "Não existem cursos criados. Por favor, crie um", Toast.LENGTH_LONG).show();
        }
        listCursoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listCurso);
        listViewCurso.setAdapter(listCursoAdapter);
        listViewCurso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            CursoEnt cursoSelect = listCurso.get(position);
            edtIntent.putExtra("cursoSelecionadoId", cursoSelect.getCursoid());
            startActivity(edtIntent);
            }
        });

    }
}