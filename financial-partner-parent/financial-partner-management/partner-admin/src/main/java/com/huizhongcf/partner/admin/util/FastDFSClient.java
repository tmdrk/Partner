package com.huizhongcf.partner.admin.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.util.ResourceUtils;

public class FastDFSClient {

	private static String  CONFIG_FILENAME = "classpath:property/config.properties";
	private static StorageClient1 storageClient1 = null;

	// 初始化FastDFS Client
	static {
		try {
			String configFilePath = ResourceUtils.getFile(CONFIG_FILENAME).getPath();
			PropertyUtil.init(configFilePath);
			ClientGlobal.init(configFilePath);
			TrackerClient trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
			TrackerServer trackerServer = trackerClient.getConnection();
			if (trackerServer == null) {
				throw new IllegalStateException("get fastdfs Connection return null");
			}
			StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
			if (storageServer == null) {
				throw new IllegalStateException("get fastdfs StoreStorage return null");
			}
			storageClient1 = new StorageClient1(trackerServer,storageServer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 上传文件
	 * @param file 文件对象
	 * @param fileName 文件名
	 * @return
	 */
	public static String uploadFile(byte[] buff, String fileExtName) {
		return uploadFile(buff,fileExtName,null);
	}

	/**
	 * 上传文件
	 * @param file 文件对象
	 * @param fileName 文件名
	 * @param metaList 文件元数据
	 * @return
	 */
	public static String uploadFile(byte[] buff, String fileExtName, Map<String,String> metaList) {
		try {
			//byte[] buff =  input2byte(new FileInputStream(file));
			NameValuePair[] nameValuePairs = null;
			if (metaList != null) {
				nameValuePairs = new NameValuePair[metaList.size()];
				int index = 0;
				for (Iterator<Map.Entry<String,String>> iterator = metaList.entrySet().iterator(); iterator.hasNext();) {
					Map.Entry<String,String> entry = iterator.next();
					String name = entry.getKey();
					String value = entry.getValue();
					nameValuePairs[index++] = new NameValuePair(name,value);
				}
			}
			String storagePort = PropertyUtil.dataMap.get("storage_port");
			String protocol = PropertyUtil.dataMap.get("protocol");
			return protocol+"://"+storagePort+"/"+storageClient1.upload_file1(buff,fileExtName,nameValuePairs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取文件元数据
	 * @param fileId 文件ID,实例：group1/M00/00/00/CgoU2VhT9XuAB1bGAABi7sZpreM166.jpg
	 * @return
	 */
	public static Map<String,String> getFileMetadata(String fileId) {
		try {
			NameValuePair[] metaList = storageClient1.get_metadata1(fileId);
			if (metaList != null) {
				HashMap<String,String> map = new HashMap<String, String>();
				for (NameValuePair metaItem : metaList) {
					map.put(metaItem.getName(),metaItem.getValue());
				}
				return map;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除文件
	 * @param fileId 文件ID
	 * @return 删除失败返回-1，否则返回0
	 */
	public static int deleteFile(String fileId) {
		try {
			return storageClient1.delete_file1(fileId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 下载文件
	 * @param fileId 文件ID（上传文件成功后返回的ID）
	 * @param outFile 文件下载保存位置
	 * @return
	 */
	public static int downloadFile(String fileId, File outFile) {
		FileOutputStream fos = null;
		try {
			byte[] content = storageClient1.download_file1(fileId);
			fos = new FileOutputStream(outFile);
			fos.write(content, 0, content.length);
			fos.flush();
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return -1;
	}

	/**
	 * 
	 * Description: 流转字节数组
	 *
	 * @param 
	 * @return byte[]
	 * @throws 
	 * @Author bao
	 * Create Date: 2017年6月20日 上午11:26:26
	 */
	public static final byte[] input2byte(InputStream inStream)  
			throws IOException {  
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
		byte[] buff = new byte[100];  
		int rc = 0;  
		while ((rc = inStream.read(buff, 0, 100)) > 0) {  
			swapStream.write(buff, 0, rc);  
		}  
		byte[] in2b = swapStream.toByteArray();  
		return in2b;  
	}  

	public static void main(String[] args) throws Exception {
		File file = new File("D:\\aaa.jpg");
		InputStream inStream = new FileInputStream(file);
		byte[] b = input2byte(inStream);
    	String uploadFile = uploadFile(b, "jpg");
    	System.out.println(uploadFile);

		/*Map<String, String> fileMetadata = getFileMetadata("group1/M00/00/00/CgoU2VhT9XuAB1bGAABi7sZpreM166.jpg");
		for (Iterator<Map.Entry<String,String>>  iterator = fileMetadata.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry<String,String> entry = iterator.next();
			String name = entry.getKey();
			String value = entry.getValue();
			System.out.println(name + " = " + value );
		}*/
		// deleteFile("group1/M00/00/00/CgoU2VhUMvCAAI0iAAvqH_kipG8986.png");
		/*File outFile = new File("D:\\aa.jpg");
    	downloadFile("group1/M00/00/00/CgoU2VhULfGADmEYAAvqH_kipG820..jpg", outFile);*/
	}

}
