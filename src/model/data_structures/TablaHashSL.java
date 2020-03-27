package model.data_structures;

public class TablaHashSL<K extends Comparable<K> , V>
{
	/**
	 * Llaves de la tablas
	 */
	private K[] keys;

	/**
	 * Valores de la table
	 */
	private V[] values;

	/**
	 * Capacidad la tabla
	 */
	private int M = 20;

	/**
	 * Cantidad de duplas en la tabla
	 */
	private int N;
	
	/**
	 * Numero de rehashes
	 */
	private int R;
	
	/**
	 * Capacidad inicial de la tabla
	 */
	private int Mi;

	/**
	 * Constructor
	 */
	public TablaHashSL() 
	{
		keys = (K[]) new Comparable[M];
		values = (V[]) new Object[M];
		Mi = M;
	}
	
	/**
	 * Contructor
	 * @param cap Capacidad con la que se inicia la tabla
	 */
	public TablaHashSL(int cap) 
	{
		keys = (K[]) new Comparable[cap];
		values = (V[]) new Object[cap];
	}

	/**
	 * Agraga una dupla de llave y valor a la tabla
	 * @param key Llave a agregar. key =! null
	 * @param value Valor a agregar. valor != null
	 */
	public void agregar(K key , V value)
	{
		if (0.75 <= N/M)
			resize(2*M);
//		else if (0.5 >= N/M)
//			resize((int) M/2);
		
		int i; 
		for (i = hash(key) ; keys[i] != null; i = (i + 1) % M) 
			if (keys[i].equals(key)) 
			{ 
				values[i] = value; 
				return; 
			} 
		keys[i] = key; 
		values[i] = value; 
		N++;
	}
	
	/**
	 * Cambia el tamaño de la tabla por uno dado por parámetro
	 * @param cap Nuevp tamaño de la tabla
	 */
	private void resize(int cap) 
	{    
		TablaHashSL<K, V> t;    
		t = new TablaHashSL<K, V>(cap);    
		for (int i = 0; i < M; i++)       
			if (keys[i] != null)           
				t.agregar(keys[i], values[i]);    
		keys = t.keys;    
		values = t.values;    
		M    = t.M; 
		R++;
	}

	/**
	 * Retorna el valor para una llave recibida por parámetro
	 * @param key Llave a buscar en la tabla. key != null
	 * @return Valor correpondiente la llave recibida. Null en caso de que no esté la llave en la tabla
	 */
	public V dar(K key)
	{      
		for (int i = hash(key); keys[i] != null && i+1<M; i = (i + 1) % M)         
			if (keys[i].equals(key))             
				return values[i];      
		return null;   
	}

	/**
	 * Elimina una dupla con un allave recibida por parámetro
	 * @param key llave a buscar y eliminar en la tabla.
	 */
	public void eliminar(K key)
	{

	}

	/**
	 * Reorna las llaves de la tabla
	 * @return Llaves de la tabla
	 */
	public K[] getKeys() {
		return keys;
	}

	/**
	 * Retorna la capacidad actual de la tabla
	 * @return Capacidad actual de la tabla
	 */
	public int getM() {
		return M;
	}

	/**
	 * Retorna el numero de duplas en la tabla
	 * @return Numero de duplas en la tabla
	 */
	public int getN() {
		return N;
	}
	
	/**
	 * Retorna la capacidad inicial de la tabla
	 * @return Capacida inicial de la tabla
	 */
	public int getMi() {
		return Mi;
	}
	
	/**
	 * Retorna el numero de rahashes en la tabla
	 * @return Número de rehashes en la tabla
	 */
	public int getR() {
		return R;
	}

	/**
	 * Retona el arreglo de valores de la tabla
	 * @return Arreglo de valores de la tabla
	 */
	public V[] getValues() {
		return values;
	}
	
	/**
	 * Retorna el código hash de un llave reciba por parámetro
	 * @param key Llave de la cual obtener el código hash 
	 * @return Código hash de la llave
	 */
	public int hash(K key)
	{
		 return (key.hashCode() & 0x7fffffff) % M; 
	}
}
