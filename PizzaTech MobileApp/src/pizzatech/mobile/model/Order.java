package pizzatech.mobile.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import pizzatech.mobile.PizzaTechMobileAppActivity;

import android.util.Log;

public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5399327604487676798L;
	private int id;
	private String address;
	private boolean delivered;
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	private Set<Pizza> pizzas = new HashSet<Pizza>();

	public void addPizza(Pizza pizza) {

		pizzas.add(pizza);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", address=" + address + ", delivered="
				+ delivered + ", date=" + date + ", pizzas=" + pizzas + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (delivered ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((pizzas == null) ? 0 : pizzas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (delivered != other.delivered)
			return false;
		if (id != other.id)
			return false;
		if (pizzas == null) {
			if (other.pizzas != null)
				return false;
		} else if (!pizzas.equals(other.pizzas))
			return false;
		return true;
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

	public static Order createOrderFromSOAP(SoapObject pizzaObj) {
		SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Order result = new Order();
		int properyCount = pizzaObj.getPropertyCount();
		for (int i = 0; i < properyCount; i++) {
			PropertyInfo info = new PropertyInfo();
			pizzaObj.getPropertyInfo(i, info);
			if (info.getName().equals("id")) {
				result.id = Integer.parseInt(pizzaObj.getPropertyAsString(i));
			} else if (info.getName().equals("address")) {
				result.address = pizzaObj.getPropertyAsString(i);
			} else if (info.getName().equals("delivered")) {
				result.delivered = Boolean.getBoolean(pizzaObj
						.getPropertyAsString(i));
			} else if (info.getName().equals("date")) {
				String date = pizzaObj.getPropertyAsString(i);
				date = date.replace(date.charAt(10), ' ');
				try {
					result.date = mDateFormat.parse(date);
				} catch (ParseException e) {
					Log.e(PizzaTechMobileAppActivity.TAG, e.getMessage());
				}
			} else if (info.getName().equals("pizzas")) {
				SoapObject ingredientObj = (SoapObject) pizzaObj.getProperty(i);
				Pizza ingredient = Pizza.createPizzaFromSOAP(ingredientObj);
				result.addPizza(ingredient);
			}
		}
		return result;
	}
}
