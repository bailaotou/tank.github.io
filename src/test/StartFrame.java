package test;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.StyledEditorKit.ForegroundAction;



public class StartFrame extends JFrame implements KeyListener{
	
//	CardLayout card = new CardLayout();
//	JPanel pane=new JPanel();
//	SettingJPanel sj = new SettingJPanel(); 

	
	//设置图标
	ImageIcon imgBack = new ImageIcon("images/back.jpg");//背景图
	ImageIcon imgSelect =new ImageIcon("images/select.jpg"); //选项图标
	ImageIcon imgbutton1 =new ImageIcon("images/bf1.png"); //按钮一图标
	ImageIcon imgbutton2 =new ImageIcon("images/bf2.png"); //按钮二图标
	
	ImageIcon runTank1 =new ImageIcon("images/runTank11.png"); //动态效果，就是界面不停动的
	ImageIcon runTank2 =new ImageIcon("images/runTank12.png"); 
	ImageIcon runDancer = new ImageIcon("myselfPic/timgs.gif");
	ImageIcon runDancel = new ImageIcon("myselfPic/timgs2.gif");
	
	
	
	//把图片换成标签组件
	JLabel jlBack = new JLabel(imgBack);
	JLabel jlSelect1 = new JLabel(imgSelect);
	JLabel jlButton1 = new JLabel(imgbutton1);
	JLabel jlButton2 = new JLabel(imgbutton2);
	
	JLabel jrunDancer = new JLabel(runDancer);
	JLabel jrunDancel = new JLabel(runDancel);
	
	JLabel jlRunTank1 = new JLabel(runTank1);
	JLabel jlRunTank2 = new JLabel(runTank2);
	
	int choose;//确地选择的选项

	static String userName;
	public static String getUserName(){
		return userName;
	}
	
	
	//JButton jb = new JButton(imgbutton1); //设置按钮
	
	public StartFrame() {
		

		
		
		jlSelect1.setFocusable(true); //设置能否被选中
		jlSelect1.addKeyListener(this);//按键监听
		
		jlButton1.setBounds(330, 370, 140, 40);
		jlButton1.addMouseListener(new MouseAdapter() {//按钮监听事件
			public void mouseEntered(MouseEvent e) {//，若鼠标移动到此按钮，则把选项图标移动相应位置
				jlSelect1.setBounds(260,368,70,40);
			}
			public void mousePressed(MouseEvent e) {//若点击此则进入输入用户名
				String name = JOptionPane.showInputDialog("输入用户名");
				if(name.equals("")){
					userName = "无名小卒";
				}else{
					userName = name;
				}
				dispose();

				//Music.close();
				new GameFrame();
		    }
		});
		

		
		jlButton2.setBounds(331, 450, 140, 40);
		jlButton2.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				//new Thread(new Music(Music.PLAY_CHOOSE)).start();
				jlSelect1.setBounds(260,448,70,40);
			}
			public void mousePressed(MouseEvent e) {
				dispose();
//				Music.musicBack(false);
				//Music.closeSound();
				//new DoubleFrame();
				System.exit(0);
		    }
		});
		
		
		
		//在界面把标签添加上去
		add(jlSelect1);
		add(jlButton1);
		add(jlButton2);
		add(jrunDancer,BorderLayout.EAST);
		add(jrunDancel,BorderLayout.WEST);
		add(jlRunTank1);
		add(jlRunTank2);
		
		
		//给容器增加标签
		this.getContentPane().add(jlBack);
		
		//设置背景图位置
		jlBack.setBounds(60, 20, imgBack.getIconWidth(), imgBack.getIconHeight());
		//将面板设为透明
		((JPanel) this.getContentPane()).setOpaque(false);
		//将背景图设置为底层
		this.getLayeredPane().add(jlBack, new Integer(Integer.MIN_VALUE));

		//设置背景色
		setBackground(Color.black);
		//setLayout(null);
		//界面名
		setTitle("菜单");
		//设置退出方式
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//页面大小
		setSize(800, 600);
		//不可自由调整大小
		setResizable(false);
		//将消息框设置在桌面的中央
		setLocationRelativeTo(null);
		//设置窗口是否可见
		setVisible(true);
		System.out.print("aaa");
		
		
       
		Thread tankRun = new Thread(new Runnable(){
			int x1=-481,y1=5;
			int x2=800,y2=5;
			boolean dir = true;
			public void run() {
				while(true){
					if(dir){
						x1+=5;
						jlRunTank1.setBounds(x1,y1,481,90);
					}
					if(x1>770){
						dir=false;
						x1=-481;
					}
					if(!dir){
						x2-=5;
						jlRunTank2.setBounds(x2,y2,481,90);
					}
					if(x2<-481){
						dir=true;
						x2=800;
					}
		
					try {
						Thread.sleep(30);
					} catch (Exception e) {}
					repaint();
				}
			}
		});
		//执行线程
		tankRun.start();
		//Music.open(Music.MenuBGM);
		
	}

	public static void main(String[] args) {
//		Music.musicBack(true);
		//Music.openSound();
		new StartFrame();
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method 
	}


	//按键按下的监听事件
	@Override
	public void keyPressed(KeyEvent e) {
		//按w则向将选项设为第一个
		if(e.getKeyCode()==KeyEvent.VK_W){
			//new Thread(new Music(Music.PLAY_CHOOSE)).start();
			jlSelect1.setBounds(260,368,70,40);
			choose = 1;
		}
		//按s则向将选项设为第一个
		if(e.getKeyCode()==KeyEvent.VK_S){
			//new Thread(new Music(Music.PLAY_CHOOSE)).start();
			jlSelect1.setBounds(260,448,70,40);
			choose = 2;
		}
		//按下回车则执行选项内容
		if(e.getKeyCode()==KeyEvent.VK_ENTER && choose==1){
			//new Thread(new Music(Music.PLAY_CHOOSE)).start();
			userName = JOptionPane.showInputDialog("输入用户名");
			if(userName.equals("")){
				userName = "无名小卒";
			}else{
				userName = userName;
			}
			dispose();
			//Music.close();
			new GameFrame();	
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER && choose==2){
			System.exit(0);	
		}
		
	
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
