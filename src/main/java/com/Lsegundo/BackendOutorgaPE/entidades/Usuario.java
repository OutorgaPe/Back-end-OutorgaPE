package com.Lsegundo.BackendOutorgaPE.entidades;

import com.Lsegundo.BackendOutorgaPE.controler.dto.requisicoes.RequisicaoCadastro;
import com.Lsegundo.BackendOutorgaPE.controler.dto.requisicoes.RequisicaoLogin;
import com.Lsegundo.BackendOutorgaPE.entidades.ComplementoUsuario.Contatos;
import com.Lsegundo.BackendOutorgaPE.entidades.ComplementoUsuario.Endereco;
import com.Lsegundo.BackendOutorgaPE.entidades.ComplementoUsuario.Funcao;
import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Tabela_Usuarios")
public class Usuario {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        @Column(name = "Coluna_ID")
        private UUID id;
        @Column(name = "Coluna_Nome")
        private String nome;

        @Column(name = "Coluna_Senha")
        private String senha;

        @Column(name = "Coluna_CPF/CNPJ", unique = true)
        private String cpfCnpj;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn
                (
                        name = "Endereco_ID",
                        referencedColumnName = "Coluna_ID"
                )
        private Endereco endereco;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn
                (
                        name = "Contatos_ID",
                        referencedColumnName = "Coluna_ID"
                )
        private Contatos contatos;
        @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
        private List<FormularioOutorga> formularios;

        @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        @JoinTable
        (
                name = "Tabela_Usua_funcao",
                joinColumns = @JoinColumn(name = "Id_Usuario"),
                inverseJoinColumns = @JoinColumn(name = "Id_funcao")
        )
        private Set<Funcao> Funcoes;

        @Column(name = "tabela_Data_Criacao")
        private LocalDateTime dataCriacao;

    public Usuario() {
    }

    public Usuario(RequisicaoCadastro requisicao, Funcao funcao, BCryptPasswordEncoder codificadorSenha){

        this.nome = requisicao.nome();
        this.senha = codificadorSenha.encode(requisicao.senha());
        this.cpfCnpj = requisicao.cpfCnpj();
        this.setFuncoes(Set.of(funcao));

        this.endereco = new Endereco();
        this.endereco.setNumeroCasa(requisicao.numeroCasa());
        this.endereco.setCep(requisicao.cep());
        this.endereco.setBairro(requisicao.bairro());
        this.endereco.setCidade(requisicao.cidade());
        this.endereco.setEstado(requisicao.estado());
        this.endereco.setLogradouro(requisicao.logradouro());
        this.endereco.setComplemento(requisicao.complemento());

        this.contatos = new Contatos();
        this.contatos.setCelular(requisicao.celular());
        this.contatos.setTelefone(requisicao.telefone());
        this.contatos.setEmail(requisicao.email());

        this.dataCriacao = LocalDateTime.now();

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Contatos getContatos() {
        return contatos;
    }

    public void setContatos(Contatos contatos) {
        this.contatos = contatos;
    }

    public List<FormularioOutorga> getFormularios() {
        return formularios;
    }

    public void setFormularios(List<FormularioOutorga> formularios) {
        this.formularios = formularios;
    }

    public Set<Funcao> getFuncoes() {
        return Funcoes;
    }

    public void setFuncoes(Set<Funcao> funcoes) {
        Funcoes = funcoes;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String dataFormatada = dataCriacao.format(formatter);
        return dataFormatada;
    }

    public String getHoraString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String horaFormatada = dataCriacao.format(formatter);
        return horaFormatada;
    }

    public boolean comparacao(RequisicaoLogin requisicao, PasswordEncoder codificador){
        return codificador.matches(requisicao.senha(), this.senha );
    }
    
    public boolean validacao() {

        if (
                nome != null
                && senha != null
                && cpfCnpj != null
                && endereco.getBairro() != null
                && endereco.getCep() != null
                && endereco.getCidade() != null
                && endereco.getEstado() != null
                && endereco.getLogradouro() != null
                && endereco.getNumeroCasa() != null
                && contatos.getCelular() != null
                && contatos.getEmail() != null
                && contatos.getTelefone() != null
        )  return true;
        else return false;
    }

}
