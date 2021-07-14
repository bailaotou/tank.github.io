package test;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Missile {
	int x,y;//�ӵ�λ��
	static int hurt = 20;//�ӵ��˺�
	static int SPEED =4,NSPEED=4;
	static int count =0;//ɱ��������
	static int WIDTH = 4;//�ӵ��Ĵ�С
	static int HEIGHT = 4;
	static int missileColor=0;
	public static void setMissileColor(int missileColor1){
		missileColor = missileColor1;
	}
	boolean good;//����̹�˺û�
	boolean live = true;//�ӵ��Ĵ��
	Tank.Direction kdirection;
	
	public static int getCount(){
		return count;
	}
	
	public boolean isLive(){
		return live;
	}
	
	public static void setNspeed(int NSPEED1){
		NSPEED=NSPEED1;
	}
	
	public static void setHurt(int hurt1){
		hurt = hurt1;
	}
	
	public static int getHurt() {
		return hurt;
	}
	
	public Missile(int x,int y,Boolean good,Tank.Direction kdirection){
		this.x=x;
		this.y=y;
		this.good = good;
		this.kdirection=kdirection;
	}
	
	public void draw(Graphics g) {
		if(!live) {
			return;
		}
		else if(good){//���ݺû��������ӵ���ɫ
			g.setColor(Color.white);
			g.fillOval(x, y, WIDTH, HEIGHT);
		}else if(missileColor == 0){
			g.setColor(Color.yellow);
			g.fillOval(x, y, WIDTH, HEIGHT);
		}else if(missileColor == 1){
			g.setColor(new Color(184,134,11));
			g.fillOval(x, y, WIDTH, HEIGHT);
		}else if(missileColor == 2){
			g.setColor(new Color(125,56,125));
			g.fillOval(x, y, WIDTH, HEIGHT);
		}else if(missileColor == 3){
			g.setColor(Color.green);
			g.fillOval(x, y, WIDTH, HEIGHT);
		}
		move();//���ӵ���ʱ���Ҫ�ƶ�
	}

	 void move() {
		 if(!good){//���ݺû��������ӵ��ٶ�
			 SPEED=NSPEED;
		}else{
			SPEED=5;
		}
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
	 
	 public boolean hitTank(Tank t) {
			if(this.live && this.getRect().intersects(t.getRect()) && t.isLive() && this.good != t.isGood() ){
				if(t.isGood()){//��ұ����м�Ѫ
					t.setLife(t.getLife()- hurt);
					if(t.getLife()<=0){
						t.setLife(0);
						t.setLive(false);
					}
				}else{//�з�ֱ����
					t.setLive(false);
					count++;
				}
				this.live = false;
				new Thread(new Music(Music.PLAY_EXPLODE)).start();
				Explode e = new Explode(x, y);
				Center.explodes.add(e);
				return true;
			}
			return false;
		}

	public void hitTanks(java.util.List<Tank> tanks) {
		for(int i=0; i<tanks.size(); i++) {
			if(hitTank(tanks.get(i))) {//������hitTank����
				tanks.remove(tanks.get(i));
			}
		}
	}
	
	public boolean hitWalls(Wall w) { // �ӵ�����ͨǽ��
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.live = false;//�ӵ���ʧ
			Center.walls.remove(w); // �ӵ���ǽ��ʱ���Ƴ��˻���ǽ
			return true;
		}
		return false;
	}
	
	public boolean hitWalls(HardWall hw) {
		if (this.live && this.getRect().intersects(hw.getRect())) {
//			new Thread(new Music(Music.PLAY_HIT)).start();
			this.live = false;//�ӵ���ʧ
			return true;
		}
		return false;
	}
	
		public boolean hitHome() { // ���ӵ��򵽼�ʱ
			if (this.live && this.getRect().intersects(Center.home.getRect())) {
				this.live = false;
				Home.setLive(false); // ��ҽ���һǹ������
				return true;
			}
			return false;
		}

}

