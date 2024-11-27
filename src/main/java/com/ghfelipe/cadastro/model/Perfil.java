package com.ghfelipe.cadastro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Entity(name = "tb_perfil")
public class Perfil implements GrantedAuthority, Serializable {

    @Id
    @Column(name = "id_perfil")
    public int id;

    @Column(name = "tx_nome")
    public String nome;

    public Perfil() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getAuthority() {
        return this.nome;
    }
}
