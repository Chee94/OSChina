package com.z.lib_core.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
 * Date：2020/9/11 - 17:04
 * Desc：
 */
public abstract class CoreFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (registerEventBust()) {
            EventBus.getDefault().register(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    /**
     * 布局id
     *
     * @return
     */
    public abstract int getLayoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (registerEventBust()) {
            EventBus.getDefault().unregister(this);
        }
    }

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


}
