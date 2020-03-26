package model.data_structures;

public class TablaHashSL<K extends Comparable<K> , V>
{
	private K[] keys;

	private V[] values;

	private int M = 20;

	private int N;
	
	private int R;
	
	private int Mi;

	public TablaHashSL() 
	{
		keys = (K[]) new Comparable[M];
		values = (V[]) new Object[M];
		Mi = M;
	}
	
	public TablaHashSL(int cap) 
	{
		keys = (K[]) new Comparable[cap];
		values = (V[]) new Object[cap];
	}

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

	public V dar(K key)
	{      
		for (int i = hash(key); keys[i] != null && i+1<M; i = (i + 1) % M)         
			if (keys[i].equals(key))             
				return values[i];      
		return null;   
	}

	public void eliminar(K key)
	{

	}

	public K[] getKeys() {
		return keys;
	}

	public int getM() {
		return M;
	}

	public int getN() {
		return N;
	}
	
	public int getMi() {
		return Mi;
	}
	
	public int getR() {
		return R;
	}

	public V[] getValues() {
		return values;
	}
	
	public int hash(K key)
	{
		 return (key.hashCode() & 0x7fffffff) % M; 
	}
}
