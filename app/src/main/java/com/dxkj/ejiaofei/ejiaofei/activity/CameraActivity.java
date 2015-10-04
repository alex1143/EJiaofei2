package com.dxkj.ejiaofei.ejiaofei.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.dxkj.ejiaofei.R;
import com.dxkj.ejiaofei.ejiaofei.custom.CameraView;

public class CameraActivity extends Activity {

    private CameraView cameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 设置横屏模式以及全屏模式
        cameraView = new CameraView(this);// 通过一个surfaceview的view来实现拍照
        cameraView.setId(1);
        cameraView = new CameraView(this, this);
        setContentView(R.layout.camera_layout);
        RelativeLayout relative = (RelativeLayout) this.findViewById(R.id.ly);
        RelativeLayout.LayoutParams Layout = new RelativeLayout.LayoutParams(3, 3);// 设置surfaceview使其满足需求无法观看预览
        Layout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);
        Layout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 1);
        relative.addView(cameraView, Layout);
    }
}
