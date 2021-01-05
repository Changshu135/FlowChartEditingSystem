package com.lcs.shapes.impl;

import com.lcs.shapes.Shape;

import java.awt.*;

/**
 * @Author: Changshu
 * @Date: 2020/11/22 22:13
 * @Version 1.0
 */
public class HorizontalArrow extends Shape {
    private int d=5;

    @Override
    public int onPress(int x, int y) {
        //点击转折点是可位移状态
        if(x>x1-d&&x<x1+d&&y>y2-d&&y<y2+d){
            state=1;
        }
        //点击起点
        if(x>x1-d&&x<x1+d&&y>y1-d&&y<y1+d){
            state=2;
        }
        //点击终点
        if(x>x2-d&&x<x2+d&&y>y2-d&&y<y2+d){
            state=3;
        }
        return state;
    }

    @Override
    public void onMove(int cx, int cy) {
        if (state==1){
            this.setX1(x1+cx);
            this.setY1(y1+cy);
            this.setX2(x2+cx);
            this.setY2(y2+cy);
        }
        if(state==2){
            this.setY1(y1+cy);
        }
        if (state==3){
            this.setX2(x2+cx);
        }
    }

    @Override
    public void draw(Graphics g, Color color) {
        g.setColor(color);
        g.drawLine(x1,y1,x1,y2);
        g.drawLine(x1,y2,x2,y2);
        if(x2>x1) {
            g.drawLine(x2, y2, x2 - 5, y2 - 5);
            g.drawLine(x2, y2, x2 - 5, y2 + 5);
        }else{
            g.drawLine(x2, y2, x2 + 5, y2 - 5);
            g.drawLine(x2, y2, x2 + 5, y2 + 5);
        }

    }

    @Override
    public void drawCode(Graphics g, Color color, String font) {

    }
}
