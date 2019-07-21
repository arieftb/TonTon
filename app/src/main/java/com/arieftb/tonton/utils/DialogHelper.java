package com.arieftb.tonton.utils;

import android.app.Activity;
import android.app.Dialog;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;

public class DialogHelper {
    private String title;
    private String message;
    private String primaryButtonTitle;
    private String secondaryButtonTitle;
    private Dialog.OnClickListener primaryButtonListener;
    private Dialog.OnClickListener secondaryButtonListener;
    private final Activity context;
    private boolean showIcon;
    private boolean cancellable = true;

    public DialogHelper(Activity context) {
        this.context = context;
    }

    public DialogHelper setTitle(@StringRes int title) {
        this.title = context.getString(title);
        return this;
    }

    public DialogHelper setTitle(String title) {
        this.title = title;
        return this;
    }

    public DialogHelper setMessage(@StringRes int message) {
        this.message = context.getString(message);
        return this;
    }

    public DialogHelper setMessage(String message) {
        this.message = message;
        return this;
    }

    public DialogHelper setShowIcon() {
        showIcon = true;
        return this;
    }

    public DialogHelper setPrimaryButton(@StringRes int title, Dialog.OnClickListener listener) {
        primaryButtonTitle = context.getString(title);
        primaryButtonListener = listener;
        return this;
    }

    public DialogHelper setSecondaryButton(@StringRes int title, Dialog.OnClickListener listener) {
        secondaryButtonTitle = context.getString(title);
        secondaryButtonListener = listener;
        return this;
    }

    public DialogHelper setCancellable(boolean cancellable) {
        this.cancellable = cancellable;
        return this;
    }

    public AlertDialog create() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(message)
                .setCancelable(cancellable)
                .setPositiveButton(primaryButtonTitle, primaryButtonListener);

        return builder.create();
    }
}
