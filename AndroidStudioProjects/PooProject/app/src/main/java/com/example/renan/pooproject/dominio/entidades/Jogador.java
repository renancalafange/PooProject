package com.example.renan.pooproject.dominio.entidades;

import java.io.Serializable;

/**
 * Created by Renan on 16/10/2016.
 */

public class Jogador implements Serializable {

    public static String ID = "_id";
    public static String NOME = "NOME";
    public static String TELEFONE = "TELEFONE";
    public static String SKILL = "SKILL";
    public static String TIPO = "TIPO";

    private long id;
    private String nome;
    private String telefone;
    private String email;
    private float skill;
    private String tipo;

    //CONSTRUTORES
    public Jogador () {
        id=0;
    }


    //METODOS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getSkill() {
        return skill;
    }

    public void setSkill(float skill) {
        this.skill = skill;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString (){
        return nome.toUpperCase();
    }

}
