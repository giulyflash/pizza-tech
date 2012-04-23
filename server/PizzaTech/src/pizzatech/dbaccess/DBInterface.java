package pizzatech.dbaccess;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import pizzatech.model.Pizza;

@WebService(name = "PizzaTechService", targetNamespace = "http://pizzatech")
public interface DBInterface {

	/**
	 * Adds a new row to the table of pizzas with the information stored in the
	 * parameter object.
	 * 
	 * @param pizza
	 *            the object containing the information to be added to the table
	 */
	public void addPizza(Pizza pizza);

	/**
	 * @return all the pizzas in the system
	 */
	public List<Pizza> getAllPizzas();

}