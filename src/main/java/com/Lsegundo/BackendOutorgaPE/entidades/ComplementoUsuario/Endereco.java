package com.Lsegundo.BackendOutorgaPE.entidades.ComplementoUsuario;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tabela_endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Coluna_ID")
    private UUID id;

    @Column(name = "Coluna_CEP")
    private String cep;

    @Column(name = "Coluna_Logradouro")
    private String logradouro;

    @Column(name = "Coluna_NumeroCasa")
    private String numeroCasa;


    @Column(name = "Coluna_Complemento")
    private String complemento;


    @Column(name = "Coluna_Bairro")
    private String bairro;

    @Column(name = "Coluna_Cidade")
    private String cidade;

    @Column(name = "Coluna_Estado")
    private String estado;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
