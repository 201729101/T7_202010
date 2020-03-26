package model.logic;

import java.util.Date;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import model.data_structures.Comparendo;
import model.data_structures.ListaEncadenada;
import model.data_structures.Nodo;
import model.data_structures.TablaHashES;
import model.data_structures.TablaHashSL;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo
{
	/**
	 * Estrutura de datos que tendrá los comparendos
	 */
	private TablaHashES tablaES;
	
	private TablaHashSL tablaSL;

	/**
	 * Constructor
	 */
	public Modelo ()
	{
		tablaES = new TablaHashES();
		tablaSL = new TablaHashSL();
	}

	/**
	 * Inicia la lectura del archivo JSON y rellena la lista
	 * @param path, ruta del archivo a leer
	 */
	public Comparendo[] cargarDatos(String PATH) 
	{
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();

			SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd");
			Comparendo primero = null;
			Comparendo ultimo = null;
			Comparendo[] retorno = new Comparendo[2];

			for(JsonElement e: e2) {
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();

				String s = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();	
				Date FECHA_HORA = parser.parse(s); 

				String MEDIO_DETE = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETE").getAsString();
				String CLASE_VEHI = e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHI").getAsString();
				String TIPO_SERVI = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVI").getAsString();
				String INFRACCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DES_INFRAC = e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRAC").getAsString();	
				String LOCALIDAD = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();

				double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsDouble();

				double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsDouble();

				Comparendo c = new Comparendo(OBJECTID, FECHA_HORA, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, DES_INFRAC, LOCALIDAD, longitud, latitud);
				String key = s+CLASE_VEHI+INFRACCION;
				agregarES(key, c);
				agregarSL(key, c);
				if(primero == null)
					primero =c;
				ultimo = c;
			}
			
			retorno[0] = primero;
			retorno[1] = ultimo;
			return retorno;
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}	
	}
	
	public ArrayList rUno(String datos)
	{
		ArrayList<Comparendo> lista = new ArrayList<Comparendo>();
		ListaEncadenada retorno = (ListaEncadenada) tablaSL.dar(datos);
		
		for(Nodo n=retorno.darPrimero() ; n!=null ; n=n.darSiguiente())
			if(lista.size()!=0 && lista.get(lista.size()-1).getId()<((Comparendo) n.darElemento()).getId())
				lista.add((Comparendo) n.darElemento()); 
			else if(lista.size()!=0 && lista.get(0).getId()>((Comparendo) n.darElemento()).getId())
				lista.add(0, (Comparendo) n.darElemento());
			else if(lista.size()==0)
				lista.add((Comparendo) n.darElemento());
			else
				for(int j = 1 ; j<lista.size() ; j++)
					if(lista.get(j).getId()>((Comparendo) n.darElemento()).getId())
						lista.add(j-1, (Comparendo) n.darElemento());
		
//		String[] keys = (String[]) tablaSL.getKeys();
//		int i; 
//		for (i = tablaSL.hash(datos) ; tablaSL.getKeys()[i] != null; i = (i + 1) % tablaSL.getM()) 
//			if (((String) tablaSL.getKeys()[i]).equals(datos)) 
//			{ 
//				if(lista.size()!=0 && lista.get(lista.size()-1).getId()<((Comparendo)tablaSL.getValues()[i]).getId())
//					lista.add((Comparendo)tablaSL.getValues()[i]); 
//				else if(lista.size()!=0 && lista.get(0).getId()>((Comparendo)tablaSL.getValues()[i]).getId())
//					lista.add(0, (Comparendo)tablaSL.getValues()[i]);
//				else if(lista.size()==0)
//					lista.add((Comparendo)tablaSL.getValues()[i]);
//				else
//					for(int j = 1 ; j<lista.size() ; j++)
//						if(lista.get(j).getId()>((Comparendo)tablaSL.getValues()[i]).getId())
//							lista.add(j-1, (Comparendo)tablaSL.getValues()[i]);
//			} 
//
//		if(lista.size()!=0 && lista.get(lista.size()-1).getId()<((Comparendo)tablaSL.getValues()[i]).getId())
//			lista.add((Comparendo)tablaSL.getValues()[i]); 
//		else if(lista.size()!=0 && lista.get(0).getId()>((Comparendo)tablaSL.getValues()[i]).getId())
//			lista.add(0, (Comparendo)tablaSL.getValues()[i]);
//		else if(lista.size()==0)
//			lista.add((Comparendo)tablaSL.getValues()[i]);
//		else
//			for(int j = 1 ; j<lista.size() ; j++)
//				if(lista.get(j).getId()>((Comparendo)tablaSL.getValues()[i]).getId())
//					lista.add(j-1, (Comparendo)tablaSL.getValues()[i]);
////		lista.add((Comparendo)tablaSL.getValues()[i]); 
		
		return lista;
	}
	
	public ArrayList rDos(String datos)
	{
		ArrayList<Comparendo> lista  = new ArrayList<Comparendo>();
		ListaEncadenada retorno = (ListaEncadenada) tablaES.dar(datos);
		
		for(Nodo n=retorno.darPrimero() ; n!=null ; n=n.darSiguiente())
			if(lista.size()!=0 && lista.get(lista.size()-1).getId()<((Comparendo) n.darElemento()).getId())
				lista.add((Comparendo) n.darElemento()); 
			else if(lista.size()!=0 && lista.get(0).getId()>((Comparendo) n.darElemento()).getId())
				lista.add(0, (Comparendo) n.darElemento());
			else if(lista.size()==0)
				lista.add((Comparendo) n.darElemento());
			else
				for(int j = 1 ; j<lista.size() ; j++)
					if(lista.get(j).getId()>((Comparendo) n.darElemento()).getId())
						lista.add(j-1, (Comparendo) n.darElemento());
		
//		ListaEncadenada<String,Comparendo> value = (ListaEncadenada<String,Comparendo>)tablaES.getValues()[hash];
//		
//		for(Nodo n=value.darPrimero() ; n!= null ; n=n.darSiguiente())
//			if(n.getKey().equals(datos))
//				if(lista.size()!=0 && lista.get(lista.size()-1).getId()<((Comparendo) n.darElemento()).getId())
//					lista.add((Comparendo) n.darElemento()); 
//				else if(lista.size()!=0 && lista.get(0).getId()>((Comparendo) n.darElemento()).getId())
//					lista.add(0, (Comparendo) n.darElemento());
//				else if(lista.size()==0)
//					lista.add((Comparendo) n.darElemento());
//				else
//					for(int j = 1 ; j<lista.size() ; j++)
//						if(lista.get(j).getId()>((Comparendo) n.darElemento()).getId())
//							lista.add(j-1, (Comparendo) n.darElemento());
		
		return lista;
	}
	
	public Double[] rTres()
	{
		ArrayList tiemposSL = new ArrayList<Double>();
		ArrayList tiemposES = new ArrayList<Double>();
		Object[] llaves = tablaSL.getKeys();
		
		double minSL = 100;
		double minES = 100;
		double sumSL = 0.0;
		double sumES = 0.0;
		double maxSL = 0.0;
		double maxES = 0.0;
		
		for(int i=0 ; i<=80 ; i++)
		{
			double p = Math.random()*10;
			int valor = (int) p;
			String key = "";
			if (llaves[valor]!=null)
				key = (String) llaves[valor];
			else{
				while(llaves[valor]!=null)
				{
					valor= (int) Math.random()*10;
				}
				key = (String) llaves[valor];}
				
			
			long startTimeSL = System.currentTimeMillis();
			tablaSL.dar(key);
			long endTimeSL = System.currentTimeMillis();          
			long durationSL = endTimeSL - startTimeSL; 
			tiemposSL.add(durationSL);
			sumSL += durationSL;
			if(durationSL<minSL)
				minSL = durationSL;
			if(durationSL>maxSL)
				maxSL = durationSL;
			
			
			long startTimeES = System.currentTimeMillis();
			tablaES.dar(key);
			long endTimeES = System.currentTimeMillis();          
			long durationES = endTimeES - startTimeES; 
			tiemposES.add(durationES);
			sumES += durationES;
			if(durationES<minES)
				minES = durationES;
			if(durationES>maxES)
				maxES = durationES;
		}
		System.out.println("LLegó 1");
		
		for(int i=0 ; i<=20 ; i++)
		{
			String key = "2019"+Math.random() * 10;
			long startTimeSL = System.currentTimeMillis();
			tablaSL.dar(key);
			long endTimeSL = System.currentTimeMillis();          
			long durationSL = endTimeSL - startTimeSL; 
			tiemposSL.add(durationSL);
			sumSL += durationSL;
			if(durationSL<minSL)
				minSL = durationSL;
			if(durationSL>maxSL)
				maxSL = durationSL;
			
			long startTimeES = System.currentTimeMillis();
			tablaES.dar(key);
			long endTimeES = System.currentTimeMillis();          
			long durationES = endTimeES - startTimeES; 
			tiemposES.add(durationES);
			sumES += durationES;
			if(durationES<minES)
				minES = durationES;
			if(durationES>maxES)
				maxES = durationES;
		}
		
		System.out.println("Llegó 2");
		
		Double[] retorno = new Double[6];
		retorno[0] = minSL;
		retorno[1] = minES;
		retorno[2] = sumSL/100;
		retorno[3] = sumES/100;
		retorno[4] = maxSL;
		retorno[5] = maxES;
		
		return retorno;
	}

	public TablaHashSL getTablaSL() {
		return tablaSL;
	}
	
	public TablaHashES getTablaES() {
		return tablaES;
	}
	
	public void agregarES(String key, Comparendo value)
	{
		ListaEncadenada b = (ListaEncadenada) tablaES.dar(key);
		
		if(b==null)
		{
			b = new ListaEncadenada<Comparendo>();
			b.agregarFinal(value);
			tablaES.agregar(key, b);
		}
		else
		{
			b.agregarFinal(value);
		}
	}
	
	public void agregarSL(String key, Comparendo value)
	{
		ListaEncadenada b = (ListaEncadenada) tablaSL.dar(key);
		
		if(b==null)
		{
			b = new ListaEncadenada<Comparendo>();
			b.agregarFinal(value);
			tablaSL.agregar(key, b);
		}
		else
		{
			b.agregarFinal(value);
		}
	}

}
