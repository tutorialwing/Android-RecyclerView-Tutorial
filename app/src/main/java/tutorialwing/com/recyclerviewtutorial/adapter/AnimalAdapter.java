package tutorialwing.com.recyclerviewtutorial.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tutorialwing.com.recyclerviewtutorial.R;
import tutorialwing.com.recyclerviewtutorial.model.Animal;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> {

	private List<Animal> animals;
	private int rowLayout;

	public AnimalAdapter(List<Animal> animals, int rowLayout) {
		this.animals = animals;
		this.rowLayout = rowLayout;
	}

	@Override
	public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
		return new AnimalViewHolder(view);
	}

	@Override
	public void onBindViewHolder(AnimalViewHolder holder, final int position) {
		holder.name.setText(animals.get(position).getName());
		holder.details.setText(animals.get(position).getDetails());
	}

	@Override
	public int getItemCount() {
		return animals.size();
	}

	public static class AnimalViewHolder extends RecyclerView.ViewHolder {
		TextView name;
		TextView details;

		public AnimalViewHolder(View v) {
			super(v);
			name = (TextView) v.findViewById(R.id.name);
			details = (TextView) v.findViewById(R.id.details);
		}
	}
}