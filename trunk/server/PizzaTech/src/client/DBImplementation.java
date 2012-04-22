package client;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.ServletContext;

public class DBImplementation implements DBInterface {

	private EntityManager em = null;

	private static DBInterface instance;

	private DBImplementation(ServletContext context) {
		EntityManagerFactory emf = (EntityManagerFactory) context
				.getAttribute("emf");
		this.em = emf.createEntityManager();
	}

	public static DBInterface getInstance(ServletContext context) {
		if (instance == null)
			instance = new DBImplementation(context);
		return instance;
	}

	public Pizza getPizzaById(int id) {
		Query q = em.createNamedQuery(QueryConstants.GET_PIZZA_BY_ID);
		q.setParameter("id", id);
		Pizza pizza = (Pizza) q.getSingleResult();
		return pizza;
	}
	
	public List<Pizza> getAllPizzas() {
		Query q = em.createNamedQuery(QueryConstants.GET_ALL_PIZZAS);
		List<Pizza> regs = (List<Pizza>) q.getResultList();
		return regs;
	}

	@Override
	public void addPizza(Pizza pizza) {
		em.getTransaction().begin();
		try {
			pizza.computeDate();
			em.persist(pizza);
		} finally {
			em.getTransaction().commit();
		}
	}
	
	public Ingredient getIngredientById(int id){
		Query q = em.createNamedQuery(QueryConstants.GET_INGREDIENT_BY_ID);
		q.setParameter("id", id);
		Ingredient ingr = (Ingredient) q.getSingleResult();
		return ingr;
	}
	
	public List<Ingredient> getIngredients() {
		Query q = em.createNamedQuery(QueryConstants.GET_ALL_INGREDIENTS);
		List<Ingredient> regs = (List<Ingredient>) q.getResultList();
		return regs;
	}
	
	public List<Order> getUndeliveredOrders() {
		Query q = em.createNamedQuery(QueryConstants.GET_UNDELIVERED_ORDERS);
		List<Order> regs = (List<Order>) q.getResultList();
		return regs;
	}
	
	public List<Order> getDeliveredOrders(){
		Query q = em.createNamedQuery(QueryConstants.GET_DELIVERED_ORDERS);
		List<Order> regs = (List<Order>) q.getResultList();
		return regs;
	}
	public List<Order> getAllOrders(){
		Query q = em.createNamedQuery(QueryConstants.GET_ALL_ORDERS);
		List<Order> regs = (List<Order>) q.getResultList();
		return regs;
	}
	
	public List<Pizza> getPermanentPizzas(){
		Query q = em.createNamedQuery(QueryConstants.GET_PERMANENT_PIZZAS);
		List<Pizza> regs = (List<Pizza>) q.getResultList();
		return regs;
	}
	
	public List<Pizza> getNonPermanentPizzas(){
		Query q = em.createNamedQuery(QueryConstants.GET_NONPERMANENT_PIZZAS);
		List<Pizza> regs = (List<Pizza>) q.getResultList();
		return regs;
	}
	
	public List<Pizza> getLastThreePizzas(){
		Query q = em.createNamedQuery(QueryConstants.GET_LASTTHREE_PIZZAS);
		List<Pizza> regs = (List<Pizza>) q.getResultList();
		return regs;
	}
	
	public void markAsDelivered(int orderId){
	//To be implemented	
	}
	
	public void addIngredient(Ingredient ingredient){
		em.getTransaction().begin();
		try {
			//ingredient.computeDate();
			em.persist(ingredient);
		} finally {
			em.getTransaction().commit();
		}
	}
	
	public void addOrder(Order order){
		em.getTransaction().begin();
		try {
			//ingredient.computeDate();
			em.persist(order);
		} finally {
			em.getTransaction().commit();
		}
	}
	protected void finalize() throws Throwable {
		em.clear();
	}
}
