package controller;

import java.util.ArrayList;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import model.data_structures.*;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	/**
	 * Corre el sistema mediante la consola 
	 */
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd");
			switch(option){
			case 1:
				modelo = new Modelo(); 
//				modelo.cargarEstaciones("./data/estacionpolicia.geojson.json");
				modelo.cargarVertices("./data/bogota_vertices.txt");
				modelo.cargarArcos("./data/bogota_arcos.txt");
				break;

			case 2:

				break;

			case 3:

				break;

			case 4:
				break;

			case 5: 
				view.printMessage("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;	

			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
