package com.lfsfxy.classassistant.activities;

import com.lfsfxy.classassistant.R;
import com.lfsfxy.classassistant.utils.MD5Utils;

import android.app.Activity;
import android.content.Intent;
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

public class ModifyPswActivity extends Activity {
	private SharedPreferences sp;
	private EditText et_originalpsw;
	private EditText et_newpsw;
	private String username;
	private String originalPsw;
	private String newPsw;
	private String renewPsw;
	private EditText et_renewpsw;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modifypsw);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
		init();
		username = sp.getString("loginUsername", "");
	}
	private void init(){
		TextView tv_title = (TextView) findViewById(R.id.tv_title_main);
		tv_title.setText("修改密码");
		TextView tv_back = (TextView) findViewById(R.id.tv_title_back);
		et_originalpsw = (EditText) findViewById(R.id.et_modify_originalpsw);
		et_newpsw = (EditText) findViewById(R.id.et_modify_newpsw);
		et_renewpsw = (EditText) findViewById(R.id.et_modify_renewpsw);
		Button btn_save = (Button) findViewById(R.id.btn_save);
		tv_back = (TextView) findViewById(R.id.tv_title_back);
		tv_back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ModifyPswActivity.this.finish();
			}
			
		});
		btn_save.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String oldPsw = et_originalpsw.getText().toString().trim();
				String newPsw = et_newpsw.getText().toString().trim();
				String renewPsw = et_renewpsw.getText().toString().trim();
				// TODO Auto-generated method stub
				getEditString();
				if(TextUtils.isEmpty(oldPsw)){
					Toast.makeText(ModifyPswActivity.this,"请输入原始密码",0).show();
					return;
				}
				if(!MD5Utils.md5(oldPsw).equals(sp.getString(username,""))){
					Toast.makeText(ModifyPswActivity.this,"输入的密码与原密码不一致",0).show();
					return;
				}
				if(TextUtils.isEmpty(newPsw)){
					Toast.makeText(ModifyPswActivity.this,"请输入新密码",0).show();
					return;
				}				
				if(MD5Utils.md5(newPsw).equals(sp.getString(username,""))){
					Toast.makeText(ModifyPswActivity.this,"新输入的密码与原始密码不能一致",0).show();
					return;
				}				
				if(TextUtils.isEmpty(renewPsw)){
					Toast.makeText(ModifyPswActivity.this,"请再次输入新密码",0).show();
					return;
				}
				if(!newPsw.equals(renewPsw)){
					Toast.makeText(ModifyPswActivity.this,"再次输入的新密码不一致",0).show();
					return;
				}
				modifyPsw(newPsw);
				Toast.makeText(ModifyPswActivity.this, "新密码设置成功", 0).show();
				//意图（页面跳转）
				Intent intent = new Intent(ModifyPswActivity.this,LoginActivity.class);
				startActivity(intent);
				ModifyPswActivity.this.finish();
			}
		});
	}
	protected void modifyPsw(String newPsw2){
		String md5Psw = MD5Utils.md5(newPsw);
		Editor edit = sp.edit();
		edit.putString(username,md5Psw);
		edit.commit();
	}
	protected void getEditString(){
		originalPsw = et_originalpsw.getText().toString().trim();
		newPsw = et_newpsw.getText().toString().trim();
		renewPsw = et_renewpsw.getText().toString().trim();
	}
}
