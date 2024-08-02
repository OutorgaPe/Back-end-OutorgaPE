package com.Lsegundo.BackendOutorgaPE.controler;

import com.Lsegundo.BackendOutorgaPE.controler.dto.respostas.RespostaItem;
import com.Lsegundo.BackendOutorgaPE.entidades.FormularioOutorga;
import com.Lsegundo.BackendOutorgaPE.repositorio.ComplementoFormularioRepositorio.RepositorioFinalidade;
import com.Lsegundo.BackendOutorgaPE.repositorio.ComplementoFormularioRepositorio.RepositorioStatus;
import com.Lsegundo.BackendOutorgaPE.repositorio.ComplementoFormularioRepositorio.RepositorioTipoInterferencia;
import com.Lsegundo.BackendOutorgaPE.repositorio.RepositorioFormularioOutorga;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
public class ListagemControler {

    private RepositorioFormularioOutorga formRepo;
    private RepositorioFinalidade finalidadeRepo;
    private RepositorioTipoInterferencia interRepo;
    private RepositorioStatus statusRepo;

    public ListagemControler(RepositorioFormularioOutorga formRepo, RepositorioStatus statusRepo,RepositorioFinalidade finalidadeRepo, RepositorioTipoInterferencia interRepo) {
        this.formRepo = formRepo;
        this.finalidadeRepo = finalidadeRepo;
        this.interRepo = interRepo;
        this.statusRepo = statusRepo;
    }

    @GetMapping("/listar")
    @Transactional
    public ResponseEntity<List<RespostaItem>> listar( JwtAuthenticationToken token){

        UUID usuarioID = UUID.fromString(token.getName());

        var lista = formRepo.findAllByUsuario_Id(usuarioID);

        if(lista.isEmpty()) return ResponseEntity.noContent().build();

        var resposta = tratarLista(lista);

        return ResponseEntity.ok(resposta);

    }

    @GetMapping("/listarFinalidade/{referencia}")
    @Transactional
    public ResponseEntity<List<RespostaItem>> listarFinalidade(@PathVariable String referencia, JwtAuthenticationToken token){

        UUID usuarioID = UUID.fromString(token.getName());

        var finalidade = finalidadeRepo.findByNome(referencia);

        if (!finalidade.isPresent()) return ResponseEntity.notFound().build();

        var lista = formRepo.findAllByUsuario_IdAndFinalidade_Id(usuarioID, finalidade.get().getId());

        if(lista.isEmpty()) return ResponseEntity.noContent().build();

        var resposta = tratarLista(lista);

        return ResponseEntity.ok(resposta);

    }

    @GetMapping("/listarTipoInter/{referencia}")
    @Transactional
    public ResponseEntity<List<RespostaItem>> listarModalidade(@PathVariable String referencia, JwtAuthenticationToken token){
        UUID usuarioID = UUID.fromString(token.getName());

        var interferencia = interRepo.findByNome(referencia);

        if (!interferencia.isPresent()) return ResponseEntity.notFound().build();

        var lista = formRepo.findAllByUsuario_IdAndModalidade_Id(usuarioID, interferencia.get().getId());

        if(lista.isEmpty()) return ResponseEntity.noContent().build();

        var resposta = tratarLista(lista);

        return ResponseEntity.ok(resposta);

    }

    @GetMapping("/listarStatus/{referencia}")
    @Transactional
    public ResponseEntity<List<RespostaItem>> listarStatus(@PathVariable String referencia, JwtAuthenticationToken token){

        UUID usuarioID = UUID.fromString(token.getName());

        var statuss = statusRepo.findByNome(referencia);

        if (!statuss.isPresent()) return ResponseEntity.notFound().build();

        var lista = formRepo.findAllByUsuario_IdAndStatussForm_Id(usuarioID, statuss.get().getId());

        if(lista.isEmpty()) return ResponseEntity.noContent().build();

        var resposta = tratarLista(lista);

        return ResponseEntity.ok(resposta);

    }

    public List<RespostaItem> tratarLista(List<FormularioOutorga> lista){


        var resposta = new ArrayList<RespostaItem>();

        for (FormularioOutorga i : lista ){
            var item =  new RespostaItem(
                    
                    i.getId().toString(),
                    i.getStatussForm().getNome(),
                    i.getDataString(),
                    i.getHoraString()
            );

            resposta.add(item);

        }
        return resposta;
    }


}
