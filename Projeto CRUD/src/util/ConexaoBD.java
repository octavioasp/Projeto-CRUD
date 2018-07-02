package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    public static Connection conexao = null;

    public static Connection getConexao() {

        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost/siscadastro", "postgres", "123");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return conexao;
    }

}
