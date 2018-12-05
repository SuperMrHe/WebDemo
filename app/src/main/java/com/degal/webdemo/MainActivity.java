package com.degal.webdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.tvDp)
    TextView tvDp;
    @BindView(R.id.tvBuMen)
    TextView tvBuMen;
    @BindView(R.id.tvBM)
    TextView tvBM;
    @BindView(R.id.tvJob)
    TextView tvJob;
    @BindView(R.id.tvJB)
    TextView tvJB;
    @BindView(R.id.tvNumber)
    TextView tvNumber;
    @BindView(R.id.tvGH)
    TextView tvGH;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.tvResult)
    TextView tvResult;
    @BindView(R.id.tv_connect)
    TextView tvConnect;

    private RefreshReceiver mRefreshReceiver;
    //更新广播
    public static final String ACTION_REFRESH = "ACTION_REFRESH";
    //重启服务
    public static final String ACTION_RESTART_SERVER = "RESTART_SERVER";
    public static Context mContext;
    private Subscription mSubscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        startService(new Intent(this, WebServer.class));
        registBroadercast();
        netStatus();
    }

    /**
     * 注册广播
     */
    private void registBroadercast() {
        mRefreshReceiver = new RefreshReceiver();
        IntentFilter filter = new IntentFilter(ACTION_REFRESH);
        filter.addAction(ACTION_RESTART_SERVER);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mRefreshReceiver, filter);
    }

    /**
     * 网络状态
     */
    private void netStatus() {
        if (!NetUtils.isNetworkConnected(MainActivity.this)) {
            tvConnect.setVisibility(View.VISIBLE);
        } else {
            tvConnect.setVisibility(View.GONE);
            startService(new Intent(MainActivity.this, WebServer.class));
        }
    }

    /**
     * 刷新界面
     * @param user 刷卡人员
     */
    private void refresh(User user) {
        tvName.setText(user.getEmpname());
        tvDp.setText(user.getCompany());
        tvBM.setText(user.getDepartment());
        tvGH.setText(user.getIckh());
        tvJB.setText(user.getPosition());
        imageView3.setVisibility(View.VISIBLE);
        Glide.with(MainActivity.this).load(user.getPhoto()).error(R.drawable.default_round_head).into(imageView);
        imageView3.setImageResource(user.getFlag().equals("1") ? R.drawable.ic_ok : R.drawable.ic_error);
        tvResult.setText(user.getFlag().equals("1") ? "通过" : "异常");
        unSub();
        mSubscriber = Observable.timer(5, TimeUnit.MINUTES)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        initUser();
                    }
                });
    }

    /**
     * 界面初始化
     */
    private void initUser() {
        tvName.setText("");
        tvDp.setText("");
        tvBM.setText("");
        tvGH.setText("");
        tvJB.setText("");
        Glide.with(MainActivity.this).load(R.drawable.default_round_head).error(R.drawable.default_round_head).into(imageView);
        imageView3.setVisibility(View.INVISIBLE);
        tvResult.setText("");
    }

    private void unSub() {
        if (null != mSubscriber) {
            mSubscriber.unsubscribe();
        }
    }


    @Override
    protected void onDestroy() {
        unSub();
        super.onDestroy();
        if (null != mRefreshReceiver) {
            unregisterReceiver(mRefreshReceiver);
        }
    }


    class RefreshReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ACTION_REFRESH)) {
                String data = intent.getStringExtra("user");
                if (!TextUtils.isEmpty(data)) {
                    User user = new Gson().fromJson(data, User.class);
                    if (null != user) {
                        refresh(user);
                    }
                }
            } else if (intent.getAction().equals(ACTION_RESTART_SERVER)) {
                startService(new Intent(MainActivity.this, WebServer.class));
            } else if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                netStatus();
            }
        }
    }

}