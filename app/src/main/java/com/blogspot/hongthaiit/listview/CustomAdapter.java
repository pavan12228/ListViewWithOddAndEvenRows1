package com.blogspot.hongthaiit.listview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.LinkedList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {


	List<Model> modelList=new LinkedList<>();
	Context mContext;

	public CustomAdapter(Context mContext, List<Model> modelList) {
		this.mContext = mContext;
		this.modelList = modelList;
	}

	@Override
	public int getItemViewType(int position) {
		return position%2;
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public int getCount() {
		return modelList.size();
	}

	@Override
	public Object getItem(int position) {
		return modelList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

		int layoutResource = 0; // determined by view type
		int viewType = getItemViewType(position);
		switch (viewType) {
			case 0:
				layoutResource = R.layout.item_even_listview;
				break;

			case 1:
				layoutResource = R.layout.item_odd_listview;
				break;
		}

		if (convertView != null) {
			holder = (ViewHolder) convertView.getTag();
		} else {
			convertView = inflater.inflate(layoutResource, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}


		ImageLoader imageLoader=ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
		Model model=   modelList.get(position);
		imageLoader.displayImage(model.getGender(),holder.gender);
		holder.name.setText(model.getName());

     return convertView;

	}
	private class ViewHolder {
		private ImageView gender;
		private TextView name;

		public ViewHolder(View v) {
			gender = (ImageView) v.findViewById(R.id.gender_image);
			name = (TextView) v.findViewById(R.id.name);
		}
	}


	}

