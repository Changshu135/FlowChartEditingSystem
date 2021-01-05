package com.lcs.listener;

import com.lcs.shapes.ICode;
import com.lcs.shapes.Shape;
import com.lcs.shapes.impl.*;
import com.lcs.shapes.impl.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Changshu
 * @Date: 2020/11/19 20:22
 * @Version 1.0
 */
public class DrawListener implements MouseListener, MouseMotionListener, ActionListener {
    private int x1, y1, x2, y2;// 声明四个整数变量，用来记录按下和释放时的坐标值
    private Graphics2D g;
    private String type;
    //图形集合，统一管理
    private List<Shape> shapes;
    //此时被选中的图形，规定一次只能选中一个图形
    private Shape selectedShape;
    //记录被选中图形的原始状态
//    private Shape oldShape;

    public DrawListener() {
        //因为可能会经常增加和删除，所以使用linkedlist
        shapes=new LinkedList<Shape>();
        type="";
        selectedShape=null;
    }

    public void setGraphics(Graphics g) {
        this.g = (Graphics2D)g;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        type = e.getActionCommand();// ActionCommand获取按钮上的文字或者获取事件源对象
        if(type.equals("画矩形")){
            Rectangle rectangle=new Rectangle();
            shapes.add(rectangle);
            selectedShape=rectangle;
        }
        if(type.equals("画弧矩形")){
            ArcRectangle arcRectangle=new ArcRectangle();
            shapes.add(arcRectangle);
            selectedShape=arcRectangle;
        }
        if(type.equals("画菱形")){
            Diamond diamond=new Diamond();
            shapes.add(diamond);
            selectedShape=diamond;
        }
        if(type.equals("画四边形")){
            Parallelogram parallelogram=new Parallelogram();
            shapes.add(parallelogram);
            selectedShape=parallelogram;
        }
        if(type.equals("画圆形")){
            Circle circle=new Circle();
            shapes.add(circle);
            selectedShape=circle;
        }
        if(type.equals("画垂直箭头")){
            VerticalArrow verticalArrow=new VerticalArrow();
            shapes.add(verticalArrow);
            selectedShape=verticalArrow;
        }
        if(type.equals("画水平箭头")){
            HorizontalArrow horizontalArrow=new HorizontalArrow();
            shapes.add(horizontalArrow);
            selectedShape=horizontalArrow;
        }
        if(type.equals("画顺序结构")){
            Sequence sequence=new Sequence();
            shapes.add(sequence);
            selectedShape=sequence;
        }
        if(type.equals("画判断结构")){
            Judge judge=new Judge();
            shapes.add(judge);
            selectedShape=judge;
        }
        if(type.equals("画循环结构")){
            Cycle cycle=new Cycle();
            shapes.add(cycle);
            selectedShape=cycle;
        }
        if(type.equals("编辑代码")){
            editCode();
        }
        if(type.equals("生成代码")){
            if(shapes!=null&&shapes.size()!=0) {
                String code = generateCode();
                System.out.println(code);
                writeIntoFile("code.txt", code);
            }
        }
        if(type.equals("删除图形")) {
//            System.out.println("删除图形");
            deleteShape();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
        if(type.contains("画")){
            selectedShape.setX1(x1);
            selectedShape.setY1(y1);
        }else{
            for(Shape shape:shapes){
                if(shape.onPress(x1,y1)!=-1){
                    selectedShape=shape;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        x2=e.getX();
        y2=e.getY();
        if(type.contains("画")){
            selectedShape.setX2(x2);
            selectedShape.setY2(y2);
            selectedShape.draw(g, Color.black);
        }else{
            int cx=x2-x1;
            int cy=y2-y1;
            if (selectedShape!=null) {
                selectedShape.draw(g, Color.white);
                selectedShape.drawCode(g,Color.WHITE,"Arial");
                selectedShape.onMove(cx, cy);
                selectedShape.draw(g, Color.black);
                selectedShape.drawCode(g,Color.black,"Arial");
            }
        }
        type="";
        for (Shape shape:shapes){
            shape.onRelease();
        }
    }

    /**
     * 编辑被选中图形的代码
     */
    public void editCode(){
        if(selectedShape==null){
            JOptionPane.showMessageDialog(null, "还没有被选中的图形", "【请注意】", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String inputCode=JOptionPane.showInputDialog(null,"请键入代码",selectedShape.getCode());
//        System.out.println(inputCode);
        if(inputCode!=null) {
            selectedShape.drawCode(g, Color.white,"Arial");
            selectedShape.setCode(inputCode);
            selectedShape.drawCode(g, Color.black,"Arial");
        }
    }

    /**
     * 删除图形,只能删除最后添加的图形

    public void deleteShape(){
        if(shapes.isEmpty())
            return;
        Shape shape=shapes.get(shapes.size()-1);
        shape.draw(g, Color.white);
        shape.drawCode(g,Color.white,"Arial");
        if (selectedShape==shape){
            selectedShape=null;
        }
        shape=null;
        shapes.remove(shapes.size()-1);
    }
     */
    public void deleteShape(){
        for (int i=0;i<shapes.size();i++){
            Shape shape=shapes.get(i);
            if(shape==selectedShape){
                shapes.remove(i);
            }
        }
        if(selectedShape!=null) {
            selectedShape.draw(g, Color.white);
            selectedShape.drawCode(g, Color.white, "Arial");
        }
        selectedShape=null;
    }

    /**
     * 生成代码
     */
    private String generateCode(){
        String res="public static void main(String[] args){\n"+
                "Scanner scanner=new Scanner(System.in);\n";
        for (Shape shape:shapes){
            if(shape instanceof Sequence||shape instanceof Judge||shape instanceof Cycle){
                res+=((ICode) shape).getAllCode();
            }else if(shape instanceof Rectangle){
                res+="\n"+shape.getCode()+"\n";
            }
        }
        res+="}";
        return res;
    }

    private void writeIntoFile(String srcFile,String content){
        File file=new File(srcFile);
        if(file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedWriter bw=null;
        try {
            bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            bw.write(content);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
