package presenation;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import business.BaseProduct;
import business.CompositeProduct;
import business.MenuItem;



public class ChefGraphicalUserInterface extends JFrame implements Observer {

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
				
		JPanel jpa=new JPanel();
		jpa.setLayout(new BorderLayout());
		JLabel label1 = new JLabel("NEW oreder!");
		
		jpa.add(label1, BorderLayout.NORTH); 

	
		this.retrieveProperties(((ArrayList<MenuItem>) arg1),jpa );

	}
	
	public void retrieveProperties(ArrayList<MenuItem> lista, JPanel jpa) {

		DefaultTableModel model = new DefaultTableModel();
		JDialog d1 = new JDialog(this, "Update");

		int i=0;
		int ok=1;

		for(MenuItem a:lista) {
			String data[]=new String[100];
			if(a instanceof BaseProduct) {
			for(Field field:a.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object value;
				try {
					value=field.get(a);
					if(ok==1)
						model.addColumn(field.getName());
					data[i]=value.toString();
					i++;
				}
				
				catch(IllegalArgumentException e) {
					e.printStackTrace();
				}
				catch(IllegalAccessException e) {
					e.printStackTrace();
				}
			} 
			model.addRow(data);
			i=0;
			ok=0;
			}
			if(a instanceof CompositeProduct) {
				ArrayList<MenuItem> listCM;
				listCM=((CompositeProduct) a).getCompositList();
				for(MenuItem objC: listCM) {
					for(Field field:objC.getClass().getDeclaredFields()) {
						field.setAccessible(true);
						Object value;
						try {
							value=field.get(objC);
							if(ok==1)
								model.addColumn(field.getName());
							data[i]=value.toString();
							i++;
						}
						
						catch(IllegalArgumentException e) {
							e.printStackTrace();
						}
						catch(IllegalAccessException e) {
							e.printStackTrace();
						}
					} 
					model.addRow(data);
					i=0;
					ok=0;
					
					
				}
			}
		}

		JTable j=new JTable(model);
		JScrollPane sp = new JScrollPane(j);
		
		JDialog d = new JDialog(this, "New order!");
		d.setLayout(new BorderLayout());
		JLabel label1=new JLabel("NEW ORDER!");
		d.add(label1, BorderLayout.NORTH);
		d.add(sp, BorderLayout.CENTER);
		
		d.setSize(600,400);
		d.setVisible(true);
	}

}
