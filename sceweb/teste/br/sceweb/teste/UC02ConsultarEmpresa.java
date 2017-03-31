package br.sceweb.teste;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

public class UC02ConsultarEmpresa {
	public static Empresa empresa;
	public static EmpresaDAO empresaDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		empresa = new Empresa();
		empresaDAO = new EmpresaDAO();
		
		empresa.setNomeDaEmpresa("Casas Bahia S/A");
		empresa.setCnpj("60430951000122");
		empresa.setNomeFantasia("Casas Bahia");
		empresa.setEndereco("rua taquari");
		empresa.setTelefone("111111");
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Before
	public void preCondicao_insereEmpresa(){
		empresaDAO.adiciona(empresa);
	}

	@Test
	public void CT01UC02ConsultaEmpresa_cnpj_valido() {
		assertTrue(empresa.equals(empresaDAO.consultaEmpresa("60430951000122")));
	}

	@After
	public void tearDown() throws Exception {
		empresaDAO.exclui("60430951000122");
	}
	
}
