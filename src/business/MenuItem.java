package business;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
	/*private String denumire;
	private float cantitate;
	private float pret;*/
		
	public abstract float computePrice();
	public abstract void addMenuItem(MenuItem a);
	public abstract String getNume();
	public abstract float getPret();
	public abstract float getCantititate();
	public abstract float getId();
	
	/*public float getCantitate() {
		return cantitate;
	}
	
	public String toString() {
		return "Nume"+denumire+", cantitate"+cantitate+", pret:"+pret;
	}*/

}
