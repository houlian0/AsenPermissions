package com.asen.android.permissions;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.asen.android.lib.permissions.BaseActivity;
import com.asen.android.lib.permissions.core.OnCheckPermissionListener;
import com.asen.android.lib.permissions.core.PermissionManager;
import com.asen.android.lib.permissions.utils.ToastUtils;

/**
 * Android动态权限测试Activity
 *
 * @author Asen
 * @version v2.0
 * @email 597356831@qq.com
 * @date 2017/7/18 22:29
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化控件
        initViews();
        // 注册监听
        registerListener();
    }

    private Button btnShow1, btnShow2;

    private TextView tvShow;

    // 初始化控件
    private void initViews() {
        tvShow = (TextView) findViewById(R.id.tvShow);
        btnShow1 = (Button) findViewById(R.id.btnShow1);
        btnShow2 = (Button) findViewById(R.id.btnShow2);

        // 测试Fragment中的动态权限申请
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.add(R.id.layoutFrame, new MainFragment());
        beginTransaction.commitAllowingStateLoss();
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
                }, PermissionManager.PERMISSION_WRITE_EXTERNAL_STORAGE);

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
                }, PermissionManager.PERMISSION_CAMERA);

            }
        });
    }

}
