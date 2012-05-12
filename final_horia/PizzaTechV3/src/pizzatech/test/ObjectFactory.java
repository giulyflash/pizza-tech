
package pizzatech.test;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pizzatech.test package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CreateOrderResponse_QNAME = new QName("http://pizzatech", "createOrderResponse");
    private final static QName _GetAllDoughTypesResponse_QNAME = new QName("http://pizzatech", "getAllDoughTypesResponse");
    private final static QName _GetAllPizzasResponse_QNAME = new QName("http://pizzatech", "getAllPizzasResponse");
    private final static QName _GetAllToppingsResponse_QNAME = new QName("http://pizzatech", "getAllToppingsResponse");
    private final static QName _AddPizza_QNAME = new QName("http://pizzatech", "addPizza");
    private final static QName _CreateOrder_QNAME = new QName("http://pizzatech", "createOrder");
    private final static QName _GetAllToppings_QNAME = new QName("http://pizzatech", "getAllToppings");
    private final static QName _AddPizzaResponse_QNAME = new QName("http://pizzatech", "addPizzaResponse");
    private final static QName _GetAllOrders_QNAME = new QName("http://pizzatech", "getAllOrders");
    private final static QName _GetAllOrdersResponse_QNAME = new QName("http://pizzatech", "getAllOrdersResponse");
    private final static QName _GetAllDoughTypes_QNAME = new QName("http://pizzatech", "getAllDoughTypes");
    private final static QName _GetAllMainIngedients_QNAME = new QName("http://pizzatech", "getAllMainIngedients");
    private final static QName _GetAllMainIngedientsResponse_QNAME = new QName("http://pizzatech", "getAllMainIngedientsResponse");
    private final static QName _GetAllPizzas_QNAME = new QName("http://pizzatech", "getAllPizzas");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pizzatech.test
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddPizzaResponse }
     * 
     */
    public AddPizzaResponse createAddPizzaResponse() {
        return new AddPizzaResponse();
    }

    /**
     * Create an instance of {@link GetAllOrders }
     * 
     */
    public GetAllOrders createGetAllOrders() {
        return new GetAllOrders();
    }

    /**
     * Create an instance of {@link GetAllOrdersResponse }
     * 
     */
    public GetAllOrdersResponse createGetAllOrdersResponse() {
        return new GetAllOrdersResponse();
    }

    /**
     * Create an instance of {@link GetAllDoughTypes }
     * 
     */
    public GetAllDoughTypes createGetAllDoughTypes() {
        return new GetAllDoughTypes();
    }

    /**
     * Create an instance of {@link GetAllMainIngedients }
     * 
     */
    public GetAllMainIngedients createGetAllMainIngedients() {
        return new GetAllMainIngedients();
    }

    /**
     * Create an instance of {@link GetAllMainIngedientsResponse }
     * 
     */
    public GetAllMainIngedientsResponse createGetAllMainIngedientsResponse() {
        return new GetAllMainIngedientsResponse();
    }

    /**
     * Create an instance of {@link GetAllPizzas }
     * 
     */
    public GetAllPizzas createGetAllPizzas() {
        return new GetAllPizzas();
    }

    /**
     * Create an instance of {@link AddPizza }
     * 
     */
    public AddPizza createAddPizza() {
        return new AddPizza();
    }

    /**
     * Create an instance of {@link CreateOrder }
     * 
     */
    public CreateOrder createCreateOrder() {
        return new CreateOrder();
    }

    /**
     * Create an instance of {@link GetAllToppings }
     * 
     */
    public GetAllToppings createGetAllToppings() {
        return new GetAllToppings();
    }

    /**
     * Create an instance of {@link CreateOrderResponse }
     * 
     */
    public CreateOrderResponse createCreateOrderResponse() {
        return new CreateOrderResponse();
    }

    /**
     * Create an instance of {@link GetAllDoughTypesResponse }
     * 
     */
    public GetAllDoughTypesResponse createGetAllDoughTypesResponse() {
        return new GetAllDoughTypesResponse();
    }

    /**
     * Create an instance of {@link GetAllPizzasResponse }
     * 
     */
    public GetAllPizzasResponse createGetAllPizzasResponse() {
        return new GetAllPizzasResponse();
    }

    /**
     * Create an instance of {@link GetAllToppingsResponse }
     * 
     */
    public GetAllToppingsResponse createGetAllToppingsResponse() {
        return new GetAllToppingsResponse();
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link Pizza }
     * 
     */
    public Pizza createPizza() {
        return new Pizza();
    }

    /**
     * Create an instance of {@link Ingredient }
     * 
     */
    public Ingredient createIngredient() {
        return new Ingredient();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pizzatech", name = "createOrderResponse")
    public JAXBElement<CreateOrderResponse> createCreateOrderResponse(CreateOrderResponse value) {
        return new JAXBElement<CreateOrderResponse>(_CreateOrderResponse_QNAME, CreateOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllDoughTypesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pizzatech", name = "getAllDoughTypesResponse")
    public JAXBElement<GetAllDoughTypesResponse> createGetAllDoughTypesResponse(GetAllDoughTypesResponse value) {
        return new JAXBElement<GetAllDoughTypesResponse>(_GetAllDoughTypesResponse_QNAME, GetAllDoughTypesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllPizzasResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pizzatech", name = "getAllPizzasResponse")
    public JAXBElement<GetAllPizzasResponse> createGetAllPizzasResponse(GetAllPizzasResponse value) {
        return new JAXBElement<GetAllPizzasResponse>(_GetAllPizzasResponse_QNAME, GetAllPizzasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllToppingsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pizzatech", name = "getAllToppingsResponse")
    public JAXBElement<GetAllToppingsResponse> createGetAllToppingsResponse(GetAllToppingsResponse value) {
        return new JAXBElement<GetAllToppingsResponse>(_GetAllToppingsResponse_QNAME, GetAllToppingsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPizza }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pizzatech", name = "addPizza")
    public JAXBElement<AddPizza> createAddPizza(AddPizza value) {
        return new JAXBElement<AddPizza>(_AddPizza_QNAME, AddPizza.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pizzatech", name = "createOrder")
    public JAXBElement<CreateOrder> createCreateOrder(CreateOrder value) {
        return new JAXBElement<CreateOrder>(_CreateOrder_QNAME, CreateOrder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllToppings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pizzatech", name = "getAllToppings")
    public JAXBElement<GetAllToppings> createGetAllToppings(GetAllToppings value) {
        return new JAXBElement<GetAllToppings>(_GetAllToppings_QNAME, GetAllToppings.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPizzaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pizzatech", name = "addPizzaResponse")
    public JAXBElement<AddPizzaResponse> createAddPizzaResponse(AddPizzaResponse value) {
        return new JAXBElement<AddPizzaResponse>(_AddPizzaResponse_QNAME, AddPizzaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllOrders }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pizzatech", name = "getAllOrders")
    public JAXBElement<GetAllOrders> createGetAllOrders(GetAllOrders value) {
        return new JAXBElement<GetAllOrders>(_GetAllOrders_QNAME, GetAllOrders.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllOrdersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pizzatech", name = "getAllOrdersResponse")
    public JAXBElement<GetAllOrdersResponse> createGetAllOrdersResponse(GetAllOrdersResponse value) {
        return new JAXBElement<GetAllOrdersResponse>(_GetAllOrdersResponse_QNAME, GetAllOrdersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllDoughTypes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pizzatech", name = "getAllDoughTypes")
    public JAXBElement<GetAllDoughTypes> createGetAllDoughTypes(GetAllDoughTypes value) {
        return new JAXBElement<GetAllDoughTypes>(_GetAllDoughTypes_QNAME, GetAllDoughTypes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllMainIngedients }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pizzatech", name = "getAllMainIngedients")
    public JAXBElement<GetAllMainIngedients> createGetAllMainIngedients(GetAllMainIngedients value) {
        return new JAXBElement<GetAllMainIngedients>(_GetAllMainIngedients_QNAME, GetAllMainIngedients.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllMainIngedientsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pizzatech", name = "getAllMainIngedientsResponse")
    public JAXBElement<GetAllMainIngedientsResponse> createGetAllMainIngedientsResponse(GetAllMainIngedientsResponse value) {
        return new JAXBElement<GetAllMainIngedientsResponse>(_GetAllMainIngedientsResponse_QNAME, GetAllMainIngedientsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllPizzas }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pizzatech", name = "getAllPizzas")
    public JAXBElement<GetAllPizzas> createGetAllPizzas(GetAllPizzas value) {
        return new JAXBElement<GetAllPizzas>(_GetAllPizzas_QNAME, GetAllPizzas.class, null, value);
    }

}
