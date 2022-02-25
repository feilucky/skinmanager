package com.mxtech.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mxtech.demo.entity.DynamicAttr;
import com.mxtech.demo.listener.IDynamicNewView;
import com.mxtech.demo.listener.ISkinUpdate;
import com.mxtech.demo.loader.SkinInflaterFactory;
import com.mxtech.demo.loader.SkinManager;

import java.util.List;

public class BaseActivity extends AppCompatActivity implements ISkinUpdate, IDynamicNewView {
    private static final String TAG = BaseActivity.class.getSimpleName();
    private SkinInflaterFactory mSkinInflaterFactory;
    private boolean isResponseOnSkinChanging = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        verifyStoragePermissions(this);
        mSkinInflaterFactory = new SkinInflaterFactory();
        getLayoutInflater().setFactory(mSkinInflaterFactory);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SkinManager.getInstance().attach(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().detach(this);
        mSkinInflaterFactory.clean();
    }

    @Override
    public void onThemeUpdate() {
        Log.d("zhanfei",TAG + ".onThemeUpdate......");
        if (!isResponseOnSkinChanging) {
            return;
        }
//        mSkinInflaterFactory.applySkin();
    }

    @Override
    public void dynamicAddView(View view, List<DynamicAttr> pDAttrs) {
        mSkinInflaterFactory.dynamicAddSkinEnableView(this, view, pDAttrs);
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };

    //然后通过一个函数来申请
    public  void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}