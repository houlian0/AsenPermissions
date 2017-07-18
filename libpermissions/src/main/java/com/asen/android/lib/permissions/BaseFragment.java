package com.asen.android.lib.permissions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.asen.android.lib.permissions.core.OnCheckPermissionListener;
import com.asen.android.lib.permissions.core.PermissionManager;
import com.asen.android.lib.permissions.core.PermissionResultListener;

import static com.asen.android.lib.permissions.BaseActivity.REQUEST_PERMISSION;

/**
 * 基础的Fragment
 *
 * @author Asen
 * @version v2.0
 * @email 597356831@qq.com
 * @date 2017/5/2 15:41
 */
public class BaseFragment extends Fragment implements PermissionResultListener {


    /**
     * Android的上下文
     */
    protected Context mContext;

    /**
     * 加载此Fragment的Activity对象
     */
    protected BaseActivity mActivity;


    private PermissionManager permissionManager; // 权限

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity instanceof BaseActivity) {
            this.mActivity = (BaseActivity) activity;
        }

        this.mContext = activity.getApplicationContext();
        this.permissionManager = new PermissionManager(mContext, this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
