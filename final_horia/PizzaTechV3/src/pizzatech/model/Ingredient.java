package pizzatech.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import pizzatech.dbaccess.QueryConstants;

@Entity
@Table(name = "INGREDIENT")
@NamedQueries({
		@NamedQuery(name = QueryConstants.GET_INGREDIENT_BY_ID, query = "SELECT e from Ingredient e WHERE e.id = :id"),
		@NamedQuery(name = QueryConstants.GET_ALL_INGREDIENTS, query = "SELECT e from Ingredient e"),
		@NamedQuery(name = QueryConstants.GET_INGREDIENTS_BY_TYPE, query = "SELECT e from Ingredient e where e.type = :type")})
public class Ingredient {
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue
	private int id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "price", nullable = false)
	private double price;
	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false)
	private IngredientType type;
	
	public IngredientType getType() {
		return type;
	}

	public void setType(IngredientType type) {
		this.type = type;
	}

//	public List<Pizza> getPizzas() {
//		return pizzas;
//	}
//
//	public void setPizzas(List<Pizza> pizzas) {
//		this.pizzas = pizzas;
//	}
//
//	@ManyToMany(mappedBy = "ingredients", fetch = FetchType.EAGER)
//	// @Transient
//	private List<Pizza> pizzas;

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
