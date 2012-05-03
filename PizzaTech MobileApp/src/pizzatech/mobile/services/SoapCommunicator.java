package pizzatech.mobile.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import pizzatech.mobile.model.Ingredient;
import pizzatech.mobile.model.Order;
import pizzatech.mobile.model.Pizza;
import android.util.Log;
import android.util.Xml;

public class SoapCommunicator {
	public static final String NAMESPACE = "http://pizzatech";
	public static String URL = "http://192.168.60.120:8080/PizzaTechV3/DBImplementation?wsdl";// "http://192.168.60.120:8080/PizzaTechV1/PizzaTechService?wsdl";
	public static final String METHOD_NAME = "getAllPizzas";
	public static final String SOAP_ACTION = "http://pizzatech/getAllPizzas";

	@SuppressWarnings("unchecked")
	public List<Pizza> getAllPizzas() throws IOException,
			XmlPullParserException {
		SoapObject request = new SoapObject(NAMESPACE, "getAllPizzas");
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		androidHttpTransport.call(NAMESPACE + "/getAllPizzas", envelope);

		Vector<SoapObject> resultsRequestSOAP = (Vector<SoapObject>) envelope
				.getResponse();

		List<Pizza> pizzas = new ArrayList<Pizza>();
		for (SoapObject pizzaObj : resultsRequestSOAP) {
			Pizza pizza = Pizza.createPizzaFromSOAP(pizzaObj);
			pizzas.add(pizza);
		}
		return pizzas;
	}

	public void createOrder(Order order) throws IOException,
			XmlPullParserException {
		SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		OutputStream o = new ByteArrayOutputStream();
		XmlSerializer serializer = Xml.newSerializer();
		serializer.setOutput(o, "UTF-8");
		serializer.startDocument(null, true);
		serializer.startTag(null, "soapenv:Envelope");
		serializer.attribute(null, "xmlns:q0", "http://pizzatech");
		serializer.attribute(null, "xmlns:soapenv",
				"http://schemas.xmlsoap.org/soap/envelope/");
		serializer.attribute(null, "xmlns:xsd",
				"http://www.w3.org/2001/XMLSchema");
		serializer.attribute(null, "xmlns:xsi",
				"http://www.w3.org/2001/XMLSchema-instance");

		serializer.startTag(null, "soapenv:Header");
		serializer.endTag(null, "soapenv:Header");

		serializer.startTag(null, "soapenv:Body");

		serializer.startTag(null, "q0:createOrder");

		serializer.startTag(null, "arg0");
		serializer.startTag(null, "address");
		serializer.text(order.getAddress());
		serializer.endTag(null, "address");

		serializer.startTag(null, "date");
		String date = mDateFormat.format(order.getDate());
		date = date.replace(date.charAt(10), 'T');
		serializer.text(date);
		serializer.endTag(null, "date");

		serializer.startTag(null, "delivered");
		serializer.text(order.getDelivered() + "");
		serializer.endTag(null, "delivered");

		serializer.startTag(null, "id");
		serializer.endTag(null, "id");

		serializer.endTag(null, "arg0");

		for (Pizza i : order.getPizzas()) {
			serializer.startTag(null, "arg1");
			serializer.text(i.getId() + "");
			serializer.endTag(null, "arg1");
		}

		serializer.endTag(null, "q0:createOrder");

		serializer.endTag(null, "soapenv:Body");

		serializer.endTag(null, "soapenv:Envelope");
		serializer.endDocument();
		serializer.flush();

		final DefaultHttpClient httpClient = new DefaultHttpClient();
		// request parameters
		HttpParams params = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, 10000);
		HttpConnectionParams.setSoTimeout(params, 15000);
		// set parameter
		HttpProtocolParams.setUseExpectContinue(httpClient.getParams(), true);

		// POST the envelope
		HttpPost httppost = new HttpPost(URL);
		// add headers
		httppost.setHeader("soapaction", NAMESPACE + "/createOrder");
		httppost.setHeader("Content-Type", "text/xml; charset=utf-8");

		String s = o.toString();
		String responseString = "";
		try {

			// the entity holds the request
			HttpEntity entity = new StringEntity(s);
			httppost.setEntity(entity);

			// Response handler
			ResponseHandler<String> rh = new ResponseHandler<String>() {
				// invoked when client receives response
				public String handleResponse(HttpResponse response)
						throws ClientProtocolException, IOException {

					// get response entity
					HttpEntity entity = response.getEntity();

					// read the response as byte array
					StringBuffer out = new StringBuffer();
					byte[] b = EntityUtils.toByteArray(entity);

					// write the response byte array to a string buffer
					out.append(new String(b, 0, b.length));
					return out.toString();
				}
			};

			responseString = httpClient.execute(httppost, rh);

		} catch (Exception e) {
			Log.v("exception", e.toString());
		}

		// close the connection
		httpClient.getConnectionManager().shutdown();
	}

	public List<Order> getAllOrders() throws IOException, XmlPullParserException {
		SoapObject request = new SoapObject(NAMESPACE, "getAllOrders");
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		androidHttpTransport.call(NAMESPACE + "/getAllOrders", envelope);

		Vector<SoapObject> resultsRequestSOAP = (Vector<SoapObject>) envelope
				.getResponse();

		List<Order> pizzas = new ArrayList<Order>();
		for (SoapObject pizzaObj : resultsRequestSOAP) {
			Order pizza = Order.createOrderFromSOAP(pizzaObj);
			pizzas.add(pizza);
		}
		return pizzas;
	}

	public List<Ingredient> getAllDoughTypes() throws IOException,
			XmlPullParserException {
		SoapObject request = new SoapObject(NAMESPACE, "getAllDoughTypes");
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		androidHttpTransport.call(NAMESPACE + "/getAllDoughTypes", envelope);

		Vector<SoapObject> resultsRequestSOAP = (Vector<SoapObject>) envelope
				.getResponse();

		List<Ingredient> pizzas = new ArrayList<Ingredient>();
		for (SoapObject pizzaObj : resultsRequestSOAP) {
			Ingredient pizza = Ingredient.createIngredientFromSOAP(pizzaObj);
			pizzas.add(pizza);
		}
		return pizzas;
	}

	public List<Ingredient> getMainIngredients() throws IOException,
			XmlPullParserException {
		SoapObject request = new SoapObject(NAMESPACE, "getAllMainIngedients");
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		androidHttpTransport
				.call(NAMESPACE + "/getAllMainIngedients", envelope);

		Vector<SoapObject> resultsRequestSOAP = (Vector<SoapObject>) envelope
				.getResponse();

		List<Ingredient> pizzas = new ArrayList<Ingredient>();
		for (SoapObject pizzaObj : resultsRequestSOAP) {
			Ingredient pizza = Ingredient.createIngredientFromSOAP(pizzaObj);
			pizzas.add(pizza);
		}
		return pizzas;
	}

	public List<Ingredient> getAllToppings() throws IOException,
			XmlPullParserException {
		SoapObject request = new SoapObject(NAMESPACE, "getAllToppings");
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		androidHttpTransport.call(NAMESPACE + "/getAllToppings", envelope);

		Vector<SoapObject> resultsRequestSOAP = (Vector<SoapObject>) envelope
				.getResponse();

		List<Ingredient> pizzas = new ArrayList<Ingredient>();
		for (SoapObject pizzaObj : resultsRequestSOAP) {
			Ingredient pizza = Ingredient.createIngredientFromSOAP(pizzaObj);
			pizzas.add(pizza);
		}
		return pizzas;
	}

	public void createPizza(Pizza pizza) throws IOException,
			XmlPullParserException {
		OutputStream o = new ByteArrayOutputStream();

		XmlSerializer serializer = Xml.newSerializer();
		serializer.setOutput(o, "UTF-8");
		serializer.startDocument(null, true);
		serializer.startTag(null, "soapenv:Envelope");
		serializer.attribute(null, "xmlns:q0", "http://pizzatech");
		serializer.attribute(null, "xmlns:soapenv",
				"http://schemas.xmlsoap.org/soap/envelope/");
		serializer.attribute(null, "xmlns:xsd",
				"http://www.w3.org/2001/XMLSchema");
		serializer.attribute(null, "xmlns:xsi",
				"http://www.w3.org/2001/XMLSchema-instance");

		serializer.startTag(null, "soapenv:Header");
		serializer.endTag(null, "soapenv:Header");

		serializer.startTag(null, "soapenv:Body");

		serializer.startTag(null, "q0:addPizza");

		serializer.startTag(null, "arg0");
		serializer.startTag(null, "description");
		serializer.text(pizza.getDescription());
		serializer.endTag(null, "description");

		serializer.startTag(null, "id");
		serializer.endTag(null, "id");

		serializer.startTag(null, "link");
		if (pizza.getPictureLink() != null)
			serializer.text(pizza.getPictureLink());
		else
			serializer.text("");
		serializer.endTag(null, "link");

		serializer.startTag(null, "name");
		serializer.text(pizza.getName());
		serializer.endTag(null, "name");
		serializer.endTag(null, "arg0");

		for (Ingredient i : pizza.getIngredients()) {
			serializer.startTag(null, "arg1");
			serializer.text(i.getId() + "");
			serializer.endTag(null, "arg1");
		}

		serializer.endTag(null, "q0:addPizza");

		serializer.endTag(null, "soapenv:Body");

		serializer.endTag(null, "soapenv:Envelope");
		serializer.endDocument();
		serializer.flush();

		final DefaultHttpClient httpClient = new DefaultHttpClient();
		// request parameters
		HttpParams params = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, 10000);
		HttpConnectionParams.setSoTimeout(params, 15000);
		// set parameter
		HttpProtocolParams.setUseExpectContinue(httpClient.getParams(), true);

		// POST the envelope
		HttpPost httppost = new HttpPost(URL);
		// add headers
		httppost.setHeader("soapaction", NAMESPACE + "/addPizza");
		httppost.setHeader("Content-Type", "text/xml; charset=utf-8");

		String s = o.toString();
		String responseString = "";
		try {

			// the entity holds the request
			HttpEntity entity = new StringEntity(s);
			httppost.setEntity(entity);

			// Response handler
			ResponseHandler<String> rh = new ResponseHandler<String>() {
				// invoked when client receives response
				public String handleResponse(HttpResponse response)
						throws ClientProtocolException, IOException {

					// get response entity
					HttpEntity entity = response.getEntity();

					// read the response as byte array
					StringBuffer out = new StringBuffer();
					byte[] b = EntityUtils.toByteArray(entity);

					// write the response byte array to a string buffer
					out.append(new String(b, 0, b.length));
					return out.toString();
				}
			};

			responseString = httpClient.execute(httppost, rh);

		} catch (Exception e) {
			Log.v("exception", e.toString());
		}

		// close the connection
		httpClient.getConnectionManager().shutdown();

		// SoapObject request = new SoapObject(NAMESPACE, "addPizza");
		// SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
		// SoapEnvelope.VER11);
		//
		// SoapObject pizzaObj = new SoapObject(NAMESPACE, "arg0");
		// pizzaObj.addProperty("name", pizza.getName());
		// pizzaObj.addProperty("description", pizza.getDescription());
		// pizzaObj.addProperty("pictureLink", "");
		//
		// SoapObject ingredients = new SoapObject(NAMESPACE, "arg1");
		//
		// for (Ingredient ingredient : pizza.getIngredients()) {
		// SoapObject ingredientObj = new SoapObject(NAMESPACE, "ids");
		// ingredientObj.addProperty("id", ingredient.getId());
		// ingredients.addSoapObject(ingredientObj);
		// }
		//
		// request.addSoapObject(pizzaObj);
		// request.addSoapObject(ingredients);
		//
		// envelope.setOutputSoapObject(request);
		// HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		//
		// androidHttpTransport.call(NAMESPACE + "/addPizza", envelope);
	}
}