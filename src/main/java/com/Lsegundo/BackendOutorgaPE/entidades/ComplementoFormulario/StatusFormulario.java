package com.Lsegundo.BackendOutorgaPE.entidades.ComplementoFormulario;

import jakarta.persistence.*;

@Entity
@Table(name = "tabela_status")
public class StatusFormulario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Coluna_ID")
    private Long id;

    @Column(name = "Coluna_Nome")

    private String nome;

    public  enum valores{

        AGUARDANDO(1L),
        ANALISE(2L),
        RECUSADO(3L),

        ACEITO(4L);

        long status;

        valores(long status) {
            this.status = status;
        }

        public long getStatus() {
            return status;
        }

        public void setInterferencia(long status) {
            this.status = status;
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
