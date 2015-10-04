package com.dxkj.ejiaofei.ejiaofei.custom;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.media.AudioManager;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dxkj on 2015/10/4.
 */
public class CameraView extends SurfaceView implements SurfaceHolder.Callback, Camera.PictureCallback {

    private SurfaceHolder holder;

    private Camera camera;

    private Camera.Parameters parameters;

    private Activity act;

    private Handler handler = new Handler();

    private Context context;

    private SurfaceView surfaceView;

    private AudioManager audio;

    private int current;


    public CameraView(Context context) {
        super(context);
        surfaceView = this;

        audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        final int current = audio.getRingerMode();

        audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);

        this.context = context;

        holder = getHolder();// 生成Surface Holder

        holder.addCallback(this);

        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);// 指定Push Buffer

        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (camera == null) {
                    handler.postDelayed(this, 1 * 1000);// 由于启动camera需要时间，在此让其等两秒再进行聚焦知道camera不为空
                } else {
                    camera.autoFocus(new Camera.AutoFocusCallback() {

                        @Override
                        public void onAutoFocus(boolean success, Camera camera) {
                            if (success) {
                                camera.takePicture(new Camera.ShutterCallback() {// 如果聚焦成功则进行拍照

                                    @Override
                                    public void onShutter() {

                                    }
                                }, null, CameraView.this);
                            } else {
                            }
                        }
                    });
                }
            }
        }, 2 * 1000);
    }

    /**
     * 在此定义一个构造方法用于拍照过后把CameraActivity给finish掉
     */
    public CameraView(Context context, Activity act) {
        this(context);
        this.act = act;

    }

    @Override
    public void surfaceCreated(final SurfaceHolder holder) {
        camera = Camera.open();          //初始化相机
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (holder != null) {
                    try {
                        camera.setPreviewDisplay(holder);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {
                    handler.postDelayed(this, 1 * 1000);
                }
            }
        }, 2 * 1000);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        parameters = camera.getParameters();
        camera.setParameters(parameters);// 设置参数
        camera.startPreview();// 开始预览
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    /**
     * 拍摄完毕保存照片
     * @param data
     * @param camera
     */
    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        try {
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            String time = format.format(date);
            //在SD卡上创建文件夹
            File file = new File(Environment.getExternalStorageDirectory()
                    + "/myCamera/pic");
            if (!file.exists()) {
                file.mkdirs();
            }
            String path = Environment.getExternalStorageDirectory()
                    + "/myCamera/pic/" + time + ".jpg";
            Log.i("123","path--->"+path);
            data2file(data, path);

            camera.setPreviewCallback(null);
            camera.release();
            camera = null;
            holder.removeCallback(CameraView.this);
            audio.setRingerMode(current);
            act.finish();
            //uploadFile(path);
        } catch (Exception e) {

        }
    }

    /**
     *  将二进制数据转换为文件
     * @param w
     * @param fileName
     * @throws Exception
     */
    private void data2file(byte[] w, String fileName) throws Exception {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(fileName);
            out.write(w);
            out.close();
        } catch (Exception e) {
            if (out != null)
                out.close();
            throw e;
        }
    }
}
