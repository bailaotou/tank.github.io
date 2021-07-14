package test;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class DoubleMissile1 {
	int x,y;//�ӵ�λ��
	static int SPEED =5;
	static int WIDTH = 5;//�ӵ��Ĵ�С
	static int HEIGHT = 5;
	boolean live = true;//�ӵ��Ĵ��
	static int fit = 20;//�ӵ����˺�
    DoubleTank1.Direction kdirection;
	
	public boolean isLive(){
		return live;
	}
	
	public static void setFit(int fit1){
		fit = fit1;
	}
	public static int getFit() {
		return fit;
	}
	
	public DoubleMissile1(int x,int y,DoubleTank1.Direction kdirection){
		this.x=x;
		this.y=y;
		this.kdirection=kdirection;
	}
	
	public void draw(Graphics g) {
		if(!live) {
			return;
		}
		if(fit == 20){
			g.setColor(Color.white);
			g.fillOval(x, y, WIDTH, HEIGHT);
		}else{
			g.setColor(Color.pink);
			g.fillOval(x-1, y-2, 7, 7);
		}
		move();//���ӵ���ʱ���Ҫ�ƶ�
	}

	 void move() {
	
		 switch(kdirection) {//ͨ����λ�ж��ٶ�
			case L:
				x -= SPEED;
				break;
			case LU:
				x -= SPEED;
				y -= SPEED;
				break;
			case U:
				y -= SPEED;
				break;
			case RU:
				x += SPEED;
				y -= SPEED;
				break;
			case R:
				x += SPEED;
				break;
			case RD:
				x += SPEED;
				y += SPEED;
				break;
			case D:
				y += SPEED;
				break;
			case LD:
				x -= SPEED;
				y += SPEED;
				break;
			case STOP:
				break;
			}
		 
			//�ӵ�Խ���˾�Ҫ��
			if(x < 0 || y < 0 || x > 800 || y > 600) {
				live = false;
			}
	}
	 
	 public Rectangle getRect() {
			return new Rectangle(x, y, WIDTH, HEIGHT);
		}
	 
	 public boolean hitTank(DoubleTank2 dt2) {
			if(this.live && this.getRect().intersects(dt2.getRect()) && dt2.isLive()){
				this.live = false;//�����ӵ���ʧ
				new Thread(new Music(Music.PLAY_EXPLODE)).start();
				dt2.setLife(dt2.getLife()-getFit());
				if(dt2.getLife()<=0){
					dt2.setLife(0);
					dt2.setLive(false);
				}
				Explode e = new Explode(x, y);
				DoubleFrame.explodes.add(e);
			}
			return false;
		}


}


