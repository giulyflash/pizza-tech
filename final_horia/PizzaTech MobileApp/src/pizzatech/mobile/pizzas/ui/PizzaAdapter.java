package pizzatech.mobile.pizzas.ui;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.List;

import pizzatech.mobile.R;
import pizzatech.mobile.io.FlushedInputStream;
import pizzatech.mobile.model.Pizza;
import pizzatech.mobile.services.HttpRetriever;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PizzaAdapter extends ArrayAdapter<Pizza> {

	private HttpRetriever httpRetriever = new HttpRetriever();

	private List<Pizza> pizzaDataItems;

	private Activity context;

	public PizzaAdapter(Activity context, int textViewResourceId,
			List<Pizza> pizzaDataItems) {
		super(context, textViewResourceId, pizzaDataItems);
		this.context = context;
		this.pizzaDataItems = pizzaDataItems;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;
		if (view == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = vi.inflate(R.layout.pizza_data_row, null);
		}

		Pizza pizza = pizzaDataItems.get(position);

		if (pizza != null) {

			// name
			TextView nameTextView = (TextView) view
					.findViewById(R.id.name_text_view);
			nameTextView.setText(pizza.getName());

			// rating
			TextView priceTextView = (TextView) view
					.findViewById(R.id.price_text_view);
			priceTextView.setText("Price: " + pizza.getPrice());

			// description
			TextView descriptionTextView = (TextView) view
					.findViewById(R.id.description_text_view);
			descriptionTextView.setText(pizza.getDescription());

			// thumb image
			ImageView imageView = (ImageView) view
					.findViewById(R.id.movie_thumb_icon);
			String url = pizza.getPictureLink();

			if (url != null) {
				Bitmap bitmap = fetchBitmapFromCache(url);
				if (bitmap == null) {
					new BitmapDownloaderTask(imageView).execute(url);
				} else {
					imageView.setImageBitmap(bitmap);
				}
			} else {
				imageView.setImageBitmap(null);
			}

		}

		return view;

	}

	private LinkedHashMap<String, Bitmap> bitmapCache = new LinkedHashMap<String, Bitmap>();

	private void addBitmapToCache(String url, Bitmap bitmap) {
		if (bitmap != null) {
			synchronized (bitmapCache) {
				bitmapCache.put(url, bitmap);
			}
		}
	}

	private Bitmap fetchBitmapFromCache(String url) {

		synchronized (bitmapCache) {
			final Bitmap bitmap = bitmapCache.get(url);
			if (bitmap != null) {
				// Bitmap found in cache
				// Move element to first position, so that it is removed last
				bitmapCache.remove(url);
				bitmapCache.put(url, bitmap);
				return bitmap;
			}
		}

		return null;

	}

	private class BitmapDownloaderTask extends AsyncTask<String, Void, Bitmap> {

		private String url;
		private final WeakReference<ImageView> imageViewReference;

		public BitmapDownloaderTask(ImageView imageView) {
			imageViewReference = new WeakReference<ImageView>(imageView);
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			url = params[0];
			InputStream is = httpRetriever.retrieveStream(url);
			if (is == null) {
				return null;
			}
			return BitmapFactory.decodeStream(new FlushedInputStream(is));
		}

		@Override
		protected void onPostExecute(Bitmap bitmap) {
			if (isCancelled()) {
				bitmap = null;
			}

			addBitmapToCache(url, bitmap);

			if (imageViewReference != null) {
				ImageView imageView = imageViewReference.get();
				if (imageView != null) {
					imageView.setImageBitmap(bitmap);
				}
			}
		}
	}

}
