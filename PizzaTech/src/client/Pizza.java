package client;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.hibernate.classic.Session;

import sun.util.calendar.LocalGregorianCalendar.Date;

public class Pizza implements Cloneable {

	private int id;
	private String name;
	private String description;
	private boolean isPermanent;
	private Set ingredients = new HashSet();

	private String link;
	private String date;

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

		ingredients.add(ingredient);
	}

	public void removeIngredient(Ingredient ingredient) {

		ingredients.remove(ingredient);
	}

	public Set getIngredients() {

		return ingredients;
	}

	public void setIngredients(Set ingredients) {

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
