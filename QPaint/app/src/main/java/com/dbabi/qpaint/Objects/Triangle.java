package com.dbabi.qpaint.Objects;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Triangle extends Shape {
    private int countTouch = 0;
    private float baseXTriangle = 0;
    private float baseYTriangle = 0;

    public Triangle(Paint paint) {
        super(paint);
    }

    @Override
    public void drawing(Canvas canvas) {
        if (countTouch < 2){
            canvas.drawLine(mHeadPoint.x, mHeadPoint.y, mLastPoint.x, mLastPoint.y, mPaint);
        }else if (countTouch == 2){
            canvas.drawLine(mLastPoint.x, mLastPoint.y, mHeadPoint.x, mHeadPoint.y, mPaint);
            canvas.drawLine(mLastPoint.x, mLastPoint.y, baseXTriangle, baseYTriangle, mPaint);
        }
    }

    @Override
    public void actionDown(float x, float y) {
        countTouch++;
        if (countTouch == 1){
            mHeadPoint.x = x;
            mHeadPoint.y = y;

            mLastPoint.x = x;
            mLastPoint.y = y;
        }
    }

    @Override
    public void actionMove(float x, float y) {
        mLastPoint.x = x;
        mLastPoint.y = y;
    }

    @Override
    public void actionUp(float x, float y) {
        mLastPoint.x = x;
        mLastPoint.y = y;

        if (countTouch<2){
            baseXTriangle = x;
            baseYTriangle = y;
        } else {
            countTouch =0;
        }
    }
}
