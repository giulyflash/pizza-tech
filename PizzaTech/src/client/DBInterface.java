package client;

import java.util.ArrayList;

public interface DBInterface {

	/**
	 * Creates an object of type client.Pizza with a specific id, and assigns to
	 * it the corresponding information from database
	 * 
	 * @param id
	 *            the id of the object in the database
	 * @return the created object
	 */
	public abstract Pizza getPizzaById(int id);

	/**
	 * Creates an object of type client.Ingredient with a specific id, and
	 * assigns to it the corresponding information from database
	 * 
	 * @param id
	 *            the id of the object in the database
	 * @return the created object
	 */
	public abstract Ingredient getIngredientById(int id);

	/**
	 * Gets the list of objects of type client.Ingredient representing all the
	 * ingredients from database
	 * 
	 * @return the list
	 */
	public abstract ArrayList<Ingredient> getIngredients();

	/**
	 * Gets the list of objects of type client.Order representing the orders
	 * from database that are marked as not being delivered
	 * 
	 * @return the list
	 */
	public abstract ArrayList<Order> getUndeliveredOrders();

	/**
	 * Gets the list of objects of type client.Order representing the orders
	 * from database that are marked as being delivered
	 * 
	 * @return the list
	 */
	public abstract ArrayList<Order> getDeliveredOrders();

	/**
	 * Gets the list of objects of type client.Order representing all the orders
	 * from database
	 * 
	 * @return the list
	 */
	public abstract ArrayList<Order> getAllOrders();

	/**
	 * Gets the list of objects of type client.Pizza representing the orders
	 * from database that are marked as being permanent
	 * 
	 * @return the list
	 */
	public abstract ArrayList<Pizza> getPermanentPizzas();

	/**
	 * Gets the list of objects of type client.Pizza representing the orders
	 * from database that are not marked as being permanent
	 * 
	 * @return the list
	 */
	public abstract ArrayList<Pizza> getNonPermanentPizzas();

	/**
	 * Gets the list of three objects of type client.Pizza representing the
	 * pizzas most recently added to database
	 * 
	 * @return the list
	 */
	public abstract ArrayList<Pizza> getLastThreePizzas();

	/**
	 * Marks a specific order from the database as delivered, regardless of
	 * whether the order is already marked delivered or not. The new state of
	 * the order is saved to database.
	 * 
	 * @param orderId
	 *            the id of the order
	 */
	public abstract void markAsDelivered(int orderId);

	/**
	 * Adds a new row to the table of ingredients with the information stored in
	 * the parameter object
	 * 
	 * @param ingredient
	 *            the object containing the information to be added to the table
	 */
	public abstract void addIngredient(Ingredient ingredient);

	/**
	 * Adds a new row to the table of pizzas with the information stored in the
	 * parameter object.
	 * 
	 * @param pizza
	 *            the object containing the information to be added to the table
	 */
	public abstract void addPizza(Pizza pizza);

	/**
	 * Adds a new row to the table of orders with the information stored in the
	 * parameter object.
	 * 
	 * @param order
	 *            the object containing the information to be added to the table
	 */
	public abstract void addOrder(Order order);

	/**
	 * Updates the information of a specific client.Ingredient object in the
	 * database. This method requests that the object exist already in the
	 * database.
	 * 
	 * @param ingredient
	 *            the object to be updated
	 */
	public abstract void update(Ingredient ingredient);

	/**
	 * Updates the information of a specific client.Pizza object in the
	 * database. This method requests that the object exist already in the
	 * database.
	 * 
	 * @param pizza
	 *            the object to be updated
	 */
	public abstract void update(Pizza pizza);

	/**
	 * Updates the information of a specific client.Order object in the
	 * database. This method requests that the object exist already in the
	 * database.
	 * 
	 * @param order
	 *            the object to be updated
	 */
	public abstract void update(Order order);

}