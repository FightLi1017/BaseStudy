package router.android.lcx.basestudy.Test;

import java.lang.reflect.Field;

import router.android.lcx.basestudy.DataBase.Annotation.Colum;
import router.android.lcx.basestudy.DataBase.Annotation.Table;


/**
 * Created by lichenxi on 2017/4/7.
 */

public class Test {
    public static void main(String[] args)  throws Exception{
        Developer1 de=new Developer1();
        de.setName("lcx");
        de.setId("id");
        Class<?>clz= Developer1.class;
        if (clz.isAnnotationPresent(Table.class)){
            Table table=clz.getAnnotation(Table.class);
            System.out.println(clz.getSimpleName()+"----table:"+table.name());
            Field[] fields=clz.getDeclaredFields();
            for (Field field: fields) {
                if (field.isAnnotationPresent(Colum.class)){
                      Colum colum=field.getAnnotation(Colum.class);
                      field.setAccessible(true);
                    System.out.println(clz.getSimpleName()+"."+field.getName()+"----"+table.name()+colum.name()+":"+field.get(de).toString());
                }else {
                    System.out.println("is not a column");
                }
            }
        }
    }
}
