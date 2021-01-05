package com.lcs.shapes.impl;

import com.lcs.shapes.Shape;

import java.awt.*;

/**
 * @Author: Changshu
 * @Date: 2020/11/22 21:41
 * @Version 1.0
 */
public class VerticalArrow extends Shape {
    private int d=5;

    public VerticalArrow(){
        super();
    }

    public VerticalArrow(int x1, int y1, int x2, int y2){
        super(x1,y1,x2,y2);
    }

    /**
     * 只允许两个点动
     * @param x
     * @param y
     * @return
     */
    @Override
    public int onPress(int x, int y) {
        //点击转折点是可位移状态
        if(x>x2-d&&x<x2+d&&y>y1-d&&y<y1+d){
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
            this.setX1(x1+cx);
        }
        if (state==3){
            this.setY2(y2+cy);
        }
    }

    @Override
    public void draw(Graphics g, Color color) {
        g.setColor(color);
        g.drawLine(x1,y1,x2,y1);
        g.drawLine(x2,y1,x2,y2);
        if(y2>y1) {
            g.drawLine(x2, y2, x2 - 5, y2 - 5);
            g.drawLine(x2, y2, x2 + 5, y2 - 5);
        }else{
            g.drawLine(x2, y2, x2 - 5, y2 + 5);
            g.drawLine(x2, y2, x2 + 5, y2 + 5);
        }
    }

    @Override
    public void drawCode(Graphics g, Color color, String font) {
        if(this.code==null)
            this.code="";
        g.setColor(color);
        g.setFont(new Font(font,Font.BOLD,15));
        int x=x1>x2?x1:x2;
        g.drawString(this.code,x-Math.abs(x1-x2)/2,y1-10);

    }
}
