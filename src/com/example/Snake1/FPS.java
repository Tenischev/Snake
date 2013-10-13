package com.example.Snake1;

import android.os.SystemClock;

public class FPS {

    private static long lastFpsCalcUptime;
    private static long frameCounter;
    private static final long FPS_CALC_INTERVAL = 1000L;
    private static long fps;

    public static long measureFps() {
        frameCounter++;
        long now = SystemClock.uptimeMillis();
        long delta = now - lastFpsCalcUptime;
        if (delta > FPS_CALC_INTERVAL) {
            fps = frameCounter * FPS_CALC_INTERVAL / delta;
            frameCounter = 0;
            lastFpsCalcUptime = now;
        }
        return fps;
    }
}
