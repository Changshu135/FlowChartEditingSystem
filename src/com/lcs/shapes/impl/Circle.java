package com.lcs.shapes.impl;

/**
 * @Author: Changshu
 * @Date: 2020/11/22 21:14
 * @Version 1.0
 */

import com.lcs.shapes.Shape;

import java.awt.*;

/**
 * 画连接点
 */
public class Circle extends Shape {

    @Override
    public void setX1(int x1) {
        super.setX1(x1);
    }

    @Override
    public void setY1(int y1) {
        super.setY1(y1);
    }

    @Override
    public void setX2(int x2) {
        super.setX2(x1+10);
    }

    @Override
    public void setY2(int y2) {
        super.setY2(y1+10);
    }

    @Override
    public int onPress(int x, int y) {
        //每个图形都设置一个矩形边界，
        //那么当鼠标点击矩形内部时,表明是让图形位移，此时状态变为0
        if(x>x1-5&&x<x2+5&&y>y1-5&&y<y2+5){
            state=0;
        }
        return state;
    }

    @Override
    public void onMove(int cx, int cy) {
        //整体位移
        if(state==0){
            this.setX1(x1+cx);
            this.setY1(y1+cy);
            this.setX2(x2+cx);
            this.setY2(y2+cy);
        }
    }

    @Override
    public void draw(Graphics g, Color color) {
        g.setColor(color);
        g.fillOval(x1,y1,10,10);
    }

    @Override
    public void drawCode(Graphics g, Color color, String font) {

    }
}
