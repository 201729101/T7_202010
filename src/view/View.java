package view;

import java.util.ArrayList;

import java.util.Date;

import model.data_structures.Estacion;
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
			System.out.println("1. Cargar vertices y arcos");
			System.out.println("2. Escribir archivo JSON");
			System.out.println("3. Leer JSON creado");
			System.out.println("4. Cargar Estaciones");
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
		
		public void printEstación(Estacion inf)
		{
			System.out.println("[");
			System.out.println("ID: "+inf.getOBJECTID());
			System.out.println("Descripción: "+inf.getEPODESCRIP());
			System.out.println("Dirección: " + inf.getEPODIR_SITIO());
			System.out.println("Horario: "+inf.getEPOHORARIO());
			System.out.println("Telefono: "+inf.getEPOTELEFON());
			System.out.println("Correo electrónico: "+inf.getEPOCELECTR());
			System.out.println("Nombre: "+inf.getEPONOMBRE());
			System.out.println("Función: "+inf.getEPOFUNCION());
			System.out.println("Coordenadas: "+inf.getEPOLATITUD()+" , "+inf.getEPOLONGITU());
			System.out.println("]");
		}
		
		/**
		 * Imprime toda una Lista recibida por parámetro
		 * @param lista Lista a imprimir
		 */
		public void printLista(ListaEncadenada lista)
		{
			System.out.println("Estaciones: {");
			for(Object e:lista)
			{
				Estacion inf = (Estacion) e;
				printEstación(inf);
			}
			System.out.println("}");
		}
		
}
