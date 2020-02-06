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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import model.data_structures.ListaEncadenada;
import model.data_structures.Nodo;
/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo
{
	/**
	 * Estrutura de datos que tendrá los comparendos
	 */
	private ListaEncadenada lista;
	
	/**
	 * Constructor
	 */
	public Modelo ()
	{
		lista = new ListaEncadenada();
	}
	
	/**
	 * Inicia la lectura del archivo JSON y rellena la lista
	 * @param path, ruta del archivo a leer
	 */
	public void leerDatos(String path)
	{
		Gson gson = new Gson();
		JsonReader reader;
		
		try 
		{
			readJsonStream(new FileInputStream("./data/"+path));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Lee el archivo JSON
	 * @param in InputStream mediante el cual se hace la lectura, in!=null
	 * @return lista rellenada con los datos
	 * @throws IOException si no es posible leer el archivo 
	 */
	public ListaEncadenada readJsonStream(InputStream in) throws IOException 
	{
		JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
		try 
		{
			return readGeneral(reader);
		} 
		finally 
		{
			reader.close();
		}
	}
	
	/**
	 * Lee el mensaje general en el archivo
	 * @param reader JsonReader que debe leer
	 * @return lista con los comparendos
	 * @throws IOException si no es posible leer el archivo
	 */
	public ListaEncadenada readGeneral(JsonReader reader) throws IOException 
	{
		String type = null;
		String n = null;
		String crs = null;
		

		reader.beginObject();
		while (reader.hasNext()) 
		{
			String name = reader.nextName();
			if (name.equals("type")) 
			{
				type = reader.nextString();
			} 
			else if (name.equals("name")) 
			{
				n = reader.nextString();
			} 
			else if (name.equals("crs"))
			{
				String t = null;
				
				reader.beginObject();
				while (reader.hasNext())
				{
					String read = reader.nextName();
					if(read.equals("type"))
					{
						t = reader.nextString();
					}
					else if(read.equals("properties"))
					{
						String p = null;
						
						reader.beginObject();
						while(reader.hasNext())
						{
							String read2 = reader.nextName();
							if(read2.equals("name"))
							{
								p = reader.nextString();
							}
							else
							{
								reader.skipValue();
							}
						}
						reader.endObject();
					}
					else
					{
						reader.skipValue();
					}
				}
				reader.endObject();
			}
			else if( name.equals("features"))
			{
				lista = readMessagesArray(reader);
			}
			else
			{
				reader.skipValue();
			}
		}
		reader.endObject();
		return lista;
	}
	
	/**
	 * Lee el arreglo de mensajes en la sección "features" del archivo de comparendos
	 * @param reader JsonReeader a leer
	 * @return lista con los comparendos en el archivo
	 * @throws IOException si no es posible leer la lista
	 */
	public ListaEncadenada readMessagesArray(JsonReader reader) throws IOException 
	{
		ListaEncadenada messages = new ListaEncadenada();

		reader.beginArray();
		while (reader.hasNext()) 
		{
			messages.agregarFinal(readFeatures(reader));
		}
		reader.endArray();
		return messages;
	}
	
	/**
	 * Lee las infracciones de la sección features en el archivo json de los comparendos
	 * @param reader JsonReader a leer
	 * @return Infraccion leida del archivo
	 * @throws IOException si no se puede leer el archivo
	 */
	public Infraccion readFeatures(JsonReader reader) throws IOException 
	{
		String type = null;
		String[] datos = null;
		ArrayList<Double> geo = null;

		reader.beginObject();
		while (reader.hasNext()) 
		{
			String name = reader.nextName();
			if (name.equals("type")) 
			{
				type = reader.nextString();
			} 
			else if (name.equals("properties")) 
			{
				datos = readProperties(reader);
			} 
			else if (name.equals("geometry"))
			{
				geo = readGeometry(reader);
			}
			else
			{
				reader.skipValue();
			}
		}
		reader.endObject();
		try
		{
			Infraccion retorno = new Infraccion(Integer.parseInt(datos[0]),datos[1],datos[2],datos[3],datos[4],datos[5],datos[6],datos[7],geo.get(0),geo.get(1));
			return retorno;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Lee los datos de la sección properties en el archivo json de los comparendos
	 * @param reader JsonReader a leer
	 * @return arreglo de strings con los datos leidos
	 * @throws IOException si no se pudo leer el archivo
	 */
	public String[] readProperties(JsonReader reader) throws IOException 
	{
		String[] data = new String[8];
		reader.beginObject();
		while (reader.hasNext()) 
		{
			String name = reader.nextName();
			if (name.equals("OBJECTID")) 
			{
				data[0] = reader.nextString();
			} 
			else if (name.equals("FECHA_HORA")) 
			{
				data[1] = reader.nextString();
			} 
			else if (name.equals("MEDIO_DETE")) 
			{
				data[2] = reader.nextString();
			} 
			else if (name.equals("CLASE_VEHI")) 
			{
				data[3] = reader.nextString();
			} 
			else if (name.equals("TIPO_SERVI")) 
			{
				data[4] = reader.nextString();
			} 
			else if (name.equals("INFRACCION")) 
			{
				data[5] = reader.nextString();
			} 
			else if (name.equals("DES_INFRAC")) 
			{
				data[6] = reader.nextString();
			}
			else if (name.equals("LOCALIDAD"))
			{
				data[7] = reader.nextString();
			}
			else
			{
				reader.skipValue();
			}
		}
		reader.endObject();
		return data;
	}
	
	/**
	 * Lee los datos de la sección geometry del archivo json con los comparendos
	 * @param reader JsonReader a leer
	 * @return ArrayList con los doubles de la longitud y latitud
	 * @throws IOException si no se pudo leer el archivo
	 */
	public ArrayList<Double> readGeometry(JsonReader reader) throws IOException 
	{
		ArrayList<Double> data = new ArrayList();
		String type = null;
		
		reader.beginObject();
		while (reader.hasNext()) 
		{
			String name = reader.nextName();
			if (name.equals("type"))
			{
				type = reader.nextString();
			}
			else if (name.equals("coordinates")) 
			{
				data = (ArrayList<Double>) readDoublesArray(reader);
			}
			else
			{
				reader.skipValue();
			}
		}
		reader.endObject();
		return data;
	}
	
	/**
	 * Lee el arreglo de doubles en el archivo json con los comparendos en la sección geometry
	 * @param reader JsonReader a leer
	 * @return Lista con los doubles leidos
	 * @throws IOException si no se pudo leer los datos
	 */
	public List<Double> readDoublesArray(JsonReader reader) throws IOException 
	{
		List<Double> doubles = new ArrayList<Double>();

		reader.beginArray();
		while (reader.hasNext()) 
		{
			doubles.add(reader.nextDouble());
		}
		reader.endArray();
		return doubles;
	}
	
	/**
	 * Busca una infracción en la lista con un ID recibido por parámetro
	 * @param pId ID de la infracción a buscar
	 * @return Infracción buscada, null si no es encontrada
	 */
	public Infraccion buscar(int pId)
	{
		Infraccion buscada = null;
		for(Nodo e = lista.darPrimero() ; e!=null ; e = e.darSiguiente())
		{
			Infraccion actual = (Infraccion) e.darElemento();
			
			if(actual.getId()==pId)
			{
				return actual;
			}
		}
		return null;
	}
	
	/**
	 * Elimina y retorna una infracción con un id recibido por parámetro
	 * @param pId ID de la infraccion a eliminar
	 * @return infraccion eliminada, null si no está la infraccion
	 */
	public Infraccion eliminar(int pId)
	{
		Infraccion inf = buscar(pId);
		return (Infraccion) lista.eliminar(inf);
	}
	
	/**
	 * Retona el tamaño de la lista
	 * @return tamaño de la lista
	 */
	public int darTamano()
	{
		return lista.darTamano();
	}
	
	/**
	 * Agrega una infracción recibida por parámetro al final de la lista
	 * @param pInf infracción a agregar
	 */
	public void agregarFinal(Infraccion pInf)
	{
		lista.agregarFinal(pInf);
	}
	
	/**
	 * Agrega una infracción rebida por parámetro al inicio de la lista
	 * @param pInf nfracción a agregar
	 */
	public void agregarInicio(Infraccion pInf)
	{
		lista.agregarInicio(pInf);
	}
	
	/**
	 * retorna la lista encadenada
	 * @return lista encadenada
	 */
	public ListaEncadenada darLista() {
		return lista;
	}
}
