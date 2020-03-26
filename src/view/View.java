package view;

import java.util.ArrayList;
import java.util.Date;

import model.data_structures.Comparendo;

import model.data_structures.ListaEncadenada;
import model.data_structures.Nodo;
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
			System.out.println("1. Cargar Comaprendos");
			System.out.println("2. Dar comparendos por fecha, clase e infracción (SL)");
			System.out.println("3. Dar comparendos por fecha, clase e infracción (ES)");
			System.out.println("4. Pruebas");
			System.out.println("5. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		/**
		 * Imprime un mensaje recibido por parámetro
		 * @param mensaje mensaje a imprimir
		 */
		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printComparendo(Comparendo inf)
		{
			System.out.println("[");
			System.out.println("ID: "+inf.getId());
			System.out.println("Fecha: "+inf.getFecha());
			System.out.println("Medio de detección: " + inf.getMedio());
			System.out.println("Clase de vehículo: "+inf.getClase());
			System.out.println("Tipo de servicio: "+inf.getTipo());
			System.out.println("Infracción: "+inf.getInfr());
			System.out.println("Descripción: "+inf.getDesc());
			System.out.println("Localidad: "+inf.getLocalidad());
			System.out.println("Coordenadas: "+inf.getLatitud()+" , "+inf.getLongitud());
			System.out.println("]");
		}
		
		/**
		 * Imprime todo un modelo recibido por parámetro
		 * @param modelo Modelo a imprimir
		 */
		public void printLista(ArrayList lista)
		{
			System.out.println("Comparendos buscados: {");
			for(int i=0 ; i<lista.size() ; i++)
			{
				Comparendo inf = (Comparendo) lista.get(i);
				printComparendo(inf);
			}
			System.out.println("}");
		}
		
}
