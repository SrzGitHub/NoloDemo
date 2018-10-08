package com.unity.nolotest.tcp;

import android.app.Application;

/**
 * Created by Mao on 2018/9/25.
 */

public class TcpJson {


    private String requestSerialNumber;//请求序列号
    private String timeStamp;//时间戳
    private String deviceId;//设备ID
    private String  userId;//用户ID
    private String businessId;//商户ID
    private String applicationId;//应用ID

    public TcpJson() {
    }

    public TcpJson(String requestSerialNumber, String timeStamp, String deviceId, String userId, String businessId, String applicationId) {
        this.requestSerialNumber = requestSerialNumber;
        this.timeStamp = timeStamp;
        this.deviceId = deviceId;
        this.userId = userId;
        this.businessId = businessId;
        this.applicationId = applicationId;
    }

    public String getRequestSerialNumber() {
        return requestSerialNumber;
    }

    public void setRequestSerialNumber(String requestSerialNumber) {
        this.requestSerialNumber = requestSerialNumber;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
}
