package com.example.portfolioupgraded.ui.doodle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.portfolioupgraded.R;

public class doodleView extends View {
    private Path drawPath;

    //defines what to draw
    private Paint canvasPaint;

    //defines how to draw
    private Paint drawPaint;

    //initial color
    private int paintColor = 0xFF660000;

    //holds the drawing and transfers it to the View
    private Canvas drawCanvas;

    //canvas bitmap
    private Bitmap canvasBitmap;
    //brush size
    private float brushSize;

    public doodleView(Context context) {
        super(context);
        init();
    }

    public doodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        brushSize = getResources().getInteger(R.integer.brush_size);
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);

    }

    public void resize(int size) {
        drawPaint.setStrokeWidth(size);
    }

    public void repaint(int progress,int progress1, int progress2) {
        //copiolet to use rgb values
        drawPaint.setColor(Color.rgb(progress,progress1,progress2));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }


    @Override
    protected void onSizeChanged(int w, int h, int prevW, int prevH) {
        //create canvas matching device size
        super.onSizeChanged(w, h, prevW, prevH);

        //create Bitmap of certain w,h
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        //apply bitmap to graphic to start drawing.
        drawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        //down, move and up events
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawPath.lineTo(touchX, touchY);
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }
        //redraw
        invalidate();
        return true;
    }
}