package com.Lsegundo.BackendOutorgaPE.repositorio.ComplementoUsuarioRepositorio;

import com.Lsegundo.BackendOutorgaPE.entidades.ComplementoUsuario.Funcao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RepositorioFuncao extends JpaRepository<Funcao, Long> {


    Optional<Funcao> findByNome(String nome);
}
