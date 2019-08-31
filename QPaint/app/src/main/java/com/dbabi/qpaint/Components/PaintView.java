package com.dbabi.qpaint.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.dbabi.qpaint.Utils.ObjectType;
import com.dbabi.qpaint.Objects.Bezier;
import com.dbabi.qpaint.Objects.Circle;
import com.dbabi.qpaint.Objects.DrawingObject;
import com.dbabi.qpaint.Objects.Erase;
import com.dbabi.qpaint.Objects.Line;
import com.dbabi.qpaint.Objects.Lozenge;
import com.dbabi.qpaint.Objects.Pen;
import com.dbabi.qpaint.Objects.Rectangle;
import com.dbabi.qpaint.Objects.Triangle;

public class PaintView extends View {
    public static int BRUSH_SIZE = 20;
    public static ObjectType DEFAULT_OBJECT = ObjectType.NONE;
    public static final int DEFAULT_COLOR = Color.BLACK;
    public static final int DEFAULT_BG_COLOR = Color.WHITE;
    private Boolean isDrawing = false;
    private float mX, mY;
    private Paint mPaint;
    private ObjectType currentObject;
    private boolean emboss;
    private boolean blur;
    private MaskFilter mEmboss;
    private MaskFilter mBlur;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private DrawingObject drawingObject;


    //TODO: add navigation, size, type, brush, fix triangle, repair circle, reset image bezier

    public PaintView(Context context) {
        this(context, null);
    }

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(DEFAULT_COLOR);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setXfermode(null);
        mPaint.setAlpha(0xff);
        mPaint.setStrokeWidth(BRUSH_SIZE);

//        mEmboss = new EmbossMaskFilter(new float[] {1, 1, 1}, 0.4f, 6, 3.5f);
//        mBlur = new BlurMaskFilter(5, BlurMaskFilter.Blur.NORMAL);
    }

    public void init(DisplayMetrics metrics) {
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        mCanvas = new Canvas(mBitmap);

        drawingObject = new Pen(mPaint);
        currentObject  = DEFAULT_OBJECT;
    }

    public void setColor(int color){
        mPaint.setColor(color);
    }

    public void setStrokeWidth(int size){
        mPaint.setStrokeWidth(size);
    }

    public void setObject(ObjectType object){
        currentObject = object;
        switch (object) {
            case PEN:
                drawingObject = new Pen(mPaint);
                break;
            case LINE:
                drawingObject = new Line(mPaint);
                break;
            case CIRCLE:
                drawingObject = new Circle(mPaint);
                break;
            case RECTANGLE:
                drawingObject = new Rectangle(mPaint);
                break;
            case TRIANGLE:
                drawingObject = new Triangle(mPaint);
                break;
            case BEZIER:
                drawingObject = new Bezier(mPaint);
                break;
            case LOZENGE:
                drawingObject = new Lozenge(mPaint);
                break;
            case ERASE: drawingObject = new Erase(mPaint);
        }
    }

    public void normal() {
        emboss = false;
        blur = false;
    }

    public void emboss() {
        emboss = true;
        blur = false;
    }

    public void blur() {
        emboss = false;
        blur = true;
    }

    public void clear() {
        normal();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        if (isDrawing){
            switch (currentObject) {
                case NONE:
                    return;
                case ERASE:
                case PEN:
                    drawingObject.drawing(mCanvas);
                    break;
                default:
                    drawingObject.drawing(canvas);
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(currentObject == ObjectType.NONE)
            return false;
        mX = event.getX();
        mY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDrawing = true;
                drawingObject.actionDown(mX, mY);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                drawingObject.actionMove(mX, mY);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                isDrawing = false;
                drawingObject.actionUp(mX, mY);
                drawingObject.drawing(mCanvas);
                invalidate();
                break;
        }
        return true;
    }
}
