package test;
import java.awt.Color;
import java.awt.Graphics;

public class Explode {
	int x, y;// 爆炸位置
	private boolean live = true;// 爆炸是否存在
	int step = 0;// 爆炸时间控制
	int[] diameter = new int[] { 4, 7, 12, 18, 26, 32, 49, 56, 65, 77, 80, 50, 40, 30, 14, 6 };// 爆炸范围

	public Explode(int x, int y) {
		//super();
		this.x = x;
		this.y = y;
	}

	// 画爆炸
	public void draw(Graphics g) {
		if (!live)return;// 如果爆炸死亡状态不画结束
		// 如果爆炸时间结束爆炸不存在并在集合中删除
		if (step == diameter.length) {
			live = false;// 爆炸死亡
			step = 0; // 步骤时间归0
			Center.explodes.remove(this);//集合中删除
			return;
		}

		// 画爆炸设置
		Color c = g.getColor();
		g.setColor(Color.red);
//		Music.musicExplode();
		g.fillOval(x, y, diameter[step], diameter[step]);
		//g.setColor(c);

		step++;
	}

}
