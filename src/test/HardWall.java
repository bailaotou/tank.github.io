package test;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class HardWall {
	public static final int width = 33; // 设置金属墙的长宽静态全局参数
	public static final int length = 33;
	private int x, y;
	Image hWallImag =  new ImageIcon("images/hWall.gif").getImage();

	public HardWall(int x, int y) {// 构造函数，传递要构造的长宽并赋值
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g) { // 画金属墙
		g.drawImage(hWallImag, x, y, null);
	}

	public Rectangle getRect() { // 构造指定参数的长方形实例
		return new Rectangle(x, y, width, length);
	}
}