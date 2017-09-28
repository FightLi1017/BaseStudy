package lcx.lcxpermission;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import lcx.lcxpermission.Target.ActivityAction;
import lcx.lcxpermission.Target.ContextAction;
import lcx.lcxpermission.Target.FragmentAction;

/**
 * Created by lichenxi on 2017/9/28.
 */

public class SimplePermission {

    public  static RequestCreator  with(Activity activity){
        return  new RequestCreator(new ActivityAction(activity)) ;
    }
    public  static RequestCreator  with(Fragment  fragment){
        return  new RequestCreator(new FragmentAction(fragment));
    }
    public  static RequestCreator  with(Context context){
        return  new RequestCreator(new ContextAction(context));
    }


}
