package test;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


public class River {
	public static final int riverWidth = 33;
	public static final int riverLength = 33;
	private int x, y;
	
	Image riverImag =  new ImageIcon("images/river.gif").getImage();
	
	
	public River(int x, int y) {//River�Ĺ��췽��
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {
		g.drawImage(riverImag,x, y, null);            //�ڶ�ӦX��Y������
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, riverWidth, riverLength);
	}


}

