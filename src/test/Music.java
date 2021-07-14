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
		
		/** ���������ļ���ַ */
		public final static String PLAY_FIRE = "music/fire.wav";
		
		/** ��ò��������ļ���ַ */
		public final static String PLAY_EAT = "music/eat.wav";
		
		/** ����ƶ��ļ���ַ */
		public final static String PLAY_CHOOSE = "music/choose.wav";

		/** ��ը�����ļ���ַ */
		public final static String PLAY_EXPLODE = "music/explode.wav";

		/** ��ײ��Ч�ļ���ַ */
		public final static String PLAY_HIT = "music/hit.wav";
		
		/** ���������ļ���ַ */
		public final static String PLAY_BACKMUSIC = "music/backMusic.wav";
		
		/** ʤ�������ļ���ַ */
		public final static String PLAY_WIN = "music/win.wav";
		
		/** ʧ�������ļ���ַ */
		public final static String PLAY_LOSE = "music/lose.wav";
		
		/** �����ϰ��������ļ���ַ */
		public final static String PLAY_SET = "music/set.wav";
		
		/** �Ƴ��ϰ��������ļ���ַ */
		public final static String PLAY_SETMOVE = "music/setMove.wav";
		
		static File f = new File("music/backMusic.wav");
		static URL url1;
		static AudioClip aau;
			
		private String url;
		
		public Music(String url){
			this.url = url;
		}
		
		// ���ű������ַ���
		public static void openSound(){

			System.out.println("����");
			try {
				url1 = f.toURL();
			} catch (MalformedURLException e) {
				e.printStackTrace();// ����·��
			}
			aau = Applet.newAudioClip(url1);
			aau.loop();
		}
		public static void closeSound(){
			System.out.println("ֹͣ");
			try {
				url1 = f.toURL();
			} catch (MalformedURLException e) {
				e.printStackTrace();// ����·��
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
			//���ǻ���
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
//		//���췽��
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
//			System.out.println("����");
//			try {
//				url1 = f.toURL();
//			} catch (MalformedURLException e) {
//				e.printStackTrace();// ����·��
//			}
//			aau = Applet.newAudioClip(url1);
//			aau.loop();
//		}
//		public static void openSound(){
//			System.out.println("ֹͣ");
//			try {
//				url1 = f.toURL();
//			} catch (MalformedURLException e) {
//				e.printStackTrace();// ����·��
//			}
//			aau = Applet.newAudioClip(url1);
//			aau.stop();
//		}
//}
