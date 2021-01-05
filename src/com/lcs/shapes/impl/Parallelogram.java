package com.lcs.shapes.impl;

/**
 * @Author: Changshu
 * @Date: 2020/11/22 20:06
 * @Version 1.0
 */

import com.lcs.shapes.Shape;

import java.awt.*;

/**
 * 平行四边形
 */
public class Parallelogram extends Shape {
    @Override
    public void draw(Graphics g, Color color) {
        g.setColor(color);
        int[]xPoints={x1,x2+50,x2,x1-50};
        int[]yPoints={y1,y1,y2,y2};
        g.drawPolygon(xPoints,yPoints,4);
    }

    @Override
    public void drawCode(Graphics g, Color color, String font) {
        if(this.code==null)
            this.code="";
        g.setColor(color);
        g.setFont(new Font(font,Font.BOLD,15));
        g.drawString(this.code,x1+(x2-x1)/3,y1+(y2-y1)/2);
    }
}
