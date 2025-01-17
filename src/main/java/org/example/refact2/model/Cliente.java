package org.example.refact2.model;

import java.math.BigDecimal;

public class Cliente {
    private BigDecimal id;
    private String nome;
    private String email;
    private String senha;
    private String situacao;

    public Cliente() {
    }

    public Cliente(String nome, String email, String senha, String situacao) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.situacao = situacao;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
