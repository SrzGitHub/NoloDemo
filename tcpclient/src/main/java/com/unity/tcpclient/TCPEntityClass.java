package com.unity.tcpclient;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.List;

import static com.unity.tcpclient.TCP_Api.IP;
import static com.unity.tcpclient.TCP_Api.LETIN;
import static com.unity.tcpclient.TCP_Api.PORT;

/**
 * Created by Administrator on 2018\9\27 0027.
 */

public class TCPEntityClass implements LeTinCallback {

    private static final String TAG = "TCPEntityClass";
    public Context mContext;

    private DatagramChannel channel;
    private SharedPreferences letin;
    private TCPClient tcpClient;
    private String TCP_IP = "192.168.10.75";
    private int TCP_PORT = 1010;
    private int OM = 0;
    private int BUF = 1024 * 1024;
    private int variables = 0;
    private ByteBuffer buff = ByteBuffer.allocate(BUF);
    private boolean isWhile = true;
    //接受消息线程
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            ByteBuffer buffer = ByteBuffer.allocate(BUF);
            byte b[] ;
            while (true) {
                buffer.clear();
                SocketAddress socketAddress = null;
                try {
                    socketAddress = channel.receive(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (socketAddress != null) {
                    int position = buffer.position();
                    b = new byte[position];
                    buffer.flip();
                    for (int i = 0; i < position; ++i) {
                        b[i] = buffer.get();

                    }
                    try {
                        Log.e(TAG, "接收到UDP数据: "+new String(b,"UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    Callbacks.getByte(b);//接收到的UDP数据 发送给 Nolo-c
                }

            }




        }
    };
    private Thread thread = new Thread(runnable);

    public TCPEntityClass(Context context) {
        this.mContext = context;
        UDPCallback.setOnClickLisetent(this);//初始化接口
        try {
            channel = DatagramChannel.open();
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
            e.printStackTrace();
        }
        //创建本地存储
        letin = mContext.getSharedPreferences(LETIN, Context.MODE_PRIVATE);
        tcpClient = TCPClient.build();//创建 TCPClient单例
        tcp1Client();//TCP请求 Letin-S  IP_PORT
    }


    @Override
    public void getNoloinstructions(int code, byte[] bytes) {

        try {
            Log.e(TAG, "bytes: " + new String(bytes,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //取出本地存储的  ip和端口
        String ip = letin.getString(IP, "");
        int anInt = letin.getInt(PORT, 0);
        if (code == 0 && !ip.isEmpty() && anInt > 0) {
            //如果 code等于0异或ip不为空异或端口大于0 则执行UDP请求（三个条件必须符合 才会进入if
            Log.e(TAG, "1111111111111111111111: ");
            try {
                //发送udp请求方法
                sendUDPMessage(channel, bytes, ip, anInt);
            } catch (IOException e) {
                Log.e(TAG, "catch0: " + e.getMessage());
                e.printStackTrace();
            }
            if (OM == 0) {
                thread.start();//开启UDP接收消息子线程（不可重复执行
                OM = 1;
            }


        } else if (code == 1 && !ip.isEmpty() && anInt > 0) {
            //如果 code等于1异或ip不为空异或端口大于0 则执行UDP请求（三个条件必须符合 才会进入if
            Log.e(TAG, "222222222222222222222222222222: ");
            try {
                sendUDPMessage(channel, bytes, ip, anInt);
            } catch (IOException e) {
                Log.e(TAG, "catch1: " + e.getMessage());
                e.printStackTrace();
            }
            if (OM == 0) {
                thread.start();
                OM = 1;//开启UDP接收消息子线程（不可重复执行
            }

        }
    }


    //第一次TCP请求 IP 端口
    public void tcp1Client() {

        tcpClient = TCPClient.build()
                .server(TCP_IP, TCP_PORT)//请求letin-s ip和端口
                .connTimeout(10 * 1000);//设置请求超时时间
        tcpClient.request("100001100118601208054".getBytes(), 8000, new RequestCallback() {
            @Override
            public void onTimeout() {
                Callbacks.getLetinFailListenet();
                Log.e(TAG, "onTimeout: 网络连接失败");
            }

            @Override
            public void onFail(Throwable throwable) {
                Callbacks.getThrowable(throwable);
                Log.e(TAG, "onFail: 错误信息3" + throwable.toString());
            }
        }, new ResponseCallback() {
            @Override
            public void onRec() {

                MyRecParse recParse = new MyRecParse();
                List<String> parse = recParse.parse();
                Log.e(TAG, "接收json: " + parse.get(0));
                Gson gson = new Gson();
                TCPEntity tcpEntity = gson.fromJson(parse.get(0), TCPEntity.class);
                SharedPreferences.Editor edit = letin.edit();
                if (!tcpEntity.getIP().isEmpty() && tcpEntity.getPORT() > 0) {
                    //  ip和端口不为空  则存储本地
                    edit.putInt(PORT, tcpEntity.getPORT());
                    edit.putString(IP, tcpEntity.getIP());
                    edit.commit();
                    tcpClient.closeTcp();//断开当前连接
                    Callbacks.getLetinSucessListenet(1);//通知拿到 IP 断开连接
                } else {
                    Log.e(TAG, "请求IP失败");
                }
            }

            @Override
            public void onFail(Throwable throwable) {
                //异常消息
                Callbacks.getThrowable(throwable);
            }
        });
    }


    @Override
    public void getNoloUdpByte(int code, byte[] data) {
        //获取对方UDP传输过来的数据 传输给NOLO（废弃
//        Callbacks.getByte(data);
//        try {
//            Log.e(TAG, "getNoloUdpByte: " + code + "\n" + new String(data, "UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            Log.e(TAG, "UnsupportedEncodingException: " + e.getMessage());
//            e.printStackTrace();
//        }
    }

    @Override
    public void setMesg(String error) {
        //获取nolo-c传来的错误信息
        Log.e(TAG, "setMesg: " + error);
    }

    /**
     * @param channel UDP
     * @param mes     Nolo-c 指令字节数组
     * @param ips     Nolo-s ip地址
     * @param port    Nolo-s 端口
     * @throws IOException
     */

    public void sendUDPMessage(DatagramChannel channel, byte[] mes, final String ips, final int port) throws IOException {
        if (mes == null || mes.length == 0) {
            return;
        }
        buff.clear();//清除缓冲区
        Log.e(TAG, "mes: "+new String(mes,"UTF-8"));//指令消息
        buff.put(mes);//将指令放进缓冲区
        buff.flip();//翻转 buffer 将buffer置为待写入状态
        try {
            //携带有缓冲指令的buffer  进行UDP请求
            int send = channel.send(buff, new InetSocketAddress(ips, port));
            Callbacks.getNoloSSucessListenet();//通知Nolo-c 连接Nolo-s成功
            Log.e(TAG, "UDP发送执行完毕: ++++" + send);
        } catch (IOException e) {
            Log.e(TAG, "catch2: " + e.getMessage());
            e.printStackTrace();
        }


    }


}
