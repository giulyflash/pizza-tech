package pizzatech.mobile.order;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.xmlpull.v1.XmlPullParserException;

import pizzatech.mobile.PizzaTechMobileAppActivity;
import pizzatech.mobile.R;
import pizzatech.mobile.model.Order;
import pizzatech.mobile.model.Pizza;
import pizzatech.mobile.pizzas.ui.PizzaAdapter;
import pizzatech.mobile.services.SoapCommunicator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class OrderActivity extends Activity {
	private SoapCommunicator mSoapCommunicator = new SoapCommunicator();

	private Order order;

	public static final String ORDER = "ORDER";
	public static final int ADD_PIZZA = 2;
	public static final int DONE = 1;
	public static final int REQUEST_CODE = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order);
		
		order = (Order) getIntent().getExtras().get(OrderActivity.ORDER);

		final Button cancel = (Button) findViewById(R.id.cancel);
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setResult(OrderActivity.DONE);
				finish();
			}
		});

		final Button submit = (Button) findViewById(R.id.submit);
		submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
					int userId = getSharedPreferences(PizzaTechMobileAppActivity.TAG, MODE_PRIVATE).getInt(PizzaTechMobileAppActivity.USER_ID, -1);
				
					order.setDate(new Date());
					order.setAddress("AA");
					order.setDelivered(false);
					order.setUser(userId);
					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								mSoapCommunicator.createOrder(order);
							} catch (IOException e) {
								Log.e(PizzaTechMobileAppActivity.TAG, e.getMessage());
							} catch (XmlPullParserException e) {
								Log.e(PizzaTechMobileAppActivity.TAG, e.getMessage());
							}
						}
					}).start();
				setResult(OrderActivity.DONE);
				finish();
			}
		});

		final Button add = (Button) findViewById(R.id.add_pizza_to_order);
		add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra(ORDER, OrderActivity.this.order);
				intent.putExtra("x", 1);
				setResult(OrderActivity.ADD_PIZZA, intent);
				finish();
			}
		});

		ListView pizzas = (ListView) findViewById(R.id.order_pizzas);
		PizzaAdapter pizzaAdapter = new PizzaAdapter(this,
				R.layout.pizza_data_row, new ArrayList<Pizza>());
		pizzas.setAdapter(pizzaAdapter);
		pizzaAdapter.notifyDataSetChanged();
		pizzaAdapter.clear();
		for (Pizza pizza : order.getPizzas()) {
			pizzaAdapter.add(pizza);
		}
		pizzaAdapter.notifyDataSetChanged();
	}
}
