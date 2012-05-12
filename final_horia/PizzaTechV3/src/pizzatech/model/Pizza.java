package pizzatech.model;

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
@Table(name = "PIZZA")
@NamedQueries({
		@NamedQuery(name = QueryConstants.GET_PIZZA_BY_ID, query = "SELECT e from Pizza e WHERE e.id = :id"),
		@NamedQuery(name = QueryConstants.GET_ALL_PIZZAS, query = "SELECT e from Pizza e") })
// correct it
public class Pizza implements Cloneable {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue
	private int id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "pictureLink", nullable = false)
	private String pictureLink;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "PIZZA_DETAIL", joinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
	// @Transient
	private Set<Ingredient> ingredients;

	// @ManyToMany(mappedBy = "pizzas", fetch = FetchType.EAGER)
	// // @Transient
	// Set<Order> orders;

	// public Set<Order> getOrders() {
	// return orders;
	// }
	//
	// public void setOrders(Set<Order> orders) {
	// this.orders = orders;
	// }

	public void setLink(String link) {

		this.pictureLink = link;
	}

	public String getLink() {

		return this.pictureLink;
	}

	public void addIngredient(Ingredient ingredient) {
		if (ingredients == null) {
			ingredients = new HashSet<Ingredient>();
		}

		ingredients.add(ingredient);
	}

	public void removeIngredient(Ingredient ingredient) {
		if (ingredients == null) {
			return;
		}
		ingredients.remove(ingredient);
	}

	public Set<Ingredient> getIngredients() {

		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {

		this.ingredients = ingredients;
	}

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

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public double getPrice() {

		double price = 0;

		Iterator<Ingredient> i = ingredients.iterator();

		while (i.hasNext()) {

			Ingredient ingredient = (Ingredient) i.next();
			price = price + ingredient.getPrice();
		}

		System.out.println(price);

		return price;
	}

	public Object clone() throws CloneNotSupportedException {

		Pizza p = new Pizza();

		p.description = description;
		p.id = id;
		p.ingredients = ingredients;
		p.name = name;

		return p;
	}
}
