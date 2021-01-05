package com.lcs.shapes;

import java.awt.*;

/**
 * @Author: Changshu
 * @Date: 2020/11/21 21:50
 * @Version 1.0
 */
public abstract class Shape {
    private final int bound=5;
    //(x1,y1)表示起点，(x2,y2)表示终点
    protected int x1;
    protected int y1;
    protected int x2;
    protected int y2;

    //每个图形都可以嵌入代码
    protected String code;

    //每个图形都有状态，-1表示未选中，0表示选中等等。。
    public int state;

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Shape(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.state = -1;
        this.code="";
    }

    public Shape(){
        this.state = -1;
    }


    /**
     * 画出图形
     * @param g
     * @param color
     */
    public abstract void draw(Graphics g,Color color);

    /**
     * 在图形中画出代码
     * @param g
     * @param color
     * @param font
     */
    public abstract void drawCode(Graphics g,Color color,String font);

    /**
     * 修改图形的状态
     * @param x
     * @param y
     * @return
     */
    public int onPress(int x,int y){
        //每个图形都设置一个矩形边界，
        //那么当鼠标点击矩形内部时,表明是让图形位移，此时状态变为0
        if(x>x1+bound&&x<x2-bound&&y>y1+bound&&y<y2-bound){
            state=0;
        }
        //当鼠标碰到矩形的左边界时，说明要移动矩形的左边界，此时设置状态为1
        if(x>x1-bound&&x<x1+bound&&y>y1-bound&&y<y2+bound){
            state=1;
        }
        //当鼠标碰到矩形的上边界时，说明要移动矩形的上边界，此时设置状态为2
        if(x>x1-bound&&x<x2+bound&&y>y1-bound&&y<y1+bound){
            state=2;
        }
        //当鼠标碰到矩形的右边界时，说明要移动矩形的右边界，此时设置状态为3
        if(x>x2-bound&&x<x2+bound&&y>y1-bound&&y<y2+bound){
            state=3;
        }
        //当鼠标碰到矩形的下边界时，说明要移动矩形的下边界，此时设置状态为4
        if(x>x1-bound&&x<x2+bound&&y>y2-bound&&y<y2+bound){
            state=4;
        }
        return state;
    }

    /**
     * 图形的移动
     * @param cx
     * @param cy
     */
    public void onMove(int cx,int cy){
        //整体位移
        if(state==0){
            this.setX1(x1+cx);
            this.setY1(y1+cy);
            this.setX2(x2+cx);
            this.setY2(y2+cy);
        }
        //移动左边界
        if(state==1){
            this.setX1(x1+cx);
        }
        //移动上边界
        if(state==2){
            this.setY1(y1+cy);
        }
        //移动右边界
        if(state==3){
            this.setX2(x2+cx);
        }
        //移动上边界
        if(state==4){
            this.setY2(y2+cy);
        }
    }


    /**
     * 释放图形
     */
    public void onRelease(){
        state=-1;
    }



}
