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
	
	public Nodo<E> darUltimo()
	{
		return ultimo;
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
	
	public E buscar(int i)
	{
		E elemento = null;
		Nodo<E> item = primero;
		int cont = 0;
		while(item!=null)
		{
			if(cont==i)
			{
				elemento = item.darElemento();
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
	
	public E eliminar(E elemento)
	{
		if(primero!=null && primero.darElemento().compareTo(elemento)!=0)
		{
			Nodo<E> anterior = primero;
			for(Nodo<E> n = primero.darSiguiente() ; n!=null ; n = n.darSiguiente())
			{
				if(n.darElemento().compareTo(elemento)==0)
				{
					anterior.cambiarSiguiente(n.darSiguiente());
					n.cambiarSiguiente(null);
					return n.darElemento();
				}
				anterior = n;
			}
		}
		else if(primero.darElemento().compareTo(elemento)==0)
		{
			Nodo<E> nuevo = primero;
			primero = primero.darSiguiente();
			nuevo.cambiarSiguiente(null);
			return primero.darElemento();
		}
		return null;
	}
	
	public E eliminar(int i)
	{
		E elemento = null;
		Nodo<E> anterior = primero;
		Nodo<E> item = primero.darSiguiente();
		int cont = 1;
		if(i!=0)
		{
			while(item!=null)
			{
				if(cont==i)
				{
					anterior.cambiarSiguiente(item.darSiguiente());
					elemento = item.darElemento();
					item.cambiarSiguiente(null);
					break;
				}
				anterior = item;
				item = item.darSiguiente();
				cont ++;
			}
		}
		else
		{
			Nodo<E> temp = primero;
			primero = primero.darSiguiente();
			temp.cambiarSiguiente(null);
			elemento = temp.darElemento();
		}
		
		return elemento;
	}
}
