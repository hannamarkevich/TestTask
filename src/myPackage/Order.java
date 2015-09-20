package myPackage;
import java.util.Date;


public class Order {
private int id;
private Date date;
private int amount;
private String status;
private int carId;
public Order(int id,Date date,int amount,String status, int carId){
	this.id=id;
	this.date=date;
	this.amount=amount;
	this.status=status;
	this.carId=carId;
}
public String toString()
{
	return date.toString()+" "+amount+ " "+status;
}
public String parseForHref()
{
	return "id="+id+"&date="+date.toString()+"&amount="+amount+"&status="+status+"&carId="+carId;
}
}
