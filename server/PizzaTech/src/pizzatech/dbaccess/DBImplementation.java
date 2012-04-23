package pizzatech.dbaccess;

import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pizzatech.model.Pizza;

@WebService(portName = "PizzaTechPort", serviceName = "PizzaTechService", endpointInterface = "pizzatech.dbaccess.DBInterface", targetNamespace = "http://pizzatech")
public class DBImplementation implements DBInterface {

	@PersistenceContext(unitName = "hello-world")
	private EntityManager em = null;

	public DBImplementation() {

	}

	@Override
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
		em.close();
	}
}
