/*
 * Developed by arieftb on 7/9/19 1:52 PM.
 * Last Modified 7/9/19 1:52 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.network;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DefaultSubscriber;

public abstract class NetworkCallback<M> implements Observer<M> {

    public abstract void onSuccess(M model);

    public abstract void onFailure(int code, String message);

    public abstract void onFinish();

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onError(Throwable e) {
        onFailure(0, e.getLocalizedMessage());
    }

    @Override
    public void onComplete() {
        onFinish();
    }

    @Override
    public void onNext(M m) {
        onSuccess(m);
    }
}
