package test;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class DoubleMissile1 {
	int x,y;//子弹位置
	static int SPEED =5;
	static int WIDTH = 5;//子弹的大小
	static int HEIGHT = 5;
	boolean live = true;//子弹的存活
	static int fit = 20;//子弹的伤害
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
		move();//画子弹的时候就要移动
	}

	 void move() {
	
		 switch(kdirection) {//通过方位判断速度
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
		 
			//子弹越界了就要死
			if(x < 0 || y < 0 || x > 800 || y > 600) {
				live = false;
			}
	}
	 
	 public Rectangle getRect() {
			return new Rectangle(x, y, WIDTH, HEIGHT);
		}
	 
	 public boolean hitTank(DoubleTank2 dt2) {
			if(this.live && this.getRect().intersects(dt2.getRect()) && dt2.isLive()){
				this.live = false;//击中子弹消失
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


