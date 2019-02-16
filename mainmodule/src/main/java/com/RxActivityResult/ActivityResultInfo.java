package com.RxActivityResult;

import android.content.Intent;

/**
 * @author：lichenxi
 * @date 2019/2/12 14
 * email：525603977@qq.com
 * Fighting
 */
public class ActivityResultInfo {
    private int requestCode;
    private int resultCode;
    private Intent data;
    public ActivityResultInfo(int requestCode, int resultCode, Intent data) {
        this.requestCode = requestCode;
        this.resultCode = resultCode;
        this.data = data;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public ActivityResultInfo(int resultCode, Intent data) {
        this.resultCode = resultCode;
        this.data = data;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Intent getData() {
        return data;
    }
    public  boolean isNullData(){
        return data!=null;
    }
    public void setData(Intent data) {
        this.data = data;
    }
}
