package com.asen.android.permissions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.asen.android.lib.permissions.BaseFragment;
import com.asen.android.lib.permissions.core.OnCheckPermissionListener;
import com.asen.android.lib.permissions.core.PermissionManager;
import com.asen.android.lib.permissions.utils.ToastUtils;

/**
 * Android动态权限测试Fragment
 *
 * @author Asen
 * @version v2.0
 * @email 597356831@qq.com
 * @date 2017/7/18 22:29
 */
public class MainFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, null);
        initViews(view);
        registerListener();
        return view;
    }

    private Button btnShow1, btnShow2;

    private TextView tvShow;

    // 初始化控件
    private void initViews(View view) {
        tvShow = (TextView) view.findViewById(R.id.tvShow);
        btnShow1 = (Button) view.findViewById(R.id.btnShow1);
        btnShow2 = (Button) view.findViewById(R.id.btnShow2);
    }

    // 注册监听
    private void registerListener() {
        btnShow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission(new OnCheckPermissionListener() {
                    @Override
                    public void permissionGranted() {
                        ToastUtils.showShortToast(mContext, getString(R.string.sd_permissions_success));
                        tvShow.setText(R.string.sd_permissions_success);
                    }
                }, PermissionManager.PERMISSION_WRITE_EXTERNAL_STORAGE); // 可同时验证多个权限

            }
        });
        btnShow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission(new OnCheckPermissionListener() {
                    @Override
                    public void permissionGranted() {
                        ToastUtils.showShortToast(mContext, getString(R.string.camera_permissions_success));
                        tvShow.setText(R.string.camera_permissions_success);
                    }
                }, PermissionManager.PERMISSION_CAMERA); // 可同时验证多个权限

            }
        });
    }


}
