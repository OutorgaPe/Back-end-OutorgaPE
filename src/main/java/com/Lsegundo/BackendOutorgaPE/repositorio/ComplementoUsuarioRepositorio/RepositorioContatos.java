package com.Lsegundo.BackendOutorgaPE.repositorio.ComplementoUsuarioRepositorio;

import com.Lsegundo.BackendOutorgaPE.entidades.ComplementoUsuario.Contatos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositorioContatos extends JpaRepository<Contatos, UUID> {

}
