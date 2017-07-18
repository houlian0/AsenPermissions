package com.asen.android.lib.permissions.core;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;


import com.asen.android.lib.permissions.BaseActivity;
import com.asen.android.lib.permissions.BaseFragment;
import com.asen.android.lib.permissions.R;
import com.asen.android.lib.permissions.utils.DialogUtils;
import com.asen.android.lib.permissions.utils.VersionUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 动态权限管理类
 *
 * @author Asen
 * @version v2.0
 * @email 597356831@qq.com
 * @date 2017/5/15 16:51
 */
public class PermissionManager {

    /**
     * 权限组-日历
     */
    public static final String PERMISSION_GROUP_CALENDAR = Manifest.permission_group.CALENDAR;
    /**
     * 读取日历
     */
    public static final String PERMISSION_READ_CALENDAR = Manifest.permission.READ_CALENDAR;
    /**
     * 改写日历
     */
    public static final String PERMISSION_WRITE_CALENDAR = Manifest.permission.WRITE_CALENDAR;

    /**
     * 权限组-相机
     */
    public static final String PERMISSION_GROUP_CAMERA = Manifest.permission_group.CAMERA;
    /**
     * 开启相机
     */
    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;

    /**
     * 权限组-联系人
     */
    public static final String PERMISSION_GROUP_CONTACTS = Manifest.permission_group.CONTACTS;
    /**
     * 读取联系人
     */
    public static final String PERMISSION_READ_CONTACTS = Manifest.permission.READ_CONTACTS;
    /**
     * 写入联系人
     */
    public static final String PERMISSION_WRITE_CONTACTS = Manifest.permission.WRITE_CONTACTS;
    /**
     * 获取联系人
     */
    public static final String PERMISSION_GET_ACCOUNTS = Manifest.permission.GET_ACCOUNTS;

    /**
     * 权限组-位置
     */
    public static final String PERMISSION_GROUP_LOCATION = Manifest.permission_group.LOCATION;
    /**
     * 获取设备位置信息（粗略）
     */
    public static final String PERMISSION_ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    /**
     * 获取设备位置信息（精确）
     */
    public static final String PERMISSION_ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;

    /**
     * 权限组-麦克风
     */
    public static final String PERMISSION_GROUP_MICROPHONE = Manifest.permission_group.MICROPHONE;
    /**
     * 开启录音
     */
    public static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;

    /**
     * 权限组-电话
     */
    public static final String PERMISSION_GROUP_PHONE = Manifest.permission_group.PHONE;
    /**
     * 读取手机状态
     */
    public static final String PERMISSION_READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    /**
     * 打电话
     */
    public static final String PERMISSION_CALL_PHONE = Manifest.permission.CALL_PHONE;
    /**
     * 读取手机log日志
     */
    public static final String PERMISSION_READ_CALL_LOG = Manifest.permission.READ_CALL_LOG;
    /**
     * 写入手机log日志
     */
    public static final String PERMISSION_WRITE_CALL_LOG = Manifest.permission.WRITE_CALL_LOG;
    /**
     * 允许使用语音邮件
     */
    public static final String PERMISSION_ADD_VOICEMAIL = Manifest.permission.ADD_VOICEMAIL;
    /**
     * 允许使用SIP视频服务
     */
    public static final String PERMISSION_USE_SIP = Manifest.permission.USE_SIP;
    /**
     * 处理拨出电话
     */
    public static final String PERMISSION_PROCESS_OUTGOING_CALLS = Manifest.permission.PROCESS_OUTGOING_CALLS;

    /**
     * 权限组-传感器
     */
    public static final String PERMISSION_GROUP_SENSORS = Manifest.permission_group.SENSORS;
    /**
     * 操作传感器
     */
    public static final String PERMISSION_BODY_SENSORS = Manifest.permission.BODY_SENSORS;

    /**
     * 权限组-短信
     */
    public static final String PERMISSION_GROUP_SMS = Manifest.permission_group.SMS;
    /**
     * 发送短信
     */
    public static final String PERMISSION_SEND_SMS = Manifest.permission.SEND_SMS;
    /**
     * 接收短信
     */
    public static final String PERMISSION_RECEIVE_SMS = Manifest.permission.RECEIVE_SMS;
    /**
     * 读取短信
     */
    public static final String PERMISSION_READ_SMS = Manifest.permission.READ_SMS;
    /**
     * 接收 WAP PUSH 信息
     */
    public static final String PERMISSION_RECEIVE_WAP_PUSH = Manifest.permission.RECEIVE_WAP_PUSH;
    /**
     * 接收彩信
     */
    public static final String PERMISSION_RECEIVE_MMS = Manifest.permission.RECEIVE_MMS;

    /**
     * 权限组-存储
     */
    public static final String PERMISSION_GROUP_STORAGE = Manifest.permission_group.STORAGE;
    /**
     * 读取SD卡
     */
    public static final String PERMISSION_READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    /**
     * 写入SD卡
     */
    public static final String PERMISSION_WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    @StringDef({
            PERMISSION_READ_CALENDAR, PERMISSION_WRITE_CALENDAR,
            PERMISSION_CAMERA,
            PERMISSION_READ_CONTACTS, PERMISSION_WRITE_CONTACTS, PERMISSION_GET_ACCOUNTS,
            PERMISSION_ACCESS_FINE_LOCATION, PERMISSION_ACCESS_COARSE_LOCATION,
            PERMISSION_RECORD_AUDIO,
            PERMISSION_READ_PHONE_STATE, PERMISSION_CALL_PHONE, PERMISSION_READ_CALL_LOG, PERMISSION_WRITE_CALL_LOG, PERMISSION_ADD_VOICEMAIL, PERMISSION_USE_SIP, PERMISSION_PROCESS_OUTGOING_CALLS,
            PERMISSION_BODY_SENSORS,
            PERMISSION_SEND_SMS, PERMISSION_RECEIVE_SMS, PERMISSION_READ_SMS, PERMISSION_RECEIVE_WAP_PUSH, PERMISSION_RECEIVE_MMS,
            PERMISSION_READ_EXTERNAL_STORAGE, PERMISSION_WRITE_EXTERNAL_STORAGE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PermissionName {
    }

    @StringDef({
            PERMISSION_GROUP_CALENDAR,
            PERMISSION_GROUP_CAMERA,
            PERMISSION_GROUP_CONTACTS,
            PERMISSION_GROUP_LOCATION,
            PERMISSION_GROUP_MICROPHONE,
            PERMISSION_GROUP_PHONE,
            PERMISSION_GROUP_SENSORS,
            PERMISSION_GROUP_SMS,
            PERMISSION_GROUP_STORAGE
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface PermissionGroupName {

    }

    private Context context;


    private PermissionResultListener resultListener;

    /**
     * 构造函数
     *
     * @param context Android上下文
     */
    public PermissionManager(Context context, PermissionResultListener resultListener) {
        this.context = context;
        this.resultListener = resultListener;
    }

    // 获取权限别名
    private String getPermissionAlias(@PermissionName String permission) {
        // 日历
        if (PERMISSION_READ_CALENDAR.equals(permission) || PERMISSION_WRITE_CALENDAR.equals(permission)) {
            return context.getResources().getString(R.string.permissions_permission_group_calendar);
        }
        // 相机
        else if (PERMISSION_CAMERA.equals(permission)) {
            return context.getResources().getString(R.string.permissions_permission_group_camera);
        }
        // 通讯录
        else if (PERMISSION_READ_CONTACTS.equals(permission) || PERMISSION_WRITE_CONTACTS.equals(permission) || PERMISSION_GET_ACCOUNTS.equals(permission)) {
            return context.getResources().getString(R.string.permissions_permission_group_contacts);
        }
        // 位置信息
        else if (PERMISSION_ACCESS_FINE_LOCATION.equals(permission) || PERMISSION_ACCESS_COARSE_LOCATION.equals(permission)) {
            return context.getResources().getString(R.string.permissions_permission_group_location);
        }
        // 麦克风
        else if (PERMISSION_RECORD_AUDIO.equals(permission)) {
            return context.getResources().getString(R.string.permissions_permission_group_microphone);
        }
        // 电话
        else if (PERMISSION_READ_PHONE_STATE.equals(permission) || PERMISSION_CALL_PHONE.equals(permission) || PERMISSION_READ_CALL_LOG.equals(permission) ||
                PERMISSION_WRITE_CALL_LOG.equals(permission) || PERMISSION_ADD_VOICEMAIL.equals(permission) || PERMISSION_USE_SIP.equals(permission) ||
                PERMISSION_PROCESS_OUTGOING_CALLS.equals(permission)) {
            return context.getResources().getString(R.string.permissions_permission_group_phone);
        }
        // 传感器
        else if (PERMISSION_BODY_SENSORS.equals(permission)) {
            return context.getResources().getString(R.string.permissions_permission_group_sensors);
        }
        // 短信
        else if (PERMISSION_SEND_SMS.equals(permission) || PERMISSION_RECEIVE_SMS.equals(permission) || PERMISSION_READ_SMS.equals(permission) ||
                PERMISSION_RECEIVE_WAP_PUSH.equals(permission) || PERMISSION_RECEIVE_MMS.equals(permission)) {
            return context.getResources().getString(R.string.permissions_permission_group_sms);
        }
        // 存储空间
        else if (PERMISSION_READ_EXTERNAL_STORAGE.equals(permission) || PERMISSION_WRITE_EXTERNAL_STORAGE.equals(permission)) {
            return context.getResources().getString(R.string.permissions_permission_group_storage);
        }
        //
        else {
            return null;
        }
    }

    // 获取权限组别名
    private String getPermissionGroupAlias(@PermissionGroupName String permissionGroup) {
        // 日历
        if (PERMISSION_GROUP_CALENDAR.equals(permissionGroup)) {
            return context.getResources().getString(R.string.permissions_permission_group_calendar);
        }
        // 相机
        else if (PERMISSION_GROUP_CAMERA.equals(permissionGroup)) {
            return context.getResources().getString(R.string.permissions_permission_group_camera);
        }
        // 通讯录
        else if (PERMISSION_GROUP_CONTACTS.equals(permissionGroup)) {
            return context.getResources().getString(R.string.permissions_permission_group_contacts);
        }
        // 位置信息
        else if (PERMISSION_GROUP_LOCATION.equals(permissionGroup)) {
            return context.getResources().getString(R.string.permissions_permission_group_location);
        }
        // 麦克风
        else if (PERMISSION_GROUP_MICROPHONE.equals(permissionGroup)) {
            return context.getResources().getString(R.string.permissions_permission_group_microphone);
        }
        // 电话
        else if (PERMISSION_GROUP_PHONE.equals(permissionGroup)) {
            return context.getResources().getString(R.string.permissions_permission_group_phone);
        }
        // 传感器
        else if (PERMISSION_GROUP_SENSORS.equals(permissionGroup)) {
            return context.getResources().getString(R.string.permissions_permission_group_sensors);
        }
        // 短信
        else if (PERMISSION_GROUP_SMS.equals(permissionGroup)) {
            return context.getResources().getString(R.string.permissions_permission_group_sms);
        }
        // 存储空间
        else if (PERMISSION_GROUP_STORAGE.equals(permissionGroup)) {
            return context.getResources().getString(R.string.permissions_permission_group_storage);
        }
        //
        else {
            return null;
        }
    }

    // 获取权限别名集合
    private String[] getPermissionsAliasArr(@PermissionName String... permissions) {
        List<String> aliasList = new ArrayList<>();
        for (String permission : permissions) {
            String alias = getPermissionAlias(permission);
            if (alias != null && !aliasList.contains(alias)) {
                aliasList.add(alias);
            }
        }
        return aliasList.toArray(new String[aliasList.size()]);
    }

    // 获取权限组别名集合
    private String[] getPermissionGroupsAliasArr(@PermissionGroupName String... permissionGroups) {
        List<String> aliasList = new ArrayList<>();
        for (String permissionGroup : permissionGroups) {
            String alias = getPermissionGroupAlias(permissionGroup);
            if (alias != null && !aliasList.contains(alias)) {
                aliasList.add(alias);
            }
        }
        return aliasList.toArray(new String[aliasList.size()]);
    }

    // 获取别名内容
    private String getAliasContent(String[] aliasArr) {
        StringBuilder sb = null;
        for (int i = 0; i < aliasArr.length; i++) {
            String alias = aliasArr[i];
            if (sb == null) {
                sb = new StringBuilder(alias);
            } else {
                if (i == aliasArr.length - 1) {
                    sb.append(context.getString(R.string.permissions_text_add)).append(alias);
                } else {
                    sb.append(context.getString(R.string.permissions_text_separator)).append(alias);
                }
            }
        }
        return sb == null ? "" : sb.toString();
    }


    /**
     * 检查权限是否打开
     *
     * @param context    Android上下文
     * @param permission 权限名称
     * @return true，权限已经允许；false，权限未经允许
     */
    private boolean checkPermission(Context context, @PermissionName String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 获取所填权限集合中未授权的所有权限
     *
     * @param context     Android上下文
     * @param permissions 权限名称集合
     * @return 返回未授权的所有权限
     */
    public String[] getUnauthorizedPermission(Context context, @PermissionName String... permissions) {
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (!checkPermission(context, permission)) {
                permissionList.add(permission);
            }
        }
        return permissionList.toArray(new String[permissionList.size()]);
    }

    /**
     * 请求权限
     *
     * @param object      BaseActivity或者BaseFragment
     * @param requestCode 请求代码
     * @param permission  请求的权限
     */
    public void requestPermission(Object object, int requestCode, @PermissionName String permission) {
        requestPermissions(object, requestCode, permission);
    }

    /**
     * 请求权限
     *
     * @param object        BaseActivity或者BaseFragment
     * @param requestCode   请求码
     * @param permissionArr 请求的权限集合
     */
    public void requestPermissions(final Object object, int requestCode, @PermissionName String... permissionArr) {
        // Android M 以下的版本，不需要申请动态权限
        if (!VersionUtils.hasMarshmallow()) {
            onPermissionGranted(true, requestCode, permissionArr);
            return;
        }
        // 获取所列权限中所有未授权的权限
        @PermissionName String[] permissions = getUnauthorizedPermission(context, permissionArr);
        if (permissions.length == 0) {
            onPermissionGranted(true, requestCode, permissionArr);
            return;
        }
        // 判断未授权的权限中是否存在不提示授权信息的权限
        boolean shouldShowRationale = shouldShowRequestPermissionsRationale(object, permissions);

        if (!shouldShowRationale) {
            showSettingSelectDialog(getActivity(object), String.format(Locale.getDefault(), context.getString(R.string.permissions_permission_tip), getAliasContent(getPermissionsAliasArr(permissions))),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            gotoSettingActivity(object);
                        }
                    });

        } else {
            if (object instanceof BaseActivity) {
                ActivityCompat.requestPermissions((BaseActivity) object, permissions, requestCode);
            } else if (object instanceof BaseFragment) {
                ((BaseFragment) object).requestPermissions(permissions, requestCode);
            }
        }
    }

    /**
     * 提示未授予的权限
     *
     * @param object        BaseActivity或者BaseFragment
     * @param permissionArr 未授予的权限集合
     */
    public void hintNoPermissions(Object object, @PermissionName String... permissionArr) {
        DialogUtils.showAlertDialog(getActivity(object), null, String.format(Locale.getDefault(), context.getString(R.string.permissions_no_permission_tip), getAliasContent(getPermissionsAliasArr(permissionArr))), null);
    }

    // 显示是否进入设置的dialog
    private void showSettingSelectDialog(Activity activity, CharSequence message, DialogInterface.OnClickListener listener) {
        DialogUtils.showTitleMessageDialog(activity, null, message,
                new CharSequence[]{
                        context.getString(R.string.permissions_setting),
                        context.getString(R.string.permissions_cancel)
                },
                new DialogInterface.OnClickListener[]{
                        listener
                });
    }

    /**
     * 获取Activity
     *
     * @param object BaseActivity或者BaseFragment
     * @return 当前的Activity对象
     */
    private Activity getActivity(Object object) {
        if (object instanceof BaseActivity) {
            return ((BaseActivity) object);
        } else if (object instanceof BaseFragment) {
            return ((BaseFragment) object).getActivity();
        } else {
            return null;
        }
    }

    // 进入设置页面
    private void gotoSettingActivity(Object object) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        if (object instanceof BaseActivity) {
            ((BaseActivity) object).startActivityForResult(intent, BaseActivity.REQUEST_PERMISSION);
        } else if (object instanceof BaseFragment) {
            ((BaseFragment) object).startActivityForResult(intent, BaseActivity.REQUEST_PERMISSION);
        }
    }

    /**
     * 判断是否不再提示权限授权
     *
     * @param object     BaseActivity或者BaseFragment
     * @param permission 请求的权限
     * @return true，需要显示请求权限的理由；false，不需要显示理由
     */
    private static boolean shouldShowRequestPermissionRationale(Object object, String permission) {
        if (object instanceof BaseActivity) {
            return ActivityCompat.shouldShowRequestPermissionRationale((BaseActivity) object, permission);
        } else if (object instanceof BaseFragment) {
            return ((BaseFragment) object).shouldShowRequestPermissionRationale(permission);
        } else {
            return false;
        }
    }

    /**
     * 判断是否不再提示权限授权
     *
     * @param object      BaseActivity或者BaseFragment
     * @param permissions 请求的权限集合
     * @return true，需要显示请求权限的理由；false，不需要显示理由
     */
    private static boolean shouldShowRequestPermissionsRationale(Object object, String... permissions) {
        boolean shouldShowRationale = false;
        for (String permission : permissions) {
            shouldShowRationale = shouldShowRationale || shouldShowRequestPermissionRationale(object, permission);
        }
        return shouldShowRationale;
    }

    /**
     * 权限请求回调结果
     *
     * @param requestCode  请求码
     * @param permissions  申请的权限
     * @param grantResults 对应权限的处理结果
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        List<String> permissionGrantedList = new ArrayList<>();
        List<String> permissionDeniedList = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                permissionGrantedList.add(permissions[i]);
            } else {
                permissionDeniedList.add(permissions[i]);
            }
        }
        if (permissionDeniedList.size() == 0) { // 所有权限全部授权
            onPermissionGranted(true, requestCode, permissions);
        } else {
            if (permissionGrantedList.size() != 0) {
                @PermissionManager.PermissionName String[] permissionGrantedArr = permissionGrantedList.toArray(new String[permissionGrantedList.size()]);
                onPermissionGranted(false, requestCode, permissionGrantedArr);
            }
            @PermissionManager.PermissionName String[] permissionDeniedArr = permissionDeniedList.toArray(new String[permissionDeniedList.size()]);
            onPermissionDenied(requestCode, permissionDeniedArr);
        }
    }

    private void onPermissionGranted(boolean isAll, int requestCode, @PermissionManager.PermissionName String... permissions) {
        if (resultListener != null) {
            if (isAll) {
                resultListener.onPermissionGrantedAll();
            }
            resultListener.onPermissionGranted(requestCode, permissions);
        }
    }

    private void onPermissionDenied(int requestCode, @PermissionManager.PermissionName String... permissions) {
        if (resultListener != null) {
            resultListener.onPermissionDenied(requestCode, permissions);
        }
    }

}
