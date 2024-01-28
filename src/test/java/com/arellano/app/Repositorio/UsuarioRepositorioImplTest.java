package com.arellano.app.Repositorio;

import com.arellano.app.modelo.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioRepositorioImplTest {
    private static UsuarioRepositorioImpl repositorio;

    @Test
    void buscarIdTest(){
        Usuario user = new Usuario(1, "Humberto", "1234567", "humbertoyham25@gmail.com");
        Usuario userBD = repositorio.buscarId(1L);

        assertEquals(user, userBD);
    }
}