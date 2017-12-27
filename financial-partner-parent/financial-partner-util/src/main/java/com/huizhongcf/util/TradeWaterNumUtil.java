package com.huizhongcf.util;

import java.text.ParseException;
import java.util.Date;
import java.util.Random;

import com.huizhongcf.Enum.UserTypeEnum;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
/** 
 *
 * Description: 生成交易流水号
 *
 * @author lijie
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2015年1月15日      lijie       1.0         1.0 Version 
 * </pre>
 */

public class TradeWaterNumUtil {
	private Logger logger = LoggerFactory.getLogger(TradeWaterNumUtil.class);
	/**
	 * 
	 * Description: 生成交易流水号  
	 * 			格式：例    交易类型编码-100034000-566034000-平台唯一编码
	 *
	 * @param TradeType 交易类型编码
	 * @param busPlatform 平台唯一编码
	 * @return String 返回交易流水号
	 * @Author lijie
	 * @Create Date: 2015年1月16日 上午10:05:00
	 */
	public static String genTradeWaterNum(TradeTypesEnum TradeType, String busPlatform){
		
		StringBuffer sb = new StringBuffer();

		if (StringUtil.isNotBlank(TradeType.name()) && StringUtil.isNotBlank(busPlatform)) {
			sb.append(TradeType)
			  .append("-")
			  .append(getRandom())
			  .append("-")
			  .append(getRandom())
			  .append("-")
			  .append(busPlatform);
		}

		return sb.toString();
	}
	/**
	 * Description: 生成“奖励单号”“结算单号”
	 * 			格式：例    交易类型编码-100034000-566034000
	 * @param TradeType 交易类型编码
	 * @return String 返回交易流水号
	 * @Author lijie
	 * @Create Date: 2015年1月16日 上午10:05:00
	 */
	public static String genTradeWaterNumForOrder(TradeTypesEnum TradeType){
		StringBuffer sb = new StringBuffer();
		if (StringUtil.isNotBlank(TradeType.name())) {
			sb.append(TradeType)
					.append("-")
					.append(getRandom())
					.append("-")
					.append(getRandom());
		}
		return sb.toString();
	}
	/**
	 * 
	 * Description: 生成100000000到999999999之间的随机数
	 *
	 * @param 
	 * @return int
	 * @throws 
	 * @Author yaodawei
	 * Create Date: 2015年1月22日 下午2:23:14
	 */
	private static int getRandom(){
		int max = 999999999;
        int min = 100000000;
        Random random = new Random();
        int randomInt = random.nextInt(max)%(max-min+1) + min;
        return randomInt;
	}
	
	/**
	 * 
	 * Description: 生成合伙人推荐码或者客户推荐码
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author bao
	 * Create Date: 2017年12月12日 下午12:46:40
	 */
	public static String genTradeWaterNum(UserTypeEnum usertypeenum){
		StringBuffer sb = new StringBuffer();
		if (StringUtil.isNotBlank(usertypeenum.getType())) {
			sb.append(usertypeenum.getType()).append(getRandomEX());
		}
		return sb.toString();
	}
	
	public static int getRandomEX(){
		//预留邀请码使用
//		int max = 10050000;
//		int min = 10000000;
		//注册使用
		int max = 99999999;
		int min = 10050001;
        Random random = new Random();
        int randomInt = random.nextInt(max)%(max-min+1) + min;
        return randomInt;
	}
	/**生成10000个预留邀请码
	 *
	 *
	 * **/
	private static void insertReservedInviteCode() throws ParseException {
		Date create_time=new Date();
		System.out.println("生成10000个预留邀请码-----开始时间："+DateUtil.getDateStringByApplyPattern(create_time,"yyyy-MM-dd HH:mm:ss"));
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://10.20.200.100:3306/financial_partner";
		String username = "partner";
		String password = "g4^9Vh#kFk5C";
		Connection conn = null;
		String sql1 = "select count(1) countNum from t_reserved_invite_code where invite_code=?";
		String sql2 = "INSERT INTO t_reserved_invite_code (reserved_id, invite_code_type, invite_code, use_status,create_time) VALUES (NULL, ?,?,?,now())";
		PreparedStatement pstmt=null;
		int result = 0;
		String use_status="1";//使用状态(参考字典表): 1:未使用,  2:已使用
		try {
			Class.forName(driver); //classLoader,加载对应驱动
			conn = (Connection) DriverManager.getConnection(url, username, password);
			for (int i = 1; i <=10000; i++) {
				String invite_code="";
				for (int j = 0; ;j++) {
					pstmt = (PreparedStatement) conn.prepareStatement(sql1);
					invite_code="301"+getRandomEX();
					pstmt.setString(1,invite_code);
					ResultSet rs = pstmt.executeQuery();
					rs.next();
					Long count=rs.getLong("countNum");
					if(count==0){
						break;
					}else{
						continue;
					}
				}
				String invite_code_type="";
				if(i>=1 && i<=100){
					invite_code_type="10";//邀请码类型(参照字典表): 10:平台邀请码, 20:团队管理者邀请码
				}else{
					invite_code_type="20";//邀请码类型(参照字典表): 10:平台邀请码, 20:团队管理者邀请码
				}
				pstmt = (PreparedStatement) conn.prepareStatement(sql2);
				pstmt.setString(1, invite_code_type);
				pstmt.setString(2, invite_code);
				pstmt.setString(3, use_status);
				result = pstmt.executeUpdate();
			}
			pstmt.close();
			conn.close();
			System.out.println("生成10000个预留邀请码-----结束时间："+DateUtil.getDateStringByApplyPattern(new Date(),"yyyy-MM-dd HH:mm:ss"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		}
	}




	public static void main(String[] args) {
//		System.out.println(genTradeWaterNum(TradeTypesEnum.LCHB, PlatformCodeConstants.ONLINE_PLATFORM_CODE));
//		System.out.println(genTradeWaterNum(TradeTypesEnum.JLDH, ""));

		//生成预留邀请码
//		insertReservedInviteCode();
		//测试“奖励单号”“结算单号”
		System.out.println(genTradeWaterNumForOrder(TradeTypesEnum.JLDH));




	}
}
