package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.plaf.SliderUI;

public class prop {
	
	Random r = new Random();
	int x, y;
	blood bld = new blood(x, y);
	bloom blm = new bloom(x, y);
	public static long oldTime;
	public static long nowTime;

	public prop() {
		// TODO Auto-generated constructor stub
		oldTime = System.currentTimeMillis();
		nowTime = System.currentTimeMillis();
		
	}
	
	//创建道具
	public void createProp() {
		this.x=r.nextInt(700);
		this.y=r.nextInt(400);
		
		System.out.print("被创建");
		
		switch (r.nextInt(2)) {
		case 0:
			bld = new blood(x, y);
			bld.live=true;
			break;
		case 1:
			blm = new bloom(x, y);
			blm.live=true;
			break;

		default:
			break;
		}
		
		
	}
	//绘制方法
	public void draw(Graphics g) {// 画
		//System.out.print(nowTime+"\n");
		//System.out.print(nowTime-oldTime+"\n");
		nowTime = System.currentTimeMillis();
		if((this.nowTime-this.oldTime)/1000>8) {
			this.bld.live=false;
			this.blm.live=false;
			oldTime = nowTime;
			createProp();
			
		}
		
		if(bld.live!=false) {
			bld.collideTank();
			bld.draw(g);
			
		}
		if(blm.live!=false) {
			blm.collideTank();
			blm.draw(g);
			
		}
	}

}

interface props{
	void collideTank();
}

class blood implements props{
	
	public boolean live = false;
	Image bloodimg =  new ImageIcon("images/blood.png").getImage();
	public static final int width = 40; 
	public static final int length = 40;
	int x, y;
	
	public blood(int x, int y) { // 构造函数
		this.x = x;
		this.y = y;
	}
	public void draw(Graphics g) {// 画
		if(live) {
			g.drawImage(bloodimg, x, y, null);
		}
	}
	public Rectangle getRect() {// 构造指定参数的长方形实例
		return new Rectangle(x, y, width, length);
	}
	
	
	@Override
	public void collideTank() {
		// TODO Auto-generated method stub
		if(this.getRect().intersects(Center.hero.getRect())) {
			this.live=false;
			Center.hero.setLife(100);
			
		}
		
	}
	
	
}

class bloom implements props{

	
	boolean live = false;
	Image bloomimg =  new ImageIcon("images/bloom.png").getImage();
	public static final int width = 40; 
	public static final int length = 40;
	int x, y;
	
	public bloom(int x,int y) {
		this.x =x;
		this.y=y;
		
	}
	public void draw(Graphics g) {// 画
		if(live) {
			g.drawImage(bloomimg, x, y, null);
		}
	}
	public Rectangle getRect() {// 构造指定参数的长方形实例
		return new Rectangle(x, y, width, length);
	}
	
	@Override
	public void collideTank() {
		//
		// TODO Auto-generated method stub
		if(this.getRect().intersects(Center.hero.getRect())) {
			this.live=false;
			System.out.print("吃到了\n");
			Thread ex = new Thread(new Runnable() {
				
				int step=0;
				Graphics g= Center.jp.getGraphics();
				int[] diameter = new int[] {10,11,12,13,20,21,22,23,30,31,32,33,50,51,52,53,80,81,82,83,130,131,132,150,151,152,153,200,210,220,230,231,232,233,240,241};
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while(step<diameter.length) {
						try {
							//System.out.print("爆炸\n");
							g.setColor(Color.red);
							g.fillOval(x-diameter[step], y-diameter[step], diameter[step]*2, diameter[step]*2);
							Rectangle rec = new Rectangle(x-diameter[step], y-diameter[step], diameter[step]*2, diameter[step]*2);
							
							for(int i=0;i<Center.tanks.size();i++) {
								Tank t = Center.tanks.get(i);
								if(rec.intersects(t.getRect())) {
									t.setLive(false);
									new Thread(new Music(Music.PLAY_EXPLODE)).start();
									Center.tanks.remove(t);
									Missile.count++;
								}
								
							}
							
							step++;
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
			});
			ex.start();
			
			
		}
	}
	
}
