package model.dao;

import model.beans.Cliente;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

	PreparedStatement preparedStatement;
	ResultSet resultSet;
	private Connection connection;

	public ClienteDAO() {
		this.connection = Conexao.getConexao();

	}

	public void salvar(Cliente cliente) {

		String sql = "INSERT INTO cliente " + "("
				+ "cli_nome, cli_endereco, cli_cidade, cli_uf, cli_cep, cli_bairro, cli_cpf, "
				+ "cli_telefone, cli_email, cli_sexo" + ") VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

		try {

			this.connection = Conexao.getConexao();
			int posicao = 0;

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(++posicao, cliente.getNome());
			preparedStatement.setString(++posicao, cliente.getEndereco());
			preparedStatement.setString(++posicao, cliente.getCidade());
			preparedStatement.setString(++posicao, cliente.getUf());
			preparedStatement.setString(++posicao, cliente.getCep());
			preparedStatement.setString(++posicao, cliente.getBairro());
			preparedStatement.setString(++posicao, cliente.getCpf());
			preparedStatement.setString(++posicao, cliente.getTelefone());
			preparedStatement.setString(++posicao, cliente.getEmail());
			preparedStatement.setString(++posicao, cliente.getSexo());
			preparedStatement.execute();

			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Cliente> listar() {

		List<Cliente> list = new ArrayList<Cliente>();
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM cliente");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Cliente cliente = new Cliente();
				cliente.setCodigo(resultSet.getInt(1));
				cliente.setNome(resultSet.getString(2));
				cliente.setEndereco(resultSet.getString(3));
				cliente.setCidade(resultSet.getString(4));
				cliente.setUf(resultSet.getString(5));
				cliente.setCep(resultSet.getString(6));
				cliente.setBairro(resultSet.getString(7));
				cliente.setCpf(resultSet.getString(8));
				cliente.setTelefone(resultSet.getString(9));
				cliente.setEmail(resultSet.getString(10));
				cliente.setSexo(resultSet.getString(11));

				list.add(cliente);
			}
			preparedStatement.close();
			resultSet.close();
			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return list;
	}

	public void alterar(Cliente cliente) {

		String sql = "UPDATE cliente SET cli_nome = ?, cli_endereco = ?, cli_cidade = ?,"
				+ "cli_uf = ?, cli_cep = ?, cli_bairro = ?, cli_cpf = ?, cli_telefone = ?, cli_email = ?, cli_sexo = ? WHERE cli_codigo = ?";

		try {
			int posicao = 0;

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(++posicao, cliente.getNome());
			preparedStatement.setString(++posicao, cliente.getEndereco());
			preparedStatement.setString(++posicao, cliente.getCidade());
			preparedStatement.setString(++posicao, cliente.getUf());
			preparedStatement.setString(++posicao, cliente.getCep());
			preparedStatement.setString(++posicao, cliente.getBairro());
			preparedStatement.setString(++posicao, cliente.getCpf());
			preparedStatement.setString(++posicao, cliente.getTelefone());
			preparedStatement.setString(++posicao, cliente.getEmail());
			preparedStatement.setString(++posicao, cliente.getSexo());
			preparedStatement.setInt(++posicao, cliente.getCodigo());
			preparedStatement.execute();

			preparedStatement.close();
			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void excluir(int codigo) {
		try {
			preparedStatement = connection.prepareStatement("DELETE FROM cliente WHERE cli_codigo = ?");
			preparedStatement.setInt(1, codigo);
			preparedStatement.execute();

			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Cliente pesquisarId(int codigo) {

		Cliente cliente = new Cliente();

		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM cliente WHERE cli_codigo = ?",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			preparedStatement.setInt(1, codigo);
			resultSet = preparedStatement.executeQuery();
			resultSet.first();

			cliente.setCodigo(resultSet.getInt(1));
			cliente.setNome(resultSet.getString(2));
			cliente.setEndereco(resultSet.getString(3));
			cliente.setCidade(resultSet.getString(4));
			cliente.setUf(resultSet.getString(5));
			cliente.setCep(resultSet.getString(6));
			cliente.setBairro(resultSet.getString(7));
			cliente.setCpf(resultSet.getString(8));
			cliente.setTelefone(resultSet.getString(9));
			cliente.setEmail(resultSet.getString(10));
			cliente.setSexo(resultSet.getString(11));

			preparedStatement.close();
			resultSet.close();
			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return cliente;
	}

	public List<Cliente> pesquisarNome(String str) {

		List<Cliente> list = new ArrayList<Cliente>();
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM cliente WHERE cli_nome LIKE ?");
			preparedStatement.setString(1, '%' + str + '%');
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Cliente cliente = new Cliente();
				cliente.setCodigo(resultSet.getInt(1));
				cliente.setNome(resultSet.getString(2));
				cliente.setEndereco(resultSet.getString(3));
				cliente.setCidade(resultSet.getString(4));
				cliente.setUf(resultSet.getString(5));
				cliente.setCep(resultSet.getString(6));
				cliente.setBairro(resultSet.getString(7));
				cliente.setCpf(resultSet.getString(8));
				cliente.setTelefone(resultSet.getString(9));
				cliente.setEmail(resultSet.getString(10));
				cliente.setSexo(resultSet.getString(11));

				list.add(cliente);
			}
			preparedStatement.close();
			resultSet.close();
			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return list;
	}

	public ResultSet carregarGrade() {
		try {
			preparedStatement = connection.prepareStatement("SELECT cli_codigo, cli_nome FROM cliente ORDER BY cli_codigo");
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultSet;

	}

	public ResultSet pesquisa(String campo, String valor) {

		String sql = "SELECT cli_codigo, cli_nome, cli_endereco, cli_cidade, cli_uf, cli_cep, cli_bairro, cli_cpf, cli_telefone, cli_email, cli_sexo FROM cliente WHERE "
				+ campo + " ::varchar like '%" + valor + "%' ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultSet;

	}

	public List<String> nomeCampos() {
		List<String> campos = new ArrayList<String>();
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM cliente limit 1");
			resultSet = preparedStatement.executeQuery();
			ResultSetMetaData rSetMetaData = resultSet.getMetaData();

			for (int i = 1; i <= rSetMetaData.getColumnCount(); i++)
				campos.add(rSetMetaData.getColumnName(i));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return campos;

	}

}
