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
		g.drawString("你的分数：" + Missile.getCount(), 10, 20);
		g.drawString("你的生命值：" + Center.hero.getLife(), 10, 40);
		g.drawString("敌人对你的伤害：" + Missile.getHurt(), 10, 60);
		g.drawString("第：" + Center.round + " 轮战斗" + "，敌人总数："+ Center.tanks.size(), 10, 80);
		
		
		//血条
		g.drawRect(45, 515, 700, 15);
		g.fillRect(45, 515, Center.hero.getLife()*7, 15);
		
		Center.hero.draw(g);// 画出玩家的坦克
		Center.home.draw(g);//画出自己的家
		Center.hero.collideWithTanks(Center.tanks);//玩家坦克撞上敌方坦克
		Center.hero.collideWithHome(Center.home);
		
		
		// 把容器里面的子弹都画出来
		for (int i = 0; i < Center.missiles.size(); i++) {
			Missile m = Center.missiles.get(i);
			m.draw(g);// 画子弹
			m.hitTanks(Center.tanks);// 玩家子弹攻击敌方
			m.hitTank(Center.hero);// 敌方子弹攻击玩家
			m.hitHome();// 敌方子弹攻击老家
			
			for (int j = 0; j < Center.walls.size(); j++) {
				Wall w = Center.walls.get(j);
				m.hitWalls(w);// 每一个子弹打到其他墙上
			}
			for (int j = 0; j < Center.hWalls.size(); j++) {
				HardWall hw = Center.hWalls.get(j);
				m.hitWalls(hw);// 每一个子弹打到金属墙上
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
		
		// 把容器里面的坦克都画出来
		for (int i = 0; i < Center.tanks.size(); i++) {
			Tank t = Center.tanks.get(i);
			t.draw(g);
			
			//画出普通墙
			for (int j = 0; j < Center.walls.size(); j++) { 
				Wall w = Center.walls.get(j);
				w.draw(g);
				t.collideWall(w);// 每一个坦克撞到普通墙
				Center.hero.collideWall(w);
			}
			
			// 画出金属墙
			for (int n = 0; n < Center.hWalls.size(); n++) { 
				HardWall hw = Center.hWalls.get(n);
				hw.draw(g);
				t.collideHardWall(hw);// 每一个坦克撞到金属墙
				Center.hero.collideHardWall(hw);
			}
			
			// 画出河
			for (int n = 0; n < Center.rivers.size(); n++) { 
				River river = Center.rivers.get(n);
				river.draw(g);
				t.collideRiver(river);// 每一个坦克撞到金属墙
				Center.hero.collideRiver(river);
			}
			
			// 画出树
			for (int n = 0; n < Center.trees.size(); n++) { 
				Tree tree = Center.trees.get(n);
				tree.draw(g);
			}
			
			t.collideWithTanks(Center.tanks); // 敌方坦克撞到自己人
			t.collideWithHome(Center.home);
			
		}
		
		// 把容器里面的爆炸全部画出来
		for (int i = 0; i < Center.explodes.size(); i++) {
			Explode e = Center.explodes.get(i);
			e.draw(g);
		}
		
		//每关卡的敌人设置
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