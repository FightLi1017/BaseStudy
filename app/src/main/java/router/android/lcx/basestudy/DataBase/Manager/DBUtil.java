package router.android.lcx.basestudy.DataBase.Manager;

/**
 * Created by lichenxi on 2017/4/6.
 */

public class DBUtil {
   public static String getTable(Class clz){
       return clz.getSimpleName();
   }
}