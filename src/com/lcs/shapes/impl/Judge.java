package com.lcs.shapes.impl;

import com.lcs.shapes.ICode;
import com.lcs.shapes.Shape;

import java.awt.*;

/**
 * @Author: Changshu
 * @Date: 2020/12/14 22:20
 * @Version 1.0
 */

/**
 * 判断结构
 */
public class Judge extends Shape implements ICode {
    private int space=5;
    //记录state
    private int tempState=-1;

    private Diamond diamond;
    //左上箭头1
    private VerticalArrow arrow1;
    //右上箭头2
    private VerticalArrow arrow2;
    //右下箭头3
    private VerticalArrow arrow3;
    //左下箭头4
    private VerticalArrow arrow4;
    //顶箭头
    private VerticalArrow arrow5;
    //底箭头
    private VerticalArrow arrow6;
    //左矩形
    private Rectangle rectangle1;
    //右矩形
    private Rectangle rectangle2;

    public Judge(){
        super();
        diamond=new Diamond();
        arrow1=new VerticalArrow();
        arrow2=new VerticalArrow();
        arrow3=new VerticalArrow();
        arrow4=new VerticalArrow();
        arrow5=new VerticalArrow();
        arrow6=new VerticalArrow();
        rectangle1=new Rectangle();
        rectangle2=new Rectangle();
    }

    public Judge(int x1, int y1, int x2, int y2){
        super(x1, y1, x2, y2);
    }

    @Override
    public void draw(Graphics g, Color color) {
        int fourthX=(x2-x1)/4;
        int fourthY=(y2-y1)/4;
        /*----------------菱形-----------------*/
        diamond.setX1(x1+fourthX);
        diamond.setY1(y1-fourthY);
        diamond.setX2(x2-fourthX);
        diamond.setY2(y1+fourthY);
        /*----------------箭头1-----------------*/
        arrow1.setX1(x1+fourthX);
        arrow1.setY1(y1);
        arrow1.setX2(x1);
        arrow1.setY2(y1+2*fourthY);
        /*----------------箭头2-----------------*/
        arrow2.setX1(x2-fourthX);
        arrow2.setY1(y1);
        arrow2.setX2(x2);
        arrow2.setY2(y1+2*fourthY);
        /*----------------箭头3-----------------*/
        arrow3.setX1(x2-space);
        arrow3.setY1(y2-fourthY);
        arrow3.setX2(x2);
        arrow3.setY2(y2);
        /*----------------箭头4-----------------*/
        arrow4.setX1(x1+space);
        arrow4.setY1(y2-fourthY);
        arrow4.setX2(x1);
        arrow4.setY2(y2);
        /*----------------箭头5-----------------*/
        arrow5.setX1(x1+2*fourthX+space);
        arrow5.setY1(y1-2*fourthY);
        arrow5.setX2(x1+2*fourthX);
        arrow5.setY2(y1-fourthY);
        /*----------------画直线-----------------*/
        g.setColor(color);
        g.drawLine(x1,y2,x2,y2);
        /*----------------箭头6-----------------*/
        arrow6.setX1(x1+2*fourthX+space);
        arrow6.setY1(y2);
        arrow6.setX2(x1+2*fourthX);
        arrow6.setY2(y2+fourthY);


        /*----------------左矩形-----------------*/
        rectangle1.setX1(x1-fourthX);
        rectangle1.setY1(y1+2*fourthY);
        rectangle1.setX2(x1+fourthX);
        rectangle1.setY2(y2-fourthY);
        /*----------------右矩形-----------------*/
        rectangle2.setX1(x2-fourthX);
        rectangle2.setY1(y1+2*fourthY);
        rectangle2.setX2(x2+fourthX);
        rectangle2.setY2(y2-fourthY);

        diamond.draw(g,color);
        arrow1.draw(g,color);
        arrow2.draw(g,color);
        arrow3.draw(g,color);
        arrow4.draw(g,color);
        arrow5.draw(g,color);
        arrow6.draw(g,color);
        rectangle1.draw(g,color);
        rectangle2.draw(g,color);



    }

    @Override
    public void drawCode(Graphics g, Color color, String font) {
        arrow1.drawCode(g,color,font);
        arrow2.drawCode(g,color,font);
        diamond.drawCode(g,color,font);
        rectangle1.drawCode(g,color,font);
        rectangle2.drawCode(g,color,font);
    }

    @Override
    public String getCode() {
        String s="";
        if(tempState==1){
            s=arrow1.getCode();
        }else if(tempState==3){
            s=arrow2.getCode();
        }else if(tempState==5){
            s=diamond.getCode();
        }else if(tempState==6){
            s=rectangle1.getCode();
        }else if(tempState==7){
            s=rectangle2.getCode();
        }
        return s;
    }

    @Override
    public void setCode(String code) {
        if(tempState==1){
            arrow1.setCode(code);
        }else if(tempState==3){
            arrow2.setCode(code);
        }else if(tempState==5){
            diamond.setCode(code);
        }else if(tempState==6){
            rectangle1.setCode(code);
        }else if(tempState==7){
            rectangle2.setCode(code);
        }
    }

    //state=0:选中图形内部，让图形位移
    //state=1:左边界
    //state=2:上边界
    //state=3:右边界
    //state=4:下边界
    //state=5:选中菱形
    //state=6:选中左矩形
    //state=7:选中右矩形
    @Override
    public int onPress(int x, int y) {
        int fourthX=(x2-x1)/4;
        int fourthY=(y2-y1)/4;
        if(x>x1+space&&x<x2-space&&y>y1+space&&y<y2-space){
            state=0;
        }
        if(x>x1-space&&x<x1+space&&y>y1-space&&y<y2+space){
            state=1;
        }
        if(x>x1-space&&x<x2+space&&y>y1-space&&y<y1+space){
            state=2;
        }
        if(x>x2-space&&x<x2+space&&y>y1-space&&y<y2+space){
            state=3;
        }
        if(x>x1-space&&x<x2+space&&y>y2-space&&y<y2+space){
            state=4;
        }
        if(x>x1+fourthX&&x<x2-fourthX&&y>y1-fourthY&&y<y1+fourthY){
            state=5;
        }
        if(x>x1-fourthX&&x<x1+fourthX&&y>y1+2*fourthY&&y<y2-fourthY){
            state=6;
        }
        if(x>x2-fourthX&&x<x2+fourthX&&y>y1+2*fourthY&&y<y2-fourthY){
            state=7;
        }
        tempState=this.state;
        return state;
    }

    @Override
    public void onMove(int cx, int cy) {
        if (state==0){
            this.setX1(x1+cx);
            this.setY1(y1+cy);
            this.setX2(x2+cx);
            this.setY2(y2+cy);
        }else if(state==1){
            this.setX1(x1+cx);
        }else if(state==2){
            this.setY1(y1+cy);
        }else if(state==3){
            this.setX2(x2+cx);
        }else if(state==4){
            this.setY2(y2+cy);
        }

    }


    @Override
    public String getAllCode() {
        String res="";
        if(diamond.getCode()!=""){
            res+="if("+diamond.getCode()+"){"+"\n";
        }
        if(rectangle1.getCode()!=""){
            res+=rectangle1.getCode()+"\n}"+"\n";
        }else{
            res+="}\n";
        }
        if(rectangle2.getCode()!=""){
            res+="else{\n"+rectangle2.getCode()+"\n}\n";
        }
        return res;
    }
}
