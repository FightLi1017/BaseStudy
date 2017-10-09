package lcx.lcxpermission;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by lichenxi on 2017/10/9.
 */

public class SettingDialog {
   private Context mContext;
   private SettingExecutor mSettingExecutor;
    private AlertDialog.Builder mBuilder;
    public SettingDialog(Context context, SettingExecutor settingExecutor) {
        mSettingExecutor = settingExecutor;
        mBuilder=new AlertDialog.Builder(context)
                        .setCancelable(false)//点击外部不消失
                        .setTitle(R.string.permission_title_permission_failed)
                        .setMessage(R.string.permission_message_permission_failed)
                        .setPositiveButton(R.string.permission_setting,mClickListener)
                        .setNegativeButton(R.string.permission_cancel,mClickListener);
    }
   public void show(){
       mBuilder.show();
   }
    /**
     * The dialog's btn click listener.
     */
    private DialogInterface.OnClickListener mClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    mSettingExecutor.enterSetting();
                    break;
            }
        }
    };
}
