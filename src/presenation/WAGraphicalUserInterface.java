package presenation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import business.MenuItem;
import business.Order;
import business.BaseProduct;
import business.CompositeProduct;
import business.Restaurant;

public class WAGraphicalUserInterface extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel pane3 = new JPanel(new GridBagLayout());
	private JPanel pane1 = new JPanel(new GridBagLayout());
	private JPanel pane2 = new JPanel(new GridBagLayout());
	private JPanel mainPane = new JPanel(new GridBagLayout());

	GridBagConstraints c = new GridBagConstraints();

	private JButton buttonC1 = new JButton("Waiter system interface");
	private JButton buttonC2 = new JButton("Administrator system interface");
	private JButton buttonC3 = new JButton("Restaurant menu");
	private JButton buttonC4 = new JButton("View all object");
	private JButton buttonC5 = new JButton("View object");
	private JButton buttonP = new JButton("Place an order");

	private JLabel label1 = new JLabel("Restaurant Management System");
	private Restaurant res=new Restaurant("ABC"); 

	public WAGraphicalUserInterface(String name) {
		super(name);
		
		ChefGraphicalUserInterface obs1=new ChefGraphicalUserInterface();
		res.addObserver(obs1);

		c.gridx = 0;
		c.gridy = 0;
		pane2.add(label1, c);
		
		c.gridx = 0;
		c.gridy = 1;
		buttonC1.addActionListener(this);
		pane2.add(buttonC1, c);
		
		c.gridx = 0;
		c.gridy = 2;
		buttonC2.addActionListener(this);
		pane2.add(buttonC2, c);
		
		c.gridx = 0;
		c.gridy = 3;
		buttonC3.addActionListener(this);
		pane2.add(buttonC3, c);
		
		this.add(pane2);
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		
		
		
		if(source==buttonC1) {
			JDialog d=new JDialog(this,"Waiter interface");
			JPanel panec1 = new JPanel(new GridBagLayout());
			JPanel panec2 = new JPanel(new BorderLayout());
			JPanel mainPane = new JPanel(new  BorderLayout());

			GridBagConstraints c = new GridBagConstraints();

			JButton buttonC1 = new JButton("Add new order");
			JButton button2 = new JButton("View all orders");
			JButton button3 = new JButton("Compute bill order");
			
			JTextField text1id = new JTextField(8);
			JTextField text2date = new JTextField(8);
			JTextField text3table = new JTextField(8);
			JTextField text4ListM = new JTextField(8);
			
			JLabel label1 = new JLabel("Id");
			JLabel label2 = new JLabel("Date");
			JLabel label3 = new JLabel("Table");
			JLabel label4 = new JLabel("MenuList");
			
			c.gridx = 0;
			c.gridy = 0;
			panec1.add(label1, c);
			
			c.gridx = 0;
			c.gridy = 1;
			panec1.add(text1id, c);
			
			c.gridx = 0;
			c.gridy = 2;
			panec1.add(label2, c);
			
			c.gridx = 0;
			c.gridy = 3;
			panec1.add(text2date, c);
			
			c.gridx = 0;
			c.gridy = 4;
			panec1.add(label3, c);
			
			c.gridx = 0;
			c.gridy = 5;
			panec1.add(text3table, c);
			
			c.gridx = 0;
			c.gridy = 6;
			panec1.add(label4, c);
			
			c.gridx = 0;
			c.gridy = 7;
			panec1.add(text4ListM, c);
			
			c.gridx = 0;
			c.gridy = 8;
			panec1.add(buttonC1, c);
			
			panec2.add(button2, BorderLayout.NORTH);
			
			
			buttonC1.addActionListener(new ActionListener(){  
				public void actionPerformed(ActionEvent e){  
					String s=text4ListM.getText();
					String[] ss=s.split(",");
					int[] a = new int[100];
					int i;
					for(i=0;i<ss.length;i++)
					{	    
					    a[i]=Integer.parseInt(ss[i]);
					}
					ArrayList<MenuItem> men1=new ArrayList<MenuItem>();
					for(int j=0;j<i;j++)
						for(MenuItem m1:res.getMeniu()) {
							if(m1 instanceof BaseProduct)
								if(m1.getId()==a[j]) {
									men1.add(m1);
								}
							if(m1 instanceof CompositeProduct) {
								ArrayList<MenuItem> lista;
								lista=((CompositeProduct)m1).getCompositList();
								for(MenuItem m2:lista)
									if(m2.getId()==a[j])
										men1.add(m2);
							}
					} 
					res.creatOrder(Integer.parseInt(text1id.getText()), text2date.getText(), Integer.parseInt(text3table.getText()), men1);
				}  
			});
			
			button2.addActionListener(new ActionListener(){  
				public void actionPerformed(ActionEvent e){  
					
					DefaultTableModel model = new DefaultTableModel();
					Map<Order, List<MenuItem>> com;
					com=res.getComenzi();
					
					
					int i=0;
					int ok=1;

					for(Order object:com.keySet()) {
						String data[]=new String[100];
						System.out.println(object);
						for(Field field:object.getClass().getDeclaredFields()) {
							field.setAccessible(true);
							Object value;
							try {
								value=field.get(object);
								if(ok==1)
									model.addColumn(field.getName());
								data[i]=value.toString();
								i++;


							}
							catch(IllegalArgumentException exp) {
								exp.printStackTrace();
							}
							catch(IllegalAccessException exp) {
								exp.printStackTrace();
							}
						} 
						model.addRow(data);
						i=0;
						ok=0;
					}

					JTable j=new JTable(model);
					JScrollPane sp = new JScrollPane(j);
					
					panec2.add(sp, BorderLayout.CENTER);
					
					panec2.revalidate();
					
				}  
			});
			
			button3.addActionListener(new ActionListener(){  
				public void actionPerformed(ActionEvent e){  
					res.computePriceOrder(new Order(Integer.parseInt(text1id.getText()), text2date.getText(), Integer.parseInt(text3table.getText())));
				}  
			});
			
			
			mainPane.add(panec1,BorderLayout.WEST);
			mainPane.add(panec2,BorderLayout.CENTER);
			mainPane.add(button3,BorderLayout.EAST);
			d.add(mainPane);
			d.setSize(600,400);
			d.setVisible(true);
			
			

			
			
			
		}
		if(source==buttonC2) {
			JDialog d=new JDialog(this,"Administrator interface");
			JPanel panec1 = new JPanel(new GridBagLayout());
			JPanel paneEdit = new JPanel(new GridBagLayout());
			JPanel paneDelete = new JPanel(new GridBagLayout());
			JPanel panec2 = new JPanel(new BorderLayout());
			JPanel mainPane = new JPanel(new  BorderLayout());

			GridBagConstraints c = new GridBagConstraints();

			JButton buttonC1 = new JButton("Add new menu item");
			JButton button2 = new JButton("Edit menu item");
			JButton button3 = new JButton("Delete menu item");
			JButton button4 = new JButton("View all menu item");
			
			JTextField text1id = new JTextField(8);
			JTextField text2pret= new JTextField(8);
			JTextField text3can = new JTextField(8);
			JTextField text4den = new JTextField(8);
			
			JLabel label1 = new JLabel("Id");
			JLabel label2 = new JLabel("Pret");
			JLabel label3 = new JLabel("Cantitate");
			JLabel label4 = new JLabel("Denumire");
			
			c.gridx = 0;
			c.gridy = 0;
			panec1.add(label1, c);
			
			c.gridx = 0;
			c.gridy = 1;
			panec1.add(text1id, c);
			
			c.gridx = 0;
			c.gridy = 2;
			panec1.add(label2, c);
			
			c.gridx = 0;
			c.gridy = 3;
			panec1.add(text2pret, c);
			
			c.gridx = 0;
			c.gridy = 4;
			panec1.add(label3, c);
			
			c.gridx = 0;
			c.gridy = 5;
			panec1.add(text3can, c);
			
			c.gridx = 0;
			c.gridy = 6;
			panec1.add(label4, c);
			
			c.gridx = 0;
			c.gridy = 7;
			panec1.add(text4den, c);
			
			c.gridx = 0;
			c.gridy = 8;
			panec1.add(buttonC1, c);
			
			
			buttonC1.addActionListener(new ActionListener(){  
				public void actionPerformed(ActionEvent e){  
					MenuItem m1=new BaseProduct(Integer.parseInt(text1id.getText()),Integer.parseInt(text2pret.getText()),Integer.parseInt(text3can.getText()),text4den.getText());
					res.addMenuItem(m1);
				}  
			});
				
			String optionTableSet[]= {"Pret","Cantitate","Denumire"};
			JList optSet=new JList(optionTableSet);
			optSet.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			JTextField texte1id = new JTextField(8);
			JTextField texteModific= new JTextField(8);
			
			JLabel labele1 = new JLabel("Modifiy by id");
			
			c.gridx = 0;
			c.gridy = 0;
			paneEdit.add(labele1, c);
			
			c.gridx = 0;
			c.gridy = 1;
			paneEdit.add(texte1id, c);
			
			c.gridx = 0;
			c.gridy = 2;
			paneEdit.add(optSet, c);
			
			c.gridx = 0;
			c.gridy = 3;
			paneEdit.add(texteModific, c);
			
			c.gridx = 0;
			c.gridy = 4;
			paneEdit.add(button2, c);
			
			/*c.gridx = 0;
			c.gridy = 4;
			paneEdit.add(labele3, c);
			
			c.gridx = 0;
			c.gridy = 5;
			paneEdit.add(texte3can, c);
			
			c.gridx = 0;
			c.gridy = 6;
			paneEdit.add(labele4, c);
			
			c.gridx = 0;
			c.gridy = 7;
			paneEdit.add(texte4den, c);*/
			
			button2.addActionListener(new ActionListener(){  
				public void actionPerformed(ActionEvent e){  
					int option=(int) optSet.getSelectedIndex();
					
					res.editMenuItem(Integer.parseInt(texte1id.getText()), texteModific.getText(), option);
					
				}  
			});
			
			
			
			JTextField textd1id = new JTextField(8);
			
			JLabel labeld1 = new JLabel("Delete by id");
			
			c.gridx = 0;
			c.gridy = 0;
			paneDelete.add(labeld1, c);
			
			c.gridx = 0;
			c.gridy = 1;
			paneDelete.add(textd1id, c);
			
			c.gridx = 0;
			c.gridy = 2;
			paneDelete.add(button3, c);
			
			button3.addActionListener(new ActionListener(){  
				public void actionPerformed(ActionEvent e){  
					res.removeMenuItem(Integer.parseInt(textd1id.getText()));
				}  
			});
			
			button4.addActionListener(new ActionListener(){  
				public void actionPerformed(ActionEvent e){  
					ArrayList<MenuItem> lista1=res.getMeniu();
					for(MenuItem a:lista1)
						System.out.println(a);
					
					retrieveProperties(lista1);
				}  
			});
			
			
			mainPane.add(button4,BorderLayout.NORTH);
			mainPane.add(panec1,BorderLayout.WEST);
			mainPane.add(paneEdit,BorderLayout.CENTER);
			mainPane.add(paneDelete,BorderLayout.EAST);
			
			
			d.add(mainPane);
			d.setSize(600,400);
			d.setVisible(true);
			
			
		}
		if(source==buttonC3) {
			ArrayList<MenuItem> lista1=res.getMeniu();
			retrieveProperties(lista1);
			
		}
		
	}
	
	public void retrieveProperties(ArrayList<MenuItem> lista) {

		DefaultTableModel model = new DefaultTableModel();

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

		JDialog d = new JDialog(this, "Restaurant Menu");
		d.add(sp);
		d.setSize(600,400);
		d.setVisible(true);
	}
	
	public static void main(String args[]) {
		JFrame frame = new WAGraphicalUserInterface("Restaurant management");
		frame.setMinimumSize(new Dimension(400, 200));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	

}
