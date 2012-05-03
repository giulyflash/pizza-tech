package pizzatech.dbaccess;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import pizzatech.model.Ingredient;
import pizzatech.model.IngredientType;
import pizzatech.model.Order;
import pizzatech.model.Pizza;

@WebService(portName = "PizzaTechPort", serviceName = "PizzaTechService", endpointInterface = "pizzatech.dbaccess.DBInterface", targetNamespace = "http://pizzatech")
// @Stateless()
public class DBImplementation implements DBInterface {

	@PersistenceContext(unitName = "hello-world")
	private EntityManager em = null;

	@Resource
	private UserTransaction _utx = null;

	public DBImplementation() {

	}

	@Override
	public List<Pizza> getAllPizzas() {
		Query q = em.createNamedQuery(QueryConstants.GET_ALL_PIZZAS);
		List<Pizza> regs = (List<Pizza>) q.getResultList();
		return regs;
	}

	@Override
	public void addPizza(Pizza pizza, List<Integer> ingredientIDs) {
		try {
			_utx.begin();
			try {
				em.persist(pizza);
				for (Integer ingredientID : ingredientIDs) {
					Ingredient ingredient = getIngredient(ingredientID);
					pizza.addIngredient(ingredient);
				}
			} finally {
				_utx.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Ingredient getIngredient(int ingredientID) {
		Query q = em.createNamedQuery(QueryConstants.GET_INGREDIENT_BY_ID);
		q.setParameter("id", ingredientID);
		Ingredient regs =  (Ingredient) q.getSingleResult();
		return regs;
	}

	protected void finalize() throws Throwable {
		em.close();
	}

	@Override
	public void createOrder(Order order, List<Integer> pizzaIDs) {
		try {
			_utx.begin();
			try {
				em.persist(order);
				for (Integer ingredientID : pizzaIDs) {
					Pizza pizza = getPizza(ingredientID);
					order.addPizza(pizza);
				}
			} finally {
				_utx.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Pizza getPizza(Integer ingredientID) {
		Query q = em.createNamedQuery(QueryConstants.GET_PIZZA_BY_ID);
		q.setParameter("id", ingredientID);
		Pizza regs =  (Pizza) q.getSingleResult();
		return regs;
	}

	@Override
	public List<Order> getAllOrders() {
		Query q = em.createNamedQuery(QueryConstants.GET_ALL_ORDERS);
		List<Order> regs = (List<Order>) q.getResultList();
		return regs;
	}

	@Override
	public List<Ingredient> getAllDoughTypes() {
		return getIngredients(IngredientType.DOUGH);
	}

	@Override
	public List<Ingredient> getAllMainIngedients() {
		return getIngredients(IngredientType.MAIN);
	}

	@Override
	public List<Ingredient> getAllToppings() {
		return getIngredients(IngredientType.TOPPING);
	}

	private List<Ingredient> getIngredients(IngredientType type) {
		Query q = em.createNamedQuery(QueryConstants.GET_INGREDIENTS_BY_TYPE);
		q.setParameter("type", type);
		List<Ingredient> regs = (List<Ingredient>) q.getResultList();
		return regs;
	}
}
