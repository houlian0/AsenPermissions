package com.asen.android.lib.permissions.core;

/**
 * 权限授予结果监听
 *
 * @author Asen
 * @version v2.0
 * @email 597356831@qq.com
 * @date 2017/5/16 09:41
 */
public interface PermissionResultListener {

    /**
     * 全部授权请求的权限
     */
    public void onPermissionGrantedAll();

    /**
     * 授予权限
     *
     * @param requestCode 请求码
     * @param permissions 授予权限的权限名称集合
     */
    public void onPermissionGranted(int requestCode, @PermissionManager.PermissionName String... permissions);

    /**
     * 拒绝授权
     *
     * @param requestCode 请求码
     * @param permissions 拒绝授权的权限名称集合
     */
    public void onPermissionDenied(int requestCode, @PermissionManager.PermissionName String... permissions);


}
