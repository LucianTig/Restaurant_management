package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem implements Serializable{
	private ArrayList<MenuItem> menuList = new ArrayList<MenuItem>();

	
	@Override
	public float computePrice() {
		// TODO Auto-generated method stub
		float pr=0;
		for(MenuItem a:menuList)
			pr+=a.computePrice();
		return pr;
	}	
	
	public void addMenuItem(MenuItem m) {
		menuList.add(m);
	}
	
	public void removeMenuItem(MenuItem m) {
		menuList.remove(m);
	}
	
	public String toString() {
		String a="";
		for(MenuItem b:menuList)
			a=a+b.toString()+"\n";
		return a;
	}
	
	public ArrayList<MenuItem> getCompositList(){
		return menuList;
	}

	@Override
	public String getNume() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getPret() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getCantititate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getId() {
		// TODO Auto-generated method stub
		return 0;
	}
}
