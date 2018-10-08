package com.unity.tcpclient;

/**
 * Created by Administrator on 2018\9\28 0028.
 */

public interface LeTinCallback {

    /**
     * 获取Nolo指令
     * 获取NoloUdp数据
     * 获取Nolo错误消息
     *
     * @param code
     * @param bytes
     */
    void getNoloinstructions(int code, byte[] bytes);

    void getNoloUdpByte(int code, byte[] data);

    void setMesg(String error);
}
