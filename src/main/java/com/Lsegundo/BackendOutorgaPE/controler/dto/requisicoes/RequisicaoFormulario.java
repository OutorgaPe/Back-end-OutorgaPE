package com.Lsegundo.BackendOutorgaPE.controler.dto.requisicoes;

public record RequisicaoFormulario (
        Long modalidade, String nomeImovel,
        String cep, String logradouro, String cidade,
        String latitude, String longitude, Long finalidade
) {
}
