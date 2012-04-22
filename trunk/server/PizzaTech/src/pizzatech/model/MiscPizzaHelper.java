package pizzatech.model;

import java.util.ArrayList;


public class MiscPizzaHelper {
	public static int pizzaCount(Pizza p, ArrayList<Pizza> pl) {
		int nr = 0;
		if (pl == null)
			return nr;

		for (int i = 0; i < pl.size(); i++)
			if (pl.get(i).getName().equals(p.getName()))
				nr++;
		return nr;
	}

	public static boolean pizzaSeeker(String name, ArrayList<Pizza> pl) {

		if (pl == null)
			return false;

		for (int i = 0; i < pl.size(); i++)
			if (pl.get(i).getName().equals(name))
				return true;
		return false;
	}

}
