package com.unity.nolodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.unity.tcpclient.Callbacks;
import com.unity.tcpclient.NoloCallback;
import com.unity.tcpclient.TCPEntityClass;
import com.unity.tcpclient.UDPCallback;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements NoloCallback {


    private static final String TAG = "MainActivitys";
    //连接letin-S成功并断开连接
    String str = "这不是指令";
    private int OOM = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new TCPEntityClass(this);
        Callbacks.setOnClickListenet(this);



            CountDown(60);


    }


    /**
     * 倒计时
     *
     * @param min 倒计时间（分钟）
     */
    public void CountDown(int min) {

        //开始时间
        long start = System.currentTimeMillis();
        //结束时间
        final long end = start + min * 60 * 1000;

        final Timer timer = new Timer();
        //延迟0毫秒（即立即执行）开始，每隔1000毫秒执行一次
        timer.schedule(new TimerTask() {
            public void run() {
                Log.e("MainActivity", "此处实现倒计时，指定时长内，每隔1秒执行一次该任务");
//                if (OOM==0){

//                UDPCallback.setNoloinstructions(0, str.getBytes());

                Log.e(TAG, "run: "+"lpico-letin".getBytes());

//                    OOM=1;
//                }else {
//                    UDPCallback.setNoloinstructions(1,str.getBytes());
//                }


            }
        }, 0, 5);
        //计时结束时候，停止全部timer计时计划任务
        timer.schedule(new TimerTask() {
            public void run() {
                timer.cancel();
            }

        }, new Date(end));
    }


    @Override
    public void onConnLetinFail() {
        //连接letin-S失败
        Log.e(TAG, "onConnLetinFail:连接letin-S失败 ");
    }

    @Override
    public void onConnLetinSucess(int code) {
        //连接letin-S成功
        Log.e(TAG, "onConnLetinSucess: " + code);

//        CountDown(60);
    }

    @Override
    public void onConnNoloSFail() {
        //连接nolo失败
        Log.e(TAG, "onConnNoloSFail:连接Nolo-S失败 ");
    }

    @Override
    public void onConnNoloSSucess() {
        //连接nolo成功
        Log.e(TAG, "onConnNoloSSucess: 连接Nolo-S成功");
    }

    @Override
    public void onThrowable(Throwable throwable) {
        //错误信息
        Log.e(TAG, "onThrowable:错误信息 \n" + throwable.toString());
    }

    @Override
    public void onReceivedData(byte[] datas) {
        //letin传过来的nolo-s数据
        OOM++;
        try {
            String str = new String(datas, "UTF-8");
            Log.e(TAG, "接收到letin-C回传给Nolo-C的 Nolo-S数据 次数 " + OOM + "长度：" + datas.length + "---------" + str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
