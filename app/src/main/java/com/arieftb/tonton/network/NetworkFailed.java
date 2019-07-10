package com.arieftb.tonton.network;

import android.app.Application;
import android.content.Context;

import com.arieftb.tonton.R;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

public class NetworkFailed {
    public String getUserErrorMessage(Throwable e, Application application) {
        String message;

        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            message = getHttpMessage(httpException.code(), httpException.message(), application.getApplicationContext());
        } else if (e instanceof SocketTimeoutException) {
            message = application.getApplicationContext().getString(R.string.err_message_time_out);
        } else if (e instanceof UnknownHostException) {
            message = application.getApplicationContext().getString(R.string.err_message_no_connection);
        } else {
            message = application.getApplicationContext().getString(R.string.err_message_unknown, e.getLocalizedMessage(), 0);
        }

        return message;
    }


    private String getHttpMessage(int code, String localMessage, Context context) {
        String message;

        if (code > 500 && code <= 599) {
            message = context.getString(R.string.err_message_internal_server);
        } else {
            message = context.getString(R.string.err_message_unknown, localMessage, code);
        }

        return message;
    }
}
