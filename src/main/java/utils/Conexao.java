package utils;

import java.sql.Connection;
import java.sql.SQLException;

public class Conexao {

    public static Connection getConexao() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/empresa";
        String usuario = "root"; // trocar caso necessario
        String senha = "1234567"; // inserir senha
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL não encontrado.", e);
        }
        return java.sql.DriverManager.getConnection(url, usuario, senha);
    }
}