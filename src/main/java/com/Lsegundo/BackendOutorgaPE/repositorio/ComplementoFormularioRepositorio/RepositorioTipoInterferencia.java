package com.Lsegundo.BackendOutorgaPE.repositorio.ComplementoFormularioRepositorio;

import com.Lsegundo.BackendOutorgaPE.entidades.ComplementoFormulario.Finalidade;
import com.Lsegundo.BackendOutorgaPE.entidades.ComplementoFormulario.TipoInterferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RepositorioTipoInterferencia extends JpaRepository<TipoInterferencia, Long> {
    Optional<TipoInterferencia> findByNome(String nome);

}
