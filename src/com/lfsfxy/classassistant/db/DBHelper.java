package com.lfsfxy.classassistant.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 创建数据库
 * @author Administrator
 *
 */
public class DBHelper extends SQLiteOpenHelper {

	public DBHelper(Context context) {
		super(context, "ca.db", null, 1);
		// TODO Auto-generated constructor stub
		
	}
	
	/**
	 * 不能进行select操作和任何带返回值的sql语句
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//对于数据格式的要求不是很严格，所以不能存储大量的数据
		db.execSQL("create table userinfo("+
		             "id integer primary key autoincrement,"+
				     "username varchar(20),"+
		             "nickName varchar(20),"+
				     "sex varchar(2),"+
		             "signature varchar(20))");
		  
	}

	/**
	 * 更新数据库的时候被调用
	 * 比如版本更新
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

}
