package model.data_structures;

public class Nodo<E extends Comparable<E>> 
{
	private Nodo<E> siguiente;
	
	private E elemento;
	
	public Nodo(E pElelemtno)
	{
		siguiente = null;
		elemento = pElelemtno;
	}
	
	public E darElemento()
	{
		return elemento;
	}

	public Nodo<E> darSiguiente()
	{
		return siguiente;
	}
	
	public void cambiarSiguiente(Nodo<E> pSiguiente)
	{
		siguiente = pSiguiente;
	}
}
