package client;

import java.util.*;

public class Order {

	private int id;
	private String address;
	private boolean delivered;
	private Set pizzas = new HashSet();

	public void addPizza(Pizza pizza) {

		pizzas.add(pizza);
	}

	public void removePizza(Pizza pizza) {

		pizzas.remove(pizza);
	}

	public int getId() {

		return id;
	}

	public void setId(int id) {

		this.id = id;
	}

	public String getAddress() {

		return address;
	}

	public void setAddress(String address) {

		this.address = address;
	}

	public boolean getDelivered() {

		return delivered;
	}

	public void setDelivered(boolean delivered) {

		this.delivered = delivered;
	}

	public Set getPizzas() {

		return pizzas;
	}

	public void setPizzas(Set pizzas) {

		this.pizzas = pizzas;
	}

	public double getPrice() {

		double price = 0;

		Iterator<Pizza> iterator = pizzas.iterator();

		while (iterator.hasNext()) {

			Pizza pizza = iterator.next();

			price += pizza.getPrice();
		}

		return price;
	}
}
