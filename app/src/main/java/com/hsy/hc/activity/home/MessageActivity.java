package com.hsy.hc.activity.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.TextView;

import com.hsy.hc.BaseActivity;
import com.hsy.hc.R;
import com.hsy.hc.adapter.MessageAdapter;
import com.hsy.hc.entity.ServiceData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者:liupeng
 * 16/9/27 16:44
 * 注释: 消息分组
 */
public class MessageActivity extends BaseActivity {

    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.message_list)
    ListView messageList;

    private Context context;
    private MessageAdapter messageAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        context = MessageActivity.this;
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {

        titleText.setText("消息");

        messageAdapter = new MessageAdapter(context, list1());
        messageList.setAdapter(messageAdapter);

    }

    private List<ServiceData> list1() {
        List<ServiceData> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            ServiceData serviceData = new ServiceData();
            serviceData.setName("聊天消息");
            serviceData.setContent("有妹纸@你啦，赶快去看看！！！！！");
            serviceData.setText1("2016-09-09 12:12:59");
            list.add(serviceData);
        }
        return list;
    }
}
