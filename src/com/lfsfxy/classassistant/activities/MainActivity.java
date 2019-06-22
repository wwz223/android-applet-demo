package com.lfsfxy.classassistant.activities;



import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lfsfxy.classassistant.R;
import com.lfsfxy.classassistant.adapter.ExerciseAdapter;
import com.lfsfxy.classassistant.bean.ExerciseBean;
import com.lfsfxy.classassistant.utils.ExerciseParser;

public class MainActivity extends Activity implements OnClickListener {
	private RelativeLayout rl_setting;
	private RelativeLayout rl_exercise;
	private RelativeLayout rl_myinfo;
	private TextView tv_back;
	private InputStream is;
	private List<ExerciseBean> exerciseInfos;
	private ExerciseAdapter adapter;
	private ListView lv_exercise;
	public static Activity mMainActivity=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		mMainActivity=this;
		init();
		initData();
	}
	private void initData() {
		// TODO Auto-generated method stub
		is = this.getResources().openRawResource(R.raw.exercises);
		try {
			exerciseInfos = ExerciseParser.getExercisesFormJson(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		adapter = new ExerciseAdapter(this,exerciseInfos);
		lv_exercise = (ListView) findViewById(R.id.lv_main_exercise);
		lv_exercise.setAdapter(adapter);
	}
	private void init() {
		// TODO Auto-generated method stub
		TextView tv_title = (TextView) findViewById(R.id.tv_title_main);
		tv_title.setText("移动应用基础课程");
		tv_back = (TextView) findViewById(R.id.tv_title_back);
		rl_setting=(RelativeLayout) findViewById(R.id.btn_bottombar_setting);
		rl_exercise=(RelativeLayout) findViewById(R.id.btn_bottombar_exercise);
		rl_myinfo=(RelativeLayout) findViewById(R.id.btn_bottombar_myinfo);
		tv_title.setOnClickListener(this);
		rl_setting.setOnClickListener(this);
		rl_exercise.setOnClickListener(this);
		rl_myinfo.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.tv_title_back:
			finish();
			break;
		case R.id.btn_bottombar_setting:
			Intent intent = new Intent(this,SettingActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_bottombar_exercise:
			break;
		case R.id.btn_bottombar_myinfo:
			Intent intent2 = new Intent(this,UserInfoActivity.class);
			startActivity(intent2);
			break;
		}
	}
}
