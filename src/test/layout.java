package test;


import java.awt.*;
import javax.swing.*;
public class layout extends JFrame{
	//�������
	JPanel jp1,jp2;
	JButton jb1,jb2,jb3,jb4,jb5,jb6;
	public static void main(String[] args) {
		layout dm=new layout();
	}
 
	//���캯��
	public layout()
	{
		jp1=new JPanel();
		//�������
		//������JPanel����ģʽĬ�ϵ�����ʽ����FlowLayout
		/*
		jp1=new JPanel();
		
		jp2=new JPanel();
		
		jb1=new JButton("����");
		jb2=new JButton("ƻ��");
		jb3=new JButton("��֦");
		jb4=new JButton("����");
		jb5=new JButton("����");
		jb6=new JButton("�㽶");
		//���ò��֣�JPanelĬ�ϲ���FlowLayout,���������õ��ĸպ�����ʽ���֣����Բ���������
		//��������JPanel
		jp1.setBackground(Color.black);
		jp1.add(jb1);
		jp1.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		jp2.add(jb5);
		
		//��JPanel���뵽JFrame
		this.add(jp1,BorderLayout.NORTH);
		this.add(jb6,BorderLayout.CENTER);
		this.add(jp2,BorderLayout.SOUTH);
		*/
		//���ô�������
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