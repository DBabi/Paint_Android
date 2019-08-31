package com.dbabi.qpaint.Objects;

import android.graphics.Paint;
import android.graphics.PointF;

public abstract class Shape extends DrawingObject {

    protected PointF mLastPoint = new PointF();

    public Shape(Paint paint){
        this.mPaint = paint;
    }
}
