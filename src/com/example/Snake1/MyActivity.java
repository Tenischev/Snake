package com.example.Snake1;

import android.app.ActionBar;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

public class MyActivity extends Activity {
    public static int screen_w;
    public static int screen_h;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        ActionBar actionBar = getActionBar();
        actionBar.hide();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screen_w=size.x;
        screen_h=size.y;

        super.onCreate(savedInstanceState);
        setContentView(new MySurfaceView(this));

        // если хотим, чтобы приложение постоянно имело портретную ориентацию
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);// full screen
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        /*super.onCreate(savedInstanceState);
        setContentView(R.layout.main);*/
    }
}
