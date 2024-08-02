package com.Lsegundo.BackendOutorgaPE.entidades.ComplementoUsuario;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "table_contatos")
public class Contatos {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Coluna_ID")
    private UUID id;
    @Column(name = "Coluna_Email", unique = true)
    private String email;

    @Column(name = "Coluna_Telefone", unique = true)
    private String telefone;

    @Column(name = "Coluna_Celular", unique = true)
    private String celular;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
