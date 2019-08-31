package com.dbabi.qpaint.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dbabi.qpaint.Utils.ObjectType;
import com.dbabi.qpaint.Components.PaintView;
import com.dbabi.qpaint.R;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorChangedListener;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private PaintView paintView;
    private android.support.v7.widget.Toolbar toolbar;
    private int currentColor = Color.WHITE;
    private ObjectType currentObj = ObjectType.NONE;
    Button btnPicker;
    private int[] ids = {R.id.btnLine,R.id.btnCircle,R.id.btnRectangle,R.id.btnTriangle,R.id.btnBezier,
            R.id.btnLozenge,R.id.btnPen_Erase};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        paintView = findViewById(R.id.paintView);
        toolbar = findViewById(R.id.toolbar);
        mappingView();

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);
    }

    private void mappingView()
    {
        btnPicker = findViewById(R.id.btnColorPicker);
        btnPicker.setOnClickListener(this);
        for(int id : ids){
            ImageButton btn = findViewById(id);
            btn.setOnClickListener(this);
            btn.setOnFocusChangeListener(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.normal:
                paintView.normal();
                return true;
            case R.id.emboss:
                paintView.emboss();
                return true;
            case R.id.blur:
                paintView.blur();
                return true;
            case R.id.clear:
                paintView.clear();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if(b) {
            resetBackgroundButton();
            view.setBackgroundColor(getResources().getColor(R.color.backgroundButton));
        }else{
            resetBackgroundButton();
            currentObj = ObjectType.NONE;
            changeObject(currentObj);
        }
    }

    @Override
    public void onClick(final View v) {

        switch (v.getId()) {
            case R.id.btnColorPicker:
                final Context context = MainActivity.this;

                ColorPickerDialogBuilder
                        .with(context)
                        .setTitle("Choose color")
                        .initialColor(currentColor)
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12)
                        .setOnColorChangedListener(new OnColorChangedListener() {
                            @Override
                            public void onColorChanged(int selectedColor) {
                                // Handle on color change
                                Log.d("ColorPicker", "onColorChanged: 0x" + Integer.toHexString(selectedColor));
                            }
                        })
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                                //toast("onColorSelected: 0x" + Integer.toHexString(selectedColor));
                            }
                        })
                        .setPositiveButton("OK", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                changeColor(selectedColor);
                                if (allColors != null) {
                                    StringBuilder sb = null;

                                    for (Integer color : allColors) {
                                        if (color == null)
                                            continue;
                                        if (sb == null)
                                            sb = new StringBuilder("Color List:");
                                        sb.append("\r\n#" + Integer.toHexString(color).toUpperCase());
                                    }

//                                    if (sb != null)
//                                        Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .showColorEdit(true)
                        .setColorEditTextColor(ContextCompat.getColor(MainActivity.this, android.R.color.holo_blue_bright))
                        .build()
                        .show();
                return;
            case R.id.btnLine: currentObj = ObjectType.LINE;break;
            case R.id.btnCircle: currentObj = ObjectType.CIRCLE;break;
            case R.id.btnRectangle: currentObj = ObjectType.RECTANGLE;break;
            case R.id.btnTriangle: currentObj = ObjectType.TRIANGLE;break;
            case R.id.btnLozenge: currentObj = ObjectType.LOZENGE;break;
            case R.id.btnBezier: currentObj = ObjectType.BEZIER;break;
            case R.id.btnPen_Erase:
                if(currentObj == ObjectType.PEN){
                    currentObj = ObjectType.ERASE;
                    ImageButton temp = (ImageButton)v;
                    temp.setImageResource(R.drawable.eraser);
                }
                else{
                    currentObj = ObjectType.PEN;
                    ImageButton temp = (ImageButton)v;
                    temp.setImageResource(R.drawable.pencil);
                }
        }
        resetBackgroundButton();
        v.setBackgroundColor(getResources().getColor(R.color.backgroundButton));
        changeObject(currentObj);
    }

    private void changeColor(int selectedColor) {
        currentColor = selectedColor;
        btnPicker.setBackgroundColor(selectedColor);
        paintView.setColor(selectedColor);
    }

    private void changeObject(ObjectType object){
        paintView.setObject(object);
    }

    private void resetBackgroundButton(){
        for(int id : ids){
            ImageButton btn = findViewById(id);
            btn.setBackground(null);
        }
    }

    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}
