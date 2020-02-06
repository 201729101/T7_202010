package controller;

import java.util.Scanner;

import model.logic.Infraccion;
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
		Infraccion resp = null;

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
				case 1:
//					view.printMessage("--------- \nCrear Arreglo \nDar dar ruta del archivo: ");
//				    String ruta = lector.next();
				    modelo = new Modelo(); 
				    modelo.leerDatos("comparendos_dei_2018.geojson");
				    view.printMessage("Comparendos cargados");
				    view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
					break;

				case 2:
					view.printMessage("--------- \nDar ID del comparendo: ");
					int id = lector.nextInt();
					view.printMessage("--------- \nDar fecha del comparendo: ");
					String fecha = lector.next();
					view.printMessage("--------- \nDar medio de detección del comparendo: ");
					String medio = lector.next();
					view.printMessage("--------- \nDar clase de vehículo comparentado: ");
					String clase = lector.next();
					view.printMessage("--------- \nDar tipo de servició del vehículo comparentado: ");
					String tipo = lector.next();
					view.printMessage("--------- \nDar código de infracción del comparendo: ");
					String inf = lector.next();
					view.printMessage("--------- \nDar descripción del comparendo: ");
					String desc = lector.next();
					view.printMessage("--------- \nDar localidad del comparendo: ");
					String loc = lector.next();
					view.printMessage("--------- \nDar latitud del comparendo: ");
					double lat = lector.nextDouble();
					view.printMessage("--------- \nDar longitud del comparendo: ");
					double lon = lector.nextDouble();
					resp = new Infraccion(id,fecha,medio,clase,tipo,inf,desc,loc,lat,lon);
					modelo.agregarFinal(resp);
					view.printMessage("Dato agregado");
					view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
					break;

				case 3:
					view.printMessage("--------- \nDar ID del comparendo a buscar: ");
					int pId = lector.nextInt();
					resp = modelo.buscar(pId);
					if ( resp != null)
					{
						view.printMessage("Dato encontrado: \nID: "+resp.getId()+"\nFecha: "+resp.getFecha()+"\nMedio de Detección: "+resp.getMedio()+"\nClase de vehículo: "+resp.getClase()+"\nTipo de servicio: "+resp.getTipo()+"\nInfracción: "+resp.getInfr()+"\nDescripción: "+resp.getDesc()+"\nLocalidad: "+resp.getLocalidad()+"\nCoordenadas: "+resp.getLatitud()+" , "+resp.getLongitud());
					}
					else
					{
						view.printMessage("Dato NO encontrado");
					}
					view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
					break;

				case 4:
					view.printMessage("--------- \nDar id del comparendo a eliminar: ");
					int pIdE = lector.nextInt();
					resp = modelo.eliminar(pIdE);
					if ( resp != null)
					{
						view.printMessage("Dato eliminado: \nID: "+resp.getId()+"\nFecha: "+resp.getFecha()+"\nMedio de Detección: "+resp.getMedio()+"\nClase de vehículo: "+resp.getClase()+"\nTipo de servicio: "+resp.getTipo()+"\nInfracción: "+resp.getInfr()+"\nDescripción: "+resp.getDesc()+"\nLocalidad: "+resp.getLocalidad()+"\nCoordenadas: "+resp.getLatitud()+" , "+resp.getLongitud());
					}
					else
					{
						view.printMessage("Dato NO eliminado");							
					}
					view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
					break;

				case 5: 
					view.printMessage("--------- \nContenido del Arreglo: ");
					view.printModelo(modelo);
					view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
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
