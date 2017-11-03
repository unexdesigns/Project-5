package com.stundys.project5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Žanas on 2017-10-31.
 */

public class DrawingView extends android.support.v7.widget.AppCompatImageView {
    private boolean canDraw = false;
    private String text = null;
    private Bitmap bitmap = null;

    Context context;


    int paintColor;

    int width = 0;
    int height = 0;

    private Path circlePath;
    private Path    mPath;
    private Paint mBitmapPaint;
    private Paint mTextPaint;
    private Paint mPencilPaint;
    private Paint mCirclePaint;
    private Canvas  mCanvas;

    public DrawingView(Context c, @Nullable AttributeSet attrs) {
        super(c, attrs);

        context = c;
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        mTextPaint = new Paint();
        circlePath = new Path();

        mPencilPaint = new Paint();
        mPencilPaint.setAntiAlias(true);
        mPencilPaint.setColor(Color.BLUE);
        mPencilPaint.setStyle(Paint.Style.STROKE);
        mPencilPaint.setStrokeJoin(Paint.Join.ROUND);
        mPencilPaint.setStrokeWidth(14f);

        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setSubpixelText(true);
        mTextPaint.setColor(Color.GREEN);
        mTextPaint.setTextSize(36);

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(getResources().getColor(R.color.semi));
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setStrokeWidth(14f);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(bitmap != null){
            bitmap = Bitmap.createScaledBitmap(bitmap, w, h, false);
        } else {
            bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        }

        mCanvas = new Canvas(bitmap);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int width = this.getMeasuredWidth();
        int height = this.getMeasuredHeight();

        if(bitmap != null){
            Bitmap scaleBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
            canvas.drawBitmap(scaleBitmap, 0, 0, mBitmapPaint);
        }

        if(text != null){
            canvas.drawText(text, 16, height - 16, mTextPaint);
        }

        if(canDraw){
            // TO DO
            mPencilPaint.setColor(paintColor);
            canvas.drawPath( mPath,  mPencilPaint);
            canvas.drawPath( circlePath,  mCirclePaint);
        }

        super.onDraw(canvas);
    }

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    private void touch_start(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;

            circlePath.reset();
            circlePath.addCircle(mX, mY, 30, Path.Direction.CW);
        }
    }

    private void touch_up() {
        mPath.lineTo(mX, mY);
        circlePath.reset();
        // commit the path to our offscreen
        mPencilPaint.setColor(paintColor);
        mCanvas.drawPath(mPath,  mPencilPaint);
        // kill this so we don't double draw
        mPath.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        if(!canDraw) return false;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
        }
        return true;
    }





    public void setText(String text){
        if(this.text == null){
            this.text = text;
        } else {
            this.text = null;
        }
        invalidate();
    }

    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
        invalidate();
        Log.d("BITMAP:", String.valueOf(this.bitmap));
    }

    public void setPaintColor(int color){
        this.paintColor = color;
        Log.d("PAINT COLOR:", String.valueOf(this.paintColor));
    }

    public void setCanDraw(boolean state) {
        if(this.canDraw){
            this.canDraw = false;
        } else{
            this.canDraw = state;
        }
        Log.d("CAN DRAW:", String.valueOf(this.canDraw));
    }
}
