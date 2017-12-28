package router.android.lcx.basestudy.Test;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fight.android.lcx.downmanager.SimpleDown.FileCallback;
import fight.android.lcx.downmanager.SimpleDown.FileDownManager;
import fight.android.lcx.downmanager.SimpleDown.Progress;
import fight.android.lcx.downmanager.TLog;
import router.android.lcx.basestudy.DepthPageTransformer;
import router.android.lcx.basestudy.R;

public class Main2Activity extends AppCompatActivity {
 private ViewPager mViewPager;
    private int[] mImgIds = new int[] { R.drawable.bb,
            R.drawable.cc, R.drawable.aa };
    private List<ImageView> mImageViews = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mViewPager=(ViewPager)findViewById(R.id.viewpager);
        initData();
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        mViewPager.setAdapter(new PagerAdapter()
        {
            @Override
            public Object instantiateItem(ViewGroup container, int position)
            {
                container.addView(mImageViews.get(position));
                return mImageViews.get(position);
            }
            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object)
            {
                container.removeView(mImageViews.get(position));
            }
            @Override
            public boolean isViewFromObject(View view, Object object)
            {
                return view == object;
            }
            @Override
            public int getCount()
            {
                return mImgIds.length;
            }
        });
//        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FileDownManager.url(url).callback(new FileCallback() {
//                    @Override
//                    public void success(File file) {
//                        TLog.d(file.getAbsolutePath());
//                        Toast.makeText(Main2Activity.this,"下载ok"+file.getName(),Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void downloadProgress(Progress progress) {
//                        TLog.d("contentlength"+progress.contentLength+"totalReaded:"+progress.totalReaded);
//                    }
//
//                    @Override
//                    public void error() {
//                        Toast.makeText(getBaseContext(),"下载失败",Toast.LENGTH_SHORT).show();
//                    }
//                }).startdown();
//            }
//        });
    }

    private void initData() {
        for (int imgId : mImgIds)
        {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(imgId);
            mImageViews.add(imageView);
        }
    }
}
