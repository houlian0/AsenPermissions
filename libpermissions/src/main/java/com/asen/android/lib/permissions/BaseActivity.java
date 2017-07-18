package com.asen.android.lib.permissions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.asen.android.lib.permissions.core.OnCheckPermissionListener;
import com.asen.android.lib.permissions.core.PermissionManager;
import com.asen.android.lib.permissions.core.PermissionResultListener;

/**
 * 基础的Activity
 *
 * @author Asen
 * @version v2.0
 * @email 597356831@qq.com
 * @date 2017/4/27 17:52
 */
public class BaseActivity extends AppCompatActivity implements PermissionResultListener {

    /**
     * 请求权限时的请求码
     */
    public static final int REQUEST_PERMISSION = 1;

    /**
     * Android的上下文
     */
    protected Context mContext;

    private PermissionManager permissionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mContext = getApplicationContext();
        this.permissionManager = new PermissionManager(mContext, this);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_PERMISSION == requestCode) {
            @PermissionManager.PermissionName String[] permissions = permissionManager.getUnauthorizedPermission(mContext, permissionArr);
            if (permissions.length == 0) {
                onPermissionGrantedAll(); // 授权成功
            } else {
                permissionManager.hintNoPermissions(this, permissions); // 提示未授予的权限信息
            }
        }
    }

    private OnCheckPermissionListener checkPermissionListener;

    private String[] permissionArr;

    /**
     * 检查是否授予指定权限
     *
     * @param checkPermissionListener 指定权限全部授予时回调接口
     * @param permissions             指定权限集合
     */
    public void checkPermission(OnCheckPermissionListener checkPermissionListener, @PermissionManager.PermissionName String... permissions) {
        this.checkPermissionListener = checkPermissionListener;
        this.permissionArr = permissions;
        this.permissionManager.requestPermissions(this, REQUEST_PERMISSION, permissions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            permissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onPermissionGrantedAll() {
        if (checkPermissionListener != null) {
            checkPermissionListener.permissionGranted();
        }
    }

    @Override
    public void onPermissionGranted(int requestCode, @PermissionManager.PermissionName String... permissions) {
    }

    @Override
    public void onPermissionDenied(int requestCode, @PermissionManager.PermissionName String... permissions) {
        permissionManager.hintNoPermissions(this, permissions); // 提示未授予的权限信息
    }


}
