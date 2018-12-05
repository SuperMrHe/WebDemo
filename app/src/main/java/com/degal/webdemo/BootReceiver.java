package com.degal.webdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


/**
 * 作者：create by 18729 on 2018/12/4
 * 说明：
 */
public class BootReceiver extends BroadcastReceiver {

    public static final String ACTION_BOOT ="android.intent.action.BOOT_COMPLETED";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_BOOT)) {
            Intent start = new Intent(context, MainActivity.class);
            start.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(start);
        }
    }
}
