package com.degal.webdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.yanzhenjie.andserver.AndServer;
import com.yanzhenjie.andserver.Server;

/**
 * Created by user:hsj
 * data:on 2018/11/15 0015.
 * class:服务器
 * 修改人员：
 * 修改时间：
 * 修改内容：
 */
public class WebServer extends Service {

   private Server mAndServer = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        mAndServer = AndServer.serverBuilder()
                .inetAddress(NetUtils.getLocalIPAddress())
                .port(5000)
                .listener(new Server.ServerListener() {
                    @Override
                    public void onStarted() {
                        try {
                            Toast.makeText(WebServer.this, "开启服务器 IP：" + NetUtils.getLocalIPAddress().getHostAddress(), Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(WebServer.this, "请检查网络是否连接！！", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onStopped() {
                    }

                    @Override
                    public void onException(Exception e) {
                    }
                })
                .build();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mAndServer.startup();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Intent intent = new Intent(MainActivity.ACTION_RESTART_SERVER);
        sendBroadcast(intent);
        super.onDestroy();
        if (!mAndServer.isRunning()) {
            mAndServer.shutdown();
        }
    }
}
