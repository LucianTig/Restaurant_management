package business;

public class Order{
	private int orderId;
	private String date;
	private int table;
	
	public Order(int id, String date, int table) {
		orderId=id;
		this.date=date;
		this.table=table;
	}
	
	@Override
	public int hashCode() {
		return orderId%40;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
	        return true;
		if(obj==null || obj.getClass()!=this.getClass())
			return false;
		Order ord=((Order)obj);
		return (ord.getId()==this.orderId && ord.getDate().equals(this.date) && ord.getTable()==this.table);
	}
	
	public String toString() {
		return "Id="+orderId+", masa:"+table+", data:"+date;
	}
	
	public int getId() {
		return this.orderId;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public int getTable() {
		return this.table;
	}

}
