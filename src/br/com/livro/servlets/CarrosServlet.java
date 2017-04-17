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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServerException, IOException, ServletException {

		// url de teste =  http://localhost:8080/Carros/carros/1
		
		String requestUri =  req.getRequestURI();
		Long id = RegexUtil.matchId(requestUri);

		if (id != null) {
			// informou ID
			Carro carro = new CarroService().getCarro(id);

			if (carro != null) {
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(carro);
				ServletUtil.writeJSON(resp, json);
			} else {
   
		 		// url de exemplo =  http://localhost:8080/Carros/carros/9999 - informado id que nao existe no BD
				// lista todos os carros
				List<Carro> carros = carroService.getCarros();
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(carros);
				ServletUtil.writeJSON(resp, json);
			}
		}

	}

}
