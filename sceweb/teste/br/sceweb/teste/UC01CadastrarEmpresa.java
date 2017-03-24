package br.sceweb.teste;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

public class UC01CadastrarEmpresa {
	public static Empresa empresa;
	public static EmpresaDAO empresaDAO;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		empresa = new Empresa();
		empresaDAO = new EmpresaDAO();
		empresa.setNomeDaEmpresa("empresa x");
		empresa.setCnpj("89424232000180");
		empresa.setNomeFantasia("empresa x");
		empresa.setEndereco("rua taquari");
		empresa.setTelefone("2222");
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	@Test
	public void CT01UC01FB_cadastrar_empresa_com_sucesso() {
		assertEquals(1, empresaDAO.adiciona(empresa));
	}
	
	@Test(expected=RuntimeException.class)
	public void CT01A1UC01FB_cadastrar_empresa_com_cnpj_ja_cadastrado() {
		empresaDAO.adiciona(empresa);
		empresaDAO.adiciona(empresa);
	}
	
	
	
	/*
	 * verifica a validação dos dados
	 */
	@Test
	public void CT01UC01FB_verifica_validacao_dos_dados_com_sucesso() {
		try {
			empresa.setCnpj("78666151000170");
			empresa.setNomeDaEmpresa("Open informatica Ltda");
			empresa.setNomeFantasia("Open informatica");
			empresa.setEndereco("Av. Aguia de Haia 1783");
			empresa.setTelefone("12121212");
		} catch (Exception e) {
			fail("dados invalidos");
		}
	}
	
	@After
	public void excluiEmpresa() throws Exception{
		empresaDAO.exclui("89424232000180");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void CT02UC01FB_verifica_cnpj_invalido() {
		try {
			empresa.setCnpj("");
		} catch (Exception e) {
			assertEquals("CNPJ inválido!", e.getMessage());
		}
	}
	@Test
	public void CT03UC01FB_verifica_NomeDaEmpresa_invalido() {
		try {
			empresa.setNomeDaEmpresa("");
		} catch (Exception e) {
			assertEquals("Nome da empresa inválido!", e.getMessage());
		}
	}
	
	@Test
	public void CT04UC01FB_verifica_NomeFantasia_invalido() {
		try {
			empresa.setNomeFantasia("");
		} catch (Exception e) {
			assertEquals("Nome fantasia inválido!", e.getMessage());
		}
	}
	
	@Test
	public void CT05UC01FB_verifica_Endereco_invalido() {
		try {
			empresa.setEndereco("");
		} catch (Exception e) {
			assertEquals("Endereço inválido!", e.getMessage());
		}
	}
	
	@Test
	public void CT06UC01FB_verifica_Telefone_invalido() {
		try {
			empresa.setTelefone("");
		} catch (Exception e) {
			assertEquals("Telefone inválido!", e.getMessage());
		}
	}
	
	
	@Test
	public void CT07UC01FB_get_cnpj() {
		try {
			empresa.setCnpj("111222333444555");
			empresa.getCnpj();
		} catch (Exception e) {
			assertEquals("111222333444555", e.getMessage());
		}
	}
	
	@Test
	public void CT08UC01FB_get_nomeDaEmpresa() {
		try {
			empresa.setNomeDaEmpresa("Open informatica Ltda");
			empresa.getNomeDaEmpresa();
		} catch (Exception e) {
			assertEquals("Open informatica Ltda", e.getMessage());
		}
	}
	
	@Test
	public void CT010UC01FB_get_Endereco() {
		try {
			empresa.setEndereco("Av. Aguia de Haia 1783");
			empresa.getEndereco();
		} catch (Exception e) {
			assertEquals("Av. Aguia de Haia 1783", e.getMessage());
		}
	}
	
	@Test
	public void CT011UC01FB_get_Telefone() {
		try {
			empresa.setTelefone("1122226666");
			empresa.getTelefone();
		} catch (Exception e) {
			assertEquals("1122226666", e.getMessage());
		}
	}
	/*
	 * verifica a validação dos dados para cnpj invalido
	 */
	@Test(expected = IllegalArgumentException.class)
	public void CT02UC01A3cnpj_invalido() {
		empresa.setCnpj("");
	}


}
