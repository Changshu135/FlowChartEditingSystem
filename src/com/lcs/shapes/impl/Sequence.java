package com.lcs.shapes.impl;

import com.lcs.shapes.ICode;
import com.lcs.shapes.Shape;


import java.awt.*;

/**
 * @Author: Changshu
 * @Date: 2020/12/7 19:49
 * @Version 1.0
 */
public class Sequence extends Shape implements ICode {
    private int space=5;
    //记录state
    private int tempState=-1;

    public VerticalArrow arrow1=null;
    public Rectangle rectangle1=null;
    public VerticalArrow arrow2=null;
    public Rectangle rectangle2=null;
//    public VerticalArrow arrow3=null;


    public Sequence(){
        super();
        arrow1=new VerticalArrow();
        rectangle1=new Rectangle();
        arrow2=new VerticalArrow();
        rectangle2=new Rectangle();
//        arrow3=new VerticalArrow();
    }

    public Sequence(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
        /**
        int width=x2-x1;
        int height=y2-y1;
        shapeList.add(new VerticalArrow(x1+width/2+1,y1,x1+width/2,y1+height/5));
        shapeList.add(new Rectangle(x1,y1+height/5,x2,y1+2*(height/5)));
        shapeList.add(new VerticalArrow(x1+width/2+1,y1+2*(height/5),x1+width/2,y1+3*(height/5)));
        shapeList.add(new Rectangle(x1,y1+3*(height/5),x2,y1+4*(height/5)));
        shapeList.add(new VerticalArrow(x1+width/2+1,y1+4*(height/5),x1+width/2,y1+5*(height/5)));
         **/
    }

    @Override
    public void draw(Graphics g, Color color) {
        int width=x2-x1;
        int height=y2-y1;
        /*-----------------------------------*/
        arrow1.setX1(x1+width/2+1);
        arrow1.setY1(y1);
        arrow1.setX2(x1+width/2);
        arrow1.setY2(y1+height/5);
        /*-----------------------------------*/
        rectangle1.setX1(x1);
        rectangle1.setY1(y1+height/5);
        rectangle1.setX2(x2);
        rectangle1.setY2(y1+2*(height/5));
        /*-----------------------------------*/
        arrow2.setX1(x1+width/2+1);
        arrow2.setY1(y1+2*(height/5));
        arrow2.setX2(x1+width/2);
        arrow2.setY2(y1+3*(height/5));
        /*-----------------------------------*/
        rectangle2.setX1(x1);
        rectangle2.setY1(y1+3*(height/5));
        rectangle2.setX2(x2);
        rectangle2.setY2(y1+4*(height/5));
        /*-----------------------------------
        arrow3.setX1(x1+width/2+1);
        arrow3.setY1(y1+4*(height/5));
        arrow3.setX2(x1+width/2);
        arrow3.setY2(y1+5*(height/5));
        */
        //画图形
        arrow1.draw(g,color);
        rectangle1.draw(g,color);
        arrow2.draw(g,color);
        rectangle2.draw(g,color);
//        arrow3.draw(g,color);
    }

    @Override
    public void drawCode(Graphics g, Color color, String font) {
//        arrow1.drawCode(g,color,font);
        rectangle1.drawCode(g,color,font);
//        arrow2.drawCode(g,color,font);
        rectangle2.drawCode(g,color,font);
//        arrow3.drawCode(g,color,font);
    }


    @Override
    public String getCode() {
        String s="";
        if(tempState==5){
            s=rectangle1.getCode();
        }else if (tempState==6){
            s=rectangle2.getCode();
        }
        return s;
    }

    @Override
    public void setCode(String code) {
        if(tempState==5){
            rectangle1.setCode(code);
        }else if (tempState==6){
            rectangle2.setCode(code);
        }

    }


    /**
     * state=1,选中左边界
     * state=2,选中上边界
     * state=3,选中右边界
     * state=4,选中下边界
     * state=5,选中上矩形
     * state=6,选中下矩形
     * state=7,选中上箭头
     * state=8,选中下箭头
     * @param x
     * @param y
     * @return
     */
    @Override
    public int onPress(int x, int y) {
        int halfX=(x2-x1)/2;
        int fifthY=(y2-y1)/5;
        if(y1<y&&y2>y&&x1-space<=x&&x1+space>=x){
            this.state=1;
        }else if(x1<x&&x<x2&&y1+fifthY-space<y&&y1+fifthY+space>y){
            this.state=2;
        }else if(y1<y&&y2>y&&x2-space<=x&&x2+space>=x){
            this.state=3;
        }else if(x1<x&&x<x2&&y2-fifthY-space<y&&y2-fifthY+space>y){
            this.state=4;
        }else if(x1<x&&x<x2&&y1+fifthY<y&&y1+2*fifthY>y){
            this.state=5;
        }else if(x1<x&&x<x2&&y1+3*fifthY<y&&y1+4*fifthY>y){
            this.state=6;
        }else if((x-x1-halfX)*(x-x1-halfX)+(y-y1)*(y-y1)<=space*space){
            this.state=7;
        }
        /*else if((x-x1-halfX)*(x-x1-halfX)+(y-y2)*(y-y2)<=space*space){
            this.state=8;
        }*/
        else{
            this.state=-1;
        }
//        System.out.println(this.state);
        tempState=this.state;
        return this.state;
    }

    @Override
    public void onMove(int cx, int cy) {
        if(state==1){
            this.setX1(x1+cx);
        }else if(state==2||state==7){
            this.setY1(y1+cy);
        }else if(state==3){
            this.setX2(x2+cx);
        }else if(state==4||state==8){
            this.setY2(y2+cy);
        }else if(state==5||state==6){
            this.setX1(x1+cx);
            this.setY1(y1+cy);
            this.setX2(x2+cx);
            this.setY2(y2+cy);
        }
    }

    @Override
    public void onRelease() {
        super.onRelease();
    }

    @Override
    public String getAllCode() {
        String res="";
        if(rectangle1.getCode()!=""){
            res+=rectangle1.getCode()+"\n";
        }
        if(rectangle2.getCode()!=""){
            res+=rectangle2.getCode()+"\n";
        }
        return res;
    }
}
