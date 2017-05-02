package br.com.livro.servlets;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import javax.xml.bind.JAXB;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.livro.domain.Carro;
import br.com.livro.domain.CarroService;
import br.com.livro.domain.ListaCarros;
import br.com.livro.domain.Response;
import br.com.livro.util.JAXBUtil;
import br.com.livro.util.RegexUtil;
import br.com.livro.util.ServletUtil;

@WebServlet("/carros/*")
public class CarrosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private CarroService carroService = new CarroService();

	/*
	 * // utilizando a biblioteca JAXB
	 * 
	 * @Override protected void doGet(HttpServletRequest req,
	 * HttpServletResponse resp) throws ServerException, IOException{
	 * 
	 * // calsse carroservice List<Carro> carros = carroService.getCarros(); //
	 * classe Listacarro que encapsula carroservice ListaCarros lista = new
	 * ListaCarros(); lista.setCarros(carros); // gera xml String xml =
	 * JAXBUtil.toXML(lista); // escreve xml na response do servelet com
	 * application/xml ServletUtil.writeXML(resp, xml);
	 * 
	 * }
	 */

	/*
	 * // utilizando biblioteca jettison
	 * 
	 * @Override protected void doGet(HttpServletRequest req,
	 * HttpServletResponse resp) throws ServerException, IOException{
	 * 
	 * // calsse carroservice List<Carro> carros = carroService.getCarros(); //
	 * classe Listacarro que encapsula carroservice ListaCarros lista = new
	 * ListaCarros(); lista.setCarros(carros); // gera json String json =
	 * JAXBUtil.toJSON(lista); // escreve json na response do servelet com
	 * application/json ServletUtil.writeJSON(resp, json);
	 * 
	 * }
	 */

	/*
	 * // utilizando a biblioteca Gson do google
	 * 
	 * @Override protected void doGet(HttpServletRequest req,
	 * HttpServletResponse resp) throws ServerException, IOException{
	 * 
	 * // calsse carroservice List<Carro> carros = carroService.getCarros(); //
	 * classe Listacarro que encapsula carroservice ListaCarros lista = new
	 * ListaCarros(); lista.setCarros(carros);
	 * 
	 * // gera json // setPrettyPrinting utiliado para quebra de linha Gson gson
	 * = new GsonBuilder().setPrettyPrinting().create();
	 * 
	 * String json = gson.toJson(lista); // escreve json na response do servelet
	 * com application/json ServletUtil.writeJSON(resp, json); }
	 */
	// GET
	/*
	 * @Override protected void doGet(HttpServletRequest req,
	 * HttpServletResponse resp) throws ServerException, IOException,
	 * ServletException {
	 * 
	 * // url de teste = http://localhost:8080/Carros/carros/1
	 * 
	 * String requestUri = req.getRequestURI(); Long id =
	 * RegexUtil.matchId(requestUri);
	 * 
	 * if (id != null) { // informou ID Carro carro = new
	 * CarroService().getCarro(id);
	 * 
	 * if (carro != null) { Gson gson = new
	 * GsonBuilder().setPrettyPrinting().create(); String json =
	 * gson.toJson(carro); ServletUtil.writeJSON(resp, json); } else {
	 * resp.sendError(404, "Carro não encontrado"); } } else {
	 * 
	 * // url de exemplo = http://localhost:8080/Carros/carros/9999 - //
	 * informado id que nao existe no BD // lista todos os carros List<Carro>
	 * carros = carroService.getCarros(); Gson gson = new
	 * GsonBuilder().setPrettyPrinting().create(); String json =
	 * gson.toJson(carros); ServletUtil.writeJSON(resp, json); } }
	 */

	// POST
	/*
	 * @Override public void doPost(HttpServletRequest request,
	 * HttpServletResponse resp) throws IOException, ServletException { // Cria
	 * o carro Carro carro = getCarroFromRequest(request); // Salva o carro
	 * carroService.save(carro); // Escreve o JSON do novo carro salvo. Gson
	 * gson = new GsonBuilder().setPrettyPrinting().create(); String json =
	 * gson.toJson(carro); ServletUtil.writeJSON(resp, json); }
	 * 
	 * // Lê os parâmetros da request e cria o objeto Carro. private Carro
	 * getCarroFromRequest(HttpServletRequest request) { Carro c = new Carro();
	 * String id = request.getParameter("id"); if (id != null) { // Se informou
	 * o id, busca o objeto do banco de dados. c =
	 * carroService.getCarro(Long.parseLong(id)); }
	 * c.setNome(request.getParameter("nome"));
	 * c.setDesc(request.getParameter("descricao"));
	 * c.setUrlFoto(request.getParameter("url_foto"));
	 * c.setUrlVideo(request.getParameter("url_video"));
	 * c.setLatitude(request.getParameter("latitude"));
	 * c.setLongitude(request.getParameter("longitude"));
	 * c.setTipo(request.getParameter("tipo")); return c; }
	 */

	// DELETE
	// Sem retorno
/*	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String requestUri = req.getRequestURI();
		Long id = RegexUtil.matchId(requestUri);

		if (id != null) {
			carroService.delete(id);
			resp.sendError(200, "Carro excluído com sucesso");
		} else {
			// url invalida
			resp.sendError(404, "URL inválida");
		}
	}*/
	
	//com mensagem de retorno em formato JSON
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String requestUri = req.getRequestURI();
		Long id = RegexUtil.matchId(requestUri);
		
		if (id != null) {
			carroService.delete(id);
			Response r = Response.Ok("Carro excluído com sucesso");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(r);
			ServletUtil.writeJSON(resp, json);
		} else {
			// URL inválida
			resp.sendError(404, "URL inválida");
		}
	}

}
