package com.Lsegundo.BackendOutorgaPE.entidades.ComplementoFormulario;

import jakarta.persistence.*;

@Entity
@Table(name = "tabela_tipo_interfe")
public class TipoInterferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Coluna_ID")
    private Long id;

    @Column(name = "Coluna_Nome")

    private String nome;

    public  enum valores{

        SUBTERRANEA(1L),
        SUPERFICIAL(2L);

        long interferencia;

        valores(long interferencia) {
            this.interferencia = interferencia;
        }

        public long getInterferencia() {
            return interferencia;
        }

        public void setInterferencia(long interferencia) {
            this.interferencia = interferencia;
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
