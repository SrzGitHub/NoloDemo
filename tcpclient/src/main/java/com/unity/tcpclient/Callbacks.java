package com.unity.tcpclient;


/**
 * Created by Administrator on 2018\9\27 0027.
 */

public class Callbacks {


    public static NoloCallback mNoloCallback;


    public static void setOnClickListenet(NoloCallback noloCallback) {
        mNoloCallback = noloCallback;
    }


    //letin-C回传NoloUdp数组
    public static void getByte(byte[] bytes) {
        mNoloCallback.onReceivedData(bytes);
    }

    //letin连接Nolo失败
    public static void getNoloSFailListenet() {
        mNoloCallback.onConnNoloSFail();
    }

    //letin连接Nolo成功
    public static void getNoloSSucessListenet() {
        mNoloCallback.onConnNoloSSucess();
    }

    //letin-C连接letin-s失败
    public static void getLetinFailListenet() {
        mNoloCallback.onConnLetinFail();
    }

    //letin-C连接letin-s成功
    public static void getLetinSucessListenet(int code) {
        mNoloCallback.onConnLetinSucess(code);

    }

    ////letin回传连接成功后 错误信息
    public static void getThrowable(Throwable throwable) {
        mNoloCallback.onThrowable(throwable);
    }


}
