package client;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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


@Entity
@Table(name = "PIZZA")
@NamedQueries({
	@NamedQuery(name = QueryConstants.GET_PIZZA_BY_ID, query = "SELECT e from Pizza e WHERE e.id = :id"),
	@NamedQuery(name = QueryConstants.GET_ALL_PIZZAS, query = "SELECT e from Pizza e"),
	@NamedQuery(name = QueryConstants.GET_PERMANENT_PIZZAS, query = "SELECT e from Pizza e WHERE e.isPermanent = true"),
	@NamedQuery(name = QueryConstants.GET_NONPERMANENT_PIZZAS, query = "SELECT e from Pizza e WHERE e.isPermanent = 0"),
	@NamedQuery(name = QueryConstants.GET_LASTTHREE_PIZZAS, query = "SELECT e from Pizza e WHERE e.isPermanent = 0")}) //correct it
public class Pizza implements Cloneable {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "is_permanent", nullable = false, columnDefinition = "TINYINT", length = 1)
	private boolean isPermanent;
	@Column(name = "link", nullable = false)
	private String link;
	@Column(name = "date", nullable = false)
	private String date;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "PIZZA_DETAIL", joinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
	private Set<Ingredient> ingredients;

	public void setLink(String link) {

		this.link = link;
	}

	public String getLink() {

		return this.link;
	}

	public void setDate(String date) {

		this.date = date;
	}

	public void computeDate() {

		String dateString = "";

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.HH.mm");
		dateString = sdf.format(calendar.getTime());

		this.date = dateString;
	}

	public static Pizza getMostRecent(Pizza pizza1, Pizza pizza2) {

		String[] date1 = pizza1.getDate().split(".");
		String[] date2 = pizza2.getDate().split(".");

		int day1 = Integer.parseInt(date1[0]);
		int day2 = Integer.parseInt(date2[0]);
		int month1 = Integer.parseInt(date1[1]);
		int month2 = Integer.parseInt(date2[1]);
		int year1 = Integer.parseInt(date1[2]);
		int year2 = Integer.parseInt(date2[2]);
		int hour1 = Integer.parseInt(date1[3]);
		int hour2 = Integer.parseInt(date2[3]);
		int minute1 = Integer.parseInt(date1[4]);
		int minute2 = Integer.parseInt(date2[4]);

		if (year1 > year2) {

			return pizza1;
		} else if (year2 > year1) {

			return pizza2;
		} else if (year1 == year2) {

			if (month1 > month2) {

				return pizza1;
			} else if (month2 > month1) {

				return pizza2;
			} else if (month1 == month2) {

				if (day1 > day2) {

					return pizza1;
				} else if (day2 > day1) {

					return pizza2;
				} else if (day1 == day2) {

					if (hour1 > hour2) {

						return pizza1;
					} else if (hour2 > hour1) {

						return pizza2;
					} else if (hour1 == hour2) {

						if (minute1 > minute2) {

							return pizza1;
						} else {

							return pizza2;
						}
					}
				}
			}
		}

		return pizza1;
	}

	public String getDate() {

		return this.date;
	}

	public void addIngredient(Ingredient ingredient) {
		if (ingredients == null) {
			ingredients = new HashSet<Ingredient>();
		}

		ingredients.add(ingredient);
	}

	public void removeIngredient(Ingredient ingredient) {

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

	public boolean getIsPermanent() {

		return isPermanent;
	}

	public void setIsPermanent(boolean isPermanent) {

		this.isPermanent = isPermanent;
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
		p.isPermanent = isPermanent;
		p.name = name;

		return p;
	}
}
