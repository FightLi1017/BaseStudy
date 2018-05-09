package router.android.lcx.basestudy.StateView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IntDef;
import android.support.annotation.StringDef;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import router.android.lcx.basestudy.R;

/**
 * @author：lichenxi
 * @date 2016/12/13 13
 * email：525603977@qq.com
 * Fighting
 */
public class MultStateView extends RelativeLayout {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STATUS_CONTENT,STATUS_LOADING,STATUS_EMPTY,STATUS_ERROR,STATUS_NO_NETWORK})
    public @interface ViewState{}
    public static final int STATUS_CONTENT      = 0x00;
    public static final int STATUS_LOADING      = 0x01;
    public static final int STATUS_EMPTY        = 0x02;
    public static final int STATUS_ERROR        = 0x03;
    public static final int STATUS_NO_NETWORK   = 0x04;


    private static final int NULL_RESOURCE_ID   = -1;

    private int mContentViewId;
    private int mErrorViewId;
    private int mLoadingViewId;
    private int mDisNetworkViewId;
    private int mEmptyViewId;

    private View mContentView;
    private View mLoadingView;
    private View mErrorView;
    private View mDisNetworkView;
    private View mEmptyView;
    private LayoutInflater inflater;
    private OnClickListener mOnRetryClickListener;
    private final ViewGroup.LayoutParams mLayoutParams = new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    public MultStateView(Context context) {
        this(context,null);
    }

    public MultStateView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MultStateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflater=LayoutInflater.from(context);
        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.MultStateView, defStyleAttr, 0);
        mErrorViewId=array.getResourceId(R.styleable.MultStateView_error_view,R.layout.multstate_error);
        mLoadingViewId=array.getResourceId(R.styleable.MultStateView_loading_view,R.layout.multstate_loading);
        mDisNetworkViewId=array.getResourceId(R.styleable.MultStateView_dis_network_view,R.layout.multstate_nonetwork);
        mEmptyViewId=array.getResourceId(R.styleable.MultStateView_empty_view,R.layout.multstate_empty);
        mContentViewId=array.getResourceId(R.styleable.MultStateView_content_view,-1);
        array.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        showView(STATUS_CONTENT);
    }
   public void setOnRetryClickListener(OnClickListener onClickListener){
        this.mOnRetryClickListener=onClickListener;
   }
    public void showView(@ViewState int state) {
       switch (state){
           case STATUS_CONTENT:
               showContent();
               break;
           case STATUS_LOADING:
              showLoading();
               break;
           case STATUS_EMPTY:
             showEmpty();
               break;
           case STATUS_ERROR:
             showError();
               break;
           case STATUS_NO_NETWORK:
               showNoNetwork();
               break;
               default:
                   break;
       }
        showViewByStatus(state);
    }

    private void showNoNetwork() {
        if(null == mDisNetworkView){
            mDisNetworkView = inflater.inflate(mDisNetworkViewId, null);
            if (mOnRetryClickListener!=null&&mDisNetworkView!=null){
                mDisNetworkView.setOnClickListener(mOnRetryClickListener);
            }
            addView(mDisNetworkView,0, mLayoutParams);
        }
    }

    private void showError() {
        if(null == mErrorView){
            mErrorView = inflater.inflate(mErrorViewId, null);
            if (mOnRetryClickListener!=null&&mErrorView!=null){
                mErrorView.setOnClickListener(mOnRetryClickListener);
            }

            addView(mErrorView,0, mLayoutParams);
        }
    }

    private void showEmpty() {
        if(null == mEmptyView){
            mEmptyView = inflater.inflate(mEmptyViewId, null);
            if (mOnRetryClickListener!=null&&mEmptyView!=null){
                mEmptyView.setOnClickListener(mOnRetryClickListener);
            }

            addView(mEmptyView,0, mLayoutParams);
        }
    }

    private void showLoading() {
        if(null == mLoadingView){
            mLoadingView = inflater.inflate(mLoadingViewId, null);
            addView(mLoadingView,0, mLayoutParams);
        }
    }

    private void showContent() {
         if (null==mContentView){
             if (mContentViewId!=NULL_RESOURCE_ID){
                 mContentView=inflater.inflate(mContentViewId,null);
                 addView(mContentView,0,mLayoutParams);
             }else {
                 mContentView = getChildAt(0);
             }
         }
    }
    private void showViewByStatus(int viewStatus) {
        if(null != mLoadingView){
            mLoadingView.setVisibility(viewStatus == STATUS_LOADING ? View.VISIBLE : View.GONE);
        }
        if(null != mEmptyView){
            mEmptyView.setVisibility(viewStatus == STATUS_EMPTY ? View.VISIBLE : View.GONE);
        }
        if(null != mErrorView){
            mErrorView.setVisibility(viewStatus == STATUS_ERROR ? View.VISIBLE : View.GONE);
        }
        if(null != mDisNetworkView){
            mDisNetworkView.setVisibility(viewStatus == STATUS_NO_NETWORK ? View.VISIBLE : View.GONE);
        }
        if(null != mContentView){
            mContentView.setVisibility(viewStatus == STATUS_CONTENT ? View.VISIBLE : View.GONE);
        }
    }

}
