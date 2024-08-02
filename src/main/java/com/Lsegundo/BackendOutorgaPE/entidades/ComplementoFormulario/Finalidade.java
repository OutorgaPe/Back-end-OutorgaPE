package com.Lsegundo.BackendOutorgaPE.entidades.ComplementoFormulario;

import jakarta.persistence.*;

@Entity
@Table(name = "tabela_finalidade")
public class Finalidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Coluna_ID")
    private Long id;

    @Column(name = "Coluna_Nome")

    private String nome;

    public  enum valores{
        IRRIGACAO(1L),
        CONSUMO_ANIMAL(2L),
        CONSUMO_HUMANO(3L),
        ABASTECIMENTO_PUBLICO(4L),
        AQUICULTURA(5L),
        INDUSTRIA(6L),
        EXTRACAO_MINERAL(7L),
        DESASSOREAMENTO(8L);

        Long finalidade;

        valores(Long finalidade) {
            this.finalidade = finalidade;
        }

        public long getFinalidade() {
            return finalidade;
        }

        public void setFinalidade(Long finalidade) {
            this.finalidade = finalidade;
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
