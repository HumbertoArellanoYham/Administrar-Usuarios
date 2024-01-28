package com.arellano.app.Repositorio;

import com.arellano.app.modelo.Usuario;
import com.arellano.app.util.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorioImpl implements IntefaceRepositorio<Usuario> {
    private static Connection getConnexion() throws SQLException {
        return ConexionBaseDatos.getInstancia();
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> allUsers = new ArrayList<>();

        try(Statement statement = getConnexion().createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from usuarios");

            while(resultSet.next()){
                Usuario nuevoUsuario = llenarUsuario(resultSet);
                allUsers.add(nuevoUsuario);
            }

            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allUsers;
    }

    @Override
    public Usuario buscarId(Long id) {
        Usuario usuario = null;

        try(PreparedStatement preparedStatement = getConnexion().prepareStatement("select u.* from usuarios as u where u.id = ?")) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                usuario = llenarUsuario(resultSet);
            }

            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usuario;
    }

    @Override
    public void guardar(Usuario usuario) {
        String sqlQuery = "";

        if(usuario.getId() != null && usuario.getId() > 0){
            sqlQuery = "update usuarios set username = ?, password = ?, email = ? where id = ?";
        } else {
            sqlQuery = "insert into usuarios(username, password, email) values(?, ?, ?)";
        }

        try(PreparedStatement preparedStatement = getConnexion().prepareStatement(sqlQuery)){
            preparedStatement.setString(1, usuario.getUsername());
            preparedStatement.setString(2, usuario.getPassword());
            preparedStatement.setString(3, usuario.getEmail());

            if(usuario.getId() != null && usuario.getId() > 0){
                preparedStatement.setLong(4, usuario.getId());
            }

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void eliminar(Long id) {
        try (PreparedStatement preparedStatement = getConnexion().prepareStatement("delete from usuarios where id = ?")){
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Usuario llenarUsuario(ResultSet resultSet) throws SQLException {
        Usuario usuario = new Usuario();

        usuario.setId(resultSet.getInt("id"));
        usuario.setUsername(resultSet.getString("username"));
        usuario.setPassword(resultSet.getString("password"));
        usuario.setEmail(resultSet.getString("email"));

        return  usuario;
    }
}
