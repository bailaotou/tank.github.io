package test;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;




	public class Music implements Runnable {
		
		/** 开火音乐文件地址 */
		public final static String PLAY_FIRE = "music/fire.wav";
		
		/** 获得补给音乐文件地址 */
		public final static String PLAY_EAT = "music/eat.wav";
		
		/** 光标移动文件地址 */
		public final static String PLAY_CHOOSE = "music/choose.wav";

		/** 爆炸音乐文件地址 */
		public final static String PLAY_EXPLODE = "music/explode.wav";

		/** 碰撞音效文件地址 */
		public final static String PLAY_HIT = "music/hit.wav";
		
		/** 背景音乐文件地址 */
		public final static String PLAY_BACKMUSIC = "music/backMusic.wav";
		
		/** 胜利音乐文件地址 */
		public final static String PLAY_WIN = "music/win.wav";
		
		/** 失败音乐文件地址 */
		public final static String PLAY_LOSE = "music/lose.wav";
		
		/** 安放障碍物音乐文件地址 */
		public final static String PLAY_SET = "music/set.wav";
		
		/** 移除障碍物音乐文件地址 */
		public final static String PLAY_SETMOVE = "music/setMove.wav";
		
		static File f = new File("music/backMusic.wav");
		static URL url1;
		static AudioClip aau;
			
		private String url;
		
		public Music(String url){
			this.url = url;
		}
		
		// 播放背景音乐方法
		public static void openSound(){

			System.out.println("播放");
			try {
				url1 = f.toURL();
			} catch (MalformedURLException e) {
				e.printStackTrace();// 解析路径
			}
			aau = Applet.newAudioClip(url1);
			aau.loop();
		}
		public static void closeSound(){
			System.out.println("停止");
			try {
				url1 = f.toURL();
			} catch (MalformedURLException e) {
				e.printStackTrace();// 解析路径
			}
			aau = Applet.newAudioClip(url1);
			aau.stop();
		}
		
		public void run() {
			File soundFile = new File(url);

			AudioInputStream audioInputStream = null;
			try {
				audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			} catch (Exception e1) {
				e1.printStackTrace();
				return;
			}

			AudioFormat format = audioInputStream.getFormat();
			SourceDataLine auline = null;
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

			try {
				auline = (SourceDataLine) AudioSystem.getLine(info);
				auline.open(format);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}

			auline.start();
			int nBytesRead = 0;
			//这是缓冲
			byte[] abData = new byte[1024];

			try {
				while (nBytesRead != -1) {
					nBytesRead = audioInputStream.read(abData, 0, abData.length);
					if (nBytesRead >= 0)
						auline.write(abData, 0, nBytesRead);
				}
			} catch (IOException e) {
				e.printStackTrace();
				return;
			} finally {
				auline.drain();
				auline.close();
			}
		}
	}







//public class Music {
//
//	static boolean play = true;
//	static File f = new File("music/backMusic.wav");
//	static URL url1;
//	static AudioClip aau;
//
//		//构造方法
//		public Music(){
//
//			
//		}	
	
//		public void sound(int n){
//			if(play){
//				switch(n){
//				case 1:
//					sound1.loop();
//					break;
//				}
//			}
//		}
		
//		public static void closeSound(){
//
//			System.out.println("播放");
//			try {
//				url1 = f.toURL();
//			} catch (MalformedURLException e) {
//				e.printStackTrace();// 解析路径
//			}
//			aau = Applet.newAudioClip(url1);
//			aau.loop();
//		}
//		public static void openSound(){
//			System.out.println("停止");
//			try {
//				url1 = f.toURL();
//			} catch (MalformedURLException e) {
//				e.printStackTrace();// 解析路径
//			}
//			aau = Applet.newAudioClip(url1);
//			aau.stop();
//		}
//}
