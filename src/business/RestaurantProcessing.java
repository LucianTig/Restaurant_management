package business;

import java.util.ArrayList;

public interface RestaurantProcessing {
	public void addMenuItem(MenuItem m);
	public void removeMenuItem(int id);
	public void editMenuItem(int id, Object a, int option);
	
	public void creatOrder(int id, String data, int table, ArrayList<MenuItem> lista);
	public float computePriceOrder(Order a);
	

}
