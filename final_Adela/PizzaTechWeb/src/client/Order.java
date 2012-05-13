package client;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name = "ORDER_TABLE")
@NamedQueries({
	@NamedQuery(name = QueryConstants.GET_UNDELIVERED_ORDERS, query = "SELECT e from Order e WHERE e.delivered = false"),
	@NamedQuery(name = QueryConstants.GET_DELIVERED_ORDERS, query = "SELECT e from Order e WHERE e.delivered = true"),
	@NamedQuery(name = QueryConstants.GET_ALL_ORDERS, query = "SELECT e from Order e")})
public class Order{
	@Id
	@GeneratedValue
	private int id;
	@Column(name = "address", nullable = false)
	private String address;
	@Column(name = "delivered", nullable = false, columnDefinition = "TINYINT", length = 1)
	private boolean delivered;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "ORDER_DETAIL", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "id"))
	private Set<Pizza> pizzas;
	
	public void addPizza(Pizza pizza) {

		if (pizzas == null)
			pizzas = new HashSet<Pizza>();
		pizzas.add(pizza);
	}

	public void removePizza(Pizza pizza) {

		if (pizzas != null)
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

	public Set<Pizza> getPizzas() {

		return pizzas;
	}

	public void setPizzas(Set<Pizza> pizzas) {

		this.pizzas = pizzas;
	}

	public double getPrice() {

		double price = 0;

		if (pizzas != null)
		{
			Iterator<Pizza> iterator = pizzas.iterator();
	
			while (iterator.hasNext()) {
	
				Pizza pizza = iterator.next();
	
				price += pizza.getPrice();
			}
		}

		return price;
	}
}
