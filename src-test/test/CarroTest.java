package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import br.com.livro.domain.Carro;
import br.com.livro.domain.CarroService;
import junit.framework.TestCase;

public class CarroTest extends TestCase {

	private CarroService carroService = new CarroService();

	@Test
	public void testListaCarros() {
		List<Carro> carros = carroService.getCarros();
		assertNotNull(carros);

		// Valida se encontrou algo
		assertTrue(carros.size() > 0);

		// Valida se encontrou o Tucker
		Carro tucker = carroService.findByName("Tucker 1948").get(0);
		assertEquals("Tucker 1948", tucker.getNome());

		// Valida se encontrou a Ferrari
		Carro ferrari = carroService.findByName("Ferrari FF").get(0);
		assertEquals("Ferrari FF", ferrari.getNome());

		// Valida se encontrou o Bugatti
		Carro bugatti = carroService.findByName("Bugatti Veyron").get(0);
		assertEquals("Bugatti Veyron", bugatti.getNome());
	}

	@Test
	public void testSalvarDeletarCarro() {

		Carro c = new Carro();
		// teste inserir carro
		c.setNome("teste nome");
		c.setDesc("teste descricao");
		c.setUrlFoto("url foto");
		c.setUrlVideo("url video");
		c.setLatitude("latitude");
		c.setLongitude("longitude");
		c.setTipo("tipo");

		carroService.save(c);
		// id do carro salvo

		Long id = c.getId();
		assertNotNull(id);

		// busca no BD para confirmar se o carro foi inserido

		c = carroService.getCarro(id);

		assertEquals("teste nome", c.getNome());
		assertEquals("teste descricao", c.getDesc());
		assertEquals("url foto", c.getUrlFoto());
		assertEquals("url video", c.getUrlVideo());
		assertEquals("latitude", c.getLatitude());
		assertEquals("longitude", c.getLongitude());
		assertEquals("tipo", c.getTipo());

		// atualiza o carro

		c.setNome("teste UPDATE");
		carroService.save(c);
		// busca novamente para conferir se atualizou
		c = carroService.getCarro(id);
		assertEquals("teste UPDATE", c.getNome());
		// deleta o carro
		carroService.delete(id);
		// busca novamente
		c = carroService.getCarro(id);
		// dessa vez o carro nao existe mais
		assertNull(c);

	}

}