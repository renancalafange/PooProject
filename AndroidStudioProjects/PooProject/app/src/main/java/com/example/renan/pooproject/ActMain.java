package com.example.renan.pooproject;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ActMain extends AppCompatActivity implements View.OnClickListener {

    private Button btnJogadores;
    private Button btnConfiguracao;
    private Button btnJogar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJogadores = (Button)findViewById(R.id.btnJogadores);
        btnConfiguracao = (Button)findViewById(R.id.btnConfiguracao);
        btnJogar = (Button)findViewById(R.id.btnJogar);

        btnJogadores.setOnClickListener(this);



        btnConfiguracao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(ActMain.this, ActConfig.class);
                startActivity(it);
            }
        });


    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(this,ActLista.class);
        startActivity(it);
    }
}
