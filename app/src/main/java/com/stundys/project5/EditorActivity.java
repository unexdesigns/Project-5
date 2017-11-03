package com.stundys.project5;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditorActivity extends AppCompatActivity {
    public Bitmap image;
    public DrawingView imageViewer;
    public Button colorChanger;
    public int[] colors;

    public int currentColorIndex = 0;
    public int currentColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Bitmap from Intent
        Intent intent = getIntent();
        image = intent.getParcelableExtra(MainActivity.INCOMING_PICTURE_MESSAGE);

        imageViewer = findViewById(R.id.drawingView);
        imageViewer.setBackground(new BitmapDrawable(getResources(), image));

        // Color picker spinner values
        colorChanger = findViewById(R.id.color_changer);
        colors = this.getResources().getIntArray(R.array.colorPickerColors);
        currentColor = colors[currentColorIndex];
        setColor(currentColorIndex);
    }

    public void cycleColor(View view){
        if(currentColorIndex < colors.length - 1){
            currentColorIndex = currentColorIndex + 1;
        } else {
            currentColorIndex = 0;
        }

        setColor(currentColorIndex);
    }

    private void setColor(int index){
        currentColor = colors[index];

        ViewCompat.setBackgroundTintList(colorChanger, ColorStateList.valueOf(currentColor));

    }
}
