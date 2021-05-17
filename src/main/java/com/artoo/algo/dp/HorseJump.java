package com.artoo.algo.dp;

public class HorseJump {

    //象棋的马，走 k步到x,y的位置
    public static int f(int x, int y, int k) {
        if(k == 0) {
            return x == 0 && y==0 ? 1 : 0;
        }

        if(x > 9 || x <0 || y <0 || y> 8) {
            return 0;
        }

        //八个方向
        //TODO
        return 0;
    }
}
