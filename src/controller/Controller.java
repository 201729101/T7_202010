package controller;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import model.data_structures.Comparendo;
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
		Comparendo resp = null;

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd");
			switch(option){
			case 1:
				modelo = new Modelo(); 
				Comparendo[] retorno = modelo.cargarDatos("./data/Comparendos_DEI_2018_Bogotá_D.C.geojson");
//				Comparendo[] retorno = modelo.cargarDatos("./data/comparendos_dei_2018_small2.geojson");
				view.printMessage("Numero de comparendos: "+modelo.getTablaSL().getN());
				view.printMessage("Primer comparendo: ");
				view.printComparendo(retorno[0]);
				view.printMessage("Último comparendo: ");
				view.printComparendo(retorno[1]);
				System.out.println("----------------------------");
				view.printMessage("Valor N:            SL, "+modelo.getTablaSL().getN()+"    ES, "+modelo.getTablaES().getN());
				view.printMessage("Valos inicial M:    SL, "+modelo.getTablaSL().getMi()+"    ES, "+modelo.getTablaES().getMi());
				view.printMessage("Valor final M:      SL, "+modelo.getTablaSL().getM()+"    ES, "+modelo.getTablaES().getM());
				view.printMessage("Factor de carga:    SL, "+(modelo.getTablaSL().getN()/modelo.getTablaSL().getM())+"     ES, "+(modelo.getTablaES().getN()/modelo.getTablaES().getM()));
				view.printMessage("Rehashes:           SL, "+modelo.getTablaSL().getR()+"     ES, "+modelo.getTablaES().getR());
				break;

			case 2:

				view.printMessage("Ingrese fecha en formato <Años/mes/dia>, clase de vehículo y código de infracción pegados:");
				String l = lector.next();	
				try
				{
//					String[] datos = l.split("-");
//					ArrayList lista = modelo.rUno(datos[0]+datos[1]+datos[2]);
					ArrayList lista = modelo.rUno(l);
					view.printLista(lista);
				}
				catch(Exception e)
				{
					System.out.println("Hubo un error");
					e.printStackTrace();
				}
				break;

			case 3:
				view.printMessage("Ingrese fecha en formato <Años/mes/dia>, clase de vehículo y código de infracción pegados:");
				String l2 = lector.next();	
				try
				{
//					String[] datos2 = l2.split("-");
					ArrayList lista2 = modelo.rDos(l2);
					view.printLista(lista2);
				}
				catch(Exception e)
				{
					System.out.println("Hubo un error");
					e.printStackTrace();
				}
				break;

			case 4:
				Double[] r = modelo.rTres();
				view.printMessage("Tiempo mínimo:     SL, "+r[0]+"     ES, "+r[1]);
				view.printMessage("Tiempo promedio:   SL, "+r[2]+"    ES, "+r[3]);
				view.printMessage("Tiempo máximo:     SL, "+r[4]+"     ES, "+r[5]);
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
