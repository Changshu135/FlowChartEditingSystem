package com.lcs.shapes.impl;

import com.lcs.shapes.Shape;

import java.awt.*;

/**
 * @Author: Changshu
 * @Date: 2020/11/21 21:55
 * @Version 1.0
 */
public class Rectangle extends Shape {

    public Rectangle(){
        super();
    }

    public Rectangle(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void draw(Graphics g, Color color) {
        g.setColor(color);
        g.drawRect(x1,y1,x2-x1,y2-y1);

    }

    @Override
    public void drawCode(Graphics g, Color color, String font) {
        if(this.code==null)
            this.code="";
        g.setColor(color);
        g.setFont(new Font(font,Font.BOLD,15));
        g.drawString(this.code,x1+(x2-x1)/6,y1+(y2-y1)/2);
    }


}
