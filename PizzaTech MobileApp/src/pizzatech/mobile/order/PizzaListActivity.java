package pizzatech.mobile.order;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import pizzatech.mobile.PizzaTechMobileAppActivity;
import pizzatech.mobile.R;
import pizzatech.mobile.model.Order;
import pizzatech.mobile.model.Pizza;
import pizzatech.mobile.pizzas.ui.PizzaAdapter;
import pizzatech.mobile.services.SoapCommunicator;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class PizzaListActivity extends ListActivity {
	private ArrayList<Pizza> pizzaList = new ArrayList<Pizza>();
	private PizzaAdapter pizzaAdapter;

	private SoapCommunicator mSoapCommunicator = new SoapCommunicator();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pizza_list_layout);

		Order order = (Order) getIntent().getSerializableExtra(OrderActivity.ORDER);
		pizzaList.addAll(order.getPizzas());
		
		pizzaAdapter = new PizzaAdapter(this, R.layout.pizza_data_row,
				pizzaList);
		setListAdapter(pizzaAdapter);
		pizzaAdapter.notifyDataSetChanged();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
	}
}
