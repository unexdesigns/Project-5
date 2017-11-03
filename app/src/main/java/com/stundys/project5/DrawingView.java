package com.stundys.project5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Žanas on 2017-10-31.
 */

public class DrawingView extends android.support.v7.widget.AppCompatImageView {
    private boolean canDraw = false;
    private String text = null;
    private Bitmap bitmap = null;

    int paintColor;

    int width = 0;
    int height = 0;

    private Paint paint = new Paint();

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = this.getMeasuredWidth();
        int height = this.getMeasuredHeight();

        if(bitmap != null){
            Bitmap scaleBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
            canvas.drawBitmap(scaleBitmap, 0, 0, paint);
        }

        if(text != null){
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            paint.setSubpixelText(true);
            paint.setColor(Color.GREEN);
            paint.setTextSize(36);
            canvas.drawText(text, 16, height - 16, paint);
        }

        if(canDraw){

        }

        super.onDraw(canvas);
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
