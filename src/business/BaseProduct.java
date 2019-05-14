package business;

import java.io.Serializable;

public class BaseProduct extends MenuItem implements Serializable {
	private int id;
	private String denumire;
	private float cantitate;
	private float pret;

	
	public BaseProduct(int id, float p, float c, String d) {
		this.id=id;
		pret=p;
		cantitate=c;
		denumire=d;
	}
	
	@Override
	public float computePrice() {
		return pret;
	}

	@Override
	public void addMenuItem(MenuItem a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Nume: "+denumire+", cantitate:"+cantitate+", pret:"+pret;
	}

	@Override
	public String getNume() {
		// TODO Auto-generated method stub
		return denumire;
	}

	@Override
	public float getPret() {
		// TODO Auto-generated method stub
		return pret;
	}

	@Override
	public float getCantititate() {
		// TODO Auto-generated method stub
		return cantitate;
	}

	@Override
	public float getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	public void setNume(String den) {
		// TODO Auto-generated method stub
		this.denumire=den;
	}

	public void setPret(float pret) {
		// TODO Auto-generated method stub
		this.pret=pret;
	}

	public void setCantititate(float cantitate) {
		// TODO Auto-generated method stub
		this.cantitate=cantitate;
	}

}
