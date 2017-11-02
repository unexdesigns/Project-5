package com.stundys.project5;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class EditorActivity extends AppCompatActivity implements android.widget.AdapterView.OnItemSelectedListener {
    public Bitmap image;
    public DrawingView imageViewer;
    public Spinner colorSpinner;
    private Object AdapterView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Intent intent = getIntent();
        image = intent.getParcelableExtra(MainActivity.INCOMING_PICTURE_MESSAGE);

        imageViewer = findViewById(R.id.drawingView);
        imageViewer.setBackground(new BitmapDrawable(getResources(), image));

        colorSpinner = findViewById(R.id.colorSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.colors_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        colorSpinner.setAdapter(adapter);
        colorSpinner.setOnItemSelectedListener(this);
    }


    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        parent.getItemAtPosition(pos);


    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
