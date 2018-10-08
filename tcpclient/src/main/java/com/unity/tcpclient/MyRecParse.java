package com.unity.tcpclient;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义转换规则
 * 自定义接收数据的协议
 * 接收到的数据必须以$$(2个字节)开头 数据的大小（short,byte 2个字节 数据大小=2+2+数据大小）
 */

public class MyRecParse extends BaseRecParse<String> {
    private static final String TAG = "MyRecdataFilter";


    @Override
    public List<String> parse() {
        ArrayList<String> list = new ArrayList<>();
        byte[] baseData = getBaseData();//总数据
        try {
            list.add(new String(baseData, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return list;
    }
}
