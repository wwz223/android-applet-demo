package com.lfsfxy.classassistant.activities;

import com.lfsfxy.classassistant.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends Activity{
	private TextView tv_back,tv_title;
	private RelativeLayout rl_modifypsw;
	private RelativeLayout rl_security;
	private RelativeLayout rl_exit;
	private RelativeLayout rl_titlebar;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		tv_title = (TextView) findViewById(R.id.tv_title_main);
		tv_title.setText("设置");
		tv_back = (TextView) findViewById(R.id.tv_title_back);
		rl_titlebar=(RelativeLayout) findViewById(R.id.title_bar);
		rl_titlebar.setBackgroundColor(Color.parseColor("#30b4ff"));
		rl_modifypsw=(RelativeLayout) findViewById(R.id.rl_setting_modifypsw);
		rl_security=(RelativeLayout) findViewById(R.id.rl_setting_security);
		rl_exit=(RelativeLayout) findViewById(R.id.rl_setting_exit);
		tv_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SettingActivity.this.finish();
			}
		});
		rl_modifypsw.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(SettingActivity.this,ModifyPswActivity.class);
				startActivity(intent);
			}
		});
		rl_security.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(SettingActivity.this,FindPswActivity.class);
				intent.putExtra("from", "security");
				startActivity(intent);
			}
		});
		rl_exit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClearLoginStatus();
				Toast.makeText(SettingActivity.this,"退出登录系统", 0).show();
				MainActivity.mMainActivity.finish();
				SettingActivity.this.finish();
			}
		});
		
	}
	protected void ClearLoginStatus(){
		SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
		Editor edit=sp.edit();
		edit.putBoolean("isLogin", false);
		edit.putString("LoginUserName", "");
		edit.commit();
	}
}

