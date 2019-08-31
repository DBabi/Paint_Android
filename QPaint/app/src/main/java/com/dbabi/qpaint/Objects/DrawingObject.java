package com.dbabi.qpaint.Objects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

public abstract class DrawingObject {

    protected static final float TOUCH_TOLERANCE = 4;

    protected Paint mPaint;
    protected PointF mHeadPoint = new PointF();

    public abstract void drawing(Canvas canvas);

    public abstract void actionDown(float x, float y);

    public abstract void actionMove(float x, float y);

    public abstract void actionUp(float x, float y);
}
