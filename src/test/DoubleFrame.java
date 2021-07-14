package test;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class DoubleFrame extends JFrame implements Runnable,ActionListener{
	static DoubleTank1 dt1 = new DoubleTank1(150, 200,DoubleTank1.Direction.STOP);
	static DoubleTank2 dt2 = new DoubleTank2(590, 200,DoubleTank2.Direction.STOP);
	
	static List<DoubleMissile1> missiles1 = new ArrayList<DoubleMissile1>();// 装子弹的容器
	static List<DoubleMissile2> missiles2 = new ArrayList<DoubleMissile2>();// 装子弹的容器

	static List<Explode> explodes = new ArrayList<Explode>();// 装爆炸的容器
	
	Arm arm = new Arm(); // 实例化补给

	public static Boolean play = false;// 决定音乐的开启与关闭
	
	public DoubleFrame(){
		createMenu();
		
		addKeyListener(dt1);
		addKeyListener(dt2);
		
		setSize(800,600);
		setTitle("双人对战");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JPanel jp =new JPanel(){
			{
				setBackground(Color.gray);// 设置面板背景颜色
			}
			
			public void paint(Graphics g){
				super.paint(g);
				g.drawString("玩家1的攻击力："+DoubleMissile1.getFit(), 10, 30);
				g.drawString("玩家1的血量："+dt1.getLife(), 10, 60);
				g.drawRect(10, 80, 200, 15);
				g.fillRect(10, 80, dt1.getLife(), 15);
				
				g.drawString("玩家2的攻击力："+DoubleMissile2.getFit(), 675, 30);
				g.drawString("玩家2的血量："+dt2.getLife(), 680, 60);
				g.drawRect(580, 80, 200, 15);
				g.fillRect(580+(200-dt2.getLife()), 80, dt2.getLife(), 15);
				
				dt1.draw(g);
				dt1.eat(arm);
				dt1.collideWithTanks(dt2);
				
				dt2.draw(g);
				dt2.eat(arm);
				dt2.collideWithTanks(dt1);
				
				arm.draw(g);
				
				// 把容器里面的子弹都画出来
				for (int i = 0; i < missiles1.size(); i++) {
					DoubleMissile1 m1 = missiles1.get(i);
					m1.draw(g);
					m1.hitTank(dt2);
				}
				
				// 把容器里面的子弹都画出来
				for (int i = 0; i < missiles2.size(); i++) {
					DoubleMissile2 m2 = missiles2.get(i);
					m2.draw(g);
					m2.hitTank(dt1);
				}
				
				// 把爆炸都画出来
				for (int i = 0; i < explodes.size(); i++) {
					Explode e = explodes.get(i);
					e.draw(g);
				}
			}
		}; 
		
		add(jp);
		Thread t = new Thread(this);
		t.start();
	}
	public static void main(String[] args){
		new DoubleFrame();
	}
	
	public void createMenu(){
		JMenuBar jmb = new JMenuBar();
		
		JMenu jm1 = new JMenu("游戏");
		JMenu jm2 = new JMenu("帮助");
		
		JMenuItem jmi1 = new JMenuItem("背景音乐开/关");
		JMenuItem jmi2 = new JMenuItem("关于游戏");
		JMenuItem jmi3 = new JMenuItem("返回到主界面");
		
		//添加菜单
		jmb.add(jm1);
		jmb.add(jm2);
		
		//添加菜单项
		jm1.add(jmi1);
		jm1.add(jmi3);
		jm2.add(jmi2);
		
		jmi1.addActionListener(this);
		jmi1.setActionCommand("music");
		
		jmi2.addActionListener(this);
		jmi2.setActionCommand("help");
		
		jmi3.addActionListener(this);
		jmi3.setActionCommand("back");
		
		this.setJMenuBar(jmb);//添加菜单栏
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(20);
				repaint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("music")){
//			Music.musicBack(play);
//			play = !play;
			if(!play){
				Music.openSound();
			}else{
				Music.closeSound();
			}
			play = !play;
		}
		if(e.getActionCommand().equals("help")){
			JOptionPane.showMessageDialog(null, "玩家1操作：W、向上，A、向下，S、向下，D、向上，J、发射炮弹"+"\n"+"玩家2操作：↑、向上，↓、向下，←、向左，→、向右，P、发射炮弹"+"","提示",JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getActionCommand().equals("back")){
			Object[] options = { "确定", "取消" };
			int response = JOptionPane.showOptionDialog(this, "您确认要返回到主界面！", "",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[0]);
			if(response == 0){
//				Music.musicBack(true);// 开启背景音乐
				this.dispose();
				new StartFrame();
			}
			
		}
	}
}
