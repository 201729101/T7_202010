package controller;

import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.Polyline;
import com.teamdev.jxmaps.PolylineOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.sun.prism.paint.Color;
import com.teamdev.jxmaps.Circle;
import com.teamdev.jxmaps.CircleOptions;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.swing.MapView;

import model.data_structures.Arc;
import model.data_structures.Estacion;
import model.data_structures.GrafoND;
import model.data_structures.ListaEncadenada;
import model.data_structures.Vertice;
import model.logic.Modelo;
/**
 * Clase del mapa a graficar 
 */
public class Mapa extends MapView
{

	/**
	 * Contructor del mapa. Construye un mapa con un objeto de clase modelo entregado por parámetro
	 * @param model Modelo con la informacion para graficar 
	 */
	public Mapa(Modelo model) 
	{
		GrafoND grafo = model.grafo;
		setOnMapReadyHandler(new MapReadyHandler(){

			@Override
			public void onMapReady(MapStatus arg0) 
			{
				if(arg0==MapStatus.MAP_STATUS_OK)
				{
					Map map = getMap();
					MapOptions op = new MapOptions();
					MapTypeControlOptions es = new MapTypeControlOptions();
					op.setMapTypeControlOptions(es);
					map.setOptions(op);

					map.setCenter(new LatLng(4.609537, -74.078715));
					map.setZoom(14.5);
					ListaEncadenada estaciones = model.estaciones;
					graficarEstaciones(map, estaciones);
					GrafoND grafo = model.grafo;
					graficarArcos(map, grafo);

				}
			}
		});
	}

	/**
	 * Grafica las estaciones de policia con un mapa y un alista de estaciones recibidas por parámetro
	 * @param map Mapa para graficar
	 * @param estaciones Lista de estaciones para pintar
	 */
	private void graficarEstaciones(Map map,ListaEncadenada estaciones)
	{
		for(Object e: estaciones)
		{
			Estacion est = (Estacion) e;
			double eLat = est.getEPOLATITUD();
			double eLng = est.getEPOLONGITU();

			if(eLat<= 4.621360 && eLat>=4.597714 && eLng>=-74.094723 && eLng <=-74.062707)
			{
				Circle circle = new Circle(map);
				circle.setCenter(new LatLng(eLat,eLng));
				circle.setRadius(45);
				CircleOptions options = new CircleOptions();
				options.setFillColor("#399639");
				options.setFillOpacity(100);
				options.setStrokeColor("#2DD22D");
				circle.setOptions(options);
			}
		}
	}

	/**
	 * Grafica los vertices y arcos con un mapa y un grafo recibidos por parámetro
	 * @param map Mapa para graficar
	 * @param grafo Grafo con l ainformacion de los vertices y arcos
	 */
	private void graficarArcos(Map map,GrafoND grafo)
	{
		int n = grafo.V();
		for(int i=0 ; i<n ; i++)
		{
			Vertice vert = (Vertice) grafo.getInfoVertex(i);
			double vLat = vert.getLatitud();
			double vLng = vert.getLongitud();
			if(vLat<= 4.621360 && vLat>=4.597714 && vLng>=-74.094723 && vLng <=-74.062707)
			{
				Circle circle = new Circle(map);
				circle.setCenter(new LatLng(vLat,vLng));
				circle.setRadius(7.5);
				ListaEncadenada arcos = grafo.getEdges(i);
				for(Object e:arcos)
				{
					Arc arc = (Arc) e;
					Vertice vert2 = (Vertice) grafo.getInfoVertex(arc.getId());
					LatLng[] path = {new LatLng(vert.getLatitud(), vert.getLongitud()), 
							new LatLng(vert2.getLatitud(),vert2.getLongitud())};
					Polyline polyline = new Polyline(map);
					polyline.setPath(path);
				}
			}
		}
	}
}
