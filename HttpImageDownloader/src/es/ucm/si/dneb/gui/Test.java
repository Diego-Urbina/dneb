package es.ucm.si.dneb.gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class Test {

JFrame frame;
JTabbedPane tabPane;
public Test() throws Exception {
frame=new JFrame();

frame.getContentPane().setLayout(new BorderLayout());
tabPane=new JTabbedPane();
tabPane.addTab("test1", new JLabel("1"));
tabPane.addTab("test2", new JLabel("2"));
tabPane.addTab("test3", new JLabel("3"));
tabPane.setUI(new MyTabbedPaneUI());
frame.getContentPane().add(tabPane);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setSize(200,200);
frame.setLocationRelativeTo(null);
frame.setVisible(true);

}

public static void main(String[] args) throws Exception {
Test test=new Test();
}
}

class MyTabbedPaneUI extends BasicTabbedPaneUI {
public MyTabbedPaneUI() {
super();
}

protected void paintTab(Graphics g, int tabPlacement,
Rectangle[] rects, int tabIndex,
Rectangle iconRect, Rectangle textRect) {

super.paintTab(g,tabPlacement,rects,tabIndex,iconRect,textRect);

Rectangle rect=rects[tabIndex];
g.setColor(Color.black);
g.drawRect(rect.x+5,rect.y+5,10,10);
g.drawLine(rect.x+5,rect.y+5,rect.x+15,rect.y+15);
g.drawLine(rect.x+15,rect.y+5,rect.x+5,rect.y+15);
}

protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
return super.calculateTabWidth(tabPlacement,tabIndex,metrics);
}

protected MouseListener createMouseListener() {
return new MyMouseHandler();
}

class MyMouseHandler extends MouseHandler {
public MyMouseHandler() {
super();
}
public void mouseClicked(MouseEvent e) {
int x=e.getX();
int y=e.getY();
int tabIndex=-1;
int tabCount = tabPane.getTabCount();
for (int i = 0; i < tabCount; i++) {
if (rects[ i ].contains(x, y)) {
tabIndex= i;
break;
}
}
if (tabIndex >= 0) {
Rectangle tabRect=rects[tabIndex];
x=x-tabRect.x;
y=y-tabRect.y;
if ((x>=5) && (x<=15) && (y>=5) && (y<=15)) {
tabPane.remove(tabIndex);
}
}
}

}
} 
