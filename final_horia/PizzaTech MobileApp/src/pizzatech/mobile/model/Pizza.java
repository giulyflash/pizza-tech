package pizzatech.mobile.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public class Pizza implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3064031623452427785L;
	private int id;
	private String name;
	private String description;
	private String pictureLink;

	private List<Ingredient> ingredients = new ArrayList<Ingredient>();
	
	public String getPictureLink() {
		return pictureLink;
	}

	public void setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
	}

	public void addIngredient(Ingredient ingredient) {

		ingredients.add(ingredient);
	}

	public void removeIngredient(Ingredient ingredient) {

		ingredients.remove(ingredient);
	}

	public List<Ingredient> getIngredients() {

		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {

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

	public static Pizza createPizzaFromSOAP(SoapObject pizzaObj) {
		Pizza result = new Pizza();
		int properyCount = pizzaObj.getPropertyCount();
		for (int i = 0; i < properyCount; i++) {
			PropertyInfo info = new PropertyInfo();
			pizzaObj.getPropertyInfo(i, info);
			if (info.getName().equals("id")) {
				result.id = Integer.parseInt(pizzaObj.getPropertyAsString(i));
			} else if (info.getName().equals("name")) {
				result.name = (String) pizzaObj.getPropertyAsString(i);
			} else if (info.getName().equals("description")) {
				result.description = (String) pizzaObj.getPropertyAsString(i);
			} else if (info.getName().equals("ingredients")) {
				SoapObject ingredientObj = (SoapObject) pizzaObj.getProperty(i);
				Ingredient ingredient = Ingredient.createIngredientFromSOAP(ingredientObj);
				result.addIngredient(ingredient);
			}
		}
		return result;
	}
}
