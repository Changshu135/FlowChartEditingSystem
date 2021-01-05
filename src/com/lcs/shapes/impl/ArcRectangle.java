package com.lcs.shapes.impl;

/**
 * @Author: Changshu
 * @Date: 2020/11/22 17:46
 * @Version 1.0
 */


import com.lcs.shapes.Shape;

import java.awt.*;

/**
 * 边是弧的矩形
 */
public class ArcRectangle extends Shape {

    public ArcRectangle(){
        super();
    }


    @Override
    public void draw(Graphics g, Color color) {
        g.setColor(color);
        g.drawArc(x1-50,y1,100,y2-y1,90,180);
        g.drawLine(x1,y1,x2,y1);
        g.drawArc(x2-50,y1,100,y2-y1,270,180);
        g.drawLine(x1,y2,x2,y2);
//        g.drawLine(x1,y1,x1,y2);
//        g.drawLine(x2,y1,x2,y2);

    }

    @Override
    public void drawCode(Graphics g, Color color, String font) {
        if(this.code==null)
            this.code="";
        g.setColor(color);
        g.setFont(new Font(font,Font.BOLD,20));
        g.drawString(this.code,x1+(x2-x1)/6,y1+5+(y2-y1)/2);
    }


}
