package model.logic;

import java.util.Date;

public class Infraccion implements Comparable
{
	private int id;
	
	private String fecha;
	
	private String medio;
	
	private String clase;
	
	private String tipo;
	
	private String infr;
	
	private String desc;
	
	private String localidad;
	
	private double latitud;
	
	private double longitud;
	
	public Infraccion(int pId, String pFecha, String pMedio, String pClase, String pTipo, String pInfr, String pDesc, String pLoc, double pLat, double pLong)
	{
		id = pId;
		fecha = pFecha;
		medio = pMedio;
		clase = pClase;
		tipo = pTipo;
		infr = pInfr;
		desc = pDesc;
		localidad = pLoc;
		latitud = pLat;
		longitud = pLong;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public double getLatitud() {
		return latitud;
	}
	
	public double getLongitud() {
		return longitud;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public int getId() {
		return id;
	}
	
	public String getInfr() {
		return infr;
	}
	
	public String getMedio() {
		return medio;
	}
	
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setInfr(String infr) {
		this.infr = infr;
	}
	
	public void setMedio(String medio) {
		this.medio = medio;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public int compareTo(Object o) {
		try
		{
			Infraccion inf = (Infraccion) o;
			if(inf.id==this.id)
			{
				return 0;
			}
			else
			{
				return -1;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}

}
