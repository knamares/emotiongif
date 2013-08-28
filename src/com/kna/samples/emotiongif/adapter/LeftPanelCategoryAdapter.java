package com.kna.samples.emotiongif.adapter;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kna.samples.emotiongif.R;
import com.kna.samples.emotiongif.data.Category;

public class LeftPanelCategoryAdapter extends ArrayAdapter<Category> {

	private final Activity			context;
	private final List<Category>	categories;

	static class ViewHolder {
		public TextView		text;
		public ImageView	image;
	}

	public LeftPanelCategoryAdapter(Activity context, int textViewResourceId, List<Category> categories) {
		super(context, textViewResourceId, categories);
		this.context = context;
		this.categories = categories;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if (rowView == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			rowView = inflater.inflate(R.layout.leftpanelitem, null);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.text = (TextView) rowView.findViewById(R.id.leftpanelitemtext);
			viewHolder.image = (ImageView) rowView.findViewById(R.id.leftpanelitemimage);
			rowView.setTag(viewHolder);
		}

		ViewHolder holder = (ViewHolder) rowView.getTag();

		// Set text
		String text = categories.get(position).getName();
		holder.text.setText(text);

		// Set image
		holder.image.setImageResource(R.drawable.ic_launcher);

		return rowView;
	}

}
