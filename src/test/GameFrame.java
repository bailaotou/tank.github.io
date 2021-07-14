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
	static int enemyNumber = 1;// ����̹�˳�ʼ����
	static int round = 1;//ս������
	static int difficulty = 0; //��ʼ��ͼ
	
	public static void setDifficulty(int difficulty1){
		difficulty = difficulty1;
	}
	/*

	
	static List<Tank> tanks = new ArrayList<Tank>();// װ̹�˵�����
	{
		tanks.add(new Tank(80 , 50, false, Tank.Direction.D));//��ʼ��һ��̹��
	}
	
	static List<Missile> missiles = new ArrayList<Missile>();// װ�ӵ�������

	static List<Explode> explodes = new ArrayList<Explode>();// װ��ը������

	static List<Wall> walls = new ArrayList<Wall>();// װ��ͨǽ������
	
	static List<HardWall> hWalls = new ArrayList<HardWall>();// װ����ǽ������
	
	static List<Tree> trees = new ArrayList<Tree>();// װ��������
	
	static List<River> rivers = new ArrayList<River>();// װ�ӵ�����
	*/
	/*
	public static boolean play = false;// �������ֵĿ�����ر�

	public static boolean printable = true;// �����̵߳Ŀ�����ر�
	
	static Home home = new Home();// ʵ����һ���ϼ�
	
	static Tank hero = new Tank(220, 480, true, Tank.Direction.STOP);// ���̹��λ��
	*/
	
	// ��ʼ����ͼ�����õ�ͼ
	{
		Map.changeMap(Center.difficulty);
	}
	{
		Center.tanks.add(new Tank(80 , 50, false, Tank.Direction.D));//��ʼ��һ��̹��
	}
	public GameFrame() {

		addKeyListener(Center.hero);// ΪӢ��̹��ע����̼���

		createMenu();// �����˵�

		setTitle("̹�˴�ս");
		setVisible(true);
		setSize(800, 600);
		//���ܵ�������
		setResizable(false);
		//���ô��ھ���
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Center.jp = new newJp();
		Thread th = new Thread(this);
		th.start();

		// ������ϻ���̹�ˡ��ӵ���ǽ���ϼ�	
		add(Center.jp);
	}

	// �����˵���
	public void createMenu() {
		// �˵���
		JMenuBar jmb = new JMenuBar();
		// �˵���
		JMenu jm1 = new JMenu("��Ϸ");
		JMenu jm2 = new JMenu("��ʷ��¼");
		JMenu jm3 = new JMenu("����");
		JMenu jm4 = new JMenu("��Ϸ�Ѷ�");
		// �˵��ť
		JMenuItem jmi1 = new JMenuItem("��ͣ/����");
		JMenuItem jmi2 = new JMenuItem("����");
		JMenuItem jmi10 = new JMenuItem("���¿�ʼ");
		JMenuItem jmi3 = new JMenuItem("�������ֿ�/��");
		JMenuItem jmi11 = new JMenuItem("���ص�������");
		JMenuItem jmi4 = new JMenuItem("��߼�¼");
		JMenuItem jmi5 = new JMenuItem("��ҵ÷ּ�¼");
		JMenuItem jmi6 = new JMenuItem("������Ϸ");
		JMenuItem jmi12 = new JMenuItem("�Զ����ͼ");
		JMenuItem jmi7 = new JMenuItem("��ͨģʽ");
		JMenuItem jmi8 = new JMenuItem("�˼�ģʽ");
		JMenuItem jmi9 = new JMenuItem("����ģʽ");
		
		
		// ��Ӳ˵�
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm4);
		jmb.add(jm3);

		// ������Ϸ�˵���
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

		// Ϊ��ť��Ӽ����¼�
		jmi1.addActionListener(this);
		jmi1.setAccelerator(KeyStroke.getKeyStroke("F1"));// ���ÿ�ݼ�
		jmi1.setActionCommand("stop");

		jmi2.addActionListener(this);
		jmi2.setActionCommand("continue");

		jmi3.addActionListener(this);
		jmi3.setAccelerator(KeyStroke.getKeyStroke("F2"));// ���ÿ�ݼ�
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

		// ���˵����ڴ�����
		this.setJMenuBar(jmb);
	}

	public static void main(String[] args) {
		new GameFrame();
	}

	public void run() {
		// ÿ��20�������»�ͼ
		while (Center.printable) {
			try {// ��¼��ҷ���
				if (Missile.getCount()==31 || !Center.hero.isLive()) {
					Center.printable = false;// ֹͣ�߳�
					if(Missile.getCount()==31){
						new Thread(new Music(Music.PLAY_WIN)).start();
						JOptionPane.showMessageDialog(null, "Ӯ��ʤ����");
						// �ж��Ƿ�����߷�
						//maxScore();
					}
					if(!Center.hero.isLive()){
						new Thread(new Music(Music.PLAY_LOSE)).start();
						JOptionPane.showMessageDialog(null, "��Ϸ������");
						//maxScore();
					}
					/*
					try {
						FileWriter fw = new FileWriter("txt/score.txt", true);
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write("\t\t" + StartFrame.getUserName() + "��õķ���Ϊ��" + Missile.getCount() + "\n");// �����е��ļ�������ַ���
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
	
	//��߷��ж���д��
	public void maxScore(){
		String str = MaxScore.readTxt();
		String[] newstr=str.split(":");
		int b = Integer.parseInt(newstr[1]);
		if(Missile.getCount()>b) {
			b=Missile.getCount();
			MaxScore.write(StartFrame.getUserName(),b);
			if(StartFrame.getUserName().equals("�������")){
				JOptionPane.showMessageDialog(null, "��ϲ���������߷�"+b);
			}else{
				JOptionPane.showMessageDialog(null, StartFrame.getUserName()+"��"+"��ϲ���������߷�"+b);
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
				Center.printable = false;// ֹͣ�߳�
			}else{
				Center.printable = true;
			new Thread(this).start(); // �����߳�
			}
		}
		
//		if (e.getActionCommand().equals("continue")) {
//			if (!printable) {
//				printable = true;
//				new Thread(this).start(); // �߳�����
//			}
//		}
		
		if (e.getActionCommand().equals("music")) {
//			Music.musicBack(play);
//			play = !play;// ������ź���Ϊfalse
			if(!Center.play){
				Music.openSound();
			}else{
				Music.closeSound();
			}
			Center.play = !Center.play;
		}
		
		if (e.getActionCommand().equals("restart")) {
			Center.printable = false;
			Object[] options = { "ȷ��", "ȡ��" };
			int response = JOptionPane.showOptionDialog(this, "��ȷ��Ҫ��ʼ����Ϸ��", "",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[0]);
			if (response == 0) {
				//��ʼ�����б���
				Center.printable = true;
				Home.setLive(true);// ���¼����ϼ�
				Center.hero.setLife(100);// �����������Ѫ��
				Center.hero.setLive(true);// ���¼������̹��
				Center.hero.setTankLocation(220, 480 ,Tank.Direction.STOP);// ����̹��λ��
				Missile.count=0;// ���õ÷�
				Center.round=1;// ��������
				Center.enemyNumber = 1;// ���õ�������
				Tank.setNspeed(3);// ���õ���̹���ٶ�
				Tank.setTankColor(0);// ����̹����ɫ
				Home.setHomeLocation(290, 250);// �����ϼ�λ��
				Missile.setMissileColor(0);// �����ӵ���ɫ
				Missile.setNspeed(4);// ����̹���ӵ��ٶ�
				Missile.setHurt(20);// ����̹���˺�
				Center.tanks.clear();// ̹�����
				Center.missiles.clear();// �ӵ����
				Center.walls.clear();// ǽ���
				Center.hWalls.clear();// ����ǽ���
				Center.rivers.clear();// �������
				Center.trees.clear();// �����
				Center.setDifficulty(0);// ���õ�ͼ
				this.dispose();// �رյ�ǰ����
				new GameFrame();
			} else {
				Center.printable = true;
				new Thread(this).start(); // �߳�����
			}
		}
		
		if(e.getActionCommand().equals("back")){
			Center.printable = false;
			Object[] options = { "ȷ��", "ȡ��" };
			int response = JOptionPane.showOptionDialog(this, "��ȷ��Ҫ���ص������棡", "",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[0]);
			if(response == 0){
				Home.setLive(true);// ���¼����ϼ�
				Center.hero.setLife(100);// �����������
				Center.hero.setLive(true);// ���¼������̹��
				Center.hero.setTankLocation(220, 480,Tank.Direction.STOP);// ����̹��λ��
				Missile.count=0;// ���õ÷�
				Center.round=1;// ��������
				Center.enemyNumber = 1;// ���õ�������
				Tank.setNspeed(3);// ���õ���̹���ٶ�
				Tank.setTankColor(0);// ����̹����ɫ
				Home.setHomeLocation(290, 250);// �����ϼ�λ��
				Missile.setMissileColor(0);// �����ӵ���ɫ
				Missile.setNspeed(4);// ���õ���̹���ӵ��ٶ�
				Missile.setHurt(20);// ���õ���̹���˺�
				Center.tanks.clear();// ̹�����
				Center.missiles.clear();// �ӵ����
				Center.walls.clear();// ǽ���
				Center.hWalls.clear();// ����ǽ���
				Center.rivers.clear();// �������
				Center.trees.clear();// �����
				Center.setDifficulty(0);// ���õ�ͼ
//				Music.musicBack(true);// ������������
				Music.openSound();
				this.dispose();
				new StartFrame();
				Center.printable = true;
			}else {
				Center.printable = true;
				new Thread(this).start(); // �߳�����
			}
		}
		
		if (e.getActionCommand().equals("difficulty1")) {
			Home.setLive(true);// ���¼����ϼ�
			Center.tanks.clear();
			Center.missiles.clear();
			Center.walls.clear();
			Center.hWalls.clear();// ����ǽ���
			Center.rivers.clear();// �������
			Center.trees.clear();// �����
			
			Tank.setNspeed(7);// �ı����̹���ٶ�
			Tank.setTankColor(1);// �ı����̹����ɫ
			Missile.setNspeed(8);// �ı�����ӵ��ٶ�
			Missile.setMissileColor(1);// �ӵ���ɫ
			Center.setDifficulty(1);// changeMap�������õ�ֵ�ı��ͼ
			Home.setHomeLocation(370,500);// �����ϼ�λ��
			Center.hero.setLife(100);// �����������
			Center.hero.setLive(true);// ���¼������̹��
			Center.hero.setTankLocation(220, 480,Tank.Direction.STOP);// ����̹��λ��
			this.dispose();
			new GameFrame();
		}
		
		if (e.getActionCommand().equals("difficulty2")) {
			Home.setLive(true);// ���¼����ϼ�
			Center.tanks.clear();
			Center.missiles.clear();
			Center.walls.clear();
			Center.hWalls.clear();// ����ǽ���
			Center.rivers.clear();// �������
			Center.trees.clear();// �����
			
			Tank.setNspeed(9);
			Tank.setTankColor(2);// �ı����̹����ɫ
			Missile.setNspeed(10);
			Missile.setHurt(30);// ����̹���˺�
			Missile.setMissileColor(2);// �ӵ���ɫ
			Home.setHomeLocation(370,250);// �����ϼ�λ��
			Center.hero.setLife(100);// �����������
			Center.hero.setLive(true);// ���¼������̹��
			Center.hero.setTankLocation(220, 480,Tank.Direction.STOP);// ����̹��λ��
			Center.setDifficulty(2);// changeMap�������õ�ֵ�ı��ͼ
			this.dispose();
			new GameFrame();
		}
		
		if (e.getActionCommand().equals("difficulty3")) {
			Home.setLive(true);// ���¼����ϼ�
			Center.tanks.clear();
			Center.missiles.clear();
			Center.walls.clear();
			Center.hWalls.clear();// ����ǽ���
			Center.rivers.clear();// �������
			Center.trees.clear();// �����
			
			Tank.setNspeed(11);
			Tank.setTankColor(3);// �ı����̹����ɫ
			Missile.setNspeed(12);
			Missile.setMissileColor(3);// �ӵ���ɫ
			Missile.setHurt(40);
			Center.hero.setLife(100);// �����������
			Center.hero.setLive(true);// ���¼������̹��
			Home.setHomeLocation(390,250);// �����ϼ�λ��
			Center.hero.setTankLocation(220, 480,Tank.Direction.STOP);// ����̹��λ��
			Center.setDifficulty(3);// changeMap�������õ�ֵ�ı��ͼ
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
				JOptionPane.showMessageDialog(null, "�������"+"�������߷֣�"+b);
			}else{
				JOptionPane.showMessageDialog(null, newstr[0]+"�������߷֣�"+b);
			}
		}
		
		if (e.getActionCommand().equals("help")) {
			Center.printable = false;// ֹͣ�߳�
			JOptionPane.showMessageDialog(null, "W�����ϣ�A������S�����£�D�����ң�P�������ڵ���R����Ѫ"+"\n","��ʾ",JOptionPane.INFORMATION_MESSAGE);
			Center.printable = true;
			new Thread(this).start(); // �߳�����
		}
		
		if(e.getActionCommand().equals("diy")){
			Center.tanks.clear();// ̹�����
			Center.missiles.clear();// �ӵ����
			Center.walls.clear();// ǽ���
			Center.hWalls.clear();// ����ǽ���
			Center.rivers.clear();// �������
			Center.trees.clear();// �����
			Center.home.setLive(true);
			Center.hero.setLive(true);
			this.dispose();
			new DiyMapFrame();
		}
	}

}
