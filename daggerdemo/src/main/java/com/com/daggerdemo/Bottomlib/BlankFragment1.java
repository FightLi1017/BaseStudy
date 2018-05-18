package com.com.daggerdemo.Bottomlib;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.com.daggerdemo.MainActivity;
import com.com.daggerdemo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment1 extends Fragment {
    private MainActivity mActivity;

    public BlankFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    private TextView mTextView;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextView=  view.findViewById(R.id.text);
        mTextView.setText("Text1");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}
