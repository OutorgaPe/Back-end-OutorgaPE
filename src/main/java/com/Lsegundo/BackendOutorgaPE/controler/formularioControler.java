package com.Lsegundo.BackendOutorgaPE.controler;

import com.Lsegundo.BackendOutorgaPE.controler.dto.requisicoes.RequisicaoFormulario;
import com.Lsegundo.BackendOutorgaPE.entidades.ComplementoFormulario.StatusFormulario;
import com.Lsegundo.BackendOutorgaPE.entidades.FormularioOutorga;
import com.Lsegundo.BackendOutorgaPE.repositorio.ComplementoFormularioRepositorio.RepositorioFinalidade;
import com.Lsegundo.BackendOutorgaPE.repositorio.ComplementoFormularioRepositorio.RepositorioStatus;
import com.Lsegundo.BackendOutorgaPE.repositorio.ComplementoFormularioRepositorio.RepositorioTipoInterferencia;
import com.Lsegundo.BackendOutorgaPE.repositorio.RepositorioFormularioOutorga;
import com.Lsegundo.BackendOutorgaPE.repositorio.RepositorioUsuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin("*")
public class formularioControler {

    private RepositorioUsuario usuarioRepo;
    private RepositorioFormularioOutorga formRepo;
    private RepositorioFinalidade finalidadeRepo;
    private RepositorioTipoInterferencia interRepo;
    private RepositorioStatus statusRepo;

    public formularioControler(RepositorioUsuario usuarioRepo, RepositorioFormularioOutorga formRepo, RepositorioFinalidade finalidadeRepo, RepositorioTipoInterferencia interRepo, RepositorioStatus statusRepo) {
        this.usuarioRepo = usuarioRepo;
        this.formRepo = formRepo;
        this.finalidadeRepo = finalidadeRepo;
        this.interRepo = interRepo;
        this.statusRepo = statusRepo;
    }

    @PostMapping("/cadastroForm")
    @Transactional
    public ResponseEntity<String> cadastrarform(@RequestBody RequisicaoFormulario requisicao, JwtAuthenticationToken token){

        var usuario = usuarioRepo.findById(UUID.fromString(token.getName()));

        if (!usuario.isPresent()) return ResponseEntity.notFound().build();

        var finalidade = finalidadeRepo.findById(requisicao.finalidade());

        var modalidade = interRepo.findById(requisicao.modalidade());

        var statuss = statusRepo.findById(StatusFormulario.valores.AGUARDANDO.getStatus());

        if (!finalidade.isPresent() || !modalidade.isPresent()) return ResponseEntity.badRequest().build();

        if(!statuss.isPresent()) return ResponseEntity.internalServerError().build();

        var formulario = new FormularioOutorga(requisicao,usuario.get(), modalidade.get(), finalidade.get(), statuss.get());

        if (formulario.validacao() == false) return ResponseEntity.unprocessableEntity().build();

        formRepo.save(formulario);
        return ResponseEntity.ok("Formulario cadastrado por sucesso.");
    }

    @DeleteMapping("/deletarForm/{id}")
    public ResponseEntity<String> deletarFormulario(@PathVariable("id") long id, JwtAuthenticationToken token){
        var usuarioId = UUID.fromString(token.getName());

        var formularioBanco = formRepo.findByIdAndUsuario_Id(id, usuarioId);

        if (!formularioBanco.isPresent()) return ResponseEntity.badRequest().build();

        formRepo.delete(formularioBanco.get());

        return ResponseEntity.ok("Formulario deletado com sucesso");


    }
}
