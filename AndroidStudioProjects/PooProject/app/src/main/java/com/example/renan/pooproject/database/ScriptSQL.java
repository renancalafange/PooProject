package com.example.renan.pooproject.database;

/**
 * Created by Renan on 16/10/2016.
 *
 *1) criar metodo de retorno do script
 *
 */

public class ScriptSQL {

    public static String getCreateJogador () {

        StringBuilder sqlBuilder = new StringBuilder(); //junção de strings
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS JOGADOR ( ");
        sqlBuilder.append("    _id                INTEGER       NOT NULL ");
        sqlBuilder.append("                                     PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("    NOME          VARCHAR (50), ");
        sqlBuilder.append("    TELEFONE          VARCHAR (14), ");
        sqlBuilder.append("    SKILL             FLOAT,   ");
        sqlBuilder.append("    TIPO              VARCHAR (10) ");
        sqlBuilder.append("); ");

        return sqlBuilder.toString();
    }

}
