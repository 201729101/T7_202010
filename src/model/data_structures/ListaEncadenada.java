package model.data_structures;

import model.data_structures.Nodo;

public class ListaEncadenada<E extends Comparable<E>> 
{
	private Nodo<E> primero;
	
	private Nodo<E> ultimo;
	
	private int numElem;
	
	public ListaEncadenada()
	{
		primero = null;
		ultimo = null;
		numElem = 0;
	}
	
	public int darTamano()
	{
		return numElem;
	}
	
	public Nodo<E> darPrimero()
	{
		return primero;
	}
	
	
	public E get(int i)
	{
		E elemento = null;
		Nodo<E> item = primero;
		int cont = 0;
		while(item!=null)
		{
			if(cont==i)
			{
				elemento = (E) item;
				break;
			}
			item = (Nodo<E>) item.darSiguiente();
			cont ++;
		}
		
		return elemento;
	}
	
	public void agregarFinal(E elemento)
	{
		Nodo<E> nodo = new Nodo<E>( elemento );
		if(primero == null)
		{
			primero = nodo;
			ultimo = nodo;
		}
		else
		{
			ultimo.cambiarSiguiente(nodo);
			ultimo = nodo;
		}
		numElem++;
	}
	
	public void agregarInicio(E elemento)
	{
		Nodo<E> nodo = new Nodo<E>(elemento);
		
		if(primero==null)
		{
			primero = nodo;
			ultimo = nodo;
		}
		else
		{
			nodo.cambiarSiguiente(primero);
			primero = nodo;
		}
		numElem++;
	}
	
	public E buscar(E elemento)
	{	
		if(primero==null)
		{
			return null;
		}
		else
		{
			for(Nodo<E> n = primero ; n!=null ; n = n.darSiguiente())
			{
				if(n.darElemento().compareTo(elemento)==0)
				{
					return n.darElemento();
				}
			}
			return null;
		}
	}
	
	public void eliminar(E elemento)
	{
		E buscado = buscar(elemento);
		if(primero!=null && primero.darElemento().compareTo(buscado)!=0)
		{
			Nodo<E> anterior = primero;
			for(Nodo<E> n = primero.darSiguiente() ; n!=null ; n = n.darSiguiente())
			{
				if(n.darElemento().compareTo(buscado)==0)
				{
					anterior.cambiarSiguiente(n.darSiguiente());
					n.cambiarSiguiente(null);
					break;
				}
				anterior = n;
			}
		}
		else if(primero.darElemento().compareTo(elemento)==0)
		{
			Nodo<E> nuevo = primero;
			primero = primero.darSiguiente();
			nuevo.cambiarSiguiente(null);
		}
	}
}
