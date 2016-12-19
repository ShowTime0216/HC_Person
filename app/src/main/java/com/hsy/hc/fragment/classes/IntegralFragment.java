package com.hsy.hc.fragment.classes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hsy.hc.BaseFragment;
import com.hsy.hc.R;

import butterknife.ButterKnife;

/**
 * 作者:liupeng
 * 16/9/21 16:23
 * 注释: 积分商城
 */
public class IntegralFragment extends BaseFragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_integral, container, false);
        ButterKnife.bind(this, view);

        initView();
        return view;
    }

    private void initView() {
    }
}
