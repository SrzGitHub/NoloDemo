package com.unity.tcpclient;

/**
 * 接收数据callback
 */

public abstract class RequestCallback implements AbsBaseCallback {
    public abstract void onTimeout();
}
