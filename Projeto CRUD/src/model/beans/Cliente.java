package model.beans;

import java.io.Serializable;
import java.util.Objects;

public class Cliente implements Serializable {
	private int codigo;
	private String nome;
	private String endereco;
	private String cidade;
	private String uf;
	private String cep;
	private String cpf;
	private String telefone;
	private String email;
	private String bairro;
	private String sexo;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		cep = cep.replaceAll("-", "");
		this.cep = cep;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		cpf = cpf.replaceAll("\\.", "").replaceAll("-", "");
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		telefone = telefone.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("-", "").replaceAll(" ", "");
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "Cliente{" + "codigo=" + codigo + ", nome='" + nome + '\'' + ", endereco='" + endereco + '\''
				+ ", cidade='" + cidade + '\'' + ", uf='" + uf + '\'' + ", cep='" + cep + '\'' + ", cpf='" + cpf + '\''
				+ ", sexo='" + sexo + '\'' + ", telefone='" + telefone + '\'' + ", email='" + email + '\''
				+ ", bairro='" + bairro + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Cliente cliente = (Cliente) o;
		return codigo == cliente.codigo && Objects.equals(nome, cliente.nome)
				&& Objects.equals(endereco, cliente.endereco) && Objects.equals(cidade, cliente.cidade)
				&& Objects.equals(uf, cliente.uf) && Objects.equals(cep, cliente.cep)
				&& Objects.equals(sexo, cliente.sexo) && Objects.equals(cpf, cliente.cpf)
				&& Objects.equals(telefone, cliente.telefone) && Objects.equals(email, cliente.email)
				&& Objects.equals(bairro, cliente.bairro);
	}

	@Override
	public int hashCode() {

		return Objects.hash(codigo, nome, endereco, cidade, uf, cep, bairro, cpf, telefone, email, sexo);
	}
}