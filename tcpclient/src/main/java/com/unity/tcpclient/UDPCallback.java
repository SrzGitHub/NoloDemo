package com.unity.tcpclient;

/**
 * Created by Administrator on 2018\9\28 0028.
 */

public class UDPCallback {
    public static LeTinCallback mLeTinCallback;

    //nolo根据指令回传UDP数组
    public static void setLetinByte(int type,byte[] bytes){
        mLeTinCallback.getNoloUdpByte(type,bytes);
    }
    //nolo向Letin发送指令
    public static void setNoloinstructions(int type,byte[] bytes){
        mLeTinCallback.getNoloinstructions(type,bytes);
    }
    //Nolo传回错误信息
    public static void setLetinError(String error){
        mLeTinCallback.setMesg(error);
    }
    public static void setOnClickLisetent(LeTinCallback leTinCallback){
        mLeTinCallback =leTinCallback;
    }

}
