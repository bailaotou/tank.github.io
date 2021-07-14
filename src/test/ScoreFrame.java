package test;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.print.attribute.standard.OutputDeviceAssigned;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class ScoreFrame extends JFrame {
//	JPanel jpCenter = new JPanel(); 
	JPanel jpNorth = new JPanel(); 
	JLabel jl = new JLabel("玩家历史成绩");
	static JTextArea jta =new JTextArea(11,34);
	JScrollPane jspCenter = new JScrollPane(jta);
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	
	public ScoreFrame() throws IOException{
		setLayout(new BorderLayout());
//		jta.setAlignmentX(CENTER_ALIGNMENT);
		jpNorth.add(jl);
		jl.setFont(new Font("微软雅黑",1,20));
//		jl.setSize(150,70);
		jpNorth.setLayout(new FlowLayout());
	
		jta.setEditable(false);
		add(jpNorth,BorderLayout.NORTH);
		add(jspCenter,BorderLayout.CENTER);
		
		FileReader fReader =new FileReader("txt/score.txt");
		BufferedReader bReader =new BufferedReader(fReader);
		ArrayList<String> scoreList =new ArrayList<String>();
		String string;
		while((string=bReader.readLine())!=null){
			scoreList.add(string);
		}
		for (int i = 0; i <scoreList.size(); i++) {
			jta.append(scoreList.get(i)+"\n");		
		}
		
		setSize(450, 300);
		setVisible(true);
		setResizable(false);
		setTitle("历史记录");
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				setVisible(false);
				jta.setText("");
			}
		});
	}
	public static void main(String[] args) throws IOException{
		
		new ScoreFrame();
	}
}

