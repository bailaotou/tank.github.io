package test;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.ImageIcon;

public class Tank implements KeyListener {
	private int x, y;// ̹������
	private int oldX, oldY;// ̹����һ������
	static int SPEED = 3;// ��̹���ٶ�
	static int NSPEED = 3;// ��̹���ٶ�
	static int tankColor = 0;// ̹����ɫ����
	public static void setTankColor(int tankColor1){
		tankColor = tankColor1;
	}
	private int life = 100;// ����ֵ
	private boolean live = true;// ̹�˴��
	private boolean good;// ����̹�˺û�
	private static Random r = new Random();// �������������
	private int step; // ����һ�������,���ģ��̹�˵��ƶ�·��
	boolean bU = false, bD = false, bL = false, bR = false;
	private Direction direction = Direction.STOP;// ̹�˳�ʼ״̬
	private Direction kdirection = Direction.U;// ̹�˳�ʼ����

	enum Direction {// �����ö��
		U, D, L, R, LU, LD, RU, RD, STOP
	};

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}
	
	//����̹��λ��
	public void setTankLocation(int x,int y,Direction direction){
		this.x=x;
		this.y=y;
		this.direction=direction;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getLife() {
		return life;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public boolean isLive() {
		return live;
	}

	public void setGood(boolean good) {
		this.good = good;
	}

	public boolean isGood() {
		return good;
	}
	
	public static void setNspeed(int NSPEED1){
		NSPEED = NSPEED1;
	}
	
	//�жϷ�Χ���ӵ�����̹�ˣ�
	public Rectangle getRect() {
		return new Rectangle(x, y, 35, 35);
	}
 
	static Image[] tanksImages = null;

	static {
		tanksImages = new Image[] {
				// ���̹��8�������ͼƬ
				new ImageIcon("images/tankU.gif").getImage(),
				new ImageIcon("images/tankD.gif").getImage(),
				new ImageIcon("images/tankL.gif").getImage(),
				new ImageIcon("images/tankR.gif").getImage(),
				new ImageIcon("images/tankLU.gif").getImage(),
				new ImageIcon("images/tankLD.gif").getImage(),
				new ImageIcon("images/tankRU.gif").getImage(),
				new ImageIcon("images/tankRD.gif").getImage(),
				// �з�4�������ͼƬ
				new ImageIcon("images/EtankU.png").getImage(),
				new ImageIcon("images/EtankD.png").getImage(),
				new ImageIcon("images/EtankL.png").getImage(),
				new ImageIcon("images/EtankR.png").getImage(),
				//�Ѷ�3���˵�̹��
				new ImageIcon("images/E3tankU.png").getImage(),
				new ImageIcon("images/E3tankD.png").getImage(),
				new ImageIcon("images/E3tankL.png").getImage(),
				new ImageIcon("images/E3tankR.png").getImage(),
				//�Ѷ�1���˵�̹��
				new ImageIcon("images/E1tankU.png").getImage(),
				new ImageIcon("images/E1tankD.png").getImage(),
				new ImageIcon("images/E1tankL.png").getImage(),
				new ImageIcon("images/E1tankR.png").getImage(),
				//�Ѷ�2���˵�̹��
				new ImageIcon("images/E2tankU.png").getImage(),
				new ImageIcon("images/E2tankD.png").getImage(),
				new ImageIcon("images/E2tankL.png").getImage(),
				new ImageIcon("images/E2tankR.png").getImage(),
				};
	}

	// ̹�˵Ĺ��췽��
	public Tank(int x, int y, boolean good) {
		this.x = x;
		this.y = y;
		this.good = good;
		this.oldX = x;
		this.oldY = y;
	}

	public Tank(int x, int y, boolean good, Direction direction) {
		this(x, y, good);
		this.direction = direction;
	}

	//��̹�˵ķ���
	public void draw(Graphics g) {
		if (live && good ) {
			switch (kdirection) {
			case U:
				g.drawImage(tanksImages[0], x, y, null);
				break;
			case D:
				g.drawImage(tanksImages[1], x, y, null);
				break;
			case L:
				g.drawImage(tanksImages[2], x, y, null);
				break;
			case R:
				g.drawImage(tanksImages[3], x, y, null);
				break;
			case LU:
				g.drawImage(tanksImages[4], x, y, null);
				break;
			case LD:
				g.drawImage(tanksImages[5], x, y, null);
				break;
			case RU:
				g.drawImage(tanksImages[6], x, y, null);
				break;
			case RD:
				g.drawImage(tanksImages[7], x, y, null);
				break;
			}
		}
		else if(live&&!good&&tankColor==0){
			switch (kdirection) {
				case U:
					g.drawImage(tanksImages[8], x, y, null);
					break;
				case D:
					g.drawImage(tanksImages[9], x, y, null);
					break;
				case L:
					g.drawImage(tanksImages[10], x, y, null);
					break;
				case R:
					g.drawImage(tanksImages[11], x, y, null);
					break;
			}
		}
		else if(live&&!good&&tankColor==1){
			switch (kdirection) {
				case U:
					g.drawImage(tanksImages[16], x, y, null);
					break;
				case D:
					g.drawImage(tanksImages[17], x, y, null);
					break;
				case L:
					g.drawImage(tanksImages[18], x, y, null);
					break;
				case R:
					g.drawImage(tanksImages[19], x, y, null);
					break;
			}
		}
		else if(live&&!good&&tankColor==2){
			switch (kdirection) {
				case U:
					g.drawImage(tanksImages[20], x, y, null);
					break;
				case D:
					g.drawImage(tanksImages[21], x, y, null);
					break;
				case L:
					g.drawImage(tanksImages[22], x, y, null);
					break;
				case R:
					g.drawImage(tanksImages[23], x, y, null);
					break;
			}
		}
		else if(live&&!good&&tankColor==3){
			switch (kdirection) {
				case U:
					g.drawImage(tanksImages[12], x, y, null);
					break;
				case D:
					g.drawImage(tanksImages[13], x, y, null);
					break;
				case L:
					g.drawImage(tanksImages[14], x, y, null);
					break;
				case R:
					g.drawImage(tanksImages[15], x, y, null);
					break;
			}
		}
		
		move();
	}

	void move() {
		this.oldX = x;
		this.oldY = y;
		if(!good){
			SPEED = NSPEED;
		}else{
			SPEED = 3;
		}
		switch (direction) {
		case U:
			y -= SPEED;
			break;
		case D:
			y += SPEED;
			break;
		case L:
			x -= SPEED;
			break;
		case R:
			x += SPEED;
			break;
		case LU:
			x -= SPEED;
			y -= SPEED;
			break;
		case LD:
			x -= SPEED;
			y += SPEED;
			break;
		case RU:
			x += SPEED;
			y -= SPEED;
			break;
		case RD:
			x += SPEED;
			y += SPEED;
			break;
		case STOP:
			break;
		}
		if (this.direction != Direction.STOP)//�ж�ֹͣ��������ʻ�����Ƿ�һ��
			this.kdirection = this.direction;
		if (x < 0)x = 0;
		if (y < 0)y = 0;
		if (x > 758)x = 758;
		if (y > 511)y = 511;
		
		if (!good) {
			Direction[] directons = Direction.values();
			if (step == 0) {                  
				step = r.nextInt(20) + 20;//�������·��
				int rn = r.nextInt(4);
				direction = directons[rn];//�����������
			}
			step--;
			if (r.nextInt(40) > 36)//��������������Ƶ��˿���
				this.fire();
		}
		
	}
	
	//����һ���ӵ��ķ�������missiles��������ӵ�
	public void fire() {
		int x = this.x + 15;
		int y = this.y + 15;
		Missile m = new Missile(x, y,good,kdirection);
		Center.missiles.add(m);
		//return m;
	}

	//�ж�ѡ��ķ���
	public void locateDirection() {
		if(bL && !bU && !bR && !bD) direction = Direction.L;
		else if(bL && bU && !bR && !bD) direction = Direction.LU;
		else if(!bL && bU && !bR && !bD) direction = Direction.U;
		else if(!bL && bU && bR && !bD) direction = Direction.RU;
		else if(!bL && !bU && bR && !bD) direction = Direction.R;
		else if(!bL && !bU && bR && bD) direction = Direction.RD;
		else if(!bL && !bU && !bR && bD) direction = Direction.D;
		else if(bL && !bU && !bR && bD) direction = Direction.LD;
		else if(!bL && !bU && !bR && !bD) direction = Direction.STOP;
	}
	
	//���ײ����ǰ������
	private void changToOldDirection() {  
		this.x = oldX;
		this.y = oldY;
	}
		
	//�ж�̹���Ƿ�ײ��ͨǽ
	public boolean collideWall(Wall w) {
		if (this.live && this.getRect().intersects(w.getRect())) {
				this.changToOldDirection();//ת����ԭ����������
			return true;
		}
		return false;
	}
	
	//�ж�̹���Ƿ�ײ����
	public boolean collideWithHome(Home home) {
		if (this.live && this.getRect().intersects(home.getRect())) {
				this.changToOldDirection();//ת����ԭ����������
			return true;
		}
		return false;
	}
	
	//�ж�̹���Ƿ�ײ����ǽ
	public boolean collideHardWall(HardWall hw) {
		if (this.live && this.getRect().intersects(hw.getRect())) {
			this.changToOldDirection();//ת����ԭ����������
			return true;
		}
		return false;
	}
	
	//�ж�̹���Ƿ�ײ����
	public boolean collideRiver(River river) {
		if (this.live && this.getRect().intersects(river.getRect())) {
			this.changToOldDirection();//ת����ԭ����������
			return true;
		}
		return false;
	}
		
	//�жϵз�̹���Ƿ���ײ
	public boolean collideWithTanks(List<Tank> tanks) {
		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i);
			if (this != t) {
				if (this.live && t.isLive()&& this.getRect().intersects(t.getRect())) {
					this.changToOldDirection();//���̹��ת����ԭ����������
					t.changToOldDirection();//�з�̹��ת����ԭ����������
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_W:
			bU = true;
			break;
		case KeyEvent.VK_S:
			bD = true;
			break;
		case KeyEvent.VK_A:
			bL = true;
			break;
		case KeyEvent.VK_D:
			bR = true;
			break;
		case KeyEvent.VK_P:
			fire();
			new Thread(new Music(Music.PLAY_FIRE)).start();// �������Ч
			break;
		case KeyEvent.VK_R:
			if(live)
			setLife(100);
			break;
		}
		locateDirection();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_W:
			bU = false;
			break;
		case KeyEvent.VK_S:
			bD = false;
			break;
		case KeyEvent.VK_A:
			bL = false;
			break;
		case KeyEvent.VK_D:
			bR = false;
			break;
		}
		locateDirection();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	
}