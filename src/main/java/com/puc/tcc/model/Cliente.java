package com.puc.tcc.model;



public class Cliente {

	
	private String cpf;
	private String nome;
	private String estado;
	private String municipio;
	private String endereco;
	private String telefone;
	private String email;
	private String senha;
	
	public Cliente() {}
	
	public Cliente(String CliCpf, String CliNome, String CliEst, String CliMun, String CliEnd, String CliTel, String CliEmail, String CliSenha ) {
		this.cpf=CliCpf;
		this.nome = CliNome;
		this.estado = CliEst;
		this.municipio = CliMun;
		this.endereco = CliEnd;
		this.telefone = CliTel;
		this.email = CliEmail;
		this.senha = CliSenha;
		
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
