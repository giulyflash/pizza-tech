package pizzatech.mobile.order.ui;

import java.util.List;

import pizzatech.mobile.R;
import pizzatech.mobile.model.Order;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class OrderAdapter extends ArrayAdapter<Order> {

	private List<Order> orderDataItems;

	private Activity context;

	public OrderAdapter(Activity context, int textViewResourceId,
			List<Order> orderDataItems) {
		super(context, textViewResourceId, orderDataItems);
		this.context = context;
		this.orderDataItems = orderDataItems;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;
		if (view == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = vi.inflate(R.layout.order_data_row, null);
		}

		Order order = orderDataItems.get(position);

		if (order != null) {

			// name
			TextView dateTextView = (TextView) view
					.findViewById(R.id.date_text_view);
			dateTextView.setText(order.getDate().toString());
			if (order.getDelivered()) {
				dateTextView.setTextColor(Color.GREEN);
			} else {
				dateTextView.setTextColor(Color.RED);
			}

			// rating
			TextView ratingTextView = (TextView) view
					.findViewById(R.id.order_price_text_view);
			ratingTextView.setText("Price: " + order.getPrice());

			// description
			TextView descriptionTextView = (TextView) view
					.findViewById(R.id.address_text_view);
			descriptionTextView.setText(order.getAddress());

		}

		return view;

	}

}
