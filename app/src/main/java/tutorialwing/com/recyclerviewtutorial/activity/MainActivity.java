package tutorialwing.com.recyclerviewtutorial.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tutorialwing.com.recyclerviewtutorial.R;
import tutorialwing.com.recyclerviewtutorial.adapter.AnimalAdapter;
import tutorialwing.com.recyclerviewtutorial.model.Animal;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = MainActivity.class.getSimpleName();

	RecyclerView recyclerView;
	AnimalAdapter adapter;
	List<Animal> animalList = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		recyclerView = (RecyclerView) findViewById(R.id.animalListRecyclerView);

		createAnimalList(); // Create the data.

		//This is to show data for first time when we run the app.
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		adapter = new AnimalAdapter(animalList, R.layout.list_item);
		recyclerView.setAdapter(adapter);

		//Add OnItemTouchListener in RecyclerView
		recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
			@Override
			public void onClick(View view, int position) {
				Animal animal = animalList.get(position);
				Toast.makeText(getApplicationContext(), animal.getName() + " is selected!", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onLongClick(View view, int position) {
			}
		}));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		int layoutId = R.layout.list_item;
		int spanCount = 2;
		switch (item.getItemId()) {
			case R.id.linearVertical:  // Vertical scrollable using LinearLayoutManager.
				recyclerView.setLayoutManager(new LinearLayoutManager(this));
				break;
			case R.id.linearHorizontal:  // Horizontal scrollable using LinearLayoutManager.
				layoutId = R.layout.list_item_horizontal;
				recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
				break;
			case R.id.gridviewVertical:  // Vertical scrollable using GridLayoutManager.
				recyclerView.setLayoutManager(new GridLayoutManager(this, spanCount));
				break;
			case R.id.gridviewHorizontal:  // Horizontal scrollable using GridLayoutManager.
				layoutId = R.layout.list_item_horizontal;
				recyclerView.setLayoutManager(new GridLayoutManager(this, spanCount, LinearLayoutManager.HORIZONTAL, false));
				break;
			case R.id.staggeredGridviewVertical:  // Vertical scrollable using StaggeredGridLayoutManager.
				recyclerView.setLayoutManager(new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL));
				break;
			case R.id.staggeredGridviewHorizontal:  // Horizontal scrollable using StaggeredGridLayoutManager.
				layoutId = R.layout.list_item_horizontal;
				recyclerView.setLayoutManager(new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.HORIZONTAL));
				break;
		}
		adapter = new AnimalAdapter(animalList, layoutId);
		recyclerView.setAdapter(adapter);

		return super.onOptionsItemSelected(item);
	}


	private void createAnimalList() {

		// Create data...

		Animal animal1 = new Animal();
		animal1.setName("Monkey");
		animal1.setDetails("Monkeys are haplorhine (\"dry-nosed\") primates, a paraphyletic group generally possessing tails and consisting of approximately 260 known living species");

		Animal animal2 = new Animal();
		animal2.setName("Buffalo");
		animal2.setDetails("The African buffalo or Cape buffalo (Syncerus caffer) is a large African bovine");

		Animal animal3 = new Animal();
		animal3.setName("Donkey");
		animal3.setDetails("The donkey or ass is a domesticated member of the horse family, Equidae. The wild ancestor of the donkey is the African wild ass, E. africanus");

		Animal animal4 = new Animal();
		animal4.setName("Dog");
		animal4.setDetails("The domestic dog is a domesticated canid which has been selectively bred over millennia for various behaviours, sensory capabilities, and physical attributes");

		Animal animal5 = new Animal();
		animal5.setName("Goat");
		animal5.setDetails("The domestic goat is a subspecies of goat domesticated from the wild goat of southwest Asia and Eastern Europe");

		Animal animal6 = new Animal();
		animal6.setName("Tiger");
		animal6.setDetails("The tiger is the largest cat species, most recognisable for their pattern of dark vertical stripes on reddish-orange fur with a lighter underside");

		Animal animal7 = new Animal();
		animal7.setName("Lion");
		animal7.setDetails("The lion is one of the big cats in the genus Panthera and a member of the family Felidae.");

		Animal animal8 = new Animal();
		animal8.setName("Leopard");
		animal8.setDetails("The leopard is one of the five \"big cats\" in the genus Panthera");

		Animal animal9 = new Animal();
		animal9.setName("Cheetah");
		animal9.setDetails("The cheetah, also known as the hunting leopard, is a big cat that occurs mainly in eastern and southern Africa and a few parts of Iran");

		Animal animal10 = new Animal();
		animal10.setName("Rat");
		animal10.setDetails("Rats are various medium-sized, long-tailed rodents of the superfamily Muroidea");

		animalList.add(animal1);
		animalList.add(animal2);
		animalList.add(animal3);
		animalList.add(animal4);
		animalList.add(animal5);
		animalList.add(animal6);
		animalList.add(animal7);
		animalList.add(animal8);
		animalList.add(animal9);
		animalList.add(animal10);
	}


	public interface ClickListener {
		void onClick(View view, int position);

		void onLongClick(View view, int position);
	}

	public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

		private GestureDetector gestureDetector;
		private MainActivity.ClickListener clickListener;

		public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final MainActivity.ClickListener clickListener) {
			this.clickListener = clickListener;
			gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
				@Override
				public boolean onSingleTapUp(MotionEvent e) {
					return true;
				}

				@Override
				public void onLongPress(MotionEvent e) {
					View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
					if (child != null && clickListener != null) {
						clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
					}
				}
			});
		}

		@Override
		public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
			View child = rv.findChildViewUnder(e.getX(), e.getY());
			if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
				clickListener.onClick(child, rv.getChildAdapterPosition(child));
			}
			return false;
		}

		@Override
		public void onTouchEvent(RecyclerView rv, MotionEvent e) {
		}

		@Override
		public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
		}
	}
}