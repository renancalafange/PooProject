package com.example.renan.pooproject;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.example.renan.pooproject.app.MessageBox;
import com.example.renan.pooproject.database.DataBase;
import com.example.renan.pooproject.dominio.RepositorioJogador;
import com.example.renan.pooproject.dominio.entidades.Jogador;

public class ActCadastro extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtTelefone;

    private RatingBar rbSkill;

    private Spinner spinnerTipo;

    private ArrayAdapter<String> adpTipo;

    //BANCO DE DADOS
    private DataBase database;
    private SQLiteDatabase conexao;
    private RepositorioJogador repositorioJogador;
    private Jogador jogador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cadastro);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);

        rbSkill = (RatingBar) findViewById(R.id.rbSkill);

        spinnerTipo = (Spinner) findViewById(R.id.spinnerTipo);


        adpTipo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item); //(activity que usará, layout para os objetos)
        adpTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//exibe todos os itens quando clica no spinneer


        spinnerTipo.setAdapter(adpTipo);

        adpTipo.add("Defensivo");
        adpTipo.add("Ofensivo");
        adpTipo.add("Ambos");

        rbSkill.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                jogador.setSkill(rating);
            }
        });

        Bundle bundle = getIntent().getExtras();

        if(bundle != null && bundle.containsKey(ActLista.PAR_JOGADOR)){
            jogador = (Jogador)bundle.getSerializable(ActLista.PAR_JOGADOR);
            preencherDados();
        }
        else
            jogador = new Jogador();

        try {
            database = new DataBase(this); //instanciando passando a activity como parametro
            conexao = database.getWritableDatabase();

            repositorioJogador = new RepositorioJogador(conexao);


        } catch (SQLException ex) {
            MessageBox.show(this, "Erro", "Erro ao criar o DB: " + ex.getMessage());
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actcadastro, menu);

        if(jogador.getId() != 0)
            menu.getItem(1).setVisible(true);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.mni_acao1:


                salvar();
                finish();

                break;

            case R.id.mni_acao2:
                excluir();
                finish();


                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void preencherDados(){
        edtNome.setText(jogador.getNome());
        edtTelefone.setText(jogador.getTelefone());
        spinnerTipo.setSelection( Integer.parseInt(jogador.getTipo()) );
        rbSkill.setRating(jogador.getSkill());
    }


    private void excluir(){

        try{
            repositorioJogador.excluir(jogador.getId());
        } catch(Exception ex) {
            MessageBox.show(this,"Erro", "Erro ao excluir jogador: " + ex.getMessage());
        }
    }

    private void salvar(){ //metodo para recuperar o dado dos objetos




        try {

            jogador.setNome(edtNome.getText().toString());
            jogador.setTelefone(edtTelefone.getText().toString());
            jogador.setSkill(rbSkill.getRating());
            jogador.setTipo(String.valueOf(spinnerTipo.getSelectedItemPosition()));

            if (jogador.getId() == 0)
                repositorioJogador.inserir(jogador);
            else
                repositorioJogador.alterar(jogador);

        }catch(Exception ex){
            MessageBox.show(this,"Erro", "Erro ao inserir jogador: " + ex.getMessage());

        }

    }


}
