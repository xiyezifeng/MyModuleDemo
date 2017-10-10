package com.yekong.common.subscribe;

import android.util.Log;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.yekong.common.base.BaseApplication;
import com.yekong.common.dialog.BaseDialog;
import com.yekong.common.utils.DeviceUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xigua on 2017/7/27.
 */

public class BaseObserve {

    public static void sendObserve(Observable observable , final BaseDialog dialog , BaseConsumer consumer ){
        if (!DeviceUtil.checkNetAndShowToast(BaseApplication.getApplication()) ) {
            Observable.just("456")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(consumer);
            return;
        }
        if (dialog != null) {
            dialog.show();
        }
        observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Function<Throwable, ObservableSource>() {
                    @Override
                    public ObservableSource apply(@NonNull Throwable throwable) throws Exception {
                        Log.e("observe", "error next");
                        return Observable.just("123")
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e("observe", "error");
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (dialog != null)dialog.close();
                    }
                })
                .subscribe(consumer);
    }
    public static void fragmentSendObserve(final RxFragment fragment, Observable observable  , final BaseDialog dialog , BaseConsumer consumer){
        if (!DeviceUtil.checkNetAndShowToast(fragment.getContext())) {
            Observable.just("456")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(consumer);
            return;
        }
        if (dialog != null) {
            dialog.show();
        }
        observable.compose(fragment.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Function<Throwable, ObservableSource>() {
                    @Override
                    public ObservableSource apply(@NonNull Throwable throwable) throws Exception {
                        Log.e("observe", "error next");
//                        ToastUtils.showToast(fragment.getContext(),"请求出错");
                        return Observable.just("123")
                                .compose(fragment.bindToLifecycle())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e("observe", "error");
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (dialog != null) dialog.close();
                    }
                })
                .subscribe(consumer);
    }
    public static void activitySendObserve(final RxAppCompatActivity activity, Observable observable , final BaseDialog dialog , BaseConsumer consumer){
        if (!DeviceUtil.checkNetAndShowToast(activity)  ) {
            Observable.just("456")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(consumer);
            return;
        }
        if (dialog != null) {
            dialog.show();
        }
        observable .compose(activity.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Function<Throwable, ObservableSource>() {
                    @Override
                    public ObservableSource apply(@NonNull Throwable throwable) throws Exception {
                        Log.e("observe", "error next");
//                        ToastUtils.showToast(activity,"请求出错");
                        return Observable.just("123")
                                .compose(activity.bindToLifecycle())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e("observe", "error");
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (dialog != null) dialog.close();
                    }
                })
                .subscribe(consumer);
    }
}
