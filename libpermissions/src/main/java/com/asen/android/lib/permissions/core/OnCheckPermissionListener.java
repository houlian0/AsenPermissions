package com.asen.android.lib.permissions.core;

/**
 * 权限监测的监听调用
 *
 * @author Asen
 * @version v2.0
 * @email 597356831@qq.com
 * @date 2017/5/16 13:26
 */
public interface OnCheckPermissionListener {

    /**
     * 成功授予权限时回调
     */
    public void permissionGranted();

}
