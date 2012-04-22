package client;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "INGREDIENT")
@NamedQueries({
	@NamedQuery(name = QueryConstants.GET_INGREDIENT_BY_ID, query = "SELECT e from Ingredient e WHERE e.id = :id"),
	@NamedQuery(name = QueryConstants.GET_ALL_INGREDIENTS, query = "SELECT e from Ingredient") })
public class Ingredient {
	@Id
	@GeneratedValue
	private int id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "price", nullable = false)
	private double price;

	public int getId() {

		return id;
	}

	public void setId(int id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public double getPrice() {

		return price;
	}

	public void setPrice(double price) {

		this.price = price;
	}

	public String toString() {

		return name;
	}
}
