/* 
 * Copyright (C) 2014-2016 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: QRCodeUtils.java 
 *
 * Created: [2016年3月9日 上午10:42:33] by cheng 
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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**
 *
 * Description: 二维码的生成与解析
 *
 * @author chengzhichao
 * @version 1.0
 * 
 *          <pre>
 * Modification History: 
 *  Date           Author        Version       Description 
 * ------------------------------------------------------------------ 
 * 2016年3月9日      chengzhichao       1.0         1.0 Version
 * </pre>
 */

public class QRCodeUtils {

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	/**
	 * 
	 * Description: 生成二维码图片
	 *
	 * @param matrix
	 * @return
	 * @Author chengzhichao
	 * @Create Date: 2016年3月9日 上午10:53:43
	 */
	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	/**
	 * 
	 * Description: 写入磁盘目录
	 *
	 * @param matrix
	 * @param format
	 * @param file
	 * @throws IOException
	 * @Author chengzhichao
	 * @Create Date: 2016年3月9日 上午10:53:59
	 */
	public static void writeToFile(BitMatrix matrix, String format, File file)
			throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format"+ format + "to" + file);
		}
	}

	/**
	 * 
	 * Description: 写入流
	 *
	 * @param matrix
	 * @param format
	 * @param stream
	 * @throws IOException
	 * @Author chengzhichao
	 * @Create Date: 2016年3月9日 上午11:03:30
	 */
	public static void writeToStream(BitMatrix matrix, String format,
			OutputStream stream) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, stream)) {
			throw new IOException("Could not write an image of format "
					+ format);
		}
	}

	/**
	 * 
	 * Description: 解析二维码图片
	 *
	 * @param file
	 * @return
	 * @throws Exception
	 * @Author chengzhichao
	 * @Create Date: 2016年3月9日 上午11:31:17
	 */
	public static Result parseQRCodeImage(File file) throws Exception {
		MultiFormatReader formatReader = new MultiFormatReader();
		BufferedImage image = ImageIO.read(file);
		LuminanceSource source = new BufferedImageLuminanceSource(image);
		Binarizer binarizer = new HybridBinarizer(source);
		BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
		@SuppressWarnings("rawtypes")
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		Result result = formatReader.decode(binaryBitmap, hints);
		return result;
	}

	public static void main(String[] args) {
		// 生成二维码图片
//		try {
//			String content = "www.ephwealth.com";
//			String path = "D:/temp";
//			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
//			Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
//			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//			BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400, hints);
//			File file1 = new File(path, "餐巾纸.jpg");
//			QRCodeUtils.writeToFile(bitMatrix, "jpg", file1);
//		} catch (WriterException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		// 解析二维码图片
		try {
			Result result = QRCodeUtils.parseQRCodeImage(new File("d:/temp/test.jpg"));
			System.out.println("result = " + result.toString());
			System.out.println("resultFormat = " + result.getBarcodeFormat());
			System.out.println("resultText = " + result.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
