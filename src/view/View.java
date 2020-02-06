package view;

import model.data_structures.ListaEncadenada;
import model.data_structures.Nodo;
import model.logic.Infraccion;
import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
	    /**
	     *Imprime el menú 
	     */
		public void printMenu()
		{
			System.out.println("1. Cargar lista de comparendos");
			System.out.println("2. Agregar comparendo");
			System.out.println("3. Buscar comparendo");
			System.out.println("4. Eliminar comparendo");
			System.out.println("5. Imprimir el Arreglo");
			System.out.println("6. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		/**
		 * Imprime un mensaje recibido por parámetro
		 * @param mensaje mensaje a imprimir
		 */
		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		/**
		 * Imprime todo un modelo recibido por parámetro
		 * @param modelo Modelo a imprimir
		 */
		public void printModelo(Modelo modelo)
		{
			ListaEncadenada lista = modelo.darLista();
			int contador = 0;
			
			for(Nodo e = lista.darPrimero() ; e!=null ; e = e.darSiguiente())
			{
				Infraccion inf = (Infraccion) e.darElemento();
				contador ++;
				System.out.println(contador + ".)");
				System.out.println("ID: "+inf.getId());
				System.out.println("Fecha: "+inf.getFecha());
				System.out.println("Medio de detección: " + inf.getMedio());
				System.out.println("Clase de vehículo: "+inf.getClase());
				System.out.println("Tipo de servicio: "+inf.getTipo());
				System.out.println("Infracción: "+inf.getInfr());
				System.out.println("Descripción: "+inf.getDesc());
				System.out.println("Localidad: "+inf.getLocalidad());
				System.out.println("Coordenadas: "+inf.getLatitud()+" , "+inf.getLongitud());
				System.out.println(".");
				System.out.println("");
				
			}
		}
}
