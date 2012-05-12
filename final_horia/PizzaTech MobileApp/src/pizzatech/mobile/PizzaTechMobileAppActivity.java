package pizzatech.mobile;

import pizzatech.mobile.creation.NewPizzaActivity;
import pizzatech.mobile.order.OrderListActivity;
import pizzatech.mobile.pizzas.PizzaListSelectionActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PizzaTechMobileAppActivity extends Activity {
	public static final String TAG = "PizzaTechMobileAppActivity";
	public static final String USER_ID = "USER_ID";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Button createOrderButton = (Button) findViewById(R.id.create_order);
		createOrderButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PizzaTechMobileAppActivity.this,
						PizzaListSelectionActivity.class);
				startActivity(intent);
			}
		});

		final Button myBasketButton = (Button) findViewById(R.id.show_basket);
		myBasketButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PizzaTechMobileAppActivity.this,
						OrderListActivity.class);
				startActivity(intent);
			}
		});
		
		final Button newPizzaButton = (Button) findViewById(R.id.create_pizza);
		newPizzaButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PizzaTechMobileAppActivity.this,
						NewPizzaActivity.class);
				startActivity(intent);
			}
		});
	}
}