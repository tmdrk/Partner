/* 
 * Copyright (C) 2014-2015 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: ImageUtils.java 
 *
 * Created: [2015年6月2日 下午1:42:37] by cheng 
 *
 * $Id$
 * 
 * $Revision$
 *
 * $Author$
 *
 * $Date$
 *
 * ============================================================ 
 * 
 * ProjectName: ephwealth-core 
 * 
 * Description: 
 * 
 * ==========================================================*/

package com.huizhongcf.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/** 
 *
 * Description:
 *
 * @author chengzhichao 
 * @version 1.0
 * <pre>
 * Modification History: 
 *  Date           Author        Version       Description 
 * ------------------------------------------------------------------ 
 * 2015年6月2日      chengzhichao       1.0         1.0 Version 
 * </pre>
 */

public class ImageUtils {

	private ImageUtils(){}
	
	private BufferedImage image = null;
	
	public void load(File imageFile) throws IOException {
		image = ImageIO.read(imageFile);
	}
	public void loadImage(BufferedImage image) throws IOException {
		this.image = image;
	}
	
	public int getImageWidth() {
		return image.getWidth();
	}
	
	public BufferedImage getImg() {
		return image;
	}

	public int getImageHeight() {
		return image.getHeight();
	}
	
	/**
	 * 加载
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static ImageUtils load(String fileName) throws IOException {
		File file = new File(fileName);
		return fromImageFile(file);
	}
	
	private static ImageUtils fromImageFile(File file) throws IOException {
		ImageUtils utils = new ImageUtils();
		utils.load(file);
		return utils;
	}
	
	/**
	 * 
	 * Description: 缩放
	 *
	 * @param tarWidth
	 * @param tarHeight
	 * @Author chengzhichao
	 * @Create Date: 2015年6月2日 下午1:44:04
	 */
	public void zoomTo(int tarWidth, int tarHeight) {
		BufferedImage tagImage = new BufferedImage(tarWidth, tarHeight,
				BufferedImage.TYPE_INT_RGB); 
		Image image = this.image.getScaledInstance(tarWidth, tarHeight,
				Image.SCALE_SMOOTH);
		Graphics g = tagImage.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		this.image = tagImage;

	}
	
	/**
	 * 
	 * Description: 保存
	 *
	 * @param fileName
	 * @param formatName
	 * @throws IOException
	 * @Author chengzhichao
	 * @Create Date: 2015年6月2日 下午1:44:24
	 */
	public void save(String fileName, String formatName) throws IOException {
		FileOutputStream out = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(this.image, formatName, bos);
			out = new FileOutputStream(fileName);
			out.write(bos.toByteArray());
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			String p =  "D:/upload/user/123.jpg";
			ImageUtils image = ImageUtils.load(p);
			image.zoomTo(300, 300);
			image.save("e:/111.jpg", "jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
