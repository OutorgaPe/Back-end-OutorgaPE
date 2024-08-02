package com.Lsegundo.BackendOutorgaPE.controler;

import com.Lsegundo.BackendOutorgaPE.controler.dto.requisicoes.RequisicaoLogin;
import com.Lsegundo.BackendOutorgaPE.controler.dto.respostas.RespostaLogin;
import com.Lsegundo.BackendOutorgaPE.entidades.ComplementoUsuario.Funcao;
import com.Lsegundo.BackendOutorgaPE.repositorio.RepositorioUsuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class tokencontroler {

    private JwtEncoder codificador;
    private RepositorioUsuario usuarioRepo;
    private BCryptPasswordEncoder codificadorSenha;

    public tokencontroler(JwtEncoder codificador, RepositorioUsuario usuarioRepo, BCryptPasswordEncoder codificadorSenha) {
        this.codificador = codificador;
        this.usuarioRepo = usuarioRepo;
        this.codificadorSenha = codificadorSenha;
    }

    @PostMapping("/login")
    public ResponseEntity<RespostaLogin> loginUsuario(@RequestBody RequisicaoLogin requisicao){

        var usuario = usuarioRepo.findByNome(requisicao.login());

        if (usuario.isEmpty()) return ResponseEntity.notFound().build();

        if (!usuario.get().comparacao(requisicao, codificadorSenha)) return ResponseEntity.badRequest().build();

        var agora = Instant.now();

        var expiresIn = 300L;

        var scopes = usuario.get().getFuncoes()
                .stream()
                .map(Funcao::getNome)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("OutorgaPE")
                .subject(usuario.get().getId().toString())
                .issuedAt(agora)
                .expiresAt(agora.plusSeconds(expiresIn))
                .claim("scope", scopes)
                .build();

        var valorJwt = codificador.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new RespostaLogin(valorJwt, expiresIn));
    }
}
