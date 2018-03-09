package com.mays.lookimage.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.mays.lookimage.R;
import com.mays.lookimage.adapter.MyAdapter;
import com.mays.lookimage.aitaotu.CrawlMain;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private AVLoadingIndicatorView mAvi;
    private ListView mLv;

    Map<String, List<String>> urlMap;
    List<String> titleList;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        initView();
        initListener();
        initData();
    }

    private void initView() {
        mAvi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        mLv = (ListView) findViewById(R.id.lv);
    }

    private void initListener() {

    }

    private void initData() {
        getData();
    }

    private void getData() {
        mAvi.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("----getUrl()---Start");
                urlMap = new CrawlMain().getUrl();
                System.out.println("----getUrl()---End");
                setView();
            }
        }).start();
    }

    private void setView() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                titleList = getTitleList(urlMap);
                mAvi.hide();
                mLv.setAdapter(new MyAdapter(mContext, titleList, urlMap));
            }
        });
    }

    private List<String> getTitleList(Map<String, List<String>> urlMap) {
        List<String> titleList = new ArrayList<>();
        for (String s : urlMap.keySet()) {
            titleList.add(s);
        }
        return titleList;
    }

}
