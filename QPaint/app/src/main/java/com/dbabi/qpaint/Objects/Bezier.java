package com.dbabi.qpaint.Objects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

public class Bezier extends Shape {
    private Path mPath;
    private PointF mMidPoint = new PointF();
    public Bezier(Paint paint) {
        super(paint);
    }

    @Override
    public void drawing(Canvas canvas) {
        mPath = new Path();
        mPath.reset();
        mPath.moveTo(mHeadPoint.x, mHeadPoint.y);
        mPath.cubicTo((mHeadPoint.x + mMidPoint.x) / 2, mHeadPoint.y - Math.abs(mHeadPoint.x - mMidPoint.x) / 2,
                (mMidPoint.x + mLastPoint.x) / 2, mHeadPoint.y + Math.abs(mHeadPoint.x - mMidPoint.x) / 2,
                mLastPoint.x, mLastPoint.y);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public void actionDown(float x, float y) {
        mHeadPoint.x = x;
        mHeadPoint.y = y;

        mLastPoint.x = x;
        mLastPoint.y = y;

        mMidPoint.x = (mHeadPoint.x + mLastPoint.x) / 2;
        mMidPoint.y = (mHeadPoint.y + mLastPoint.y) / 2;
    }

    @Override
    public void actionMove(float x, float y) {
        mLastPoint.x = x;
        mLastPoint.y = y;

        mMidPoint.x = (mHeadPoint.x + mLastPoint.x) / 2;
        mMidPoint.y = (mHeadPoint.y + mLastPoint.y) / 2;
    }

    @Override
    public void actionUp(float x, float y) {
        mLastPoint.x = x;
        mLastPoint.y = y;

        mMidPoint.x = (mHeadPoint.x + mLastPoint.x) / 2;
        mMidPoint.y = (mHeadPoint.y + mLastPoint.y) / 2;
    }
}
