package test;

import java.util.ArrayList;
import java.util.List;

public class Center {
	static int enemyNumber = 1;// ����̹�˳�ʼ����
	static int round = 1;//ս������
	static int difficulty = 0; //��ʼ��ͼ
	
	public static void setDifficulty(int difficulty1){
		difficulty = difficulty1;
	}
	
	
	static List<Tank> tanks = new ArrayList<Tank>();// װ̹�˵�����
	
	
	static List<Missile> missiles = new ArrayList<Missile>();// װ�ӵ�������

	static List<Explode> explodes = new ArrayList<Explode>();// װ��ը������

	static List<Wall> walls = new ArrayList<Wall>();// װ��ͨǽ������
	
	static List<HardWall> hWalls = new ArrayList<HardWall>();// װ����ǽ������
	
	static List<Tree> trees = new ArrayList<Tree>();// װ��������
	
	static List<River> rivers = new ArrayList<River>();// װ�ӵ�����
	
	public static boolean play = false;// �������ֵĿ�����ر�

	public static boolean printable = true;// �����̵߳Ŀ�����ر�
	
	static Home home = new Home();// ʵ����һ���ϼ�
	
	static Tank hero = new Tank(220, 480, true, Tank.Direction.STOP);// ���̹��λ��
	
	static prop pr = new prop();//����������
	
	static newJp jp=null;
}
