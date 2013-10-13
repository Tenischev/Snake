package com.example.Snake1;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.Random;

public class DrawThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private int disX= MyActivity.screen_w;
    private int disY= MyActivity.screen_h;
    boolean runFlag;
    Snake snake;
    Apple apple;
    private int score = 0;
    private int space = 40;
    public static Random random = new Random();

    public DrawThread(SurfaceHolder surfaceHolder, Resources resources){
        this.surfaceHolder = surfaceHolder;
        snake = new Snake(disX,disY-space);
        spawnApple();
    }

    private void spawnApple(){
        apple = new Apple(disX,disY-space);
        while (snake.pointSnake(apple.x,apple.y))
            apple = new Apple(disX,disY-space);
    }

    public void setRunning(boolean run) {
        runFlag = run;
    }

    public void editVect(int rot){
        if (!snake.pointSnake(rot))
            snake.vect = rot;
    }

    Canvas canvas;
    Paint p = new Paint();

    @Override
    public void run() {
        while (runFlag) {
            if (snake.x[0] == apple.x && snake.y[0] == apple.y){
                snake.eat();
                spawnApple();
                score++;
            }
            long fps = FPS.measureFps();
            try {
                canvas = surfaceHolder.lockCanvas(null);
                synchronized (surfaceHolder) {
                    p.setColor(Color.BLACK);
                    canvas.drawRect(0,0,disX,space,p);
                    p.setColor(Color.WHITE);
                    canvas.drawRect(0,space,disX,disY,p);

                    p.setColor(Color.RED);
                    for (int i=0;i<snake.length;i++)
                        canvas.drawRect(snake.x[i]*snake.sizeX,snake.y[i]*snake.sizeY + space,snake.x[i]*snake.sizeX+snake.sizeX,snake.y[i]*snake.sizeY+snake.sizeY + space,p);
                    p.setColor(Color.GREEN);
                    canvas.drawCircle(apple.x*apple.sizeX+apple.sizeX/2,apple.y*apple.sizeY + apple.sizeY/2 + space,apple.sizeX/2,p);

                    p.setColor(Color.BLUE);
                    p.setTextSize(20);
                    canvas.drawText("fps=" + fps, 5, 30, p);
                    canvas.drawText("Score " + score, disX-100, 30, p);

                    p.setColor(Color.GRAY);
                    p.setAlpha(175);
                    int k = MySurfaceView.sizeButton;
                    int down_space = k/2;
                    canvas.drawRect(k/2,disY-k*2-down_space,k+k/2,disY-k-down_space,p);
                    canvas.drawRect(k+k/2,disY-k*3-down_space,k*2+k/2,disY-k*2-down_space,p);
                    canvas.drawRect(k+k/2,disY-k-down_space,k*2+k/2,disY-down_space,p);
                    canvas.drawRect(k*2+k/2,disY-k*2-down_space,k*3+k/2,disY-k-down_space,p);
                }
                snake.move();
            }
            finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
