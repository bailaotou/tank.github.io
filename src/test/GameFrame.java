package test;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class GameFrame extends JFrame implements ActionListener, Runnable {
	
	
	
	/*
	static int enemyNumber = 1;// 敌人坦克初始数量
	static int round = 1;//战斗轮数
	static int difficulty = 0; //初始地图
	
	public static void setDifficulty(int difficulty1){
		difficulty = difficulty1;
	}
	/*

	
	static List<Tank> tanks = new ArrayList<Tank>();// 装坦克的容器
	{
		tanks.add(new Tank(80 , 50, false, Tank.Direction.D));//初始化一个坦克
	}
	
	static List<Missile> missiles = new ArrayList<Missile>();// 装子弹的容器

	static List<Explode> explodes = new ArrayList<Explode>();// 装爆炸的容器

	static List<Wall> walls = new ArrayList<Wall>();// 装普通墙的容器
	
	static List<HardWall> hWalls = new ArrayList<HardWall>();// 装金属墙的容器
	
	static List<Tree> trees = new ArrayList<Tree>();// 装树的容器
	
	static List<River> rivers = new ArrayList<River>();// 装河的容器
	*/
	/*
	public static boolean play = false;// 决定音乐的开启与关闭

	public static boolean printable = true;// 决定线程的开启与关闭
	
	static Home home = new Home();// 实例化一个老家
	
	static Tank hero = new Tank(220, 480, true, Tank.Direction.STOP);// 玩家坦克位置
	*/
	
	// 初始化地图和设置地图
	{
		Map.changeMap(Center.difficulty);
	}
	{
		Center.tanks.add(new Tank(80 , 50, false, Tank.Direction.D));//初始化一个坦克
	}
	public GameFrame() {

		addKeyListener(Center.hero);// 为英雄坦克注册键盘监听

		createMenu();// 创建菜单

		setTitle("坦克大战");
		setVisible(true);
		setSize(800, 600);
		//不能调整窗口
		setResizable(false);
		//设置窗口居中
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Center.jp = new newJp();
		Thread th = new Thread(this);
		th.start();

		// 在面板上绘制坦克、子弹、墙、老家	
		add(Center.jp);
	}

	// 创建菜单栏
	public void createMenu() {
		// 菜单栏
		JMenuBar jmb = new JMenuBar();
		// 菜单项
		JMenu jm1 = new JMenu("游戏");
		JMenu jm2 = new JMenu("历史记录");
		JMenu jm3 = new JMenu("帮助");
		JMenu jm4 = new JMenu("游戏难度");
		// 菜单项按钮
		JMenuItem jmi1 = new JMenuItem("暂停/继续");
		JMenuItem jmi2 = new JMenuItem("继续");
		JMenuItem jmi10 = new JMenuItem("重新开始");
		JMenuItem jmi3 = new JMenuItem("背景音乐开/关");
		JMenuItem jmi11 = new JMenuItem("返回到主界面");
		JMenuItem jmi4 = new JMenuItem("最高记录");
		JMenuItem jmi5 = new JMenuItem("玩家得分记录");
		JMenuItem jmi6 = new JMenuItem("关于游戏");
		JMenuItem jmi12 = new JMenuItem("自定义地图");
		JMenuItem jmi7 = new JMenuItem("普通模式");
		JMenuItem jmi8 = new JMenuItem("人间模式");
		JMenuItem jmi9 = new JMenuItem("地狱模式");
		
		
		// 添加菜单
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm4);
		jmb.add(jm3);

		// 设置游戏菜单项
		jm1.add(jmi1);
//		jm1.add(jmi2);
		jm1.add(jmi10);
		jm1.add(jmi3);
		jm1.add(jmi11);
		jm2.add(jmi4);
		jm2.add(jmi5);
		jm3.add(jmi6);
		jm3.add(jmi12);
		jm4.add(jmi7);
		jm4.add(jmi8);
		jm4.add(jmi9);

		// 为按钮添加监听事件
		jmi1.addActionListener(this);
		jmi1.setAccelerator(KeyStroke.getKeyStroke("F1"));// 设置快捷键
		jmi1.setActionCommand("stop");

		jmi2.addActionListener(this);
		jmi2.setActionCommand("continue");

		jmi3.addActionListener(this);
		jmi3.setAccelerator(KeyStroke.getKeyStroke("F2"));// 设置快捷键
		jmi3.setActionCommand("music");

		jmi4.addActionListener(this);
		jmi4.setActionCommand("rank");

		jmi5.addActionListener(this);
		jmi5.setActionCommand("history");

		jmi6.addActionListener(this);
		jmi6.setActionCommand("help");

		jmi7.addActionListener(this);
		jmi7.setActionCommand("difficulty1");

		jmi8.addActionListener(this);
		jmi8.setActionCommand("difficulty2");

		jmi9.addActionListener(this);
		jmi9.setActionCommand("difficulty3");
		
		jmi10.addActionListener(this);
		jmi10.setActionCommand("restart");
		
		jmi11.addActionListener(this);
		jmi11.setActionCommand("back");
		
		jmi12.addActionListener(this);
		jmi12.setActionCommand("diy");

		// 将菜单放在窗体上
		this.setJMenuBar(jmb);
	}

	public static void main(String[] args) {
		new GameFrame();
	}

	public void run() {
		// 每隔20毫秒重新画图
		while (Center.printable) {
			try {// 记录玩家分数
				if (Missile.getCount()==31 || !Center.hero.isLive()) {
					Center.printable = false;// 停止线程
					if(Missile.getCount()==31){
						new Thread(new Music(Music.PLAY_WIN)).start();
						JOptionPane.showMessageDialog(null, "赢得胜利！");
						// 判断是否是最高分
						//maxScore();
					}
					if(!Center.hero.isLive()){
						new Thread(new Music(Music.PLAY_LOSE)).start();
						JOptionPane.showMessageDialog(null, "游戏结束！");
						//maxScore();
					}
					/*
					try {
						FileWriter fw = new FileWriter("txt/score.txt", true);
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write("\t\t" + StartFrame.getUserName() + "获得的分数为：" + Missile.getCount() + "\n");// 往已有的文件上添加字符串
						bw.close();
						fw.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					*/
				}
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}
			repaint();
		}
	}
	
	//最高分判定与写入
	public void maxScore(){
		String str = MaxScore.readTxt();
		String[] newstr=str.split(":");
		int b = Integer.parseInt(newstr[1]);
		if(Missile.getCount()>b) {
			b=Missile.getCount();
			MaxScore.write(StartFrame.getUserName(),b);
			if(StartFrame.getUserName().equals("匿名玩家")){
				JOptionPane.showMessageDialog(null, "恭喜您获得了最高分"+b);
			}else{
				JOptionPane.showMessageDialog(null, StartFrame.getUserName()+"，"+"恭喜您获得了最高分"+b);
			}
		}
		
//		String str1 = MaxScore.readTxt();
//		
//		int a = 0,c=0,d=0;
//		switch(difficulty){
//			case 0:if(Missile.getCount()>a)MaxScore.write(StartFrame.getUserName(),a);break;
//			case 1:if(Missile.getCount()>b)MaxScore.write(StartFrame.getUserName(),b);break;
//			case 2:if(Missile.getCount()>c)MaxScore.write(StartFrame.getUserName(),c);break;
//			case 3:if(Missile.getCount()>d)MaxScore.write(StartFrame.getUserName(),d);break;
//		}
		
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("stop")) {
			if(Center.printable){
				Center.printable = false;// 停止线程
			}else{
				Center.printable = true;
			new Thread(this).start(); // 启动线程
			}
		}
		
//		if (e.getActionCommand().equals("continue")) {
//			if (!printable) {
//				printable = true;
//				new Thread(this).start(); // 线程启动
//			}
//		}
		
		if (e.getActionCommand().equals("music")) {
//			Music.musicBack(play);
//			play = !play;// 点击播放后置为false
			if(!Center.play){
				Music.openSound();
			}else{
				Music.closeSound();
			}
			Center.play = !Center.play;
		}
		
		if (e.getActionCommand().equals("restart")) {
			Center.printable = false;
			Object[] options = { "确定", "取消" };
			int response = JOptionPane.showOptionDialog(this, "您确认要开始新游戏！", "",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[0]);
			if (response == 0) {
				//初始化所有变量
				Center.printable = true;
				Home.setLive(true);// 重新激活老家
				Center.hero.setLife(100);// 重新设置玩家血量
				Center.hero.setLive(true);// 重新激活玩家坦克
				Center.hero.setTankLocation(220, 480 ,Tank.Direction.STOP);// 重置坦克位置
				Missile.count=0;// 重置得分
				Center.round=1;// 重置轮数
				Center.enemyNumber = 1;// 重置敌人数量
				Tank.setNspeed(3);// 重置敌人坦克速度
				Tank.setTankColor(0);// 重置坦克颜色
				Home.setHomeLocation(290, 250);// 重置老家位置
				Missile.setMissileColor(0);// 重置子弹颜色
				Missile.setNspeed(4);// 敌人坦克子弹速度
				Missile.setHurt(20);// 敌人坦克伤害
				Center.tanks.clear();// 坦克清空
				Center.missiles.clear();// 子弹清空
				Center.walls.clear();// 墙清空
				Center.hWalls.clear();// 金属墙清空
				Center.rivers.clear();// 河流清空
				Center.trees.clear();// 树清空
				Center.setDifficulty(0);// 重置地图
				this.dispose();// 关闭当前窗口
				new GameFrame();
			} else {
				Center.printable = true;
				new Thread(this).start(); // 线程启动
			}
		}
		
		if(e.getActionCommand().equals("back")){
			Center.printable = false;
			Object[] options = { "确定", "取消" };
			int response = JOptionPane.showOptionDialog(this, "您确认要返回到主界面！", "",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[0]);
			if(response == 0){
				Home.setLive(true);// 重新激活老家
				Center.hero.setLife(100);// 玩家生命重置
				Center.hero.setLive(true);// 重新激活玩家坦克
				Center.hero.setTankLocation(220, 480,Tank.Direction.STOP);// 重置坦克位置
				Missile.count=0;// 重置得分
				Center.round=1;// 重置轮数
				Center.enemyNumber = 1;// 重置敌人数量
				Tank.setNspeed(3);// 重置敌人坦克速度
				Tank.setTankColor(0);// 重置坦克颜色
				Home.setHomeLocation(290, 250);// 重置老家位置
				Missile.setMissileColor(0);// 重置子弹颜色
				Missile.setNspeed(4);// 重置敌人坦克子弹速度
				Missile.setHurt(20);// 重置敌人坦克伤害
				Center.tanks.clear();// 坦克清空
				Center.missiles.clear();// 子弹清空
				Center.walls.clear();// 墙清空
				Center.hWalls.clear();// 金属墙清空
				Center.rivers.clear();// 河流清空
				Center.trees.clear();// 树清空
				Center.setDifficulty(0);// 重置地图
//				Music.musicBack(true);// 开启背景音乐
				Music.openSound();
				this.dispose();
				new StartFrame();
				Center.printable = true;
			}else {
				Center.printable = true;
				new Thread(this).start(); // 线程启动
			}
		}
		
		if (e.getActionCommand().equals("difficulty1")) {
			Home.setLive(true);// 重新激活老家
			Center.tanks.clear();
			Center.missiles.clear();
			Center.walls.clear();
			Center.hWalls.clear();// 金属墙清空
			Center.rivers.clear();// 河流清空
			Center.trees.clear();// 树清空
			
			Tank.setNspeed(7);// 改变敌人坦克速度
			Tank.setTankColor(1);// 改变敌人坦克颜色
			Missile.setNspeed(8);// 改变敌人子弹速度
			Missile.setMissileColor(1);// 子弹颜色
			Center.setDifficulty(1);// changeMap根据设置的值改变地图
			Home.setHomeLocation(370,500);// 设置老家位置
			Center.hero.setLife(100);// 玩家生命重置
			Center.hero.setLive(true);// 重新激活玩家坦克
			Center.hero.setTankLocation(220, 480,Tank.Direction.STOP);// 重置坦克位置
			this.dispose();
			new GameFrame();
		}
		
		if (e.getActionCommand().equals("difficulty2")) {
			Home.setLive(true);// 重新激活老家
			Center.tanks.clear();
			Center.missiles.clear();
			Center.walls.clear();
			Center.hWalls.clear();// 金属墙清空
			Center.rivers.clear();// 河流清空
			Center.trees.clear();// 树清空
			
			Tank.setNspeed(9);
			Tank.setTankColor(2);// 改变敌人坦克颜色
			Missile.setNspeed(10);
			Missile.setHurt(30);// 敌人坦克伤害
			Missile.setMissileColor(2);// 子弹颜色
			Home.setHomeLocation(370,250);// 设置老家位置
			Center.hero.setLife(100);// 玩家生命重置
			Center.hero.setLive(true);// 重新激活玩家坦克
			Center.hero.setTankLocation(220, 480,Tank.Direction.STOP);// 重置坦克位置
			Center.setDifficulty(2);// changeMap根据设置的值改变地图
			this.dispose();
			new GameFrame();
		}
		
		if (e.getActionCommand().equals("difficulty3")) {
			Home.setLive(true);// 重新激活老家
			Center.tanks.clear();
			Center.missiles.clear();
			Center.walls.clear();
			Center.hWalls.clear();// 金属墙清空
			Center.rivers.clear();// 河流清空
			Center.trees.clear();// 树清空
			
			Tank.setNspeed(11);
			Tank.setTankColor(3);// 改变敌人坦克颜色
			Missile.setNspeed(12);
			Missile.setMissileColor(3);// 子弹颜色
			Missile.setHurt(40);
			Center.hero.setLife(100);// 玩家生命重置
			Center.hero.setLive(true);// 重新激活玩家坦克
			Home.setHomeLocation(390,250);// 设置老家位置
			Center.hero.setTankLocation(220, 480,Tank.Direction.STOP);// 重置坦克位置
			Center.setDifficulty(3);// changeMap根据设置的值改变地图
			this.dispose();
			new GameFrame();
		}
		
		if (e.getActionCommand().equals("history")) {
			try {
				new ScoreFrame();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (e.getActionCommand().equals("rank")) {
			String str = MaxScore.readTxt();
			String[] newstr=str.split(":");
			int b = Integer.parseInt(newstr[1]);
			if(newstr[0].equals("")){
				JOptionPane.showMessageDialog(null, "匿名玩家"+"获得了最高分："+b);
			}else{
				JOptionPane.showMessageDialog(null, newstr[0]+"获得了最高分："+b);
			}
		}
		
		if (e.getActionCommand().equals("help")) {
			Center.printable = false;// 停止线程
			JOptionPane.showMessageDialog(null, "W、向上，A、向左，S、向下，D、向右，P、发射炮弹，R、加血"+"\n","提示",JOptionPane.INFORMATION_MESSAGE);
			Center.printable = true;
			new Thread(this).start(); // 线程启动
		}
		
		if(e.getActionCommand().equals("diy")){
			Center.tanks.clear();// 坦克清空
			Center.missiles.clear();// 子弹清空
			Center.walls.clear();// 墙清空
			Center.hWalls.clear();// 金属墙清空
			Center.rivers.clear();// 河流清空
			Center.trees.clear();// 树清空
			Center.home.setLive(true);
			Center.hero.setLive(true);
			this.dispose();
			new DiyMapFrame();
		}
	}

}
