package test;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.ImageIcon;

public class DoubleTank1 implements KeyListener {
	
	private int x, y;// 坦克坐标
	private int oldX, oldY;// 坦克上一步坐标
	static int SPEED = 4;// 坦克速度
	private int life = 200;// 生命值
	private boolean live = true;// 坦克存活
	boolean bU = false, bD = false, bL = false, bR = false;
	private Direction direction = Direction.STOP;// 坦克初始状态
	private Direction kdirection = Direction.U;// 坦克初始方向

	enum Direction {// 方向的枚举
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

	//判断范围（子弹进入坦克）
	public Rectangle getRect() {
		return new Rectangle(x, y, 40, 40);
	}
 
	static Image[] tanksImages = null;
	static {
		tanksImages = new Image[] {
				// 玩家坦克8个方向的图片
				new ImageIcon("images/tankU.gif").getImage(),
				new ImageIcon("images/tankD.gif").getImage(),
				new ImageIcon("images/tankL.gif").getImage(),
				new ImageIcon("images/tankR.gif").getImage(),
				new ImageIcon("images/tankLU.gif").getImage(),
				new ImageIcon("images/tankLD.gif").getImage(),
				new ImageIcon("images/tankRU.gif").getImage(),
				new ImageIcon("images/tankRD.gif").getImage(),
				};
	}

	// 坦克的构造方法
	public DoubleTank1(int x, int y) {
		this.x = x;
		this.y = y;

		this.oldX = x;
		this.oldY = y;
	}

	public DoubleTank1(int x, int y, Direction direction) {
		this(x, y);
		this.direction = direction;
	}

	
	
	public void draw(Graphics g) {
		if (live) {
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
		move();
	}

	void move() {
		this.oldX = x;
		this.oldY = y;
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
		if (this.direction != Direction.STOP)
			this.kdirection = this.direction;
		if (x < 0)x = 0;
		if (y < 0)y = 0;
		if (x > 757)x = 757;
		if (y > 510)y = 510;
			
	}
	
	//发出一发子弹的方法，向missiles里面添加子弹
	public DoubleMissile1 fire() {
		int x = this.x + 15;
		int y = this.y + 15;
		DoubleMissile1 m1 = new DoubleMissile1(x, y, kdirection);
		DoubleFrame.missiles1.add(m1);
		return m1;
	}

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
	
	//撞击后复位
	private void changToOldDirection() {  
		this.x = oldX;
		this.y = oldY;
	}
			
	//判断坦克是否相撞
	public boolean collideWithTanks(DoubleTank2 dt2) {
			if (this.live && dt2.isLive()&& this.getRect().intersects(dt2.getRect())) {
				this.changToOldDirection();
				return true;
			}
		return false;
	}
	
	public boolean eat(Arm arm) {
		if (this.live && this.getRect().intersects(arm.getRect())) {
			if(this.life<=100)
			this.life = this.life+50;      //每吃一个，增加100生命点
			else
				this.life = 200;
			new Thread(new Music(Music.PLAY_EAT)).start();
			DoubleMissile1.setFit(40);
			Random r = new Random();
			Arm.setArmSeat(r.nextInt(300)+200, r.nextInt(300)+200);
			return true;
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
		case KeyEvent.VK_J:
			if(!live)return;
			fire();
			new Thread(new Music(Music.PLAY_FIRE)).start();
			break;
		case KeyEvent.VK_R:
//			setLife(200);
//			setLive(true);
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
