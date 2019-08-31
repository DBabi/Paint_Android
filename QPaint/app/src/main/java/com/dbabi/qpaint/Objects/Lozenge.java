package com.dbabi.qpaint.Objects;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Lozenge extends Shape{
    public Lozenge(Paint paint) {
        super(paint);
    }

    @Override
    public void drawing(Canvas canvas) {
        canvas.drawLine((mHeadPoint.x + mLastPoint.x) / 2, mHeadPoint.y,
                mHeadPoint.x , (mHeadPoint.y +mLastPoint.y) / 2, mPaint);

        canvas.drawLine((mHeadPoint.x + mLastPoint.x) / 2, mLastPoint.y,
                mHeadPoint.x , (mHeadPoint.y +mLastPoint.y) / 2, mPaint);

        canvas.drawLine((mHeadPoint.x + mLastPoint.x) / 2, mHeadPoint.y,
                mLastPoint.x , (mHeadPoint.y +mLastPoint.y) / 2, mPaint);

        canvas.drawLine((mHeadPoint.x + mLastPoint.x) / 2, mLastPoint.y,
                mLastPoint.x , (mHeadPoint.y +mLastPoint.y) / 2, mPaint);
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
