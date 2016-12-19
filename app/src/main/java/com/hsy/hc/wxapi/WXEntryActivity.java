package com.hsy.hc.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.hsy.hc.R;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;


/**
 * 微信登录
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wx_login);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        finish();
    }


    @Override
    public void onReq(BaseReq baseReq) {
        // TODO Auto-generated method stub
    }


    @Override
    public void onResp(BaseResp baseResp) {
        // TODO Auto-generated method stub
    }
}

