package com.registration;

public class Food {
  private int id;
  private String category;
  private String Name;
  private float price;
  private int res_id;
  
public Food() {
	super();
}

public Food(int id, String category, String name, float price, int res_id) {
	super();
	this.id = id;
	this.category = category;
	Name = name;
	this.price = price;
	this.res_id = res_id;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getCategory() {
	return category;
}

public void setCategory(String category) {
	this.category = category;
}

public String getName() {
	return Name;
}

public void setName(String name) {
	Name = name;
}


public float getPrice() {
	return price;
}

public void setPrice(float price) {
	this.price = price;
}

public int getRes_id() {
	return res_id;
}

public void setRes_id(int res_id) {
	this.res_id = res_id;
}


        
@Override
public String toString() 
{
//	return " Name=" + Name + " "+ "category=" + category + " " + "price=" + price ;
	
//	System.out.println("Name"+"\t " + "category" +"" + "\t"+"price");
return Name +"\t  \t\t\t"+category+"        "+"\t\t\t\t"+price+"\t";
	
}

}
