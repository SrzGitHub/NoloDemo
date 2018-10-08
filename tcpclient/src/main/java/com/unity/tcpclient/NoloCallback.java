package com.unity.tcpclient;

public interface NoloCallback {

    void onConnLetinFail();//连接兰亭服务失败

    void onConnLetinSucess(int code);//连接兰亭服务成功

    void onConnNoloSFail();//连接nolo服务失败

    void onConnNoloSSucess();//连接nolo服务成功

    //Throwable throwable
    void onThrowable(Throwable throwable);

    void onReceivedData(byte[] datas);
}
