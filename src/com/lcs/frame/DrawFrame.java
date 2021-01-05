package com.lcs.frame;

import com.lcs.listener.DrawListener;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: Changshu
 * @Date: 2020/11/19 20:21
 * @Version 1.0
 */
public class DrawFrame extends JFrame {

    /**
     * 自定义初始化界面的方法
     */
    public void initUI() {
        setTitle("图形编辑系统");
        setSize(1500, 1200);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        getContentPane().setBackground(Color.WHITE);//改变背景颜色
        /*------------------------------------为界面添加控件-------------------------------------*/
        //矩形
        JButton butRectangle = new JButton("画矩形");
        add(butRectangle);
        butRectangle.setBackground(Color.ORANGE);
        //弧矩形
        JButton butArcRectangle = new JButton("画弧矩形");
        add(butArcRectangle);
        butArcRectangle.setBackground(Color.ORANGE);
        /*菱形
        JButton butDiamond = new JButton("画菱形");
        add(butDiamond);
        butDiamond.setBackground(Color.ORANGE);
         */
        /*平行四边形
        JButton butParallelogram = new JButton("画四边形");
        add(butParallelogram);
        butParallelogram.setBackground(Color.ORANGE);
         */
        //垂直箭头
        JButton butVerticalArrow = new JButton("画垂直箭头");
        add(butVerticalArrow);
        butVerticalArrow.setBackground(Color.ORANGE);
        //水平箭头
        JButton butHorizontalArrow = new JButton("画水平箭头");
        add(butHorizontalArrow);
        butHorizontalArrow.setBackground(Color.ORANGE);
        /*连接点
        JButton butCircle = new JButton("画圆形");
        add(butCircle);
        butCircle.setBackground(Color.ORANGE);
         */
        //画顺序结构
        JButton butSequence=new JButton("画顺序结构");
        add(butSequence);
        butSequence.setBackground(Color.ORANGE);
        //画判断结构
        JButton butJudge=new JButton("画判断结构");
        add(butJudge);
        butJudge.setBackground(Color.ORANGE);
        //画循环结构
        JButton butCycle=new JButton("画循环结构");
        add(butCycle);
        butCycle.setBackground(Color.ORANGE);

        //流式布局换行
        add(new JLabel("           "));     //添加空白标签来实现换行
        //编辑代码的按钮
        JButton butEdit=new JButton("编辑代码");
        butEdit.setBackground(Color.cyan);
        add(butEdit);
        //生成代码
        JButton butCode=new JButton("生成代码");
        butCode.setBackground(Color.cyan);
        add(butCode);
        //删除图形，只能按顺序删
        JButton butDelete=new JButton("删除图形");
        butDelete.setBackground(Color.cyan);
        add(butDelete);
        /*---------------------------------------------------------------------------------------*/
        setVisible(true);
        //获取窗体上画笔画布对象(注意：必须要在窗体可见之后才能获取画笔画布对象，否则获取的是null)
        Graphics g = getGraphics();
        //实例化DrawListener事件处理类的对象，对象名dl
        DrawListener dl = new DrawListener();
        //给事件源窗体对象添加addMouseListener()鼠标监听方法，指定事件处理类对象dl.
        addMouseListener(dl);
        addMouseMotionListener(dl);
        //调用画图Graphics
        dl.setGraphics(g);
        //按钮的动作监听，按钮是事件源，也就是说只有当按下按钮才会执行画图的动作，可以参考登录界面的验证登录
        butRectangle.addActionListener(dl);
        butArcRectangle.addActionListener(dl);
//        butDiamond.addActionListener(dl);
//        butParallelogram.addActionListener(dl);
//        butCircle.addActionListener(dl);
        butVerticalArrow.addActionListener(dl);
        butHorizontalArrow.addActionListener(dl);
        butSequence.addActionListener(dl);
        butJudge.addActionListener(dl);
        butCycle.addActionListener(dl);

        butEdit.addActionListener(dl);
        butCode.addActionListener(dl);
        butDelete.addActionListener(dl);
    }

}
