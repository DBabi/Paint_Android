package com.dbabi.qpaint.Objects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;


public class Pen extends DrawingObject {

    private Path mPath;

    public Pen(Paint paint){
        this.mPaint = paint;
        //this.mPath = path;
    }

    @Override
    public void drawing(Canvas canvas) {
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public void actionDown(float x, float y) {
        mPath = new Path();

        mPath.reset();
        mPath.moveTo(x, y);
        mHeadPoint.x = x;
        mHeadPoint.y = y;
    }

    @Override
    public void actionMove(float x, float y) {
        float dx = Math.abs(x - mHeadPoint.x);
        float dy = Math.abs(y - mHeadPoint.y);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(x, y, (x + mHeadPoint.x) /2, (y + mHeadPoint.y) / 2);
            mHeadPoint.x = x;
            mHeadPoint.y = y;        }
    }

    @Override
    public void actionUp(float x, float y) {
        mPath.lineTo(x, y);
    }
}
