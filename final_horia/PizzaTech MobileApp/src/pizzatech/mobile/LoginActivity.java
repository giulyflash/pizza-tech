package pizzatech.mobile;

import java.io.IOException;

import pizzatech.mobile.creation.NewPizzaActivity;
import pizzatech.mobile.services.SoapCommunicator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

public class LoginActivity extends Activity {

	private SoapCommunicator mSoapCommunicator = new SoapCommunicator();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		final EditText usernameEditText = (EditText) findViewById(R.id.username);
		final EditText passwordEditText = (EditText) findViewById(R.id.password);

		final Button ok = (Button) findViewById(R.id.login);
		ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final String username = usernameEditText.getText().toString();
				final String password = passwordEditText.getText().toString();

				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							int userId = mSoapCommunicator.login(username,
									password);

							if (userId == -1) {
								LoginActivity.this
										.runOnUiThread(new Runnable() {

											@Override
											public void run() {
												Toast.makeText(
														getApplicationContext(),
														"Login unsuccessfull",
														Toast.LENGTH_SHORT)
														.show();
											}

										});
							} else {
								SharedPreferences preferences = getSharedPreferences(
										PizzaTechMobileAppActivity.TAG,
										MODE_PRIVATE);
								preferences
										.edit()
										.putInt(PizzaTechMobileAppActivity.USER_ID,
												userId).commit();

								Intent intent = new Intent(LoginActivity.this,
										PizzaTechMobileAppActivity.class);
								startActivity(intent);
							}

						} catch (IllegalArgumentException e) {
							Log.e(PizzaTechMobileAppActivity.TAG,
									e.getMessage());
						} catch (IllegalStateException e) {
							Log.e(PizzaTechMobileAppActivity.TAG,
									e.getMessage());
						} catch (IOException e) {
							Log.e(PizzaTechMobileAppActivity.TAG,
									e.getMessage());
						}
					}

				}).start();
			}
		});
	}
}