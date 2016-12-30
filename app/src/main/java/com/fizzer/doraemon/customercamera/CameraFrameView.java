package com.fizzer.doraemon.customercamera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;


/**
 * Created by Fizzer on 2016/12/28.
 * Email: doraemonmqq@sina.com
 */

public class CameraFrameView extends View {

    private Context mContext;
    private Paint mPaint;
    //左上角的点 Left-Top Point
    private Point mLTPoint;
    //右上角的点 Right-Top Point
    private Point mRTPoint;
    //左下角的点 Left-Bottom Point
    private Point mLBPoint;
    //右下角的点 Right-Bottom Point
    private Point mRBPoint;

    private Point centerPoint;

    //画线的长度
    private int lineLength = 50;

    public CameraFrameView(Context context) {
        super(context);
    }

    public CameraFrameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CameraFrameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化
     */
    private void init(Context context) {
        mContext = context;

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);    //画笔类型
        mPaint.setColor(Color.parseColor("#ff0000"));   //设置画笔颜色
        mPaint.setStrokeWidth(5);   //设置画笔宽度

        Point screenPoint = getScreenPoint();
        //初始化左上角的点
        mLTPoint = new Point(screenPoint.x / 5, screenPoint.y / 6);
        //初始化右上角的点
        mRTPoint = new Point(screenPoint.x / 5 * 4, screenPoint.y / 6);
        //初始化左下角的点
        mLBPoint = new Point(screenPoint.x / 5, screenPoint.y / 6 * 4);
        //初始化右下角的点
        mRBPoint = new Point(screenPoint.x / 5 * 4, screenPoint.y / 6 * 4);

        //中间点
        centerPoint = new Point((mLTPoint.x + mRTPoint.x) / 2, (mLTPoint.y + mLBPoint.y) / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawArec(canvas);
    }

    private void drawArec(Canvas canvas) {

        //画左上角的折线
        canvas.drawLine(mLTPoint.x, mLTPoint.y, mLTPoint.x + lineLength, mLTPoint.y, mPaint);
        canvas.drawLine(mLTPoint.x, mLTPoint.y, mLTPoint.x, mLTPoint.y + lineLength, mPaint);

        //画右上角的折线
        canvas.drawLine(mRTPoint.x, mRTPoint.y, mRTPoint.x - lineLength, mRTPoint.y, mPaint);
        canvas.drawLine(mRTPoint.x, mRTPoint.y, mRTPoint.x, mRTPoint.y + lineLength, mPaint);

        //画左下角的折线
        canvas.drawLine(mLBPoint.x, mLBPoint.y, mLBPoint.x + lineLength, mLBPoint.y, mPaint);
        canvas.drawLine(mLBPoint.x, mLBPoint.y, mLBPoint.x, mLBPoint.y - lineLength, mPaint);

        //画右下角的折线
        canvas.drawLine(mRBPoint.x, mRBPoint.y, mRBPoint.x - lineLength, mRBPoint.y, mPaint);
        canvas.drawLine(mRBPoint.x, mRBPoint.y, mRBPoint.x, mRBPoint.y - lineLength, mPaint);

        canvas.drawLine(centerPoint.x - 25, centerPoint.y, centerPoint.x + 25, centerPoint.y, mPaint);
        canvas.drawLine(centerPoint.x, centerPoint.y - 25, centerPoint.x, centerPoint.y + 25, mPaint);
    }

    /**
     * 获取屏幕的大小
     *
     * @return 返回屏幕的右下角的点
     * 横坐标就是屏幕的宽，纵坐标就是屏幕的高
     */
    private Point getScreenPoint() {
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);

        return point;
    }
}
