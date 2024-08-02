package com.Lsegundo.BackendOutorgaPE.controler;

import com.Lsegundo.BackendOutorgaPE.controler.dto.respostas.RespostaExibicaoDados;
import com.Lsegundo.BackendOutorgaPE.entidades.FormularioOutorga;
import com.Lsegundo.BackendOutorgaPE.repositorio.ComplementoFormularioRepositorio.RepositorioFinalidade;
import com.Lsegundo.BackendOutorgaPE.repositorio.ComplementoFormularioRepositorio.RepositorioStatus;
import com.Lsegundo.BackendOutorgaPE.repositorio.ComplementoFormularioRepositorio.RepositorioTipoInterferencia;
import com.Lsegundo.BackendOutorgaPE.repositorio.RepositorioFormularioOutorga;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class FuncionarioMetodosControler {

    private RepositorioFormularioOutorga formRepo;
    private RepositorioFinalidade finalidadeRepo;
    private RepositorioTipoInterferencia interRepo;
    private RepositorioStatus statusRepo;

    public FuncionarioMetodosControler(RepositorioFormularioOutorga formRepo, RepositorioStatus statusRepo, RepositorioFinalidade finalidadeRepo, RepositorioTipoInterferencia interRepo) {
        this.formRepo = formRepo;
        this.finalidadeRepo = finalidadeRepo;
        this.interRepo = interRepo;
        this.statusRepo = statusRepo;
    }






    @GetMapping("/Funcionario/listarStatus/{referencia}")
    @Transactional
    @PreAuthorize("hasAuthority('SCOPE_Funcionario')")
    public ResponseEntity<List<RespostaExibicaoDados>> listarStatus(@PathVariable String referencia, JwtAuthenticationToken token){

        var statuss = statusRepo.findByNome(referencia);

        if (!statuss.isPresent()) return ResponseEntity.notFound().build();

        var lista = formRepo.findAllByStatussForm_Id( statuss.get().getId());

        if(lista.isEmpty()) return ResponseEntity.noContent().build();

        var resposta = tratarLista(lista);

        return ResponseEntity.ok(resposta);

    }

    @PostMapping("/Funcionario/analizar/{referencia}/{acao}")
    @Transactional
    @PreAuthorize("hasAuthority('SCOPE_Funcionario')")
    public ResponseEntity<String> confirmarAnalise(@PathVariable Long referencia,@PathVariable String acao,   JwtAuthenticationToken token){

        var formulario = formRepo.findById(referencia).get();

        if(formulario.getStatussForm().getNome().equals("Aguardando")) {

        if (acao.equals("875376")) formulario.setStatussForm(statusRepo.findByNome("Aceito").get());
            else if (acao.equals("430273")) formulario.setStatussForm(statusRepo.findByNome("Recusado").get());
                else return ResponseEntity.badRequest().build();
        formRepo.save(formulario);

        return ResponseEntity.ok("operação bem sucedida");
        }
        return ResponseEntity.unprocessableEntity().build();
    }



        public List<RespostaExibicaoDados> tratarLista(List<FormularioOutorga> lista){


        var resposta = new ArrayList<RespostaExibicaoDados>();

        for (FormularioOutorga i : lista ){
            var item =  new RespostaExibicaoDados(

                    i.getId().toString(),
                    i.getStatussForm().getNome(),
                    i.getDataString(),
                    i.getHoraString(),
                    i.getModalidade().getNome(),
                    i.getNomeImovel(),
                    i.getCep(),
                    i.getLogradouro(),
                    i.getCidade(),
                    i.getLatitude(),
                    i.getLongitude(),
                    i.getFinalidade().getNome(),
                    i.getUsuario().getNome(),
                    i.getUsuario().getCpfCnpj(),
                    i.getUsuario().getEndereco().getCep(),
                    i.getUsuario().getEndereco().getLogradouro().toString(),
                    i.getUsuario().getEndereco().getNumeroCasa(),
                    i.getUsuario().getEndereco().getComplemento(),
                    i.getUsuario().getEndereco().getBairro(),
                    i.getUsuario().getEndereco().getCidade(),
                    i.getUsuario().getEndereco().getEstado(),
                    i.getUsuario().getContatos().getEmail(),
                    i.getUsuario().getContatos().getTelefone(),
                    i.getUsuario().getContatos().getCelular()
            );

            resposta.add(item);

        }
        return resposta;
    }



}
