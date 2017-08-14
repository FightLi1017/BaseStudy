package router.android.lcx.basestudy.DataBase.Manager;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import java.lang.reflect.Field;


import router.android.lcx.basestudy.DataBase.Annotation.Column;
import router.android.lcx.basestudy.DataBase.Annotation.Table;
import router.android.lcx.basestudy.DataBase.Company;
import router.android.lcx.basestudy.DataBase.Developer;
import router.android.lcx.basestudy.DataBase.SerializeUtil;


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
    public void DropTable(){
        DBUtil.CreateTable(mDatabase,Developer.class);
        DBUtil.CreateTable(mDatabase,Company.class);
    }
  public <T> void newOrUpdate(T t){
      if (t.getClass().isAnnotationPresent(Table.class)){
          Field[] fields=t.getClass().getDeclaredFields();
          ContentValues values=new ContentValues();
          try {
              for (Field field:fields) {
                  if (field.isAnnotationPresent(Column.class)){
                      field.setAccessible(true);
                      Class<?> clz=field.getType();
                      if (clz==String.class){
                          values.put(DBUtil.getColumnName(field),field.get(t).toString());
                      }else if (clz==int.class ||clz==Integer.class){
                          values.put(DBUtil.getColumnName(field),field.getInt(t));
                      }else{
                          // TODO: 2017/4/9   toone Serializable
                           Column column=field.getAnnotation(Column.class);
                           Column.ColumnType type=column.type();
                           if (TextUtils.isEmpty(type.name())){
                              throw new RuntimeException("you should set type"+field.getClass().getSimpleName()+"..."+field.getName());
                           }
                           if (type== Column.ColumnType.Serializable){
                                byte[] bytes= SerializeUtil.serialize(field.get(t));
                                values.put(DBUtil.getColumnName(field),bytes);
                           }
                      }
                  }

              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          mDatabase.replace(DBUtil.getTableName(t.getClass()),null,values);

      }
  }

//    public void newOrUpdate(Developer developer){
//
//        ContentValues values=new ContentValues();
//        values.put(Developer._ID,developer.getId());
//        values.put(Developer._NAME,developer.getName());
//        values.put(Developer._AGE,developer.getAge());
//        values.put(Developer._COMPANY,developer.getCompany().getId());
//        // TODO: 2017/4/6
//        //values.put(Developer._SKILLS,developer.getSkill());
//        mDatabase.replace(DBUtil.getTableName(developer.getClass()),null,values);
//     }
    public <T> void delete(T t){
        try {
            String idName=DBUtil.getIDColumn(t.getClass());
            Field field=t.getClass().getField(idName);
            field.setAccessible(true);
            String id=field.get(t).toString();
            mDatabase.delete(DBUtil.getTableName(t.getClass()),idName+"=?",new String[]{id});

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public Developer queryByid(String id){
        Cursor cursor= mDatabase.rawQuery("select * from"+DBUtil.getTableName(Developer.class)+"where"+Developer._ID+"=?", new String[]{id});
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

    public <T> T queryByid(Class<T> clz,String id){
        Cursor cursor= mDatabase.rawQuery("select * from "+DBUtil.getTableName(clz)+" where "+DBUtil.getIDColumn(clz)+"=?", new String[]{id});
         T t=null;
        if (cursor.moveToNext()){
            try {
                 t=clz.newInstance();
                Field[] fields=t.getClass().getDeclaredFields();
                for (Field field:fields) {
                    if (field.isAnnotationPresent(Column.class)){
                        Class<?> classtype=field.getType();
                        field.setAccessible(true);
                        if (classtype==String.class){
                           field.set(t,cursor.getString(cursor.getColumnIndex(DBUtil.getColumnName(field))));
                        }else if (classtype==int.class||classtype==Integer.class){
                            field.setInt(t,cursor.getInt(cursor.getColumnIndex(DBUtil.getColumnName(field))));
                        }else{
                            Column column=field.getAnnotation(Column.class);
                            Column.ColumnType type=column.type();
                            if (TextUtils.isEmpty(type.name())){
                                throw new RuntimeException("you should set type"+field.getClass().getSimpleName()+"..."+field.getName());
                            }
                            if (type== Column.ColumnType.Serializable){
                               field.set(t,SerializeUtil.deserialize(cursor.getBlob(cursor.getColumnIndex(DBUtil.getColumnName(field)))));
                            }
                        }

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return t;
    }
    class Databasehelper extends SQLiteOpenHelper{
         public static final String DB_NAME="lcx.db";
        public static final int DB_VERSION=1;
        public Databasehelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
             DBUtil.DropTable(db,Developer.class);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO: 2017/4/6  create Table
            DBUtil.CreateTable(db,Developer.class);
            DBUtil.CreateTable(db,Company.class);
        }
    }
}
