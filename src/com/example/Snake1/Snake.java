package com.example.Snake1;

public class Snake {
    public int length;
    public int[] x = new int[1000];
    public int[] y = new int[1000];
    public int sizeX=10,sizeY=10;
    int poleX,poleY;
    int vect;
    boolean eatFlag;

    public Snake(int disX,int disY){
        poleX = disX/sizeX;
        poleY = disY/sizeY;
        length = 1;
        x[0]=poleX/2;
        y[0]=poleY/2;
        vect = DrawThread.random.nextInt(4) + 1;
    }

    public void eat(){
        eatFlag = true;
    }

    public void move(){
        int r=0,t=0;
        if (eatFlag){
            r=x[length-1];
            t=y[length-1];
        }
        if (vect == 4) {
            if (length > 1 && x[1] != x[0] - 1) {
                for (int i=length - 1;i>0;i--){
                    x[i] = x[i - 1];
                    y[i] = y[i - 1];
                }
            }
            x[0]--;
            x[0] = (x[0] + poleX) % poleX;
        }
        if (vect == 2) {
            if (length > 1 && y[1] != y[0] + 1) {
                for (int i=length - 1;i>0;i--){
                    x[i] = x[i - 1];
                    y[i] = y[i - 1];
                }
            }
            y[0]++;
            y[0] = y[0] % poleY;
        }
        if (vect == 3) {
            if (length > 1 && x[1] != x[0] + 1) {
                for (int i=length - 1;i>0;i--){
                    x[i] = x[i - 1];
                    y[i] = y[i - 1];
                }
            }
            x[0]++;
            x[0] = x[0] % poleX;
            }
        if (vect == 1) {
            if (length > 1 && y[1] != y[0] - 1) {
                for (int i=length - 1;i>0;i--){
                    x[i] = x[i - 1];
                    y[i] = y[i - 1];
                }
            }
            y[0]--;
            y[0] = (y[0] + poleY) % poleY;
        }
        if (eatFlag){
            eatFlag = false;
            x[length]=r;
            y[length]=t;
            length++;
        }
    }

    public boolean pointSnake(int X,int Y){
        boolean flag = false;
        for (int i=1;i<length;i++)
            if (X == x[i] && Y == y[i]){
                flag = true;
            }
        return flag;
    }
    public boolean pointSnake(int rot){
        int X = x[0];
        int Y = y[0];
        if (rot == 4)
            X = (X - 1 + poleX) % poleX;
        if (rot == 3)
            X = (X + 1 + poleX) % poleX;
        if (rot == 2)
            Y = (Y + 1 + poleY) % poleY;
        if (rot == 1)
            Y = (Y - 1 + poleY) % poleY;
        return pointSnake(X,Y);
    }
}
