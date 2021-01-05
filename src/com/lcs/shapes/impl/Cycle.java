package com.lcs.shapes.impl;


import com.lcs.shapes.ICode;
import com.lcs.shapes.Shape;

import java.awt.*;


/**
 * @Author: Changshu
 * @Date: 2020/12/16 21:32
 * @Version 1.0
 */
public class Cycle extends Shape implements ICode{
    private int space=5;
    //记录state
    private int tempState=-1;

    //上水平箭头
    private HorizontalArrow arrow1;
    //上垂直箭头
    private VerticalArrow arrow2;
    //下垂直箭头
    private VerticalArrow arrow3;
    //菱形
    private Diamond diamond;
    //矩形
    private Rectangle rectangle;

    public Cycle(){
        super();
        arrow1=new HorizontalArrow();
        arrow2=new VerticalArrow();
        arrow3=new VerticalArrow();
        diamond=new Diamond();
        rectangle=new Rectangle();
    }

    @Override
    public void draw(Graphics g, Color color) {
        int thirdX=(x2-x1)/3;
        int halfY=(y2-y1)/2;
        /*----------------箭头1-----------------*/
        arrow1.setX1(x2);
        arrow1.setY1(y1+halfY/2);
        arrow1.setX2(x1+thirdX);
        arrow1.setY2(y1);
        /*----------------箭头2-----------------*/
        arrow2.setX1(x1+thirdX+space);
        arrow2.setY1(y1-halfY);
        arrow2.setX2(x1+thirdX);
        arrow2.setY2(y1+halfY);
        /*----------------箭头3-----------------*/
        arrow3.setX1(x1+thirdX+space);
        arrow3.setY1(y2+halfY);
        arrow3.setX2(x1+thirdX);
        arrow3.setY2(y2+2*halfY);
        /*----------------菱形-----------------*/
        diamond.setX1(x1);
        diamond.setY1(y1+halfY);
        diamond.setX2(x2-thirdX);
        diamond.setY2(y2+halfY);
        /*----------------矩形-----------------*/
        rectangle.setX1(x2-thirdX);
        rectangle.setY1(y1+halfY/2);
        rectangle.setX2(x2+thirdX);
        rectangle.setY2(y2-halfY/2);

        g.setColor(color);
        g.drawLine(x1+2*thirdX,y2,x2,y2);
        g.drawLine(x2,y2,x2,y2-halfY/2);
        arrow1.draw(g,color);
        arrow2.draw(g,color);
        arrow3.draw(g,color);
        diamond.draw(g,color);
        rectangle.draw(g,color);
    }

    @Override
    public void drawCode(Graphics g, Color color, String font) {
        diamond.drawCode(g,color,font);
        rectangle.drawCode(g,color,font);
    }

    //state=0:选中图形，准备位移
    //state=1:选中上垂直箭头
    //state=2:选中右水平箭头
    //state=3:选中了右直线
    //state=4:选中下直线
    //state=5:选中了菱形
    //state=6:选中了矩形
    @Override
    public int onPress(int x, int y) {
        int thirdX=(x2-x1)/3;
        int halfY=(y2-y1)/2;
        if(x1<x&&x<x2&&y>y1&&y<y2){
            this.state=0;
        }
        if(x>x1+thirdX-space&&x<x1+thirdX+space&&y>y1&&y1<y1+halfY){
            this.state=1;
        }
        if(x>x1+thirdX&&x<x2&&y>y1-space&&y<y1+space){
            this.state=2;
        }
        if(x>x2-space&&x<x2+space&&y>y1&&y<y1+halfY/2){
            this.state=3;
        }
        if(x>x2-space&&x<x2+space&&y>y2-halfY/2&&y<y2){
            this.state=3;
        }
        if(x>x2-thirdX&&x<x2&&y>y2-space&&y<y2+space){
            this.state=4;
        }
        if(x>x1&&x<x1+2*thirdX&&y>y1+halfY&&y<y2+halfY){
            this.state=5;
        }
        if(x>x2-thirdX&&x<x2+thirdX&&y>y1+halfY/2&&y<y2-halfY/2){
            this.state=6;
        }
        tempState=this.state;
//        System.out.println(this.state);
        return this.state;
    }

    @Override
    public void onMove(int cx, int cy) {
        if(state==0){
            this.setX1(x1+cx);
            this.setY1(y1+cy);
            this.setX2(x2+cx);
            this.setY2(y2+cy);
        }
        if(state==1){
            this.setX1(x1+cx);
        }
        if (state == 2) {
            this.setY1(y1+cy);
        }
        if (state == 3) {
            this.setX2(x2+cx);
        }
        if (state == 4) {
            this.setY2(y2+cy);
        }

    }

    @Override
    public String getCode() {
        String s="";
        if(tempState==5){
            s=diamond.getCode();
        }
        if(tempState==6){
            s=rectangle.getCode();
        }
        return s;
    }

    @Override
    public void setCode(String code) {
        if(tempState==5){
            diamond.setCode(code);
        }
        if(tempState==6){
            rectangle.setCode(code);
        }
    }

    @Override
    public String getAllCode() {
        String res="";
        if(diamond.getCode()!=""){
            res+="while("+diamond.getCode()+"){\n";
        }
        if(rectangle.getCode()!=""){
            res+=rectangle.getCode()+"\n}\n";
        }
        return res;
    }
}
