package com.fizzer.doraemon.customercamera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private int OPEN_CAMERA = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.Image);
    }

    public void TakePhoto(View view) {
        Intent intent = new Intent(this, CustomerCameraActivity.class);
        startActivityForResult(intent, OPEN_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (CustomerCameraActivity.RESULT_OK != resultCode) {
            return;
        }

        if (OPEN_CAMERA == requestCode) {
            String path = data.getStringExtra("data");
            showImg(path, mImageView);
        }
    }

    /**
     * 展示图片
     *
     * @param path 图片路径
     * @param img  显示图片的控件
     */
    private void showImg(String path, ImageView img) {
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;   //不去真的解析图片，只是获取宽高信息
        BitmapFactory.decodeFile(path, option);

        int width = option.outWidth;
        int height = option.outHeight;
        Logger.myLog("宽度=" + width + "高度=" + height);

        WindowManager wm = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        Point outPoint = new Point();
        wm.getDefaultDisplay().getSize(outPoint);
        //计算缩放比
        int scale = 1;
        int scalex = width / 300;
        int scaley = height / 200;
        scale = scalex > scaley ? scalex : scaley;
        option.inSampleSize = scale;
        //开始真正的解析图片
        option.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, option);
        Logger.myLog("缩放后的宽度=" + option.outWidth + "缩放后的高度=" + option.outHeight);
        img.setImageBitmap(bitmap);
    }
}
