package test;
import java.awt.Color;
import java.awt.Graphics;

public class Explode {
	int x, y;// ��ըλ��
	private boolean live = true;// ��ը�Ƿ����
	int step = 0;// ��ըʱ�����
	int[] diameter = new int[] { 4, 7, 12, 18, 26, 32, 49, 56, 65, 77, 80, 50, 40, 30, 14, 6 };// ��ը��Χ

	public Explode(int x, int y) {
		//super();
		this.x = x;
		this.y = y;
	}

	// ����ը
	public void draw(Graphics g) {
		if (!live)return;// �����ը����״̬��������
		// �����ըʱ�������ը�����ڲ��ڼ�����ɾ��
		if (step == diameter.length) {
			live = false;// ��ը����
			step = 0; // ����ʱ���0
			Center.explodes.remove(this);//������ɾ��
			return;
		}

		// ����ը����
		Color c = g.getColor();
		g.setColor(Color.red);
//		Music.musicExplode();
		g.fillOval(x, y, diameter[step], diameter[step]);
		//g.setColor(c);

		step++;
	}

}
