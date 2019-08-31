package com.dbabi.qpaint.Objects;

import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

public class Erase extends Pen {
    public Erase(Paint paint) {
        super(paint);
        mPaint = new Paint(paint);
        mPaint.setXfermode(new PorterDuffXfermode(
                PorterDuff.Mode.CLEAR));
        mPaint.setStrokeWidth(mPaint.getStrokeWidth() + 5);
    }

}
