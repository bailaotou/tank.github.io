package test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
/**
 * �ı�д�����ȡ��
 */
public class MaxScore {
	static String maxString;
	static String fileName = "txt/maxScore.txt";
	static File file = new File(fileName);
	static String difficulty = "����ģʽ";
	
	public static void setdifficultyName(String name){
		difficulty = name;
	}
	
	//����TXT�ļ� 
	public static String readTxt(){
		BufferedReader reader = null;
		try {
			if(!file.exists()) {
				file.createNewFile();
				write(null,0);
			}
			reader = new BufferedReader(new FileReader(file));
			String temp = null;
			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����N
			while ((temp = reader.readLine()) != null) {
				maxString = temp;
			}
			reader.close();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
				    reader.close();
				} catch (IOException e1) {
					
				}
			}
		}
		return maxString;
	}
	
	
	public static void write(String username,int score){
//		String scoreString=difficulty+"��"+StartFrame.getUserName()+":"+score;
		String scoreString = StartFrame.getUserName()+":"+score;
		try {  
			PrintStream ps = new PrintStream(new FileOutputStream(file));  
			ps.println("���ĵ�Ϊ̹�˴�ս��Ϸ���ɣ�����ɾ�������������ɣ����ڿ�����åһ������������");// ���ļ���д���ַ���  
			ps.append(scoreString+"\n");
			ps.close();
		} catch (FileNotFoundException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
		}  
	}  
}

