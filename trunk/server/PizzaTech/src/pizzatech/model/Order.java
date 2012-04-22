package pizzatech.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import pizzatech.dbaccess.QueryConstants;

@Entity
@Table(name = "ORDER_TABLE")
@NamedQueries({
		@NamedQuery(name = QueryConstants.GET_UNDELIVERED_ORDERS, query = "SELECT e from Order e WHERE e.delivered = false"),
		@NamedQuery(name = QueryConstants.GET_DELIVERED_ORDERS, query = "SELECT e from Order e WHERE e.delivered = true"),
		@NamedQuery(name = QueryConstants.GET_ALL_ORDERS, query = "SELECT e from Order e") })
public class Order {
	@Id
	@GeneratedValue
	private int id;
	@Column(name = "address", nullable = false)
	private String address;
	@Column(name = "delivered", nullable = false, columnDefinition = "BIT", length = 1)
	private boolean delivered;

	@Transient
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
