package com.example.renan.pooproject.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.example.renan.pooproject.dominio.entidades.Jogador;

/**
 * Created by Renan on 16/10/2016.
 *
 * 1)criar metodo para consulta da DB.
 */

public class RepositorioJogador {

    private SQLiteDatabase conexao;

    public RepositorioJogador(SQLiteDatabase conexao){
        this.conexao=conexao;
    }


    private ContentValues preencherContentValues (Jogador jogador){

        ContentValues values = new ContentValues();

        values.put("NOME", jogador.getNome());
        values.put("TELEFONE",jogador.getTelefone());
        values.put("SKILL", jogador.getSkill());
        values.put("TIPO",jogador.getTipo());

          return values;



    }

    public void excluir (long id){
        conexao.delete("JOGADOR", "_id = ?", new String[]{String.valueOf(id)});
    }


    public void alterar (Jogador jogador){

        ContentValues values = preencherContentValues(jogador);
        //conexao.insertOrThrow("JOGADOR",null,values);

        conexao.update("JOGADOR", values, "_id = ?", new String[]{String.valueOf(jogador.getId())});

    }

    public void inserir (Jogador jogador){

        ContentValues values = preencherContentValues(jogador);
        conexao.insertOrThrow("JOGADOR",null,values);

    }



    public ArrayAdapter<Jogador> buscarJogador (Context context) {//array adapter responsavel pelo preenchimento da lista

        ArrayAdapter<Jogador> adpJogador = new ArrayAdapter<Jogador>(context, android.R.layout.simple_list_item_1); //(objeto context, objeto da lista)

        Cursor cursor = conexao.query("JOGADOR",null, null, null, null, null, null);

        if(cursor.getCount() > 0){

            cursor.moveToFirst();

            do{
                Jogador jogador = new Jogador();
                jogador.setId(cursor.getLong(cursor.getColumnIndex(Jogador.ID)));
                jogador.setNome(cursor.getString(cursor.getColumnIndex(Jogador.NOME)));
                jogador.setTelefone(cursor.getString(cursor.getColumnIndex(Jogador.TELEFONE)));
                jogador.setTipo(cursor.getString(cursor.getColumnIndex(Jogador.TIPO)));
                jogador.setSkill(cursor.getInt(cursor.getColumnIndex(Jogador.SKILL)));
                adpJogador.add(jogador);
            }while(cursor.moveToNext());

        }
        return adpJogador;

    }


}
