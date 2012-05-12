package pizzatech.mobile.creation;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import pizzatech.mobile.PizzaTechMobileAppActivity;
import pizzatech.mobile.R;
import pizzatech.mobile.model.Ingredient;
import pizzatech.mobile.model.Pizza;
import pizzatech.mobile.services.SoapCommunicator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

public class NewPizzaActivity extends PreferenceActivity {
	public static final String APP_ID = "377453538959110";
	public static final String APP_SECRET = "fe4d43f79bfa255ca01821927d78dd50";

	private SoapCommunicator mSoapCommunicator = new SoapCommunicator();
	Facebook facebook = new Facebook(APP_ID);

	private List<Ingredient> doughs = new ArrayList<Ingredient>();
	private List<Ingredient> main = new ArrayList<Ingredient>();
	private List<Ingredient> toppings = new ArrayList<Ingredient>();

	protected EditTextPreference mPizzaName;
	protected EditTextPreference mDescription;
	protected ListPreference mDoughType;
	protected PreferenceScreen mMainIngredientsScreen;
	protected PreferenceScreen mToppingsScreen;

	protected String KEY_PIZZA_NAME;

	protected String KEY_DESCRIPTION;

	protected String KEY_DOUGH_TYPE;

	protected String KEY_MAIN_INGREDIENT_SCREEN;

	protected String KEY_TOPPINGS_SCREEN;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preference);
		setContentView(R.layout.add_pizza);

		KEY_PIZZA_NAME = getString(R.string.key_name);
		KEY_DESCRIPTION = getString(R.string.key_description);
		KEY_DOUGH_TYPE = getString(R.string.key_dough);
		KEY_MAIN_INGREDIENT_SCREEN = getString(R.string.key_main_ingredients_screen);
		KEY_TOPPINGS_SCREEN = getString(R.string.key_toppings_screen);

		mPizzaName = (EditTextPreference) findPreference(KEY_PIZZA_NAME);
		mPizzaName
				.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
					@Override
					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						mPizzaName.setSummary(newValue.toString());
						return true;
					}
				});

		mDescription = (EditTextPreference) findPreference(KEY_DESCRIPTION);
		mDescription
				.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
					@Override
					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						mDescription.setSummary(newValue.toString());
						return true;
					}
				});

		mDoughType = (ListPreference) findPreference(KEY_DOUGH_TYPE);
		mDoughType
				.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
					@Override
					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						for (int i = 0; i < doughs.size(); i++) {
							if (newValue.equals(doughs.get(i).getId() + "")) {
								mDoughType.setSummary(doughs.get(i).getName());
							}
						}
						return true;
					}
				});

		new DoughDownloaderTask(mDoughType).execute();

		mMainIngredientsScreen = (PreferenceScreen) findPreference(KEY_MAIN_INGREDIENT_SCREEN);
		new MainDownloaderTask(mMainIngredientsScreen).execute();

		mToppingsScreen = (PreferenceScreen) findPreference(KEY_TOPPINGS_SCREEN);
		new ToppingsDownloaderTask(mToppingsScreen).execute();

		final Button okButton = (Button) findViewById(R.id.add_pizza_ok);
		okButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferences sharedPrefs = PreferenceManager
						.getDefaultSharedPreferences(NewPizzaActivity.this);

				String pizzaName = sharedPrefs.getString(KEY_PIZZA_NAME, null);
				String description = sharedPrefs.getString(KEY_DESCRIPTION,
						null);

				List<Ingredient> ingredients = new ArrayList<Ingredient>();
				Ingredient dough = null;
				Object object = sharedPrefs.getAll().get(KEY_DOUGH_TYPE);
				for (Ingredient doughIterator : doughs)
					if (object.equals(doughIterator.getId() + "")) {
						dough = doughIterator;
						break;
					}
				ingredients.add(dough);

				int count = mMainIngredientsScreen.getPreferenceCount();
				for (int i = 0; i < count; i++) {
					Preference ingredientPreference = mMainIngredientsScreen
							.getPreference(i);
					if (ingredientPreference.isEnabled()) {
						ingredients.add(main.get(i));
					}
				}

				count = mToppingsScreen.getPreferenceCount();
				for (int i = 0; i < count; i++) {
					Preference ingredientPreference = mToppingsScreen
							.getPreference(i);
					if (ingredientPreference.isEnabled()) {
						ingredients.add(main.get(i));
					}
				}

				final Pizza newPizza = new Pizza();
				newPizza.setName(pizzaName);
				newPizza.setDescription(description);
				newPizza.setIngredients(ingredients);

				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							mSoapCommunicator.createPizza(newPizza);
						} catch (IOException e) {
							Log.e(PizzaTechMobileAppActivity.TAG,
									e.getMessage());
						} catch (XmlPullParserException e) {
							Log.e(PizzaTechMobileAppActivity.TAG,
									e.getMessage());
						}
					}
				}).start();
				
				facebook.dialog(NewPizzaActivity.this, "feed", new DialogListener() {
					
					@Override
					public void onFacebookError(FacebookError e) {
						// TODO Auto-generated method stub
						NewPizzaActivity.this.finish();
					}
					
					@Override
					public void onError(DialogError e) {
						// TODO Auto-generated method stub
						NewPizzaActivity.this.finish();
					}
					
					@Override
					public void onComplete(Bundle values) {
						// TODO Auto-generated method stub
						NewPizzaActivity.this.finish();
					}
					
					@Override
					public void onCancel() {
						// TODO Auto-generated method stub
						NewPizzaActivity.this.finish();
					}
				});
//				finish();
			}
		});

		final Button cancelButton = (Button) findViewById(R.id.add_pizza_cancel);
		cancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private class DoughDownloaderTask extends
			AsyncTask<Void, Void, List<Ingredient>> {
		private final WeakReference<ListPreference> doughType;

		public DoughDownloaderTask(ListPreference doughTypes) {
			this.doughType = new WeakReference<ListPreference>(doughTypes);
		}

		@Override
		protected List<Ingredient> doInBackground(Void... params) {
			List<Ingredient> ingredients = new ArrayList<Ingredient>();
			try {
				ingredients = mSoapCommunicator.getAllDoughTypes();
			} catch (Exception e) {
				Log.e(PizzaTechMobileAppActivity.TAG, e.getMessage());
				return null;
			}
			return ingredients;
		}

		@Override
		protected void onPostExecute(List<Ingredient> ingredients) {
			if (isCancelled()) {
				ingredients = null;
			}

			doughs.clear();
			doughs.addAll(ingredients);
			String[] entries = new String[ingredients.size()];
			String[] entryValues = new String[ingredients.size()];
			if (doughType != null) {
				ListPreference doughs = doughType.get();
				for (int i = 0; i < ingredients.size(); i++) {
					Ingredient ingredient = ingredients.get(i);
					entryValues[i] = ingredient.getId() + "";
					entries[i] = ingredient.getName();
				}
				doughs.setEntries(entries);
				doughs.setEntryValues(entryValues);
			}

			updateLabels();
		}
	}

	private class MainDownloaderTask extends
			AsyncTask<Void, Void, List<Ingredient>> {
		private final WeakReference<PreferenceScreen> mainIngredientsReference;

		public MainDownloaderTask(PreferenceScreen mainIngredients) {
			this.mainIngredientsReference = new WeakReference<PreferenceScreen>(
					mainIngredients);
		}

		@Override
		protected List<Ingredient> doInBackground(Void... params) {
			List<Ingredient> ingredients = new ArrayList<Ingredient>();
			try {
				ingredients = mSoapCommunicator.getMainIngredients();
			} catch (Exception e) {
				Log.e(PizzaTechMobileAppActivity.TAG, e.getMessage());
				return null;
			}
			return ingredients;
		}

		@Override
		protected void onPostExecute(List<Ingredient> ingredients) {
			if (isCancelled()) {
				ingredients = null;
			}

			if (mainIngredientsReference != null) {
				PreferenceScreen mainIngredients = mainIngredientsReference
						.get();
				main.clear();
				main.addAll(ingredients);
				setupPreferenceScreen(mainIngredients, ingredients);
			}
		}
	}

	private class ToppingsDownloaderTask extends
			AsyncTask<Void, Void, List<Ingredient>> {
		private final WeakReference<PreferenceScreen> toppingsScreenReference;

		public ToppingsDownloaderTask(PreferenceScreen toppingsScreen) {
			this.toppingsScreenReference = new WeakReference<PreferenceScreen>(
					toppingsScreen);
		}

		@Override
		protected List<Ingredient> doInBackground(Void... params) {
			List<Ingredient> ingredients = new ArrayList<Ingredient>();
			try {
				ingredients = mSoapCommunicator.getAllToppings();
			} catch (Exception e) {
				Log.e(PizzaTechMobileAppActivity.TAG, e.getMessage());
				return null;
			}
			return ingredients;
		}

		@Override
		protected void onPostExecute(List<Ingredient> ingredients) {
			if (isCancelled()) {
				ingredients = null;
			}

			if (toppingsScreenReference != null) {
				PreferenceScreen toppingsScreen = toppingsScreenReference.get();
				toppings.clear();
				toppings.addAll(ingredients);
				setupPreferenceScreen(toppingsScreen, ingredients);
			}
		}
	}

	public void setupPreferenceScreen(PreferenceScreen mainIngredients,
			List<Ingredient> ingredients) {
		for (Ingredient ingredient : ingredients) {
			CheckBoxPreference checkPreference = new CheckBoxPreference(this);
			checkPreference.setTitle(ingredient.getName());
			checkPreference.setKey(ingredient.getId() + "");
			checkPreference.setChecked(false);
			mainIngredients.addPreference(checkPreference);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		updateLabels();
	}

	private void updateLabels() {
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(NewPizzaActivity.this);

		String pizzaName = sharedPrefs.getString(KEY_PIZZA_NAME, null);
		if (pizzaName != null)
			mPizzaName.setSummary(pizzaName);
		String description = sharedPrefs.getString(KEY_DESCRIPTION, null);
		if (description != null)
			mDescription.setSummary(description);

		Ingredient dough = null;
		Object object = sharedPrefs.getAll().get(KEY_DOUGH_TYPE);
		for (Ingredient doughIterator : doughs)
			if (object != null && object.equals(doughIterator.getId() + "")) {
				dough = doughIterator;
				break;
			}
		if (dough != null)
			mDoughType.setSummary(dough.getName());
	}
}