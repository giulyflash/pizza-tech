package client;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import java.util.*;

public class DBImplementation implements DBInterface {

	private static DBInterface instance;
	private Session session = null;

	private DBImplementation() {
		session = startSession();
	}

	public static DBInterface getInstance() {

		if (instance == null) {

			instance = new DBImplementation();
		}

		return instance;
	}

	public Pizza getPizzaById(int id) {

		Pizza pizza = (Pizza) session.get(Pizza.class, new Integer(id));

		session.flush();

		return pizza;
	}

	public ArrayList<Pizza> getLastThreePizzas() {

		ArrayList<Pizza> pizzas = new ArrayList<Pizza>(3);
		ArrayList<Pizza> allPizzas = getPizzas(true);

		Pizza pizza = getMostRecent(pizzas);

		pizzas.add(pizza);
		allPizzas.remove(pizza);

		pizza = getMostRecent(pizzas);
		pizzas.add(pizza);
		allPizzas.remove(pizza);

		pizza = getMostRecent(pizzas);
		pizzas.add(pizza);
		allPizzas.remove(pizza);

		return pizzas;
	}

	private Pizza getMostRecent(ArrayList<Pizza> pizzaList) {

		if (pizzaList.size() == 0) {

			return null;
		}

		if (pizzaList.size() == 1) {

			return pizzaList.get(0);
		}

		Pizza pizza = pizzaList.get(0);

		for (int i = 1; i < pizzaList.size(); i++) {

			pizza = Pizza.getMostRecent(pizza, pizzaList.get(i));
		}

		return pizza;
	}

	public ArrayList<Ingredient> getIngredients() {

		ArrayList<Ingredient> ingredients = new ArrayList();

		Transaction t = session.beginTransaction();

		List ingredientsList = session.createQuery(
				"select i from Ingredient as i").list();

		Iterator<Ingredient> iterator = ingredientsList.iterator();

		while (iterator.hasNext()) {

			Ingredient ingredient = iterator.next();

			ingredients.add(ingredient);
		}

		t.commit();

		return ingredients;
	}

	private ArrayList<Pizza> getPizzas(boolean permanent) {

		ArrayList<Pizza> pizzas = new ArrayList();

		Transaction t = session.beginTransaction();

		List pizzasList = session.createQuery("select p from Pizza as p")
				.list();

		Iterator<Pizza> iterator = pizzasList.iterator();

		while (iterator.hasNext()) {

			Pizza pizza = iterator.next();

			if (pizza.getIsPermanent() == permanent) {

				pizzas.add(pizza);
			}
		}

		t.commit();

		session.flush();

		return pizzas;
	}

	public ArrayList<Pizza> getPermanentPizzas() {

		return getPizzas(true);
	}

	public ArrayList<Pizza> getNonPermanentPizzas() {

		return getPizzas(false);
	}

	private ArrayList<Order> getOrders(boolean delivered) {

		ArrayList<Order> orders = new ArrayList();

		Transaction t = session.beginTransaction();

		List ordersList = session.createQuery("select o from Order as o")
				.list();

		Iterator<Order> iterator = ordersList.iterator();

		while (iterator.hasNext()) {

			Order order = iterator.next();

			if (order.getDelivered() == delivered) {

				orders.add(order);
			}
		}

		t.commit();

		return orders;
	}

	public ArrayList<Order> getAllOrders() {

		Transaction t = session.beginTransaction();

		List ordersList = session.createQuery("select o from Order as o")
				.list();

		t.commit();

		ArrayList<Order> orders = new ArrayList<Order>();
		orders.addAll(ordersList);
		return orders;
	}

	public ArrayList<Order> getUndeliveredOrders() {

		return getOrders(false);
	}

	public ArrayList<Order> getDeliveredOrders() {

		return getOrders(true);
	}

	public void markAsDelivered(int orderId) {

		Transaction t = null;

		t = session.beginTransaction();

		Order order = (Order) session.get(Order.class, new Integer(orderId));

		order.setDelivered(true);
		session.update(order);
		t.commit();
		session.flush();
	}

	@Override
	public void addIngredient(Ingredient ingredient) {

		Transaction t = session.beginTransaction();
		session.save(ingredient);
		t.commit();
		session.flush();
	}

	@Override
	public void addOrder(Order order) {
		// TODO Auto-generated method stub

		Transaction t = session.beginTransaction();
		session.save(order);
		t.commit();
		session.flush();
	}

	@Override
	public void addPizza(Pizza pizza) {
		// TODO Auto-generated method stub

		Transaction t = session.beginTransaction();
		pizza.computeDate();
		session.save(pizza);
		t.commit();
	}

	private Session startSession() {

		SessionFactory sf = new Configuration().configure()
				.buildSessionFactory();
		Session session = sf.openSession();

		return session;
	}

	private void closeSession(Session session) {

		session.getTransaction().commit();
		session.flush();
		session.close();
	}

	public Ingredient getIngredientById(int id) {

		Ingredient ingredient = (Ingredient) session.get(Ingredient.class,
				new Integer(id));

		session.flush();

		return ingredient;
	}

	public void update(Ingredient ingredient) {

		Transaction t = session.beginTransaction();
		session.update(ingredient);
		t.commit();
		session.flush();
	}

	public void update(Pizza pizza) {

		Transaction t = session.beginTransaction();
		session.update(pizza);
		t.commit();
		session.flush();
	}

	public void update(Order order) {

		Transaction t = session.beginTransaction();
		session.update(order);
		t.commit();
		session.flush();
	}

	protected void finalize() throws Throwable {

		closeSession(session);
	}
}
