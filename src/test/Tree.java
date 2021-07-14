package test;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


//���ý������ʹ���
public class Tree {
	public static final int width = 33;
	public static final int length = 33;
	int x, y;

	Image treeImag =  new ImageIcon("images/tree.gif").getImage();
	
	public Tree(int x, int y) { //Tree�Ĺ��췽��
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {           //������
		g.drawImage(treeImag,x, y, null);
	}
	
	public Rectangle getRect() {// ����ָ�������ĳ�����ʵ��
		return new Rectangle(x, y, width, length);
	}
	
}