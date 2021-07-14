package test;
import java.awt.*;
import java.util.Random;

import javax.swing.ImageIcon;

public class Arm {
	
	public static final int width = 36;//����ͼ��Ĵ�С
	public static final int length = 36;

	static Random r = new Random();
	static int x = r.nextInt(300)+200;
	static int y = r.nextInt(300)+200;
	
	public static void setArmSeat(int x1, int y1){
		x = x1;
		y = y1;
	}
	
	private static Image armImag = new ImageIcon("images/arm1.png").getImage();

	public void draw(Graphics g) {
		g.drawImage(armImag, x, y, null);
	}

	public Rectangle getRect() { //���ز����Ĵ�С
		return new Rectangle(x, y, width, length);
	}

}

