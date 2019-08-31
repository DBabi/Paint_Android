package com.dbabi.qpaint.Objects;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Circle extends Shape {
    public Circle(Paint paint) {
        super(paint);
    }

    @Override
    public void drawing(Canvas canvas) {
        canvas.drawCircle(mHeadPoint.x, mHeadPoint.y, calculateRadius(mHeadPoint.x, mHeadPoint.y, mLastPoint.x, mLastPoint.y), mPaint);
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

    /**
     * Function calc radius of circle
     *  last point - center point  =  radius
     * @param x1 center point x
     * @param y1 center point y
     * @param x2 last point x
     * @param y2 last point y
     * @return radius value
     */
    private float calculateRadius(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt(
                Math.pow(x1 - x2, 2) +
                        Math.pow(y1 - y2, 2)
        );
    }
}
