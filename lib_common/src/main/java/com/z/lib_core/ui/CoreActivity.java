package com.z.lib_core.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.z.lib_core.callbak.Callback;
import com.z.lib_core.utils.RxLifeCycleUtils;
import com.z.lib_core.utils.eventbus.EventMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Creator:  chee
 * Date：2020/9/3 - 15:04
 * Desc：
 */
public abstract class CoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        if (registerEventBust()) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (registerEventBust()) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        init();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        init();
    }

    protected abstract int getLayoutId();

    protected abstract void init();

    /**
     * 接收eventbus事件
     *
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEvent(EventMessage message) {
    }

    /**
     * 是否注册eventbus
     *
     * @return
     */
    protected Boolean registerEventBust() {
        return false;
    }


    /**
     * 延时任务
     *
     * @param millisecond
     * @param callback
     */
    protected void delayedTask(int millisecond, final Callback callback) {
        Observable.timer(millisecond, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(RxLifeCycleUtils.<Long>autoDispose(this))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        callback.onResult(aLong);
                    }
                });
    }

    public void toastShort(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void toastLong(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}
