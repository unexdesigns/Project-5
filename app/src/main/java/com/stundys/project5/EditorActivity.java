package com.stundys.project5;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

public class EditorActivity extends AppCompatActivity {
    public DrawingView imageViewer;
    public Spinner colorPicker;
    public int currentColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Bitmap from Intent
        Intent intent = getIntent();
        Bitmap image = intent.getParcelableExtra(MainActivity.INCOMING_PICTURE_MESSAGE);

        imageViewer = findViewById(R.id.drawingView);
        // imageViewer.setBackground(new BitmapDrawable(getResources(), image));

        imageViewer.setBitmap(image);

        //Spinner
        colorPicker = findViewById(R.id.colorPicker);
        setupColorPicker();
    }

    private void setupColorPicker() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.color_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorPicker.setAdapter(adapter);
        colorPicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String colorName = colorPicker.getSelectedItem().toString();
                currentColor = ContextCompat.getColor(getApplicationContext(), getColorIdentifier(colorName));
                imageViewer.setPaintColor(currentColor);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public int getColorIdentifier(String name) {
        return getResources().getIdentifier(name, "color", this.getPackageName());
    }

    public void addAuthorText(View view){
        String authorName = getResources().getString(R.string.author);
        imageViewer.setText(authorName);
    }

    public void setDrawingMode(View view){
        imageViewer.setCanDraw(true);
    }
}
