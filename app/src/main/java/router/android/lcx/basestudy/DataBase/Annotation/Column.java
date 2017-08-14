package router.android.lcx.basestudy.DataBase.Annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by lichenxi on 2017/4/7.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
    boolean id() default false;
    String name()default "";
    ColumnType type() default  ColumnType.UNKNOWM;
    public enum ColumnType{
        TONE,TMANY,Serializable,UNKNOWM
    }
}
