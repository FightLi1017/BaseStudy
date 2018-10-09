package demo.supermvp;

import android.support.v4.app.Fragment;

import java.lang.reflect.Method;

/**
 * @author：lichenxi
 * @date 2018/9/26 17
 * email：525603977@qq.com
 * Fighting
 */
public class Util {
   //采用反射调用isInBackStack方法
   public static  boolean isInBackStack(Fragment fragment){
       try {
           Method method = fragment.getClass().getDeclaredMethod("isInBackStack");
          return(boolean)method.invoke(fragment);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return  false;
   }
}
