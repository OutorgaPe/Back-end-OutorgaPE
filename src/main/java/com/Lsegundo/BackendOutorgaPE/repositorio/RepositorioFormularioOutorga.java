package com.Lsegundo.BackendOutorgaPE.repositorio;

import com.Lsegundo.BackendOutorgaPE.entidades.FormularioOutorga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface RepositorioFormularioOutorga extends JpaRepository<FormularioOutorga, Long> {


    List<FormularioOutorga> findAllByUsuario_Id(UUID id);
    List<FormularioOutorga> findAllByUsuario_IdAndFinalidade_Id(UUID id, long finalidade);
    List<FormularioOutorga> findAllByUsuario_IdAndModalidade_Id(UUID id, long modalidade);
    List<FormularioOutorga> findAllByUsuario_IdAndStatussForm_Id(UUID id, long statuss);
    List<FormularioOutorga> findAllByStatussForm_Id( long statuss);


    Optional<FormularioOutorga> findByIdAndUsuario_Id(Long aLong, UUID usuario);
}
