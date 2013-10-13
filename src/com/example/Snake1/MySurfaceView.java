package com.example.Snake1;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private DrawThread drawThread;
    public static int sizeButton=80;

    public MySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(getHolder(), getResources());
        drawThread.setRunning(true);
        drawThread.start();
        this.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch (View v, MotionEvent event){
                int x = (int)event.getX();
                int y = (int)event.getY();
                int k = sizeButton;
                int down_space = k/2;

                if (x>=k/2 && y>= MyActivity.screen_h-k*2-down_space && x<=k+k/2 && y<= MyActivity.screen_h-k-down_space){
                    drawThread.editVect(4);
                }
                if (x>=k+k/2 && y>= MyActivity.screen_h-k*3-down_space && x<=k*2+k/2 && y<= MyActivity.screen_h-k*2-down_space){
                    drawThread.editVect(1);
                }
                if (x>=k+k/2 && y>= MyActivity.screen_h-k-down_space && x<=k*2+k/2 && y<= MyActivity.screen_h-down_space){
                    drawThread.editVect(2);
                }
                if (x>=k*2+k/2 && y>= MyActivity.screen_h-k*2-down_space && x<=k*3+k/2 && y<= MyActivity.screen_h-k-down_space){
                    drawThread.editVect(3);
                }
                return true;
            }
        });
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        drawThread.setRunning(false);
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) { return;
            }
        }
    }
}
