package com.dbabi.qpaint.Objects;


import android.graphics.Canvas;
import android.graphics.Paint;

public class Line extends Shape {


    public Line(Paint paint) {
        super(paint);
    }

    @Override
    public void drawing(Canvas canvas) {
        float dx = Math.abs(mLastPoint.x - mHeadPoint.x);
        float dy = Math.abs(mLastPoint.y - mHeadPoint.y);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE)
            canvas.drawLine(mHeadPoint.x, mHeadPoint.y, mLastPoint.x, mLastPoint.y, mPaint);
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
