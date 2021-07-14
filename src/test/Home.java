package test;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Home {
    static int x = 290, y = 250;// �ҵĳ�ʼλ��
	public static final int width = 35, length = 35; // ȫ�־�̬��������
	static boolean live = true;

	Image homeImags = new ImageIcon("images/home.gif").getImage();
	
	public boolean isLive() { // �ж��Ƿ񻹻���
		return live;
	}

	public static void setLive(boolean live1) { // ���ô��
		live = live1;
	}
	
	public static void setHomeLocation(int x1, int y1) {// ���캯��������Home�Ĳ�������ֵ
		x = x1;
		y = y1;
	}
	
	public void draw(Graphics g) {
		if (live) { // ������ţ��򻭳�home
			g.drawImage(homeImags, x, y, null);
		} else {
			Center.hero.setLife(0);
			Center.hero.setLive(false);
		}
	}
	
	public Rectangle getRect() { // ���ؼҵķ�Χ
		return new Rectangle(x, y, width, length);
	}
	
}

