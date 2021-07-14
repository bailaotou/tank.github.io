package test;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Home {
    static int x = 290, y = 250;// 家的初始位置
	public static final int width = 35, length = 35; // 全局静态变量长宽
	static boolean live = true;

	Image homeImags = new ImageIcon("images/home.gif").getImage();
	
	public boolean isLive() { // 判读是否还活着
		return live;
	}

	public static void setLive(boolean live1) { // 设置存活
		live = live1;
	}
	
	public static void setHomeLocation(int x1, int y1) {// 构造函数，传递Home的参数并赋值
		x = x1;
		y = y1;
	}
	
	public void draw(Graphics g) {
		if (live) { // 如果活着，则画出home
			g.drawImage(homeImags, x, y, null);
		} else {
			Center.hero.setLife(0);
			Center.hero.setLive(false);
		}
	}
	
	public Rectangle getRect() { // 返回家的范围
		return new Rectangle(x, y, width, length);
	}
	
}

