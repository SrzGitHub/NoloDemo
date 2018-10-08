package com.unity.nolotest.tcp;

/**
 * Created by Mao on 2018/9/25.
 */

public class TcpEntity {

    private String app_id;
    private String session_id;

    public TcpEntity() {
    }

    public TcpEntity(String app_id, String session_id) {
        this.app_id = app_id;
        this.session_id = session_id;
    }


    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }
}
