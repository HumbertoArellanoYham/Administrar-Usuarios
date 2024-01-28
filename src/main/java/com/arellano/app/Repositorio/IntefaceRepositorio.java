package com.arellano.app.Repositorio;

import com.arellano.app.modelo.Usuario;

import java.util.List;

public interface IntefaceRepositorio<T> {
    List<T> listar();

    Usuario buscarId(Long id);

    void guardar(T t);

    void eliminar(Long id);
}
