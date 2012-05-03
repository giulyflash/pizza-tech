package pizzatech.mobile.model;

import java.io.Serializable;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

public class Ingredient implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9160443042312074622L;
	private int id;
	private String name;
	private double price;
	private IngredientType type;

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

	public IngredientType getType() {
		return type;
	}

	public void setType(IngredientType type) {
		this.type = type;
	}

	public static Ingredient createIngredientFromSOAP(SoapObject ingredientObj) {
		Ingredient result = new Ingredient();
		int properyCount = ingredientObj.getPropertyCount();
		for (int i = 0; i < properyCount; i++) {
			PropertyInfo info = new PropertyInfo();
			ingredientObj.getPropertyInfo(i, info);
			if (info.getName().equals("id")) {
				result.id = Integer.parseInt(ingredientObj
						.getPropertyAsString(i));
			} else if (info.getName().equals("name")) {
				result.name = (String) ingredientObj.getPropertyAsString(i);
			} else if (info.getName().equals("price")) {
				result.price = Double.parseDouble(ingredientObj
						.getPropertyAsString(i));
			} else if (info.getName().equals("type")) {
				String type = (String) ingredientObj.getPropertyAsString(i);
				if (type.equals("MAIN")) {
					result.type = IngredientType.MAIN;
				} else if (type.equals("TOPPING")) {
					result.type = IngredientType.TOPPING;
				} else if (type.equals("DOUGH")) {
					result.type = IngredientType.DOUGH;
				}
			}
		}
		return result;
	}
}
