package model.data_structures;

public class GrafoND<E extends Comparable<E>>
{
	private int V;  

	private int E; 

	private Object[] adj;   
	
	private boolean[] marked;  
	
	private int[] edgeTo; 
	
	private final int s=0;
	
	private int count; 

	public GrafoND(int V)
	{
		V=V;
		E=0;
		adj = new Object[V];      // Create array of lists.      
		for (int v = 0; v < V; v++)             // Initialize all lists          
			adj[v] = new Objeto(v,null);
		marked = new boolean[V]; 
		edgeTo = new int[V]; 
//		s = ps; 
		dfs(s); 
	}

	private class Objeto
	{
		private int index;

		private E elemento;

		private ListaEncadenada<Arc> edges;

		public Objeto(int i, E elem)
		{
			index = i;
			elemento = elem;
			edges = new ListaEncadenada<Arc>();
		}

		public ListaEncadenada<Arc> getEdges() {
			return edges;
		}

		public E getElemento() {
			return elemento;
		}

		public void setElemento(E elemento) {
			this.elemento = elemento;
		}

		public int getIndex() {
			return index;
		}
	}

	public int V()  {  return V;  } 

	public int E()  {  return E;  } 

	public void addEdge(int v, int w, double costArc)   
	{      
		Arc one = new Arc(w,costArc);
		Arc two = new Arc(v,costArc);
		((Objeto) adj[v]).getEdges().agregarFinal(one);      
		((Objeto) adj[w]).getEdges().agregarFinal(two);
		E++;    
	}   

	public Iterable<Integer> adj(int v)   
	{  
		return ((Objeto) adj[v]).getEdges();  
	} 

	public E getInfoVertex(int idVertex)
	{
		return ((Objeto) adj[idVertex]).getElemento();
	}

	public void setInfoVertex(int idVertex, E Vertex)
	{
		((Objeto) adj[idVertex]).setElemento(Vertex);
	}

	public double getCostArc(int idVertexIni, int idVertexFin)
	{
		ListaEncadenada lista = ((Objeto) adj[idVertexIni]).getEdges();
		for(Object e:lista)
		{
			Arc edge = (Arc) e;
			if(edge.getId()==idVertexFin)
			{
				return edge.getDistance();
			}
		}
		return -1;
	}
	
	public void setCostArc(int idVertexIni, int idVertexFin, double costArc)
	{
		ListaEncadenada lista = ((Objeto) adj[idVertexIni]).getEdges();
		for(Object e:lista)
		{
			Arc edge = (Arc) e;
			if(edge.getId()==idVertexFin)
			{
				edge.setDistance(costArc);
			}
		}
	}
	
	public void addVertex(int idVertex, E Vertex)
	{
		((Objeto) adj[idVertex]).setElemento(Vertex);
	}
	
	private void dfs(int v) 
	{ 
		marked[v] = true; 
		for (int w : adj(v)) 
			if (!marked[w]) 
			{ 
				edgeTo[w] = v; 
				dfs(w);
				count++;
			} 
	}
	
	public boolean hasPathTo(int v) 
	{ return marked[v]; }
	
	public int CC() 
	{ return count; }

}
