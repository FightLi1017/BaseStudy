package router.android.lcx.basestudy.DataBase.Manager;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.lang.reflect.Field;

import router.android.lcx.basestudy.DataBase.Annotation.Column;
import router.android.lcx.basestudy.DataBase.Annotation.Table;

/**
 * Created by lichenxi on 2017/4/6.
 */

public class DBUtil{

    public static String getIDColumn(Class<?> clz){
        if (clz.isAnnotationPresent(Table.class)){
            Field[] fields=clz.getDeclaredFields();
            Column column=null;
            for (Field field:fields) {
                 if (field.isAnnotationPresent(Column.class)){
                      column=field.getAnnotation(Column.class);
                     if (column.id()){
                         String id=column.name();
                         if (TextUtils.isEmpty(id)){
                             id=field.getName();
                         }
                         return  id;
                     }

                 }
            }
        }
        return "";
    }
    public static String getColumnName(Field field){
        Column colum= field.getAnnotation(Column.class);
        String name=colum.name();
        if (TextUtils.isEmpty(name)){
            name=field.getName();
        }
        return name;
    }
   public static String getTableName(Class<?> clz){
       if (clz.isAnnotationPresent(Table.class)){
           String name=clz.getAnnotation(Table.class).name();
           if (!TextUtils.isEmpty(name)){
                return name;
           }else{
               return clz.getSimpleName().toLowerCase();
           }
       }

       return clz.getSimpleName();
   }

    public static String getDropTable(Class<?> clz){
        return "drop table if exists"+getTableName(clz);
    }
    public static String getCreateTable(Class<?> clz){
        StringBuilder ColumnNames=new StringBuilder();
        if (clz.isAnnotationPresent(Table.class)){
            Field[] fields=clz.getDeclaredFields();
             for (int i = 0; i <fields.length ; i++) {
                if (fields[i].isAnnotationPresent(Column.class)){
                     ColumnNames.append(getOneColumn(fields[i]));
                      if (i!=fields.length-1)
                          ColumnNames.append(",");

                }
            }
        }
        return "create table if not exists "+getTableName(clz)+" ("+ColumnNames.deleteCharAt(ColumnNames.length()-1)+")";
    }

    private static String getOneColumn(Field field) {
        if (field.isAnnotationPresent(Column.class)){
            Column colum=field.getAnnotation(Column.class);
            String name=colum.name();
            if (TextUtils.isEmpty(name)){
                name="["+field.getName()+"]";
            }else{
                name="["+name+"]";
            }
            Class<?> clz=field.getType();
            String type=null;
            if (clz==String.class){
                type=" TEXT";
            }else if(clz==int.class ||clz==Integer.class){
                  type=" Integer";
            }else{
                Column.ColumnType columntype=colum.type();
                if (columntype== Column.ColumnType.TONE){
                    type=" TEXT";
                }else if (columntype== Column.ColumnType.Serializable){
                     type="BLOB";
                }
                // TODO: 2017/4/10  toone tomany
            }
            //todo not null unipueue
            name+=type;
            if (colum.id()){
                name+=" primary key";
            }
            return name;
        }
        return "";
    }

    public static void DropTable(SQLiteDatabase db, Class clz) throws SQLException{
        db.execSQL(getDropTable(clz));

    }
    public static void CreateTable(SQLiteDatabase db, Class<?> clz)throws SQLException{
        db.execSQL(getCreateTable(clz));

    }
}