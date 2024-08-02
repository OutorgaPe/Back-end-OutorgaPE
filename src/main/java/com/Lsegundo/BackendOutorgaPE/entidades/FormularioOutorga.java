package com.Lsegundo.BackendOutorgaPE.entidades;

import com.Lsegundo.BackendOutorgaPE.controler.dto.requisicoes.RequisicaoFormulario;
import com.Lsegundo.BackendOutorgaPE.entidades.ComplementoFormulario.Finalidade;
import com.Lsegundo.BackendOutorgaPE.entidades.ComplementoFormulario.StatusFormulario;
import com.Lsegundo.BackendOutorgaPE.entidades.ComplementoFormulario.TipoInterferencia;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Table(name = "tabela_form_utorga")
public class FormularioOutorga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Coluna_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "Modalidade_ID")
    private TipoInterferencia modalidade;

    @Column(name = "Coluna_NomeImovel")
    private String nomeImovel;

    @Column(name = "Coluna_CEP")
    private String cep;

    @Column(name = "Coluna_Logradouro")
    private String logradouro;

    @Column(name = "Coluna_Cidade")
    private String cidade;

    @Column(name = "Coluna_Latitude")
    private String latitude;

    @Column(name = "Coluna_Longitude")
    private String longitude;

    @ManyToOne
    @JoinColumn(name = "Finalidade_ID")
    private Finalidade finalidade;

    @ManyToOne
    @JoinColumn(name = "StatusForm_ID")
    private StatusFormulario statussForm;

    @Column(name = "tabela_Data_expedicao")
    private LocalDateTime dataExpedicao;

    public FormularioOutorga() {
    }

    public FormularioOutorga(RequisicaoFormulario requisicao, Usuario usuario, TipoInterferencia modalidade, Finalidade finalidade, StatusFormulario statuss){

        this.nomeImovel = requisicao.nomeImovel();
        this.cep = requisicao.cep();
        this.logradouro = requisicao.logradouro();
        this.cidade = requisicao.cidade();
        this.latitude = requisicao.latitude();
        this.longitude = requisicao.longitude();
        this.modalidade = modalidade;
        this.finalidade = finalidade;
        this.statussForm = statuss;
        this.usuario = usuario;
        this.dataExpedicao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoInterferencia getModalidade() {
        return modalidade;
    }

    public void setModalidade(TipoInterferencia modalidade) {
        this.modalidade = modalidade;
    }

    public String getNomeImovel() {
        return nomeImovel;
    }

    public void setNomeImovel(String nomeImovel) {
        this.nomeImovel = nomeImovel;
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Finalidade getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(Finalidade finalidade) {
        this.finalidade = finalidade;
    }

    public StatusFormulario getStatussForm() {
        return statussForm;
    }

    public void setStatussForm(StatusFormulario statussForm) {
        this.statussForm = statussForm;
    }

    public LocalDateTime getDataExpedicao() {
        return dataExpedicao;
    }

    public void setDataExpedicao(LocalDateTime dataExpedicao) {
        this.dataExpedicao = dataExpedicao;
    }

    public String getDataString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String dataFormatada = dataExpedicao.format(formatter);
        return dataFormatada;
    }

    public String getHoraString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String horaFormatada = dataExpedicao.format(formatter);
        return horaFormatada;
    }

    public boolean validacao(){
        if (
                nomeImovel != null &&
                cep != null &&
                logradouro != null &&
                cidade != null &&
                latitude != null &&
                longitude != null
        ) return true;
        else return false;
    }

}
