package com.Lsegundo.BackendOutorgaPE.controler;

import com.Lsegundo.BackendOutorgaPE.controler.dto.requisicoes.RequisicaoCadastro;
import com.Lsegundo.BackendOutorgaPE.entidades.ComplementoUsuario.Funcao;
import com.Lsegundo.BackendOutorgaPE.entidades.Usuario;
import com.Lsegundo.BackendOutorgaPE.repositorio.ComplementoUsuarioRepositorio.RepositorioFuncao;
import com.Lsegundo.BackendOutorgaPE.repositorio.RepositorioUsuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class UsuarioController {

    private RepositorioUsuario usuarioRepo;
    private BCryptPasswordEncoder codificadorSenha;
    private RepositorioFuncao funcaoRepo;

    public UsuarioController(RepositorioUsuario usuarioRepo, BCryptPasswordEncoder codificadorSenha, RepositorioFuncao funcaoRepo) {
        this.usuarioRepo = usuarioRepo;
        this.codificadorSenha = codificadorSenha;
        this.funcaoRepo = funcaoRepo;

    }

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity<String> metodoCadastro(@RequestBody RequisicaoCadastro requisicao){

        var funcaopadrao = funcaoRepo.findByNome(Funcao.Valores.USUARIO.name());

        var usuarioBanco = usuarioRepo.findByNome(requisicao.nome());

        System.out.println("resultado da validação de se o usuario está presente no banco:" + usuarioBanco.isPresent() );
        if(usuarioBanco.isPresent()) return ResponseEntity.unprocessableEntity().build();


        var usuario = new Usuario(requisicao , funcaopadrao.get(), codificadorSenha);

            System.out.println("resultado da validação :" + usuario.validacao() );
        if(usuario.validacao() == false)return ResponseEntity.badRequest().build();

        usuarioRepo.save(usuario);

        System.out.println("cadastro realizado com sucesso");
        return ResponseEntity.ok("Usuario cadastrado com sucesso");





    }

}
