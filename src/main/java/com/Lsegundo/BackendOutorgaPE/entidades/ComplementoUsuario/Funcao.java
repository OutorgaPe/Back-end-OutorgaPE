package com.Lsegundo.BackendOutorgaPE.entidades.ComplementoUsuario;

import jakarta.persistence.*;

@Entity
@Table(name = "tabela_funcoes")
public class Funcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Coluna_ID")
    private Long id;

    @Column(name = "Coluna_Nome")

    private String nome;

    public enum Valores{

        FUNCIONARIO(1L),

        USUARIO(2L);

        Long funcaoId;

        Valores(Long funcaoId) {
            this.funcaoId = funcaoId;
        }

        public Long getFuncaoId() {
            return funcaoId;
        }

        public void setFuncaoId(Long funcaoId) {
            this.funcaoId = funcaoId;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
