package com.example.renan.pooproject;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import com.example.renan.pooproject.app.MessageBox;
import com.example.renan.pooproject.database.DataBase;
import com.example.renan.pooproject.dominio.RepositorioJogador;
import com.example.renan.pooproject.dominio.entidades.Jogador;

public class ActLista extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private ImageButton btnAdd;
    private EditText edtPesquisa;
    private ListView listJogadores;
    private ArrayAdapter<Jogador> adpJogadores;

    private RepositorioJogador repositorioJogador;

    //BANCO DE DADOS
    private DataBase database;
    private SQLiteDatabase conexao;

    public static final String PAR_JOGADOR = "JOGADOR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_lista);

        btnAdd = (ImageButton)findViewById(R.id.btnAdd);
        edtPesquisa = (EditText)findViewById(R.id.edtPesquisa);
        listJogadores = (ListView)findViewById(R.id.listJogadores); //evento para abrir formulario ao clicar em um cadastro na lista

        
        btnAdd.setOnClickListener(this);
        listJogadores.setOnItemClickListener(this);

        try {
            database = new DataBase(this); //instanciando passando a activity como parametro
            conexao = database.getWritableDatabase();

            repositorioJogador = new RepositorioJogador(conexao);



            adpJogadores = repositorioJogador.buscarJogador(this);

            listJogadores.setAdapter(adpJogadores);

            //FiltroDeDados filtroDeDados = new FiltroDeDados(adpJogadores);
            edtPesquisa.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adpJogadores.getFilter().filter(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


        }catch(SQLException ex){
            MessageBox.show(this, "Erro", "Erro ao criar o DB: " + ex.getMessage());
        }

    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(this, ActCadastro.class); //class Intent para chamar outras activitys ou classes (classe que chama, classe chamada[activity])
        startActivityForResult(it,0); //
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        adpJogadores = repositorioJogador.buscarJogador(this);

        listJogadores.setAdapter(adpJogadores);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Jogador jogador = adpJogadores.getItem(position);//passa a posição

        Intent it = new Intent(this, ActCadastro.class);

        it.putExtra(PAR_JOGADOR, jogador);

        startActivityForResult(it,0);

    }

    /*private class FiltroDeDados implements TextWatcher{

        private ArrayAdapter<Jogador> arrayAdapter;

        private FiltroDeDados (ArrayAdapter<Jogador> arrayAdapter){
            this.arrayAdapter = arrayAdapter;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            arrayAdapter.getFilter().filter(s);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }*/

}
