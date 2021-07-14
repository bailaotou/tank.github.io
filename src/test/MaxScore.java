package test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
/**
 * 文本写入与读取类
 */
public class MaxScore {
	static String maxString;
	static String fileName = "txt/maxScore.txt";
	static File file = new File(fileName);
	static String difficulty = "体验模式";
	
	public static void setdifficultyName(String name){
		difficulty = name;
	}
	
	//读入TXT文件 
	public static String readTxt(){
		BufferedReader reader = null;
		try {
			if(!file.exists()) {
				file.createNewFile();
				write(null,0);
			}
			reader = new BufferedReader(new FileReader(file));
			String temp = null;
			// 一次读入一行，直到读入null为文件结束N
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
//		String scoreString=difficulty+"："+StartFrame.getUserName()+":"+score;
		String scoreString = StartFrame.getUserName()+":"+score;
		try {  
			PrintStream ps = new PrintStream(new FileOutputStream(file));  
			ps.println("此文档为坦克大战游戏生成，可以删除，但还会生成，终于可以流氓一次啦，哈哈！");// 往文件里写入字符串  
			ps.append(scoreString+"\n");
			ps.close();
		} catch (FileNotFoundException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
		}  
	}  
}

