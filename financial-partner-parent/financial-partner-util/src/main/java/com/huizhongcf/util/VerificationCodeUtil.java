package com.huizhongcf.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 
 * 
 * Description:验证码图片帮助类
 * 
 * @author ouyangjin
 * @version 1.0
 * 
 *          <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2013-10-22    ouyangjin    1.0         1.0 Version
 * </pre>
 */
public class VerificationCodeUtil {
	
	private static String value = "123456789ABCDEFGHJKLMNOPQRSTUVWXYZ";
	// 图片宽、高、验证码长度
	private final static int width = 60, height = 20, codeLength = 4;
	
	private static Random random = new Random();
	// 图片背景色
	private static Color bgcolor = new Color(204, 204, 204);
	// 字体颜色
	private static Color fontcolor = new Color(51, 51, 51);
	// 字体
	private static Font font = new Font("Times   New   Roman", Font.PLAIN, 20);
	// 图片
	private BufferedImage image;
	// 验证码
	private String codevalue;

	public final BufferedImage getImage() {
		return image;
	}

	public final String getCodevalue() {
		return codevalue;
	}

	public VerificationCodeUtil() {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 绘制图片类
		Graphics graphics = image.getGraphics();
		graphics.setColor(bgcolor);
		graphics.fillRect(0, 0, width, height);
		graphics.setFont(font);
		graphics.setColor(fontcolor);
		setDrawLine(graphics);
		codevalue = setDrawCode(graphics);
	}

	/**
	 * 
	 * Description: 绘制干扰线
	 * 
	 * @param graphics
	 * @Author ouyangjin
	 * @Create Date: 2013-10-23 上午11:07:48
	 */
	private static void setDrawLine(Graphics graphics) {
		int x,xl,y=height >>1;
		for (int i = 0; i < 20; i++) {
			x = random.nextInt(width);
			xl = random.nextInt(12);
			graphics.drawLine(x, random.nextInt(3) + y, x + xl, random.nextInt(3)+ y);
		}
	}

	/**
	 * 
	 * Description: 将验证码写入图片
	 * 
	 * @param graphics
	 * @return String 验证码value
	 * @Author ouyangjin
	 * @Create Date: 2013-10-23 上午11:29:21
	 */
	private static String setDrawCode(Graphics graphics) {
		String temp = "";
		int rand;
		String randvalue;
		for (int i = 0; i < codeLength; i++) {
			rand = (int) (Math.random() * value.length());
			randvalue = value.substring(rand, rand + 1);
			graphics.drawString(randvalue, 12 * i + 6, 16 + random.nextInt(5));
			temp += randvalue;
		}
		graphics.dispose();
		return temp;
	}

}
