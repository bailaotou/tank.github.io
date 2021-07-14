package test;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;



public class newJp extends JPanel{
	
	{
		setBackground(Color.gray);
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawString("��ķ�����" + Missile.getCount(), 10, 20);
		g.drawString("�������ֵ��" + Center.hero.getLife(), 10, 40);
		g.drawString("���˶�����˺���" + Missile.getHurt(), 10, 60);
		g.drawString("�ڣ�" + Center.round + " ��ս��" + "������������"+ Center.tanks.size(), 10, 80);
		
		
		//Ѫ��
		g.drawRect(45, 515, 700, 15);
		g.fillRect(45, 515, Center.hero.getLife()*7, 15);
		
		Center.hero.draw(g);// ������ҵ�̹��
		Center.home.draw(g);//�����Լ��ļ�
		Center.hero.collideWithTanks(Center.tanks);//���̹��ײ�ϵз�̹��
		Center.hero.collideWithHome(Center.home);
		
		
		// ������������ӵ���������
		for (int i = 0; i < Center.missiles.size(); i++) {
			Missile m = Center.missiles.get(i);
			m.draw(g);// ���ӵ�
			m.hitTanks(Center.tanks);// ����ӵ������з�
			m.hitTank(Center.hero);// �з��ӵ��������
			m.hitHome();// �з��ӵ������ϼ�
			
			for (int j = 0; j < Center.walls.size(); j++) {
				Wall w = Center.walls.get(j);
				m.hitWalls(w);// ÿһ���ӵ�������ǽ��
			}
			for (int j = 0; j < Center.hWalls.size(); j++) {
				HardWall hw = Center.hWalls.get(j);
				m.hitWalls(hw);// ÿһ���ӵ��򵽽���ǽ��
			}
		}
		//
		//System.out.print(Center.tanks.size()+"\n");
		for(int i =0;i<Center.missiles.size();i++) {
			Missile mis = Center.missiles.get(i);
			if(mis.live==false) {
				Center.missiles.remove(i);
			}
		}
		
		// �����������̹�˶�������
		for (int i = 0; i < Center.tanks.size(); i++) {
			Tank t = Center.tanks.get(i);
			t.draw(g);
			
			//������ͨǽ
			for (int j = 0; j < Center.walls.size(); j++) { 
				Wall w = Center.walls.get(j);
				w.draw(g);
				t.collideWall(w);// ÿһ��̹��ײ����ͨǽ
				Center.hero.collideWall(w);
			}
			
			// ��������ǽ
			for (int n = 0; n < Center.hWalls.size(); n++) { 
				HardWall hw = Center.hWalls.get(n);
				hw.draw(g);
				t.collideHardWall(hw);// ÿһ��̹��ײ������ǽ
				Center.hero.collideHardWall(hw);
			}
			
			// ������
			for (int n = 0; n < Center.rivers.size(); n++) { 
				River river = Center.rivers.get(n);
				river.draw(g);
				t.collideRiver(river);// ÿһ��̹��ײ������ǽ
				Center.hero.collideRiver(river);
			}
			
			// ������
			for (int n = 0; n < Center.trees.size(); n++) { 
				Tree tree = Center.trees.get(n);
				tree.draw(g);
			}
			
			t.collideWithTanks(Center.tanks); // �з�̹��ײ���Լ���
			t.collideWithHome(Center.home);
			
		}
		
		// ����������ı�ըȫ��������
		for (int i = 0; i < Center.explodes.size(); i++) {
			Explode e = Center.explodes.get(i);
			e.draw(g);
		}
		
		//ÿ�ؿ��ĵ�������
		if(Center.tanks.size()==0 && Center.round<6){
			for (int i = 0; i < Center.enemyNumber*2; i++) {
				Tank t=null;
				if(i<2){
					t=new Tank(100 + 70 * i, 50, false, Tank.Direction.L);
				}else if(i>3){
					t=new Tank(510, i * 50 + 20, false, Tank.Direction.R);
				}else{
					t=new Tank(50+i * 50 , 500, false, Tank.Direction.U);
				}
				Center.tanks.add(t);
			}
			Center.enemyNumber++;
			Center.round++;
		}
		Center.pr.draw(g);
				
	}
}