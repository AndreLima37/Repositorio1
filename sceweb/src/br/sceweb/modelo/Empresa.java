package br.sceweb.modelo;


import java.util.InputMismatchException;
/**
 * 
 * @author Lab103 - Andre Lima
 * Esta é a classe de modelo "Empresa"
 * Esta classe possui os seguintes atributos:
 * - CNPJ
 * - Nome da empresa
 * - Nome fantasia
 * - Endereço
 * - Telefone
 * Todos os atributos serão armazenados na tabela "empresa" do banco de dados
 * 
 */
public class Empresa {
	String cnpj;
	String nomeDaEmpresa;
	String nomeFantasia;
	String endereco;
	String telefone;
	
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		if(cnpj.equals("")){
			this.cnpj = cnpj;
			throw new IllegalArgumentException("CNPJ inválido!");
		}else{
			this.cnpj = cnpj;
		}
	}
	public String getNomeDaEmpresa() {
		return nomeDaEmpresa;
	}
	public void setNomeDaEmpresa(String nomeDaEmpresa) {
		if(nomeDaEmpresa.equals("")){
			throw new IllegalArgumentException("Nome da empresa inválido!");
		}else{
			this.nomeDaEmpresa = nomeDaEmpresa;
		}
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		if(nomeFantasia.equals("")){
			this.nomeFantasia = nomeFantasia;
			throw new IllegalArgumentException("Nome fantasia inválido!");
		}else{
			this.nomeFantasia = nomeFantasia;
		}
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		if(endereco.equals("")){
			this.endereco = endereco;
			throw new IllegalArgumentException("Endereço inválido!");
		}else{
			this.endereco = endereco; 
		}
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		if(telefone.equals("")){
			this.telefone = telefone;
			throw new IllegalArgumentException("Telefone inválido!");
		}else{
			this.telefone = telefone; 
		}
	}


}
