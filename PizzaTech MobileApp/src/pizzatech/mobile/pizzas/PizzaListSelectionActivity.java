package pizzatech.mobile.pizzas;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import pizzatech.mobile.PizzaTechMobileAppActivity;
import pizzatech.mobile.R;
import pizzatech.mobile.io.FlushedInputStream;
import pizzatech.mobile.model.Order;
import pizzatech.mobile.model.Pizza;
import pizzatech.mobile.order.OrderActivity;
import pizzatech.mobile.pizzas.ui.PizzaAdapter;
import pizzatech.mobile.services.HttpRetriever;
import pizzatech.mobile.services.SoapCommunicator;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

public class PizzaListSelectionActivity extends ListActivity {
	private ArrayList<Pizza> pizzaList = new ArrayList<Pizza>();
	private PizzaAdapter pizzaAdapter;

	private HttpRetriever httpRetriever = new HttpRetriever();
	private SoapCommunicator mSoapCommunicator = new SoapCommunicator();

	private ProgressDialog progressDialog;
	private ImageView imageView;
	private Order order = new Order();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pizza_list_layout);

		pizzaAdapter = new PizzaAdapter(this, R.layout.pizza_data_row,
				pizzaList);
		setListAdapter(pizzaAdapter);
		pizzaAdapter.notifyDataSetChanged();

		new PizzaDownloaderTask(pizzaAdapter).execute();
	}

	@Override
	protected void onResume() {
		super.onResume();

		new PizzaDownloaderTask(pizzaAdapter).execute();
	}

	private class PizzaDownloaderTask extends
			AsyncTask<Void, Void, List<Pizza>> {

		private final WeakReference<PizzaAdapter> pizzaAdapterReference;

		public PizzaDownloaderTask(PizzaAdapter pizzaAdapter) {
			this.pizzaAdapterReference = new WeakReference<PizzaAdapter>(
					pizzaAdapter);
		}

		@Override
		protected List<Pizza> doInBackground(Void... params) {
			List<Pizza> pizzas = new ArrayList<Pizza>();
			try {
				pizzas = mSoapCommunicator.getAllPizzas();
			} catch (Exception e) {
				Log.e(PizzaTechMobileAppActivity.TAG, e.getMessage());
				return null;
			}
			return pizzas;
		}

		@Override
		protected void onPostExecute(List<Pizza> pizzas) {
			if (isCancelled()) {
				pizzas = null;
			}

			if (pizzaAdapterReference != null) {
				PizzaAdapter pizzaAdapter = pizzaAdapterReference.get();
				if (pizzaAdapter != null) {
					pizzaAdapter.clear();
					for (int i = 0; i < pizzas.size(); i++) {
						pizzaAdapter.add(pizzas.get(i));
					}
					pizzaAdapter.notifyDataSetChanged();
				}
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (resultCode) {
		case OrderActivity.DONE:
			finish();
			break;
		case OrderActivity.ADD_PIZZA:
			this.order = (Order) data.getSerializableExtra(OrderActivity.ORDER);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		final Pizza pizza = pizzaAdapter.getItem((int) position);

		this.order.addPizza(pizza);

		Intent intent = new Intent(PizzaListSelectionActivity.this, OrderActivity.class);
		intent.putExtra(OrderActivity.ORDER, order);
		startActivityForResult(intent, OrderActivity.REQUEST_CODE);
	}

	private class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			String url = params[0];
			InputStream is = httpRetriever.retrieveStream(url);
			if (is == null) {
				return null;
			}
			return BitmapFactory.decodeStream(new FlushedInputStream(is));
		}

		@Override
		protected void onPostExecute(final Bitmap result) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if (progressDialog != null) {
						progressDialog.dismiss();
						progressDialog = null;
					}
					if (result != null) {
						imageView.setImageBitmap(result);
					}
				}
			});
		}

	}
}
