package com.Lsegundo.BackendOutorgaPE.controler.dto.respostas;

public record RespostaLista(
        String modalidade,
        String nomeImovel,
        String cpfCnpjUsuario,
        String cep,
        String logradouro,
        String cidade,
        String latitude,
        String longitude,
        String finalidade,
        String status
) {
}
