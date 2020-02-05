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
	 * Lee el archivo JSON
	 * @param path
	 */
	public void leerDatos(String path)
	{
		Gson gson = new Gson();
		JsonReader reader;
		
		try 
		{
			readJsonStream(new FileInputStream(path));
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
	
	public Infraccion eliminar(int pId)
	{
		Infraccion inf = buscar(pId);
		return (Infraccion) lista.eliminar(inf);
	}
	
	public int darTamano()
	{
		return lista.darTamano();
	}
	
	public void agregarFinal(Infraccion pInf)
	{
		lista.agregarFinal(pInf);
	}
	
	public void agregarInicio(Infraccion pInf)
	{
		lista.agregarInicio(pInf);
	}
	
	public ListaEncadenada darLista() {
		return lista;
	}
}
