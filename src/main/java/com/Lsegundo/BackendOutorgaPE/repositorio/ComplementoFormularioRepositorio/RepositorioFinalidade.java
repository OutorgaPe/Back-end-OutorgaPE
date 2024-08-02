package com.Lsegundo.BackendOutorgaPE.repositorio.ComplementoFormularioRepositorio;

import com.Lsegundo.BackendOutorgaPE.entidades.ComplementoFormulario.Finalidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RepositorioFinalidade extends JpaRepository<Finalidade, Long> {

    Optional<Finalidade> findByNome(String nome);
}
