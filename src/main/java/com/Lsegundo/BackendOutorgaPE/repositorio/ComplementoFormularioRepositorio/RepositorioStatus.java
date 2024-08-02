package com.Lsegundo.BackendOutorgaPE.repositorio.ComplementoFormularioRepositorio;

import com.Lsegundo.BackendOutorgaPE.entidades.ComplementoFormulario.StatusFormulario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioStatus extends JpaRepository<StatusFormulario, Long> {

    Optional<StatusFormulario> findByNome(String nome);

}
