package test;


import java.awt.*;
import javax.swing.*;
public class layout extends JFrame{
	//定义组件
	JPanel jp1,jp2;
	JButton jb1,jb2,jb3,jb4,jb5,jb6;
	public static void main(String[] args) {
		layout dm=new layout();
	}
 
	//构造函数
	public layout()
	{
		jp1=new JPanel();
		//创建组件
		//面板组件JPanel布局模式默认的是流式布局FlowLayout
		/*
		jp1=new JPanel();
		
		jp2=new JPanel();
		
		jb1=new JButton("西瓜");
		jb2=new JButton("苹果");
		jb3=new JButton("荔枝");
		jb4=new JButton("葡萄");
		jb5=new JButton("橘子");
		jb6=new JButton("香蕉");
		//设置布局，JPanel默认布局FlowLayout,本案例运用到的刚好是流式布局，所以不用设置了
		//把组件添加JPanel
		jp1.setBackground(Color.black);
		jp1.add(jb1);
		jp1.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		jp2.add(jb5);
		
		//把JPanel加入到JFrame
		this.add(jp1,BorderLayout.NORTH);
		this.add(jb6,BorderLayout.CENTER);
		this.add(jp2,BorderLayout.SOUTH);
		*/
		//设置窗口属性
		jp1.setBackground(Color.red);
		Canvas can = new Canvas();
		
		can.setBounds(0, 0, 30, 30);
		jp1.add(can);
		
		
		this.add(jp1,BorderLayout.WEST);
		this.setSize(300,200);
		this.setLocation(700,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
}