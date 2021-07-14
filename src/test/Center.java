package test;

import java.util.ArrayList;
import java.util.List;

public class Center {
	static int enemyNumber = 1;// 敌人坦克初始数量
	static int round = 1;//战斗轮数
	static int difficulty = 0; //初始地图
	
	public static void setDifficulty(int difficulty1){
		difficulty = difficulty1;
	}
	
	
	static List<Tank> tanks = new ArrayList<Tank>();// 装坦克的容器
	
	
	static List<Missile> missiles = new ArrayList<Missile>();// 装子弹的容器

	static List<Explode> explodes = new ArrayList<Explode>();// 装爆炸的容器

	static List<Wall> walls = new ArrayList<Wall>();// 装普通墙的容器
	
	static List<HardWall> hWalls = new ArrayList<HardWall>();// 装金属墙的容器
	
	static List<Tree> trees = new ArrayList<Tree>();// 装树的容器
	
	static List<River> rivers = new ArrayList<River>();// 装河的容器
	
	public static boolean play = false;// 决定音乐的开启与关闭

	public static boolean printable = true;// 决定线程的开启与关闭
	
	static Home home = new Home();// 实例化一个老家
	
	static Tank hero = new Tank(220, 480, true, Tank.Direction.STOP);// 玩家坦克位置
	
	static prop pr = new prop();//创建道具类
	
	static newJp jp=null;
}
