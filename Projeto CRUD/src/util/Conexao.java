package util;

/*
 * @autor : Octavio Augusto da Silva Pereira
 * 
 * Classe que realiza a conexão com o postgreSQL
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Conexao {
	public static Connection conexao = null;
	public static ResourceBundle configuracao = ResourceBundle.getBundle(InformacoesBanco.CAMINHO_ARQUIVO);

	public static Connection getConexao() {

		try {
			Class.forName(configuracao.getString(InformacoesBanco.BANCO_DRIVER));
			conexao = DriverManager.getConnection(configuracao.getString(InformacoesBanco.BANCO_URL),
					configuracao.getString(InformacoesBanco.BANCO_USUARIO),
					configuracao.getString(InformacoesBanco.BANCO_SENHA));
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		}

		return conexao;
	}

}
