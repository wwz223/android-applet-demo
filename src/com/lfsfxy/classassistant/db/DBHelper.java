package com.lfsfxy.classassistant.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * �������ݿ�
 * @author Administrator
 *
 */
public class DBHelper extends SQLiteOpenHelper {

	public DBHelper(Context context) {
		super(context, "ca.db", null, 1);
		// TODO Auto-generated constructor stub
		
	}
	
	/**
	 * ���ܽ���select�������κδ�����ֵ��sql���
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//�������ݸ�ʽ��Ҫ���Ǻ��ϸ����Բ��ܴ洢����������
		db.execSQL("create table userinfo("+
		             "id integer primary key autoincrement,"+
				     "username varchar(20),"+
		             "nickName varchar(20),"+
				     "sex varchar(2),"+
		             "signature varchar(20))");
		  
	}

	/**
	 * �������ݿ��ʱ�򱻵���
	 * ����汾����
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

}
