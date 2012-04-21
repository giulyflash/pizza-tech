package client;

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

	protected void finalize() throws Throwable {
		em.clear();
	}
}
