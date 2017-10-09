package lcx.lcxpermission;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by lichenxi on 2017/10/9.
 */

public class RationaleDialog {
   private Context mContext;
   private Rationale mRationale;
    private AlertDialog.Builder mBuilder;
    public RationaleDialog(Context context, Rationale rationale) {
        mRationale = rationale;
        mBuilder=new AlertDialog.Builder(context)
                        .setCancelable(false)//点击外部不消失
                        .setTitle(R.string.permission_title_rationale)
                        .setMessage(R.string.permission_message_rationale)
                        .setPositiveButton(R.string.permission_resume,mClickListener)
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
                case DialogInterface.BUTTON_NEGATIVE:
                    mRationale.cancel();
                    break;
                case DialogInterface.BUTTON_POSITIVE:
                    mRationale.resume();
                    break;
            }
        }
    };
}
