package test;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class HardWall {
	public static final int width = 33; // ���ý���ǽ�ĳ���̬ȫ�ֲ���
	public static final int length = 33;
	private int x, y;
	Image hWallImag =  new ImageIcon("images/hWall.gif").getImage();

	public HardWall(int x, int y) {// ���캯��������Ҫ����ĳ�����ֵ
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g) { // ������ǽ
		g.drawImage(hWallImag, x, y, null);
	}

	public Rectangle getRect() { // ����ָ�������ĳ�����ʵ��
		return new Rectangle(x, y, width, length);
	}
}