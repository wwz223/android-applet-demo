package com.lfsfxy.classassistant.activities;

import com.lfsfxy.classassistant.R;
import com.lfsfxy.classassistant.utils.MD5Utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FindPswActivity extends Activity{
	private String from;
	private EditText et_validate;
	private Button btn_validate;
	private TextView tv_resetpsw;
	private EditText et_username;
	private TextView tv_username;
	private SharedPreferences sp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_findpsw);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
		from = getIntent().getStringExtra("from");
		init();	
	}
	
	private void init(){
		TextView tv_title = (TextView) findViewById(R.id.tv_title_main);
		TextView tv_back = (TextView) findViewById(R.id.tv_title_back);
		et_validate = (EditText) findViewById(R.id.et_find_validatename);
		btn_validate = (Button) findViewById(R.id.btn_find_validate);
		tv_resetpsw = (TextView) findViewById(R.id.tv_find_resetpsw);
		et_username = (EditText) findViewById(R.id.et_find_username);
		tv_username = (TextView) findViewById(R.id.tv_find_username);
		if("security".equals(from)){
			tv_title.setText("设置密码");
			btn_validate.setText("设置");
		}else{
			tv_title.setText("找回密码");
			btn_validate.setText("验证");
			tv_username.setVisibility(View.VISIBLE);
			et_username.setVisibility(View.VISIBLE);
		}
		tv_back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FindPswActivity.this.finish();
			}
		});
		btn_validate.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String validateName = et_validate.getText().toString().trim();
				if("security".equals(from)){
					if(TextUtils.isEmpty(validateName)){
						Toast.makeText(FindPswActivity.this,"请输入验证的姓名",0).show();
						return;
					}else{
						saveSecurity(validateName);
						Toast.makeText(FindPswActivity.this,"密保设置成功",0).show();
						FindPswActivity.this.finish();
						
					}
				}else{
					String username = et_username.getText().toString();
					String sp_security = sp.getString(username+"_security","");
					if(TextUtils.isEmpty(username)){
						Toast.makeText(FindPswActivity.this,"请输入您的用户名",0).show();
						return;
					}
					if(!isExistUserName(username)){
						Toast.makeText(FindPswActivity.this,"您输入的用户名不存在",0).show();
						return;
					}
					if(TextUtils.isEmpty(validateName)){
						Toast.makeText(FindPswActivity.this,"请输入要验证的姓名",0).show();
						return;
					}
					if(!validateName.equals(sp_security)){
						Toast.makeText(FindPswActivity.this,"您输入的姓名有误",0).show();
						return;
					}
					tv_resetpsw.setVisibility(View.VISIBLE);
					tv_resetpsw.setText("初始密码：123456");
					savePsw(username);
				}
			}
			
		});
	}

	protected void savePsw(String username) {
		// TODO Auto-generated method stub
		String md5Psw = MD5Utils.md5("123456");
		Editor edit = sp.edit();
		edit.putString(username, md5Psw);
		edit.commit();
	}

	protected boolean isExistUserName(String username) {
		// TODO Auto-generated method stub
		boolean hasUserName = false;
		String spPsw = sp.getString(username, "");
		if(!TextUtils.isEmpty(spPsw)){
			hasUserName = true;
		}
		return hasUserName;
	}

	protected void saveSecurity(String validateName) {
		// TODO Auto-generated method stub
		Editor edit = sp.edit();
		edit.putString(sp.getString("loginUsername", "")+"_security",validateName);
		edit.commit();
	}
	
	
}
