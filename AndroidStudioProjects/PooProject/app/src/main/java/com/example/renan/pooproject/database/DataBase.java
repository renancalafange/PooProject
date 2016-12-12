package com.example.renan.pooproject.database;

/**
 * Created by Renan on 16/10/2016.
 *
 * 1) Herdar da SQLiteOpenHelper (abstrata) e criar um construtor default
 * 2) Criar o Script (classe) responsavel pela criação do DB. Criar no onCreate o metodo para executar o script
 */

import android.content.Context;
import android.database.sqlite.*;

public class DataBase extends SQLiteOpenHelper{


    public DataBase(Context context){ //construtor para classe abstrata
        super(context, "lista", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //passa por parametro a conexao valida do DB
        db.execSQL(ScriptSQL.getCreateJogador());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
