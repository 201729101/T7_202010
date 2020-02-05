package test.logic;

import static org.junit.Assert.*;

import model.logic.Infraccion;
import model.logic.Modelo;

import org.junit.Before;
import org.junit.Test;

public class TestModelo {
	
	private Modelo modelo;
	private static int CAPACIDAD=100;
	
	@Before
	public void setUp1() 
	{
		modelo= new Modelo();
	}
	
	public void setUp2() 
	{
		modelo.leerDatos("./data/comparendos_dei_2018_small.geojson");
	}
	
	public void setUp3()
	{
		modelo.agregarFinal(new Infraccion(0, null, null, null, null, null, null, null, 0, 0));
	}

	@Test
	public void testModelo() {
		assertTrue(modelo!=null);
		assertEquals(0, modelo.darTamano());  // Modelo con 0 elementos presentes.
	}

	@Test
	public void testDarTamano() {
		setUp2();
		assertEquals(20,modelo.darTamano());
		setUp3();
		assertEquals(21,modelo.darTamano());
	}

	@Test
	public void testAgregarFinal() {
		// TODO Completar la prueba
		setUp3();
		assertEquals(1,modelo.darTamano());
		assertNotNull(modelo.buscar(new Infraccion(0, null, null, null, null, null, null, null, 0, 0)));
	}
	
	@Test
	public void testAgregarInicio(){
		modelo.agregarInicio(new Infraccion(0, null, null, null, null, null, null, null, 0, 0));
		assertEquals(1, modelo.darTamano());
		
	}

	@Test
	public void testBuscar() {
		// TODO Completar la prueba
		setUp3();
		assertNotNull(modelo.buscar(new Infraccion(0, null, null, null, null, null, null, null, 0, 0)));
	}

	@Test
	public void testEliminar() {
		// TODO Completar la prueba
		
	}

}
