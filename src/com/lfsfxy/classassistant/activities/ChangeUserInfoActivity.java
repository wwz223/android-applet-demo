package com.lfsfxy.classassistant.activities;


import com.lfsfxy.classassistant.R;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeUserInfoActivity extends Activity {
	private String title;
	private String content;
	private int flag;
	private EditText et_content;
	private ImageView iv_delete;

	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_userinfo);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		init();
		
	}

	private void init() {
		title = getIntent().getStringExtra("title");
		content = getIntent().getStringExtra("content");
		flag = getIntent().getIntExtra("flag",0);
		TextView tv_title=(TextView) findViewById(R.id.tv_title_main);
		tv_title.setText(title);
		TextView tv_back = (TextView) findViewById(R.id.tv_title_back);
		RelativeLayout rl_titlebar=(RelativeLayout) findViewById(R.id.title_bar);
		rl_titlebar.setBackgroundColor(Color.parseColor("#30b4ff"));
		TextView tv_save=(TextView) findViewById(R.id.tv_title_save);
		tv_save.setVisibility(View.VISIBLE);
		et_content = (EditText) findViewById(R.id.et_content);
		iv_delete = (ImageView) findViewById(R.id.iv_delete);
		if(!TextUtils.isEmpty(content)){
			et_content.setText(content);
			et_content.setSelection(content.length());
		}
		contentListener();
		tv_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ChangeUserInfoActivity.this.finish();
				
			}
		});
		iv_delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				et_content.setText("");
			}
		});
		tv_save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent data=new Intent();
				String etContent=et_content.getText().toString().trim();
				switch(flag){
				case 1:
					if(!TextUtils.isEmpty(etContent)){
						data.putExtra("nickName", etContent);
						setResult(RESULT_OK,data);
						Toast.makeText(ChangeUserInfoActivity.this, "保存成功", 0).show();
						ChangeUserInfoActivity.this.finish();
					}else{
						Toast.makeText(ChangeUserInfoActivity.this,"昵称不能为空", 0).show();
					}
					break;
				case 2:
					if(!TextUtils.isEmpty(etContent)){
						data.putExtra("signature", etContent);
						setResult(RESULT_OK,data);
						Toast.makeText(ChangeUserInfoActivity.this, "保存成功", 0).show();
						ChangeUserInfoActivity.this.finish();
				}else{
					Toast.makeText(ChangeUserInfoActivity.this,"签名不能为空", 0).show();
					}	
					break;
				}
			}
		});
		
	}
/**监听个人资料修改页面输入文字**/
	private void contentListener() {
		// TODO Auto-generated method stub
		et_content.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				Editable editable=et_content.getText();
				int len=editable.length();
				if(len>0){
					iv_delete.setVisibility(View.VISIBLE);
				}else{
					iv_delete.setVisibility(View.GONE);
				}
				switch(flag){
				case 1:
					/**呢称**/
					if(len>8){
						int selEndIndex=Selection.getSelectionEnd(editable);
						String str=editable.toString();
						String newStr=str.substring(0,8);
						et_content.setText(newStr);
						editable=et_content.getText();
						int newLen=editable.length();
						if(selEndIndex>newLen){
							selEndIndex=editable.length();
						}
						Selection.setSelection(editable, selEndIndex);
					}
					break;
				case 2:
					/**签名**/
					if(len>16){
						int selEndIndex=Selection.getSelectionEnd(editable);
						String str=editable.toString();
						String newStr=str.substring(0,8);
						et_content.setText(newStr);
						editable=et_content.getText();
						int newLen=editable.length();
						if(selEndIndex>newLen){
							selEndIndex=editable.length();
						}
						Selection.setSelection(editable, selEndIndex);
					}
					break;
				default:
					break;
				}
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
