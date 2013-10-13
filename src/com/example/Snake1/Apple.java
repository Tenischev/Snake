package com.example.Snake1;

public class Apple {

    int x,y;
    int sizeX=10,sizeY=10;

    public Apple(int displayX,int displayY){
        x = DrawThread.random.nextInt(3523123) % (displayX / sizeX);
        y = DrawThread.random.nextInt(421412315) % (displayY / sizeY);
    }
}
