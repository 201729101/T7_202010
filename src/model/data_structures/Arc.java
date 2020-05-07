package model.data_structures;

public class Arc implements Comparable<Arc>
{
	private int id;

	private double distance;

	public Arc(int pId, double pDis)
	{
		id=pId;
		distance=pDis;
	}

	public double getDistance() {
		return distance;
	}

	public int getId() {
		return id;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public int compareTo(Arc o) {
		if(id==o.getId())
			return 0;
		return id>o.getId()?1:-1;
	}

}