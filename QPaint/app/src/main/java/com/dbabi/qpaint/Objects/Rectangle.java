package com.dbabi.qpaint.Objects;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Rectangle extends Shape {
    public Rectangle(Paint paint) {
        super(paint);
    }

    @Override
    public void drawing(Canvas canvas) {
        float right = mHeadPoint.x > mLastPoint.x ? mHeadPoint.x : mLastPoint.x;
        float left = mHeadPoint.x > mLastPoint.x ? mLastPoint.x : mHeadPoint.x;
        float bottom = mHeadPoint.y > mLastPoint.y ? mHeadPoint.y : mLastPoint.y;
        float top = mHeadPoint.y > mLastPoint.y ? mLastPoint.y : mHeadPoint.y;
        canvas.drawRect(left, top , right, bottom, mPaint);
    }

    @Override
    public void actionDown(float x, float y) {
        mHeadPoint.x = x;
        mHeadPoint.y = y;

        mLastPoint.x = x;
        mLastPoint.y = y;
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
    }
}
