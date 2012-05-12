package pizzatech.dbaccess;

import java.util.List;

import javax.jws.WebService;

import pizzatech.model.Ingredient;
import pizzatech.model.Order;
import pizzatech.model.Pizza;
import pizzatech.model.User;

@WebService(name = "PizzaTechService", targetNamespace = "http://pizzatech")
// @Remote
public interface DBInterface {

	/**
	 * Adds a new row to the table of pizzas with the information stored in the
	 * parameter object.
	 * 
	 * @param pizza
	 *            the object containing the information to be added to the table
	 */
	public void addPizza(Pizza pizza, List<Integer> ingredientIDs);

	/**
	 * @return all the pizzas in the system
	 */
	public List<Pizza> getAllPizzas();

	public void createOrder(Order order, List<Integer> pizzaIDs);

	public List<Order> getAllOrders(int userId);

	public List<Ingredient> getAllDoughTypes();

	public List<Ingredient> getAllMainIngedients();

	public List<Ingredient> getAllToppings();

	int login(String username, String password);

}