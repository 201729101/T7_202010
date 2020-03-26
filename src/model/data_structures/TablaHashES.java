package model.data_structures;

public class TablaHashES<K extends Comparable<K> , V> 
{	
	private Chain[] values;

	private int N;

	private int M = 20;

	private int Mi;

	private int R;

	public class Node<K extends Comparable<K> , V > 
	{
		/**
		 * Referencia al siguiente nodo
		 */
		private Node siguiente;


		/**
		 * Elelemtno generico contenido en el nodo
		 */
		private V value;

		private K key;
		/**
		 * Constructor con un elemento pasado por parametro
		 * @param pElelemtno elemento a añadir en el nodo
		 */
		public Node(K pkey, V pElelemtno)
		{
			key = pkey;
			siguiente = null;
			value = pElelemtno;
		}

		/**
		 * Retorna el elemento del nodo
		 * @return elemento del nodo
		 */
		public V getValue()
		{
			return value;
		}

		public K getKey() {
			return key;
		}

		public void setValue(V value2)
		{
			value = value2;
		}

		//		public void cambiarKey(K pkey)
		//		{
		//			key = pkey;
		//		}

		/**
		 * Retorna el siguiente nodo
		 * @return siguiente nodo
		 */
		public Node darSiguiente()
		{
			return siguiente;
		}

		/**
		 * Cambia el siguiento nodo por uno recibido por parámetro
		 * @param pSiguiente nuevo siguiente nodo
		 */
		public void cambiarSiguiente(Node pSiguiente)
		{
			siguiente = pSiguiente;
		}

	}

	public class Chain<K extends Comparable<K>, V>
	{
		private Node first;

		private Node last;

		public Chain()
		{
			first = null;
			last = null;
		}

		public Node getFirst() {
			return first;
		}

		public void add(K key, V value)
		{
			Node nodo = new Node( key, value );
			if(first == null)
			{
				first = nodo;
				last = nodo;
			}
			else
			{
				last.cambiarSiguiente(nodo);
				last = nodo;
			}
		}
	}


	public TablaHashES() 
	{
		values = new Chain[M];
		Mi=M;
	}

	public TablaHashES(int cap)
	{
		values = new Chain[cap];
	}

	public void agregar(K key , V value)
	{
		//		if (2 >= N/M)
		//			resize((int) M/2);
		//		else 
		if (5 <= N/M)
			resize(2*M);


		int hash = hash(key);

		if(values[hash]!=null)
			for(Node n = values[hash].getFirst() ; n!= null ; n=n.darSiguiente())
				if(n.getKey().equals(key))
				{
					n.setValue(value);
					return;
				}

		if(values[hash]==null)
			values[hash] = new Chain();

		values[hash].add(key, value);;
		N++;
	}

	public V dar(K key)
	{
		int hash = hash(key);

		if(values[hash]!=null)
			for(Node n = values[hash].getFirst() ; n!= null ; n = n.darSiguiente())
			{
				if(n.getKey().equals(key))
				{
					return (V) n.getValue();
				}
			}
		return null;
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

	public Chain[] getValues() {
		return values;
	}

	private void resize(int cap) 
	{    
		TablaHashES<K, V> t;    
		t = new TablaHashES<K, V>(cap);    
		for (int i = 0; i < M; i++)       
			if (values[i] != null)
				for(Node n = values[i].getFirst() ; n!= null ; n = n.darSiguiente())
					t.agregar((K) n.getKey(), (V) n.getValue());        
		values = t.values;    
		M    = t.M; 
		R++;
	}

	public int hash(K key)
	{
		return (key.hashCode() & 0x7fffffff) % M; 
	}
}
