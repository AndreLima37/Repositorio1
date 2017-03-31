package br.sceweb.controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

/**
 * Servlet implementation class ServletControle
 */
public class ServletControle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ServletControle.class);
	String mensagem = "";
	EmpresaDAO empresaDAO;
	String cnpjParaExclusao = "";// seta o cnpj para exclusao

	/**
	 * Default constructor.
	 */
	public ServletControle() {
		// TODO Auto-generated constructor stub

	}

	/**
	 * @param request - requisicao
	 * @param response - resposta do html
	 * @throws ServletException - dispara excecao de servlet
	 * @throws IOException - dispara excecao de io
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 	throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @param request - requisicao
	 * @param response - resposta do html
	 * @throws ServletException - dispara excecao de servlet
	 * @throws IOException - dispara excecao de io
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("erro", null);
		executaComando(request, response);
	}
	
	/**
	 * @param request - requisicao
	 * @param response - resposta do html
	 * @throws ServletException - dispara excecao de servlet
	 * @throws IOException - dispara excecao de io
	 */
	protected void executaComando(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String parametro = request.getParameter("acao");
		logger.info("acao do servletcontrole = " + parametro);
		String url = "";
		String resultado = "";
		request.setAttribute("erro", null);
		if (parametro.equals("IncluirEmpresa")) {
			url = "/visao/FormEmpresa.jsp";
			try {
				resultado = cadastrarEmpresa(request.getParameter("txtCNPJ"), request.getParameter("txtNomeDaEmpresa"),
						request.getParameter("txtNomeFantasia"), request.getParameter("txtEndereco"),
						request.getParameter("txtTelefone"));
				logger.info("resultado do cadastra = " + resultado);
				request.setAttribute("msg", resultado);
				request.getRequestDispatcher(url).forward(request, response);
			} catch (Exception e) {
				request.setAttribute("msg", resultado);
				request.getRequestDispatcher(url).forward(request, response);
				logger.info("erro  = " + e.getMessage());

			}
		}
		if (parametro.equals("ConsultarEmpresa")) {
			url = "/visao/FormEmpresa.jsp";
			Empresa empresa = new Empresa();
			String cnpj = request.getParameter("txtCNPJ");
			logger.info("consulta empresa  = " + cnpj);
			try {
				if (!cnpj.isEmpty()) {
					empresa = consulta(cnpj);
					if (empresa != null){
						logger.info("consulta empresa nome da empresa  = " + empresa.getNomeDaEmpresa());
						request.setAttribute("nomeDaEmpresa", empresa.getNomeDaEmpresa());
					    request.setAttribute("cnpj", empresa.getCnpj());
					    request.setAttribute("nomeFantasia", empresa.getNomeFantasia());
					    request.setAttribute("endereco", empresa.getEndereco());
					    request.setAttribute("telefone", empresa.getTelefone());
					    request.setAttribute("msg", "");
						url = "/visao/FormEmpresaResultadoDaConsulta.jsp";
					} else {
						request.setAttribute("msg", "cnpj invalido");
					}
				} else {
					request.setAttribute("msg", "cnpj invalido");
				}
			} catch (Exception e) {
				logger.info(e.getMessage() + e.getCause());
			}
			request.getRequestDispatcher(url).forward(request, response);
			
		}

		
	}
	/**
	 * @param cnpj - cnpj da empresa a ser cadastrada
	 * @param nomeDaEmpresa - nome da empresa a ser cadastrada
	 * @param nomeFantasia - nome fantasia da empresa
	 * @param endereco - endereço fantasia da empresa a ser cadastrada
	 * @param telefone - telefone da empresa a ser cadastrada
	 * @return deve ser retornado uma mensagem com sucesso ou não no cadastro da empresa
	 */
	public String cadastrarEmpresa(String cnpj, String nomeDaEmpresa, String nomeFantasia, String endereco,
			String telefone) {
		String msg = "";
		Empresa empresa = new Empresa();
		EmpresaDAO empresaDAO = new EmpresaDAO();
		try {
			empresa.setCnpj(cnpj);
			empresa.setNomeDaEmpresa(nomeDaEmpresa);
			empresa.setNomeFantasia(nomeFantasia);
			empresa.setEndereco(endereco);
			empresa.setTelefone(telefone);
			empresaDAO.adiciona(empresa);
			msg = "cadastro realizado com sucesso";

		} catch (Exception e) {
			msg = e.getMessage();
		}

		return msg;
	}

	/**
	 * @param cnpj - cnpj da empresa a ser consultada
	 * @return retorna uma empresa consultada do banco de dados
	 */
	public Empresa consulta(String cnpj) {
		logger.info("consulta empresa 2 = " + cnpj);
		EmpresaDAO empresaDAO = new EmpresaDAO();
		return empresaDAO.consultaEmpresa(cnpj);
	}

	/**
	 * @param cnpj - cnpj da empresa a ser excluida
	 * @return retorna uma mensagem dizendo se uma empresa foi ou não excluida com uscesso
	 */
	public String excluirEmpresa(String cnpj) {
		String msg = "";
		EmpresaDAO empresaDAO = new EmpresaDAO();
		try {
			empresaDAO.exclui(cnpj);
			msg = "excluido com sucesso";
		} catch (Exception e) {
			msg = e.getMessage();
		}

		return msg;
	}
}
