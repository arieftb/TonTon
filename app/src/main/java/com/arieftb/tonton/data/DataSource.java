/*
 * Developed by arieftb on 7/9/19 10:39 PM.
 * Last Modified 7/9/19 10:39 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.data;

import androidx.lifecycle.LiveData;

public interface DataSource {
    void onLoadData();
    LiveData<Boolean> isLoading();
    LiveData<String> onError();
}
