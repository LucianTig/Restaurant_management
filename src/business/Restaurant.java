package business;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Observable;


public class Restaurant extends Observable implements RestaurantProcessing {
	private HashMap<Order, List<MenuItem>> comenzi = new HashMap<Order, List<MenuItem>>();

	private ArrayList<MenuItem> meniu;
	private String name;

	public Restaurant(String name) {
		this.name=name;
		meniu=this.deserializeMenu();
	}

	public void addMenuItem(MenuItem m) {
		meniu.add(m);
		this.searilizeMenu(meniu);
	}

	public void removeMenuItem(int id) {
		ArrayList<MenuItem> temp=new ArrayList<MenuItem>();
		for(MenuItem m:meniu)
			if(m.getId()==id)
				temp.add(m);
		for(MenuItem m:temp)
			meniu.remove(m);
		this.searilizeMenu(meniu);

	}

	public ArrayList<MenuItem> getMeniu() {
		return meniu;
	}

	@Override
	public void editMenuItem(int id, Object modific, int option) {
		// TODO Auto-generated method stub
		for(MenuItem a:meniu) {
			if(a instanceof BaseProduct) {
				if(id==a.getId()) {
					if(option==0)
						((BaseProduct) a).setPret((Integer.parseInt((String)modific)));
					if(option==1)
						((BaseProduct) a).setCantititate((Integer.parseInt((String)modific)));
					if(option==2)
						((BaseProduct) a).setNume((String)modific);
						
				}
			}
			if(a instanceof CompositeProduct) {
				ArrayList<MenuItem> list=((CompositeProduct) a).getCompositList();
				for(MenuItem b:list)
					if(id==b.getId()) {
						if(option==0)
							((BaseProduct) b).setPret((Integer.parseInt((String)modific)));
						if(option==1)
							((BaseProduct) b).setCantititate((Integer.parseInt((String)modific)));
						if(option==2)
							((BaseProduct) b).setNume((String)modific);
				}
			}
		}


	}

	@Override
	public void creatOrder(int id, String data, int table, ArrayList<MenuItem> lista) {
		// TODO Auto-generated method stub
		Order a=new Order(id, data, table);

		comenzi.put(a, lista);
		setChanged();
		notifyObservers(comenzi.get(a));


	}

	@Override
	public float computePriceOrder(Order a) {
		// TODO Auto-generated method stub
		ArrayList<MenuItem> list;
		list=(ArrayList<MenuItem>) comenzi.get(a);
		try {
			PrintWriter writer = new PrintWriter("C:\\Users\\Tigarean Lucian\\Desktop\\factura.txt", "UTF-8");
			writer.println("                                  Factura                             ");
			writer.println("Detalii de facturare client ");
			writer.println(a.toString());
			writer.println("Produse comandate: \n");
			float price=0;
			for(MenuItem item:list) {
				writer.println(item.toString());
				price=price+item.computePrice();
			}
			writer.println("Total de plata: "+price+" lei");
			writer.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	public Map<Order, List<MenuItem>> getComenzi(){
		return comenzi;
	}

	/*public static void main(String args[]) {
		Restaurant res=new Restaurant("ABC");
		BaseProduct b1=new BaseProduct(1,7,450,"Cartofi");
		BaseProduct b2=new BaseProduct(2,11,250,"Piept de pui");
		BaseProduct b3=new BaseProduct(3,19,350,"Ceafa porc");


		MenuItem m1=new BaseProduct(4,3,50,"sos");
		MenuItem m2=new CompositeProduct();
		m2.addMenuItem(b1);
		m2.addMenuItem(b2);

		ArrayList<MenuItem> l3=new ArrayList<MenuItem>();
		l3.add(b3);
		res.creatOrder(2, "21.01.2018", 3, l3);

 		ArrayList<MenuItem> l1=new ArrayList<MenuItem>();
		l1.add(m1);
		l1.add(m2);
		res.searilizeMenu(l1);

		res.creatOrder(1, "20.01.2019", 4, l1);

		ArrayList<MenuItem> l2=null;

		l2=res.deserializeMenu();
		for(MenuItem a:res.getMeniu())
			System.out.println(a);


		//System.out.println(m2.toString());	
	}*/

	public void searilizeMenu(ArrayList<MenuItem> m1) {
		try {
			FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Tigarean Lucian\\Desktop\\meniu.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(m1);
			out.close();
			fileOut.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<MenuItem> deserializeMenu() {
		ArrayList<MenuItem> men1 = null;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("C:\\Users\\Tigarean Lucian\\Desktop\\meniu.ser"));
			men1 =(ArrayList<MenuItem>) in.readObject(); 
			in.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return men1;
	}

}
