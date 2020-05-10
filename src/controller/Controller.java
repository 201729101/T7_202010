package controller;

import java.util.ArrayList;


import java.util.Date;
import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.swing.JFrame;

import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.*;
import com.teamdev.jxmaps.demo.*;

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

				modelo.escribirJson("./data/Grafo.json");
				break;

			case 3:
				String l = modelo.leerArchivo("./data/Grafo.json");
				String[] ls = l.split(",");
				System.out.println("Numero de vertices cargados: "+ls[0]);
				System.out.println("Numero de arcos cargados: "+ls[1]);
				break;

			case 4:
				modelo.cargarEstaciones("./data/estacionpolicia.geojson.json");
				System.out.println("Numero de estaciones: "+modelo.estaciones.darTamano());
				view.printLista(modelo.estaciones);
				break;

			case 5:
				
				JFrame frame = new JFrame("Grafito");
				Mapa mapa = new Mapa(modelo);
				frame.add(mapa,BorderLayout.CENTER);
				frame.setSize(700,500);
				frame.setVisible(true);
				break;
				
			case 6: 
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
