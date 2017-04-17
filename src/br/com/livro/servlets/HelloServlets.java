package br.com.livro.servlets;

import java.io.IOException;
import java.rmi.ServerException;

@WebServlet("/hello")
public class HelloServlets extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServerException, IOException {
		//resp.getWriter().print("Olá mundo ServLets");
		
		String nome = req.getParameter("pnome");
		String sobrenome = req.getParameter("psobrenome");
		
		if (sobrenome == null ){
			sobrenome = "Sobrenome não informado";
		}
		resp.getWriter().print("Olá mundo GET "+ nome + " " + sobrenome);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServerException, IOException {
		//resp.getWriter().print("Olá mundo ServLets");
		
		String nome = req.getParameter("pnome");
		String sobrenome = req.getParameter("psobrenome");
		if (sobrenome.isEmpty() ){
			sobrenome = "Sobrenome não informado";
		}
		resp.getWriter().print("Olá mundo POST "+ nome + " " + sobrenome);
		
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServerException, IOException {
		//resp.getWriter().print("Olá mundo ServLets");
		
		String nome = req.getParameter("pnome");
		String sobrenome = req.getParameter("psobrenome");
		
		resp.getWriter().print("Olá mundo PUT "+ nome + " " + sobrenome);
		
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServerException, IOException {
		//resp.getWriter().print("Olá mundo ServLets");
		
		String nome = req.getParameter("pnome");
		String sobrenome = req.getParameter("psobrenome");
		
		resp.getWriter().print("Olá mundo DELETE "+ nome + " " + sobrenome);
		
	}
}
