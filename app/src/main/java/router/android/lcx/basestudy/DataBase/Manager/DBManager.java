package router.android.lcx.basestudy.DataBase.Manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import router.android.lcx.basestudy.DataBase.Developer;


/**
 * Created by lichenxi on 2017/4/6.
 */

public class DBManager {
    private static DBManager mInstance;
    private Databasehelper mhelper;
    private SQLiteDatabase mDatabase;

    public DBManager(Context context) {
         mhelper=new Databasehelper(context);
         mDatabase=mhelper.getWritableDatabase();
    }

    public static DBManager getinstance(Context context){
      if (mInstance==null){
          mInstance=new DBManager(context);
      }
        return  mInstance;
    }

   public  void release(){
         mDatabase.close();
          mInstance=null;
    }

    public void newOrUpdate(Developer developer){

        ContentValues values=new ContentValues();
        values.put(Developer._ID,developer.getId());
        values.put(Developer._NAME,developer.getName());
        values.put(Developer._AGE,developer.getAge());
        values.put(Developer._COMPANY,developer.getCompany().getId());
        // TODO: 2017/4/6
        //values.put(Developer._SKILLS,developer.getSkill());
        mDatabase.replace(DBUtil.getTable(developer.getClass()),null,values);
     }
    public void delete(Developer developer){
       mDatabase.delete(DBUtil.getTable(developer.getClass()),Developer._ID+"=?",new String[]{developer.getId()});
    }
    public Developer queryByid(String id){
       Cursor cursor= mDatabase.rawQuery("select * from"+DBUtil.getTable(Developer.class)+"where"+Developer._ID+"=?", new String[]{id});
        Developer developer=null;
        if (cursor.moveToNext()){
             developer=new Developer();
             developer.setId(cursor.getString(cursor.getColumnIndex(Developer._ID)));
            developer.setName(cursor.getString(cursor.getColumnIndex(Developer._NAME)));
            developer.setAge(cursor.getInt(cursor.getColumnIndex(Developer._AGE)));
            //developer.setCompany(cursor.get(cursor.getColumnIndex(Developer._COMPANY)));
            // TODO: 2017/4/7  skills
        }
      return developer;
    }
    class Databasehelper extends SQLiteOpenHelper{
         public static final String DB_NAME="lcx.db";
        public static final int DB_VERSION=1;
        public Databasehelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO: 2017/4/6  create Table

        }
    }
}
