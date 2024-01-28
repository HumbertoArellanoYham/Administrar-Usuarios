package com.arellano.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
    private static final String url = "jdbc:mysql://localhost:3306/proyect_users";
    private static final String user = "root";
    private static final String password = "rootsasa";
    private static Connection connection;

    public static Connection getInstancia() throws SQLException {
        if (connection == null){
            connection = DriverManager.getConnection(url, user, password);
        }

        return connection;
    }

}
