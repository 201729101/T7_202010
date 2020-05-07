package model.logic;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import model.data_structures.ListaEncadenada;
import model.data_structures.Arc;
import model.data_structures.Estacion;
import model.data_structures.GrafoND;
import model.data_structures.Vertice;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo
{	
	/**
	 * Grafo con informacion vial
	 */
	public GrafoND grafo;

	/**
	 * Lista de esatciones
	 */
	public ListaEncadenada estaciones;

	/**
	 * Constructor
	 */
	public Modelo ()
	{
		grafo =  new GrafoND(228046);
		estaciones = new ListaEncadenada();
	}

	/**
	 * Inicia la lectura del archivo JSON y rellena la lista de estaciones
	 * @param path, ruta del archivo a leer
	 */
	public void cargarEstaciones(String PATH) 
	{
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();
			for(JsonElement e: e2) {
				JsonObject a = e.getAsJsonObject().get("properties").getAsJsonObject();
				int OBJECTID = a.get("OBJECTID").getAsInt();
				int EPOCOD_PLAN = a.get("EPOCOD_PLAN").getAsInt();
				String EPOCOD_ENT = a.get("EPOCOD_ENT").getAsString();
				String EPOCOD_PROY = a.get("EPOCOD_PROY").getAsString();
				int EPOANIO_GEO= a.get("EPOANIO_GEO").getAsInt();
				long EPOFECHA_INI = a.get("EPOFECHA_INI").getAsLong();
				long EPOFECHA_FIN = a.get("EPOFECHA_FIN").getAsLong();
				String EPODESCRIP = a.get("EPODESCRIP").getAsString();
				String EPOEST_PROY = a.get("EPOEST_PROY").getAsString();
				String EPOINTERV_ESP = a.get("EPOINTERV_ESP").getAsString();
				String EPODIR_SITIO = a.get("EPODIR_SITIO").getAsString();
				String EPOCOD_SITIO = a.get("EPOCOD_SITIO").getAsString();
				double EPOLATITUD = a.get("EPOLATITUD").getAsDouble();
				double EPOLONGITU = a.get("EPOLONGITU").getAsDouble();
				String EPOSERVICIO = a.get("EPOSERVICIO").getAsString();
				String EPOHORARIO = a.get("EPOHORARIO").getAsString();
				String EPOTELEFON = a.get("EPOTELEFON").getAsString();
				String EPOCELECTR = a.get("EPOCELECTR").getAsString();
				String EPOCONTACT = a.get("EPOCONTACT").getAsString();
				String EPOPWEB = a.get("EPOPWEB").getAsString();
				String EPOIUUPLAN = a.get("EPOIUUPLAN").getAsString();
				String EPOIUSCATA = a.get("EPOIUSCATA").getAsString();
				String EPOIULOCAL = a.get("EPOIULOCAL").getAsString();
				String EPOEASOCIA = a.get("EPOEASOCIA").getAsString();
				String EPOFUNCION = a.get("EPOFUNCION").getAsString();
				String EPOTEQUIPA = a.get("EPOTEQUIPA").getAsString();
				String EPONOMBRE = a.get("EPONOMBRE").getAsString();
				String EPOIDENTIF = a.get("EPOIDENTIF").getAsString();
				long EPOFECHA_C = a.get("EPOFECHA_C").getAsLong();

				Estacion est =  new Estacion(OBJECTID ,EPOCOD_PLAN ,EPOCOD_ENT,EPOCOD_PROY,EPOANIO_GEO,EPOFECHA_INI,EPOFECHA_FIN,EPODESCRIP,EPOEST_PROY,EPOINTERV_ESP,EPODIR_SITIO,EPOCOD_SITIO,EPOLATITUD,EPOLONGITU,EPOSERVICIO,EPOHORARIO,EPOTELEFON,EPOCELECTR,EPOCONTACT,EPOPWEB,EPOIUUPLAN,EPOIUSCATA,EPOIULOCAL,EPOEASOCIA,EPOFUNCION,EPOTEQUIPA,EPONOMBRE,EPOIDENTIF,EPOFECHA_C);
				estaciones.agregarFinal(est);
			}

		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}	
	}

	/**
	 * Lee el archivo de vertices y llena le grafo
	 * @param PATH Ruta del archivo
	 */
	public void cargarVertices(String PATH) 
	{
		try 
		{
			BufferedReader lectura = new BufferedReader( new FileReader(new File(PATH)));
			String linea = lectura.readLine();
			int i =1;
			while(linea!=null)
			{
				String[] datos = linea.split(",");
				int id = Integer.parseInt(datos[0]);
				double lon = Double.parseDouble(datos[1]);
				double lat = Double.parseDouble(datos[2]);
				Vertice vert = new Vertice(id,lon,lat);
				grafo.addVertex(id, vert);
				linea = lectura.readLine();
				i++;
			}
			lectura.close();
		} 
		catch (Exception e) 
		{
			//			System.out.println(e.getMessage());
			e.printStackTrace();
		}	
	}

	/**
	 * Lee el archivo de arcos y rellena el grafo
	 * @param PATH Archivo a leer
	 */
	public void cargarArcos(String PATH)
	{
		try
		{
			Haversine dis = new Haversine();
			BufferedReader lectura = new BufferedReader( new FileReader(new File(PATH)));
			String linea = lectura.readLine();
			linea = lectura.readLine();
			linea = lectura.readLine();
			linea =lectura.readLine();
			while(!linea.equals("")||!linea.equals(null))
			{
				String[] datos = linea.split(" ");
				int org = Integer.parseInt(datos[0]);
				for(int i=1;i<datos.length;i++)
				{
					int id = Integer.parseInt(datos[i]);
					Vertice vert = (Vertice) grafo.getInfoVertex(org);
					Vertice two = (Vertice) grafo.getInfoVertex(id);
					double distance = 0.0;
					
					if(vert!=null && two != null)
					{
						double long1 = vert.getLongitud();
						double lat1 = vert.getLatitud();
						double long2 = two.getLongitud();
						double lat2 = two.getLatitud();
						distance = dis.distance(lat1, long1, lat2, long2);
						grafo.addEdge(org, id, distance);
					}
				}
				linea = lectura.readLine();
			}
		}
		catch(Exception e)
		{

		}
	}

	/**
	 * Clase para la distancia Haversiana entre coordenadas
	 */
	public class Haversine 
	{
		private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

		public double distance(double startLat, double startLong, double endLat, double endLong) 
		{

			double dLat  = Math.toRadians((endLat - startLat));
			double dLong = Math.toRadians((endLong - startLong));

			startLat = Math.toRadians(startLat);
			endLat   = Math.toRadians(endLat);

			double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

			return EARTH_RADIUS * c; // <-- d
		}

		public double haversin(double val) {
			return Math.pow(Math.sin(val / 2), 2);
		}
	}
	
	/**
	 * Escribe un archivo JSON con los datos del grafo
	 * @param path Ruta delarchivo a escribir
	 */
	public void escribirJson(String path)
	{	
		try
		{
			FileWriter file = new FileWriter(path);
//			writer = new JsonWriter(new FileWriter(path));
			JSONArray graf = new JSONArray();
			int max = grafo.V();
			for(int i = 0; i<grafo.V() ; i++)
			{
				JSONObject vertice =  new JSONObject();
				Vertice vert = (Vertice) grafo.getInfoVertex(i);
				vertice.put("OBJECT_ID", vert.getId());
				vertice.put("LONGITUD", vert.getLongitud());
				vertice.put("LATITUD", vert.getLatitud());
				JSONArray arcos = new JSONArray();
				ListaEncadenada<Arc> edges = grafo.getEdges(i);
				for(Object e: edges)
				{
					JSONObject arco = new JSONObject();
					Arc arc = (Arc) e;
					arco.put("OBJECT_ID", arc.getId());
					arco.put("DISTANCIA", arc.getDistance());
					arcos.add(arco);
				}
				vertice.put("arcos", arcos);
				graf.add(vertice);
			}
			
			file.write(graf.toJSONString());
			file.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Lee el archivo JSON creado previamente
	 * @param path Ruta del archivp
	 * @return String con numero de vertices y arcos.
	 */
	public String leerArchivo(String path)
	{
		JsonReader reader;
		GrafoND graf = new GrafoND(228046);
		try 
		{
			reader = new JsonReader(new FileReader(path));
			JsonArray elem = JsonParser.parseReader(reader).getAsJsonArray();
			for(JsonElement e: elem)
			{
				int id = e.getAsJsonObject().get("OBJECT_ID").getAsInt();
				double lon = e.getAsJsonObject().get("LONGITUD").getAsDouble();
				double lat = e.getAsJsonObject().get("LATITUD").getAsDouble();
				Vertice vert = new Vertice(id,lon,lat);
				graf.addVertex(id, vert);
				JsonArray arcos = e.getAsJsonObject().get("arcos").getAsJsonArray();
				for(JsonElement o:arcos)
				{
					int ID = o.getAsJsonObject().get("OBJECT_ID").getAsInt();
					double dis = o.getAsJsonObject().get("DISTANCIA").getAsDouble();
					Arc ar = new Arc(ID,dis);
					graf.addEdge(id, ID, dis);
				}
			}
			String retorno;
			int vert = graf.V(); int ed = graf.E();
			retorno = ""+vert+","+ed;
			return retorno;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
