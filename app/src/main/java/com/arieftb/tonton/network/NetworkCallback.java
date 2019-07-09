/*
 * Developed by arieftb on 7/9/19 1:52 PM.
 * Last Modified 7/9/19 1:52 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.network;

import rx.Subscriber;

public abstract class NetworkCallback<M> extends Subscriber<M> {

    abstract void onSuccess(M model);

    abstract void onFailure(int code, String message);

    abstract void onFinish();

    @Override
    public void onError(Throwable e) {
        onFailure(0, e.getLocalizedMessage());
    }

    @Override
    public void onCompleted() {
        onFinish();
    }

    @Override
    public void onNext(M m) {
        onSuccess(m);
    }
}
