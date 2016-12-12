package com.example.renan.pooproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.renan.pooproject.dominio.entidades.Configuracao;

public class ActConfig extends AppCompatActivity implements View.OnClickListener {

    private EditText edtTempo;
    private EditText edtQtd;
    private Button btnOk;
    public Configuracao config = new Configuracao();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_config);

        btnOk = (Button)findViewById(R.id.btnOk);

        btnOk.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        config.setQtdJogadores(Integer.parseInt(edtQtd.getText().toString()));
        config.setTempo(Integer.parseInt(edtTempo.getText().toString()));
        finish();
    }
}
