package pizzatech.mobile.order;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import pizzatech.mobile.PizzaTechMobileAppActivity;
import pizzatech.mobile.R;
import pizzatech.mobile.model.Order;
import pizzatech.mobile.order.ui.OrderAdapter;
import pizzatech.mobile.services.SoapCommunicator;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class OrderListActivity extends ListActivity {
	private ArrayList<Order> orderList = new ArrayList<Order>();
	private OrderAdapter orderAdapter;

	private SoapCommunicator mSoapCommunicator = new SoapCommunicator();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pizza_list_layout);

		orderAdapter = new OrderAdapter(this, R.layout.order_data_row,
				orderList);
		setListAdapter(orderAdapter);
		orderAdapter.notifyDataSetChanged();

		new OrderDownloaderTask(orderAdapter).execute();
	}

	@Override
	protected void onResume() {
		super.onResume();

		new OrderDownloaderTask(orderAdapter).execute();
	}

	private class OrderDownloaderTask extends
			AsyncTask<Void, Void, List<Order>> {

		private final WeakReference<OrderAdapter> orderAdapterReference;

		public OrderDownloaderTask(OrderAdapter orderAdapter) {
			this.orderAdapterReference = new WeakReference<OrderAdapter>(
					orderAdapter);
		}

		@Override
		protected List<Order> doInBackground(Void... params) {
			List<Order> orders = new ArrayList<Order>();
			try {
				orders = mSoapCommunicator.getAllOrders();
			} catch (Exception e) {
				Log.e(PizzaTechMobileAppActivity.TAG, e.getMessage());
				return null;
			}
			return orders;
		}

		@Override
		protected void onPostExecute(List<Order> orders) {
			if (isCancelled()) {
				orders = null;
			}

			if (orderAdapterReference != null) {
				OrderAdapter orderAdapter = orderAdapterReference.get();
				if (orderAdapter != null) {
					orderAdapter.clear();
					for (int i = 0; i < orders.size(); i++) {
						orderAdapter.add(orders.get(i));
					}
					orderAdapter.notifyDataSetChanged();
				}
			}
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		final Order order = orderAdapter.getItem((int) position);

		Intent intent = new Intent(OrderListActivity.this,
				PizzaListActivity.class);
		intent.putExtra(OrderActivity.ORDER, order);
		startActivity(intent);
	}
}
