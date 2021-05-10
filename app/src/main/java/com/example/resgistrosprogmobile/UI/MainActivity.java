package com.example.resgistrosprogmobile.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.resgistrosprogmobile.R;
import com.example.resgistrosprogmobile.UI.Alunos;
import com.example.resgistrosprogmobile.UI.Cursos;

public class MainActivity extends AppCompatActivity {
    private Button cursos,alunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cursos=findViewById(R.id.btt_cursos);
        alunos=findViewById(R.id.btt_alunos);

        cursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Cursos.class));
            }
        });
        alunos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Alunos.class));
            }
        });


    }
}