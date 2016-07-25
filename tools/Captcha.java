package tools;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;

public class Captcha {
	
	private static final String str = "qazwsxedcrfvtgbyhnujmklp23456789";
	private static Random random = new Random();
	private String result;
	private ImageIcon icon;
	
	private int randomfield(int min, int max) {
		int num = 0;
		num = random.nextInt(max-min)+min;
		return num;
	}
	
	public ImageIcon getInstance(int width, int height) {
		BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		int bgcolor = 255;
		g.setColor(new Color(bgcolor,bgcolor,bgcolor));
		g.fillRect(0, 0, width, height);
		result = new String("");
		for (int i=0;i<4;i++) {			
			g.setColor(new Color(randomfield(50,180),randomfield(50,180),randomfield(50,180)));
			g.setFont(new Font("黑体",Font.BOLD,width/3));
			char c = str.charAt(randomfield(0,str.length()));
			g.drawString(String.valueOf(c), width/12+i*width/4, randomfield(height-width/4,height));
			result += String.valueOf(c);
		}
		
		for (int i=0;i<10;i++) {
			g.setColor(new Color(randomfield(50,180),randomfield(50,180),randomfield(50,180)));
			int start_X = randomfield(0,width);
			int start_Y = randomfield(0,height);
			int end_X = randomfield(0,width);
			int end_Y = randomfield(0,height);
			g.drawLine(start_X, start_Y, end_X, end_Y);
		}
		icon = new ImageIcon(img);
		return icon;
	}
	
	public String getString() {
		return result;
	}
}
