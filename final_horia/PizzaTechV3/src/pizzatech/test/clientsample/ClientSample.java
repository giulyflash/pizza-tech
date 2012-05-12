package pizzatech.test.clientsample;

import pizzatech.test.*;

public class ClientSample {

	public static void main(String[] args) {
	        System.out.println("***********************");
	        System.out.println("Create Web Service Client...");
	        PizzaTechService_Service service1 = new PizzaTechService_Service();
	        System.out.println("Create Web Service...");
	        PizzaTechService port1 = service1.getPizzaTechPort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port1.getAllOrders());
	        System.out.println("Server said: port1.addPizza() is a void method!");
	        System.out.println("Server said: " + port1.getAllPizzas());
	        System.out.println("Server said: " + port1.getAllMainIngedients());
	        System.out.println("Server said: " + port1.getAllDoughTypes());
	        System.out.println("Server said: " + port1.getAllToppings());
	        System.out.println("Server said: port1.createOrder() is a void method!");
	        System.out.println("Create Web Service...");
	        PizzaTechService port2 = service1.getPizzaTechPort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port2.getAllOrders());
	        System.out.println("Server said: port2.addPizza() is a void method!");
	        System.out.println("Server said: " + port2.getAllPizzas());
	        System.out.println("Server said: " + port2.getAllMainIngedients());
	        System.out.println("Server said: " + port2.getAllDoughTypes());
	        System.out.println("Server said: " + port2.getAllToppings());
	        System.out.println("Server said: port2.createOrder() is a void method!");
	        System.out.println("***********************");
	        System.out.println("Call Over!");
	}
}
