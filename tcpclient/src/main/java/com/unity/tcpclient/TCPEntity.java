package com.unity.tcpclient;

/**
 * Created by Administrator on 2018\9\27 0027.
 */

public class TCPEntity {


    /**
     * RSP_CODE : 000000
     * IP : 192.168 .10 .75
     * PORT : 10099
     */

    private String RSP_CODE;
    private String IP;
    private int PORT;

    public TCPEntity() {
    }

    public TCPEntity(String RSP_CODE, String IP, int PORT) {
        this.RSP_CODE = RSP_CODE;
        this.IP = IP;
        this.PORT = PORT;
    }

    public String getRSP_CODE() {
        return RSP_CODE;
    }

    public void setRSP_CODE(String RSP_CODE) {
        this.RSP_CODE = RSP_CODE;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getPORT() {
        return PORT;
    }

    public void setPORT(int PORT) {
        this.PORT = PORT;
    }
}
