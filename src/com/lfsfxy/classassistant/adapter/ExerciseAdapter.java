package com.lfsfxy.classassistant.adapter;

import java.util.List;

import com.lfsfxy.classassistant.R;
import com.lfsfxy.classassistant.bean.ExerciseBean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ExerciseAdapter extends BaseAdapter {
	private Context context;
	private List<ExerciseBean> exerciseInfos;
	
	public ExerciseAdapter(Context context, List<ExerciseBean> exerciseInfos) {
		super();
		this.context = context;
		this.exerciseInfos = exerciseInfos;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return exerciseInfos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return exerciseInfos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if(convertView == null){
			convertView = View.inflate(context, R.layout.item_exercise_list, null);
			holder = new ViewHolder();
			holder.mOrderTV = (TextView) convertView.findViewById(R.id.tv_exercise_order);
			holder.mTitleTV = (TextView) convertView.findViewById(R.id.tv_exercise_title);
			holder.mContentTV = (TextView) convertView.findViewById(R.id.tv_exercise_content);
		
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.mOrderTV.setText(position+1+"");
		holder.mTitleTV.setText(exerciseInfos.get(position).title);
		holder.mContentTV.setText(exerciseInfos.get(position).content);
		return convertView;
	}
	
	static class ViewHolder{
		TextView mOrderTV;
		TextView mTitleTV;
		TextView mContentTV;
	}

}
