package pizzatech.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

import pizzatech.dbaccess.QueryConstants;

@Entity
@Table(name = "ORDER_TABLE")
@NamedQueries({
		@NamedQuery(name = QueryConstants.GET_UNDELIVERED_ORDERS, query = "SELECT e from Order e WHERE e.delivered = false"),
		@NamedQuery(name = QueryConstants.GET_DELIVERED_ORDERS, query = "SELECT e from Order e WHERE e.delivered = true"),
		@NamedQuery(name = QueryConstants.GET_USER_ORDERS, query = "SELECT e from Order e where e.userId = :userId"),
		@NamedQuery(name = QueryConstants.GET_ALL_ORDERS, query = "SELECT e from Order e") })
public class Order {
	@Id
	@GeneratedValue
	private int id;
	@Column(name = "address", nullable = false)
	private String address;
	@Column(name = "delivered", nullable = false, columnDefinition = "BIT", length = 1)
	private boolean delivered;
	@Column(name = "date", nullable = false)
	private Date date;
	@Column(name = "userId", nullable = false)
	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "ORDER_DETAIL", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "id"))
	// @Transient
	private Set<Pizza> pizzas;

	public void addPizza(Pizza pizza) {
		if (pizzas == null)
			pizzas = new HashSet<Pizza>();
		pizzas.add(pizza);
	}

	public void removePizza(Pizza pizza) {
		if (pizzas == null)
			return;
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

		Iterator<Pizza> iterator = pizzas.iterator();

		while (iterator.hasNext()) {

			Pizza pizza = iterator.next();

			price += pizza.getPrice();
		}

		return price;
	}
}
