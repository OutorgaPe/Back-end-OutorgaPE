package com.Lsegundo.BackendOutorgaPE.configuracoes;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${jwt.publica.key}")
    private RSAPublicKey chavePublica;

    @Value("${jwt.privada.key}")
    private RSAPrivateKey chavePrivada;

    // configurando filtro de segurança, permitindo o uso das rotas "/login"
    // e "/cadastro" sem o uso de autenticação, defindo o gerenciamento de
    // sessão como stateles

    @Bean
    public SecurityFilterChain filtroSeguranca(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "cadastro").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .oauth2ResourceServer(oauth2-> oauth2.jwt(Customizer.withDefaults()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

            return http.build();

    }

    // criando o decodificador e o codificador que ultilizam das chaves publicas e privadas criadas para a aplicação.
    @Bean
    public JwtDecoder decodificador(){ return NimbusJwtDecoder.withPublicKey(chavePublica).build();}

    @Bean
    public JwtEncoder codificador(){
        JWK jwk = new RSAKey.Builder(this.chavePublica)
                .privateKey(this.chavePrivada)
                .build();
        var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));

        return new NimbusJwtEncoder(jwks);
    }

    // declarando codificador de senhas;
    @Bean
     public BCryptPasswordEncoder codificadorSenha(){return new BCryptPasswordEncoder();}

}
