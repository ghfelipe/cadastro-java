package com.ghfelipe.cadastro.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity(name = "tb_usuario")
@Table
public class Usuario implements UserDetails, Serializable {

    @Id
    @Column(name = "id_usuario")
    private int id;

    @Column(name = "tx_email", unique = true)
    private String email;

    @Column(name = "tx_senha",columnDefinition = "CHAR", length = 200)
    private String senha;

    @Column(name = "tx_codigo", length = 6)
    private String codigo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_usuario_perfil"
            , joinColumns = {@JoinColumn (name = "id_usuario")}
            , inverseJoinColumns = {@JoinColumn (name = "id_perfil")}
    )
    private List<Perfil> permissoes;

    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();
        for (Perfil perfil : permissoes) {
            roles.add(perfil.getNome());
        }
        return roles;
    }

    public Usuario() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getSenha() {
//        return senha;
//    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissoes;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Perfil> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Perfil> permissoes) {
        this.permissoes = permissoes;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int hashCode() {
        return Objects.hash(senha);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id && Objects.equals(email, usuario.email) && Objects.equals(senha, usuario.senha) && Objects.equals(permissoes, usuario.permissoes);
    }
}
