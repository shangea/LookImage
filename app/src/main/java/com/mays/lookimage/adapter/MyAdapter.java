package com.mays.lookimage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.SuperKotlin.pictureviewer.ImagePagerActivity;
import com.SuperKotlin.pictureviewer.PictureConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ma on 2018/3/9.
 */

public class MyAdapter extends BaseAdapter {

    private final Context mContext;
    private List<String> titleList;
    private Map<String, List<String>> urlMap;

    public MyAdapter(Context context, List<String> titleList, Map<String, List<String>> urlMap) {
        mContext = context;
        this.titleList = titleList;
        this.urlMap = urlMap;
    }

    public void setDate(List<String> titleList, Map<String, List<String>> urlMap) {
        this.titleList = titleList;
        this.urlMap = urlMap;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return titleList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false);
        } else {
            view = convertView;
        }
        TextView tv = (TextView) view.findViewById(android.R.id.text1);
        tv.setTextSize(16);
        tv.setPadding(10, 10, 10, 10);
        tv.setText(titleList.get(position));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用方式
                PictureConfig config = new PictureConfig.Builder()
                        .setListData((ArrayList<String>) urlMap.get(titleList.get(position)))    //图片数据List<String> list
                        .setPosition(0)    //图片下标（从第position张图片开始浏览）
                        .setDownloadPath("pictureviewer")    //图片下载文件夹地址
                        .setIsShowNumber(true)//是否显示数字下标
                        .needDownload(true)    //是否支持图片下载
//                        .setPlacrHolder(R.mipmap.icon)	//占位符图片（图片加载完成前显示的资源图片，来源drawable或者mipmap）
                        .build();
                ImagePagerActivity.startActivity(mContext, config);
            }
        });
        return view;
    }
}
