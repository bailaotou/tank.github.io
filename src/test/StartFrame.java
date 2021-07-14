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

	
	//����ͼ��
	ImageIcon imgBack = new ImageIcon("images/back.jpg");//����ͼ
	ImageIcon imgSelect =new ImageIcon("images/select.jpg"); //ѡ��ͼ��
	ImageIcon imgbutton1 =new ImageIcon("images/bf1.png"); //��ťһͼ��
	ImageIcon imgbutton2 =new ImageIcon("images/bf2.png"); //��ť��ͼ��
	
	ImageIcon runTank1 =new ImageIcon("images/runTank11.png"); //��̬Ч�������ǽ��治ͣ����
	ImageIcon runTank2 =new ImageIcon("images/runTank12.png"); 
	ImageIcon runDancer = new ImageIcon("myselfPic/timgs.gif");
	ImageIcon runDancel = new ImageIcon("myselfPic/timgs2.gif");
	
	
	
	//��ͼƬ���ɱ�ǩ���
	JLabel jlBack = new JLabel(imgBack);
	JLabel jlSelect1 = new JLabel(imgSelect);
	JLabel jlButton1 = new JLabel(imgbutton1);
	JLabel jlButton2 = new JLabel(imgbutton2);
	
	JLabel jrunDancer = new JLabel(runDancer);
	JLabel jrunDancel = new JLabel(runDancel);
	
	JLabel jlRunTank1 = new JLabel(runTank1);
	JLabel jlRunTank2 = new JLabel(runTank2);
	
	int choose;//ȷ��ѡ���ѡ��

	static String userName;
	public static String getUserName(){
		return userName;
	}
	
	
	//JButton jb = new JButton(imgbutton1); //���ð�ť
	
	public StartFrame() {
		

		
		
		jlSelect1.setFocusable(true); //�����ܷ�ѡ��
		jlSelect1.addKeyListener(this);//��������
		
		jlButton1.setBounds(330, 370, 140, 40);
		jlButton1.addMouseListener(new MouseAdapter() {//��ť�����¼�
			public void mouseEntered(MouseEvent e) {//��������ƶ����˰�ť�����ѡ��ͼ���ƶ���Ӧλ��
				jlSelect1.setBounds(260,368,70,40);
			}
			public void mousePressed(MouseEvent e) {//�����������������û���
				String name = JOptionPane.showInputDialog("�����û���");
				if(name.equals("")){
					userName = "����С��";
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
		
		
		
		//�ڽ���ѱ�ǩ�����ȥ
		add(jlSelect1);
		add(jlButton1);
		add(jlButton2);
		add(jrunDancer,BorderLayout.EAST);
		add(jrunDancel,BorderLayout.WEST);
		add(jlRunTank1);
		add(jlRunTank2);
		
		
		//���������ӱ�ǩ
		this.getContentPane().add(jlBack);
		
		//���ñ���ͼλ��
		jlBack.setBounds(60, 20, imgBack.getIconWidth(), imgBack.getIconHeight());
		//�������Ϊ͸��
		((JPanel) this.getContentPane()).setOpaque(false);
		//������ͼ����Ϊ�ײ�
		this.getLayeredPane().add(jlBack, new Integer(Integer.MIN_VALUE));

		//���ñ���ɫ
		setBackground(Color.black);
		//setLayout(null);
		//������
		setTitle("�˵�");
		//�����˳���ʽ
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//ҳ���С
		setSize(800, 600);
		//�������ɵ�����С
		setResizable(false);
		//����Ϣ�����������������
		setLocationRelativeTo(null);
		//���ô����Ƿ�ɼ�
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
		//ִ���߳�
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


	//�������µļ����¼�
	@Override
	public void keyPressed(KeyEvent e) {
		//��w����ѡ����Ϊ��һ��
		if(e.getKeyCode()==KeyEvent.VK_W){
			//new Thread(new Music(Music.PLAY_CHOOSE)).start();
			jlSelect1.setBounds(260,368,70,40);
			choose = 1;
		}
		//��s����ѡ����Ϊ��һ��
		if(e.getKeyCode()==KeyEvent.VK_S){
			//new Thread(new Music(Music.PLAY_CHOOSE)).start();
			jlSelect1.setBounds(260,448,70,40);
			choose = 2;
		}
		//���»س���ִ��ѡ������
		if(e.getKeyCode()==KeyEvent.VK_ENTER && choose==1){
			//new Thread(new Music(Music.PLAY_CHOOSE)).start();
			userName = JOptionPane.showInputDialog("�����û���");
			if(userName.equals("")){
				userName = "����С��";
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
