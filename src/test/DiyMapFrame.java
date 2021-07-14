package test;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.swing.*;

import org.omg.CORBA.PUBLIC_MEMBER;

public class DiyMapFrame extends JFrame implements Runnable,KeyListener,ActionListener{
	Tank drawTank = new Tank(350 , 230, true ,Tank.Direction.STOP);//��ʼ̹�˵�λ��
	Home home = new Home();
	{
		Home.setHomeLocation(-50,-50);//��ʼ�ҵ�λ��
	}
	boolean press = true;//�Ƿ�������ϰ���
	boolean enter1 = true,enter2 = true,enter3 = true,enter4 = true,enter5 = true;//̹���Ƿ�����ϰ���
	Clear clear = new Clear();
	
	JMenuBar jmb = new JMenuBar();
	JMenu jm1 = new JMenu("��Ϸ");
	JMenu jm2 = new JMenu("����");
	JMenuItem jmi1 = new JMenuItem("��ʼ��Ϸ");
	JMenuItem jmi2 = new JMenuItem("�����Զ���");
	JMenuItem jmi3 = new JMenuItem("������Ϸ");
	
	public DiyMapFrame(){
		setJMenuBar(jmb);
		
		jm1.add(jmi1);
		jm1.add(jmi3);
		jm2.add(jmi2);
		
		jmi1.setActionCommand("start");
		jmi1.addActionListener(this);
		jmi1.setAccelerator(KeyStroke.getKeyStroke("F1"));// ���ÿ�ݼ�
		
		jmi2.setActionCommand("diy");
		jmi2.addActionListener(this);
		
		jmi3.setActionCommand("back");
		jmi3.addActionListener(this);
		jmi3.setAccelerator(KeyStroke.getKeyStroke("F2"));// ���ÿ�ݼ�
		
		jmb.add(jm1);
		jmb.add(jm2);
		
		addKeyListener(drawTank);
		addKeyListener(this);
		setTitle("�Զ����ͼ");
		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		Thread t = new Thread(this);
		t.start();
		JPanel jp = new JPanel(){
			{
				setBackground(Color.gray);//����
			}
			public void paint(Graphics g){
				super.paint(g);
				g.drawString("��ͨǽ�ĸ�����"+Center.walls.size(), 10, 20);
				g.drawString("����ǽ�ĸ�����"+Center.hWalls.size(), 10, 40);
				g.drawString("�����ĸ�����"+Center.rivers.size(), 10, 60);
				g.drawString("�ݴԵĸ�����"+Center.trees.size(), 10, 80);
				//������ͨǽ
				for (int i = 0; i < Center.walls.size(); i++) { 
					Wall w = Center.walls.get(i);
					w.draw(g);
				}
				//�ж�̹���Ƿ������ͨǽ
				for (int n = 0; n < Center.walls.size(); n++) { 
					Wall w = Center.walls.get(n);
					if (drawTank.getRect().intersects(w.getRect())) {
						enter1 = false;
						clear.type(1,w);//�����ϰ������ͺͶ���
//						System.out.println("������ǽ");
						break;
					}else{
						enter1 = true;
//						System.out.println("�˳�");
					}
				}
				if(enter1 == false && Center.walls.size() == 0){//�Խ��������ͨǽ���ж�
					enter1 = true;
				}
				
				// ��������ǽ
				for (int n = 0; n < Center.hWalls.size(); n++) { 
					HardWall hw = Center.hWalls.get(n);
					hw.draw(g);
				}
				for (int n = 0; n < Center.hWalls.size(); n++) { 
					HardWall hw = Center.hWalls.get(n);
					if (drawTank.getRect().intersects(hw.getRect())) {
						enter2 = false;
						clear.type(2,hw);
						break;
					}else{
						enter2 = true;
					}
				}
				if(enter2 == false && Center.hWalls.size() == 0){
					enter2 = true;
				}
				
				// ������
				for (int n = 0; n < Center.rivers.size(); n++) { 
					River river = Center.rivers.get(n);
					river.draw(g);
				}
				for (int n = 0; n < Center.rivers.size(); n++) { 
					River river = Center.rivers.get(n);
					if (drawTank.getRect().intersects(river.getRect())) {
						enter3 = false;
						clear.type(3,river);
						break;
					}else{
						enter3 = true;
					}
				}
				if(enter3 == false && Center.rivers.size() == 0){
					enter3 = true;
				}
				
				// ������
				for (int n = 0; n < Center.trees.size(); n++) { 
					Tree tree = Center.trees.get(n);
					tree.draw(g);
				}
				for (int n = 0; n < Center.trees.size(); n++) { 
					Tree tree = Center.trees.get(n);
					if (drawTank.getRect().intersects(tree.getRect())) {
						enter4 = false;
						clear.type(4,tree);
						break;
					}else{
						enter4 = true;
					}		
				}
				if(enter4 == false && Center.trees.size() == 0){
					enter4 = true;
				}
				
				//�ж�̹���Ƿ�����
				if(drawTank.getRect().intersects(home.getRect())){
					enter5 = false;
					clear.type(5,home);
				}else{
					enter5 = true;
				}
				
				if(enter1&&enter2&&enter3&&enter4&&enter5){//��̹��û�н�������һ���ϰ���ʱpress��Ϊtrue
					press = true;
				}else{
					press = false;
				}
//				System.out.println("press״̬��"+press);
				home.draw(g);
				drawTank.draw(g);
			}
		};
		add(jp);
	}
	
	public void run(){
		while(true){
			try{
				Thread.sleep(20);
			}catch(Exception e){
			}
			repaint();
		}
	}
	
	public static void main(String[] args){
		new DiyMapFrame();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_H && press){
			Center.walls.add(new Wall(drawTank.getX(),drawTank.getY()));;
			new Thread(new Music(Music.PLAY_SET)).start();// �����ϰ�����Ч
		}
		if(e.getKeyCode()==KeyEvent.VK_J && press){
			Center.hWalls.add(new HardWall(drawTank.getX(),drawTank.getY()));
			new Thread(new Music(Music.PLAY_SET)).start();
		}
		if(e.getKeyCode()==KeyEvent.VK_K && press){
			Center.rivers.add(new River(drawTank.getX(),drawTank.getY()));
			new Thread(new Music(Music.PLAY_SET)).start();
		}
		if(e.getKeyCode()==KeyEvent.VK_L && press){
			Center.trees.add(new Tree(drawTank.getX(),drawTank.getY()));
			new Thread(new Music(Music.PLAY_SET)).start();
		}
		if(e.getKeyCode()==KeyEvent.VK_G && press){
			Home.setHomeLocation(drawTank.getX(),drawTank.getY());
			new Thread(new Music(Music.PLAY_SET)).start();
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			new Thread(new Music(Music.PLAY_SETMOVE)).start();
			clear.removeTrash();
		}	
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("start")){
			this.dispose();
			Center.setDifficulty(4);
			//���������Զ����ͼǰ������Ҫ������������
			Center.hero.setLive(true);
			Center.hero.setLife(100);
			Center.printable = true;
			new GameFrame();
		}
		if(e.getActionCommand().equals("back")){
			this.dispose();
			Home.setHomeLocation(290, 250);// �����ϼ�λ��
			new GameFrame();
		}
		if(e.getActionCommand().equals("diy")){
			JOptionPane.showMessageDialog(null, "G���ң�H����ͨǽ��J������ǽ��K��������L���ݵأ��ո����","��ʾ",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
}

class Clear{
	int type;
	Object object;

	public void type(int t,Object o){
		type=t;
		object=o;
	}

	public void removeTrash(){
		switch(type){
			case 1: Center.walls.remove(object);break;//���̹�˽�����ϰ���
			case 2: Center.hWalls.remove(object);break;
			case 3: Center.rivers.remove(object);break;
			case 4: Center.trees.remove(object);break;
			case 5: Home.setHomeLocation(-50, -50);break;
			default:break;
		}
	}
}
