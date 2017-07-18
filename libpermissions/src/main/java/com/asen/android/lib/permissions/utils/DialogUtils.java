package com.asen.android.lib.permissions.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.asen.android.lib.permissions.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Android原生对话框展示
 *
 * @author Asen
 * @version v2.0
 * @email 597356831@qq.com
 * @date 2017/4/28 11:45
 */
public final class DialogUtils {

    private static ProgressDialog progressDialog = null;

    private static ProgressDialog horizontalProgressDialog = null;

    private static List<Dialog> dialogList = new ArrayList<>();

    private DialogUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 展示 ProgressDialog
     *
     * @param activity 当前活动的Activity
     * @param message  需要显示的内容信息
     */
    public static void showProgressDialog(Activity activity, String message) {
        cancelProgressDialog();
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    /**
     * 取消展示 ProgressDialog
     */
    public static void cancelProgressDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing())
                progressDialog.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            progressDialog = null;
        }
    }

    /**
     * 展示 水平ProgressDialog
     *
     * @param activity 当前活动的Activity
     * @param progress 水平进度条显示的进度
     */
    public static void showHorizontalProgressDialog(Activity activity, int progress) {
        showHorizontalProgressDialog(activity, null, progress);
    }

    /**
     * 展示 水平ProgressDialog
     *
     * @param activity 当前活动的Activity
     * @param message  需要显示的内容信息
     * @param progress 水平进度条显示的进度
     */
    public static void showHorizontalProgressDialog(Activity activity, String message, int progress) {
        if (horizontalProgressDialog == null || !horizontalProgressDialog.isShowing()) {
            horizontalProgressDialog = new ProgressDialog(activity);
            if (!TextUtils.isEmpty(message)) {
                horizontalProgressDialog.setMessage(message);
            } else {
                horizontalProgressDialog.setMessage(null);
            }
            horizontalProgressDialog.setCancelable(false);
            horizontalProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            horizontalProgressDialog.show();
        }
        horizontalProgressDialog.setProgress(progress);
    }

    /**
     * 取消展示 水平ProgressDialog
     */
    public static void cancelHorizontalProgressDialog() {
        try {
            if (horizontalProgressDialog != null && horizontalProgressDialog.isShowing())
                horizontalProgressDialog.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            horizontalProgressDialog = null;
        }
    }

    // 取消Dialog
    private static void cancelDialog() {
        try {
            for (Dialog dialog : dialogList) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dialogList.clear();
        }
    }

    /**
     * 消息弹出框
     *
     * @param activity  当前活动的Activity
     * @param view      自定义控件
     * @param title     标题栏信息
     * @param message   消息栏信息
     * @param btnTexts  最多三条有效值的字符信息
     * @param listeners 点击事件的监听，与btnTexts对应
     */
    public static void showTitleMessageDialog(Activity activity, View view, CharSequence title, CharSequence message, CharSequence[] btnTexts, DialogInterface.OnClickListener[] listeners) {
        cancelDialog();

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (!TextUtils.isEmpty(title)) builder.setTitle(title);
        if (!TextUtils.isEmpty(message)) builder.setMessage(message);
        builder.setCancelable(false);
        if (view != null) builder.setView(view);

        if (btnTexts == null || btnTexts.length == 0) return;

        if (listeners != null) {
            int length = listeners.length;
            for (int i = 0; i < btnTexts.length; i++) {
                if (i == 0) {
                    builder.setPositiveButton(btnTexts[i], length > i ? listeners[i] : null);
                } else if (i == 1) {
                    builder.setNeutralButton(btnTexts[i], length > i ? listeners[i] : null);
                } else if (i == 2) {
                    builder.setNegativeButton(btnTexts[i], length > i ? listeners[i] : null);
                }
            }
        }
        dialogList.add(builder.show());
    }

    /**
     * 消息弹出框
     *
     * @param activity  当前活动的Activity
     * @param title     标题栏信息
     * @param message   消息栏信息
     * @param btnTexts  最多三条有效值的字符信息
     * @param listeners 点击事件的监听，与btnTexts对应
     */
    public static void showTitleMessageDialog(Activity activity, CharSequence title, CharSequence message, CharSequence[] btnTexts, DialogInterface.OnClickListener[] listeners) {
        showTitleMessageDialog(activity, null, title, message, btnTexts, listeners);
    }

    /**
     * 消息弹出框（只有确认按钮）
     *
     * @param activity 当前活动的Activity
     * @param title    标题栏信息
     * @param message  消息栏信息
     * @param listener 确认按钮的点击事件监听
     */
    public static void showAlertDialog(Activity activity, CharSequence title, CharSequence message, DialogInterface.OnClickListener listener) {
        showTitleMessageDialog(activity, title, message, new CharSequence[]{activity.getResources().getString(R.string.permissions_confirm)}, new DialogInterface.OnClickListener[]{listener});
    }

    /**
     * 消息弹出框
     *
     * @param activity 当前活动的Activity
     * @param view     自定义控件
     * @param title    标题栏信息
     * @param message  消息栏信息
     * @param listener 确认按钮的点击事件监听
     */
    public static void showTitleMessageDialog(Activity activity, View view, CharSequence title, CharSequence message, DialogInterface.OnClickListener listener) {
        showTitleMessageDialog(activity, view, title, message, new CharSequence[]{activity.getResources().getString(R.string.permissions_confirm), activity.getResources().getString(R.string.permissions_cancel)}, new DialogInterface.OnClickListener[]{listener});
    }

    /**
     * 消息弹出框
     *
     * @param activity 当前活动的Activity
     * @param title    标题栏信息
     * @param message  消息栏信息
     * @param listener 确认按钮的点击事件监听
     */
    public static void showTitleMessageDialog(Activity activity, CharSequence title, CharSequence message, DialogInterface.OnClickListener listener) {
        showTitleMessageDialog(activity, null, title, message, listener);
    }

    /**
     * 选择框
     *
     * @param itemsId  stringArray资源ID
     * @param tvShow   文本内容框
     * @param listener 点击选中监听
     */
    public static void showTextSelectDialog(Activity activity, int itemsId, TextView tvShow, DialogInterface.OnClickListener listener) {
        CharSequence[] strArr = activity.getResources().getStringArray(itemsId);
        showTextSelectDialog(activity, strArr, tvShow, listener);
    }

    /**
     * 选择框
     *
     * @param strList  字符串集合
     * @param tvShow   文本内容框
     * @param listener 点击选中监听
     */
    public static void showTextSelectDialog(Activity activity, List<CharSequence> strList, TextView tvShow, DialogInterface.OnClickListener listener) {
        CharSequence[] strArr = strList.toArray(new CharSequence[]{});
        showTextSelectDialog(activity, strArr, tvShow, listener);
    }

    /**
     * 选择框
     *
     * @param strArr   字符串数组
     * @param tvShow   文本内容框
     * @param listener 点击选中监听
     */
    public static void showTextSelectDialog(Activity activity, final CharSequence[] strArr, final TextView tvShow, final DialogInterface.OnClickListener listener) {
        cancelDialog();
        if (strArr == null) return;

        int index = indexOfStringArr(strArr, tvShow.getText().toString());

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setSingleChoiceItems(strArr, index == -1 ? 0 : index, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                tvShow.setText(strArr[which]);
                if (listener != null) {
                    listener.onClick(dialog, which);
                }
                dialog.dismiss();
            }
        });
        dialogList.add(builder.show());
    }

    private static int indexOfStringArr(CharSequence[] strArr, CharSequence str) {
        int index = -1;
        if (strArr != null && str != null) {
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                if (str.equals(strArr[i])) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

}
