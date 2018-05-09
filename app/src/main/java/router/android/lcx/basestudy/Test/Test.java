package router.android.lcx.basestudy.Test;

import java.lang.reflect.Field;


import router.android.lcx.basestudy.DataBase.Annotation.Column;
import router.android.lcx.basestudy.DataBase.Annotation.Table;


/**
 * Created by lichenxi on 2017/4/7.
 */

public class Test {
    public static void main(String[] args){
         String a="ssssss";
         String b=a;
         b="bbbbb";
         System.out.println(a);
//        Developer1 de=new Developer1();
//        de.setName("lcx");
//        de.setId("id");
//        Class<?>clz= Developer1.class;
//        if (clz.isAnnotationPresent(Table.class)){
//            Table table=clz.getAnnotation(Table.class);
//            System.out.println(clz.getSimpleName()+"----table:"+table.name());
//            Field[] fields=clz.getDeclaredFields();
//            for (Field field: fields) {
//                if (field.isAnnotationPresent(Column.class)){
//                      Column colum=field.getAnnotation(Column.class);
//                      field.setAccessible(true);
//                    System.out.println(clz.getSimpleName()+"."+field.getName()+"----"+table.name()+colum.name()+":"+field.get(de).toString());
//                }else {
//                    System.out.println("is not a column");
//                }
//            }
//        }
    }
}
