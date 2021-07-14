package test;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


//设置界面树和丛林
public class Tree {
	public static final int width = 33;
	public static final int length = 33;
	int x, y;

	Image treeImag =  new ImageIcon("images/tree.gif").getImage();
	
	public Tree(int x, int y) { //Tree的构造方法
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {           //画出树
		g.drawImage(treeImag,x, y, null);
	}
	
	public Rectangle getRect() {// 构造指定参数的长方形实例
		return new Rectangle(x, y, width, length);
	}
	
}