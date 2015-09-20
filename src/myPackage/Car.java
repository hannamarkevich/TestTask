package myPackage;

public class Car {
private int id;
private String make;
private String model;
private int year;
private String vin;
private int clientId;
public Car(int id,String make,String model,int year,String vin, int clientId)
{
	this.id=id;
	this.make=make;
	this.model=model;
	this.year=year;
	this.vin=vin;
	this.clientId=clientId;
	
}
public String toString(){
	return make+" "+model+ " "+year+" "+vin;
	
}

public String parseForHref()
{
	return "id="+id+"&make="+make+"&model="+model+"&year="+year+"&vin="+vin+"&clientId="+clientId;
}
}
