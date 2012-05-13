package client;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import javax.transaction.UserTransaction;



public class DBImplementation implements DBInterface {

	//@PersistenceContext(unitName = "hello-world")
	private EntityManager em = null;

	//@Resource
	//private UserTransaction _utx = null;
	
	private static DBInterface instance;

	public DBImplementation(){}
	
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
	/*public void addPizza(Pizza pizza) {
		em.getTransaction().begin();
		try {
			pizza.computeDate();
			em.persist(pizza);
		} finally {
			em.getTransaction().commit();
		}
	}*/
	public void addPizza(Pizza pizza, List<Integer> ingredientIDs) {
		try {
			//_utx.begin();
			em.getTransaction().begin();
			try {
				pizza.computeDate();
				pizza.setLink("link fain");
				em.persist(pizza);
				for (Integer ingredientID : ingredientIDs) {
					Ingredient ingredient = getIngredient(ingredientID);
					pizza.addIngredient(ingredient);
				}
			} finally {
				//_utx.commit();
				em.getTransaction().commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*@Override
	public void addOrder(Order order, List<Integer> pizzaIDs){
	/*	em.getTransaction().begin();
		try {
			System.out.println("order: "+ order.getAddress()+ " "+ order.getId()+" "+order.getPizzas());
			em.persist(order);
		} finally {
			em.getTransaction().commit();
		}*/	
	/*	try {
			em.getTransaction().begin();
			//_utx.begin();
			try {
				System.out.println("order: "+ order.getAddress()+ " "+ order.getId()+" "+order.getPizzas());
				order.setDelivered(false);
				em.persist(order);
				for (Integer pizzaID : pizzaIDs) {
					Pizza pizza = getPizza(pizzaID);
					order.addPizza(pizza);
				}
			} finally {
				//_utx.commit();
				em.getTransaction().commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	@Override
	public void addOrder(Order order, List<Integer> pizzaIds) {

			/*em.getTransaction().begin();
			try {
				order.setAddress("bublbfe");
				order.setDelivered(false);
				em.persist(order);
				for (Integer pizzaID : pizzaIds) {
					Pizza pizza = getPizza(pizzaID);
					order.addPizza(pizza);
				}
			} finally {
				//_utx.commit();
				em.getTransaction().commit();
			}*/
		Pizza p = getPizzaById(2);
		System.out.println("info order:");
		System.out.println(order.getPrice());
		System.out.println(order.getDelivered());
		System.out.println(order.getId());
		System.out.println(order.getAddress());
		System.out.println(order.toString());
		//Order newOrder = new Order();
		//newOrder.addPizza(p);
		//newOrder.setAddress("fsdffd");
		//newOrder.setDelivered(false);
		//order.setId(20);
		//newOrder.setId(8);
		em.getTransaction().begin();
		em.persist(order);
		em.getTransaction().commit();
	}
	private Ingredient getIngredient(int ingredientID) {
		Query q = em.createNamedQuery(QueryConstants.GET_INGREDIENT_BY_ID);
		q.setParameter("id", ingredientID);
		Ingredient regs =  (Ingredient) q.getSingleResult();
		return regs;
	}

	private Pizza getPizza(int pizzaID) {
		Query q = em.createNamedQuery(QueryConstants.GET_PIZZA_BY_ID);
		q.setParameter("id", pizzaID);
		Pizza regs =  (Pizza) q.getSingleResult();
		return regs;
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
		em.getTransaction().begin();
		//Ingredient ingr = getIngredientById(2);
		//ingr.setName("alNume");
		//em.refresh(ingr);
		em.getTransaction().commit();
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
	
	public void update(Pizza pizza){
		em.getTransaction().begin();
		//em.refresh(pizza);
		em.getTransaction().commit();
	}

	public void update(Order order){
		em.getTransaction().begin();
		//em.refresh(order);
		//em.persist(order);
		em.getTransaction().commit();
	} 
	
	protected void finalize() throws Throwable {
		em.clear();
	}
}
