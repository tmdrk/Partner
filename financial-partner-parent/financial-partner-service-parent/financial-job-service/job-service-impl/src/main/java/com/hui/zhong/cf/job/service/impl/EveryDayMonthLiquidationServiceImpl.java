package com.hui.zhong.cf.job.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hui.zhong.cf.job.dao.*;
import com.hui.zhong.cf.job.model.CommissionLiquidation;
import com.hui.zhong.cf.job.model.Invest;
import com.hui.zhong.cf.job.model.InvestBonus;
import com.hui.zhong.cf.job.model.LoginUser;
import com.hui.zhong.cf.job.service.EveryDayMonthLiquidationService;
import com.huizhongcf.util.Constants;
import com.huizhongcf.util.DateUtil;
import com.huizhongcf.util.TradeTypesEnum;
import com.huizhongcf.util.TradeWaterNumUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.huizhongcf.util.DateUtil.getLastMonthFirstDayDate;
import static com.huizhongcf.util.DateUtil.getLastMonthLastDayDate;

/**
 * <dl>
 * <dt>类名：${FILE_NAME}</dt>
 * <dd>描述: 节点业务逻辑实现</dd>
 * <dd>创建时间： 2017/12/8 9:46</dd>
 * <dd>创建人： Administrator</dd>
 * <dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2017/12/8 9:46    peigaoxiang       1.0        1.0 Version
 * </pre>
 * </dl>
 */
@Service
public class EveryDayMonthLiquidationServiceImpl implements EveryDayMonthLiquidationService {
    private Logger logger = LoggerFactory.getLogger(EveryDayMonthLiquidationServiceImpl.class);
    @Autowired
    private InvestMapper investMapper;//投资表service
    @Autowired
    private InvestBonusMapper investBonusMapper;//投资奖励表service
    @Autowired
    private BonusConfigMapper bonusConfigMapper;//奖金系数配置表service
    @Autowired
    private LoginUserMapper loginUserMapper;//用户登录表service
    @Autowired
    private CommissionLiquidationMapper commissionLiquidationMapper;//佣金清算表service
    /**
     * @Title: insertEveryDayLiquidationInvest
     * @Description: 每天凌晨01:30:00对历史订单进行结算
        描述：投资人下单投资，每天凌晨计算历史订单，对合伙人、团队管理者产生的奖金
        场景：
            情况1：到凌晨01:30:00时，若当前时间是[2号，31号]，那么结算“buyDate（订单购买时间）”处于当月的所有未结算的订单
            情况2：到凌晨01:30:00时，若当前时间是1号，那么结算“buyDate（订单购买时间）”上一个月所有未结算的订单
        业务逻辑操作：
            循环遍历每一个符合条件的订单，根据订单，订单关联的用户，计算“出借奖金”、“差额奖金”、“服务费”
        具体操作：
            对t_invest表中的符合条件的数据计算，并存储到t_invest_bonus表中，
        注意事项：
     *
     * @throws
     */
    @Override
    public void insertEveryDayLiquidationInvest() throws ParseException{
        logger.info("======（dubbo服务定时任务：隔天跑批）job-service-impl项目everyDayInvestLiquidation方法---------每天对历史订单跑批结算---------开始====");
        /**初始化参数**/
        Date date = new Date();//当前时间
        Map<String,Object> params=new HashMap<String,Object>();
        Date startDate=null;//跑批开始时间，对于订单而言
        Date endDate=null;//跑批结束时间，对于订单而言
        BigDecimal bonusOneCoefficient1=null;//出借费系数
        BigDecimal bonusOneCoefficient2=null;//差额费系数
        BigDecimal bonusOneCoefficient3=null;//服务费系数
        /**确定要跑批的时间段**/
        SimpleDateFormat dateFormat = new SimpleDateFormat("d");
        String dayStr= dateFormat.format( date );//获取当前日期：天
        logger.info("（dubbo服务定时任务：隔天跑批）job-service-impl项目everyDayInvestLiquidation方法---当前日期："+dayStr);
        int day=Integer.parseInt(dayStr);
        if(day>=2 && day<=31){//当前时间处于[2号，31号]
            startDate=DateUtil.getCurrentMonthFirstDayDate();//获取当月第一天0时0分0秒时间，时间格式：年月日时分秒
            endDate=date;//获取当前时间，时间格式：年月日时分秒
        }else if(day==1){//当前时间处于1号
            startDate=getLastMonthFirstDayDate();//获取上一月第一天0时0分0秒时间，时间格式：年月日时分秒
            endDate=getLastMonthLastDayDate();//获取上一月最后一天23:59:59，时间格式：年月日时分秒
        }
        logger.info("（dubbo服务定时任务：隔天跑批）跑批开始时间startDate："+startDate+"，跑批结束时间endDate："+endDate);
        if(startDate==null || endDate==null){
            logger.info("（dubbo服务定时任务：隔天跑批）跑批异常，跑批开始时间和跑批结束时间为null");
        }
        /**执行隔天跑批操作
         *（1）获取要跑批的订单集合，只要t_invest表中buy_date（购买时间）在[跑批开始时间，跑批结束时间]之内，就获取
         *（2）循环遍历订单集合，对每条订单跑批
         *1、
         *2、
         * **/
        //获取符合条件的订单
        params.put("startDate",startDate);//跑批开始时间，对于订单而言
        params.put("endDate",endDate);//跑批结束时间，对于订单而言
        params.put("userRole",Constants.USER_ROLE_10);//合伙人角色
        logger.info("（dubbo服务定时任务：隔天跑批）查询要跑批的订单，请求参数："+params.toString());
        List<Map<String, Object>> investList=investMapper.selectEveryDayInvest(params);
        if(investList!=null && investList.size()>0){
            List<InvestBonus> investBonusList=new ArrayList<InvestBonus>();//将要保存的数据存储到一个集合中
            logger.info("（dubbo服务定时任务：隔天跑批）查询要跑批的订单，订单的数量为："+investList.size());
            //查询“出借费百分比系数”“差额百分比系数”“服务费百分比系数”
            params.put("bonusType1",Constants.BONUS_TYPE_10);//“出借费百分比系数”对应的字典编码
            params.put("bonusType2", Constants.BONUS_TYPE_20);//“差额百分比系数”对应的字典编码
            params.put("bonusType3",Constants.BONUS_TYPE_30);//“服务费百分比系数”对应的字典编码
            logger.info("（dubbo服务定时任务：隔天跑批）查询当前时间的“出借费系数”“差额费系数”“服务费系数”，请求参数："+params.toString());
            Map<String, Object> bonusMap= bonusConfigMapper.selectAllBonus(params);
            logger.info("（dubbo服务定时任务：隔天跑批）查询当前时间的“出借费系数”“差额费系数”“服务费系数”，查询结果："+bonusMap.toString());
            bonusOneCoefficient1=new BigDecimal(String.valueOf(bonusMap.get("bonusOneCoefficient1")));//出借费系数
            bonusOneCoefficient2=new BigDecimal(String.valueOf(bonusMap.get("bonusOneCoefficient2")));//差额费系数
            bonusOneCoefficient3=new BigDecimal(String.valueOf(bonusMap.get("bonusOneCoefficient3")));//服务费系数
            logger.info("（dubbo服务定时任务：隔天跑批）（带百分比）“出借费系数”bonusOneCoefficient1："+bonusOneCoefficient1+"，“差额费系数”bonusOneCoefficient2："+bonusOneCoefficient2+"，“服务费系数”bonusOneCoefficient3："+bonusOneCoefficient3);
            logger.info("（dubbo服务定时任务：隔天跑批）循环遍历订单，进行结算");
            for (int i = 0; i <investList.size() ; i++) {
                BigDecimal orderBaseMoney=null;//订单基础费用
                BigDecimal lend=null;//出借金额
                BigDecimal difference=null;//差额金额
                BigDecimal service=null;//服务费
                //创建和封装“投资奖励实体”
                InvestBonus investBonus=new InvestBonus();//定义投资奖励实体
                Map<String, Object> investMap=investList.get(i);
                logger.info("（dubbo服务定时任务：隔天跑批）当前订单对象："+investMap.toString());
                JSONObject jsonInvest=(JSONObject) JSONObject.toJSON(investMap);
                //定义一些局部变量
                Integer userId=jsonInvest.getInteger("user_id");//登录用户表主键
                String userRole=jsonInvest.getString("user_role");//登录用户角色
                /**下级合伙人的数量
                 *情况1：投资人是合伙人身份时，
                 *countPartner：就是投资人的下级合伙人的数量
                 *情况2：投资人是客户身份时，
                 *countPartner：就是投资人的上级合伙人的下级合伙人的数量
                 **/
                long countPartner = jsonInvest.getLongValue("countPartner");
                Integer superiorRecommendCodeUserId=jsonInvest.getInteger("superiorRecommendCodeUserId");//业绩归属上级合伙人userid(参照用户登录表主键)(封存时更新)
                Integer onSuperiorRecommendCodeUserId=jsonInvest.getInteger("onSuperiorRecommendCodeUserId");//业绩归属上上级合伙人userid(参照用户登录表主键)(封存时更新)
                Integer teamRecommendCodeUserId=jsonInvest.getInteger("teamRecommendCodeUserId");//业绩归属团队管理者userid(参照用户登录表主键)(封存时更新)

                //封装实体
                investBonus.setUserId(userId);//登录用户表主键
                investBonus.setUserRole(jsonInvest.getString("user_role"));//用户角色
                investBonus.setInfoId(jsonInvest.getInteger("info_id"));//用户表主键
                investBonus.setInvestId(jsonInvest.getInteger("invest_id"));//投资记录表主键
                investBonus.setRewardNum(TradeWaterNumUtil.genTradeWaterNumForOrder(TradeTypesEnum.JLDH));//奖励单号
//                investBonus.setUserLendBonus();//用户出借奖金(元)
//                investBonus.setUserDiffBonus();//用户差额奖金(元)
//                investBonus.setSuperiorLendBonus();//上级合伙人出借奖金(元)
//                investBonus.setSuperiorDiffBonus();//上级合伙人差额奖金(元)
//                investBonus.setSuperiorRecommendCodeUserId();//业绩归属上级合伙人userid(参照用户登录表主键)
//                investBonus.setOnSuperiorLendBonus();//上上级合伙人出借奖金(元)
//                investBonus.setOnSuperiorRecommendCodeUserId();//业绩归属上上级合伙人userid(参照用户登录表主键)
//                investBonus.setServiceBonus();//服务奖金(元)
//                investBonus.setTeamRecommendCodeUserId();//业绩归属团队管理者userid(参照用户登录表主键)
                investBonus.setCreateTime(date);//创建时间(生成时间)
//                investBonus.setOperator(0);//操作人，默认系统，存“0”
//                investBonus.setOperateTime(null);//操作时间，就是修改时间

                //计算“出借费”“差额费”“服务费”
                orderBaseMoney=jsonInvest.getBigDecimal("discount_amount");
                //出借费
                lend=orderBaseMoney.multiply(bonusOneCoefficient1).setScale(2,BigDecimal.ROUND_HALF_UP);//四舍五入
                //差额费
                difference=orderBaseMoney.multiply(bonusOneCoefficient2).setScale(2,BigDecimal.ROUND_HALF_UP);//四舍五入
                //服务费
                service=orderBaseMoney.multiply(bonusOneCoefficient3).setScale(2,BigDecimal.ROUND_HALF_UP);//四舍五入
                logger.info("（dubbo服务定时任务：隔天跑批）基础费："+orderBaseMoney.toString()+"，出借费："+lend.toString()+"，差额费："+difference.toString()+"，服务费："+service.toString());
                //判断投资人的“用户角色”，10：合伙人；20：客户
                //情况1：当投资人是合伙人时，
                Map<String,Object> params2=new HashMap<String,Object>();
                if(Constants.USER_ROLE_10.equals(userRole)){
                    logger.info("（dubbo服务定时任务：隔天跑批）当前订单投资人是合伙人，计算上级佣金");
                    /**设置“出借费”“差额费”“服务费”
                     * 步骤1：设置本人的“出借费”“差额费”
                     * 步骤2：设置上级合伙人的“出借费”“差额费”
                     * 步骤3：设置团队管理者的“服务费”
                     **/
                    //步骤1：设置本人的“出借费”“差额费”
                    investBonus.setUserLendBonus(lend);//用户出借奖金(元)
                    //查询合伙人能否拿“差额费”，就是判断合伙人是否发展了下级合伙人
                    if(countPartner>=1){
                        investBonus.setUserDiffBonus(difference);//用户差额奖金(元)
                    }
                    //步骤2：设置上级合伙人的“出借费”“差额费”
                    if(superiorRecommendCodeUserId!=null){
                        investBonus.setSuperiorLendBonus(lend);//上级合伙人出借奖金(元)
                        investBonus.setSuperiorDiffBonus(difference);//上级合伙人差额奖金(元)
                        investBonus.setSuperiorRecommendCodeUserId(superiorRecommendCodeUserId);//业绩归属上级合伙人userid(参照用户登录表主键)
                    }
                    //步骤3：设置团队管理者的“服务费”
                    if(teamRecommendCodeUserId!=null){
                        investBonus.setServiceBonus(service);//服务奖金(元)
                        investBonus.setTeamRecommendCodeUserId(teamRecommendCodeUserId);//业绩归属团队管理者userid(参照用户登录表主键)
                    }
                }else if(Constants.USER_ROLE_20.equals(userRole)){//情况2：当投资人是客户时，
                    logger.info("（dubbo服务定时任务：隔天跑批）当前订单投资人是客户，计算上级佣金");
                    if(superiorRecommendCodeUserId!=null){//当客户的上级合伙人不为空时执行操作
                        investBonus.setSuperiorLendBonus(lend);//上级合伙人出借奖金(元)
                        investBonus.setSuperiorRecommendCodeUserId(superiorRecommendCodeUserId);//业绩归属上级合伙人userid(参照用户登录表主键)
                        //判断客户的上级合伙人能否拿“差额费”
                        if(countPartner>=1){
                            investBonus.setSuperiorDiffBonus(difference);//上级合伙人差额奖金(元)
                        }
                    }
                    if(onSuperiorRecommendCodeUserId!=null){//业绩归属上上级合伙人userid(参照用户登录表主键)，当客户的上上级合伙人不为空时
                        investBonus.setOnSuperiorLendBonus(lend);//上上级合伙人出借奖金(元)
                        investBonus.setOnSuperiorRecommendCodeUserId(onSuperiorRecommendCodeUserId);//业绩归属上级合伙人userid(参照用户登录表主键)
                    }
                    if(teamRecommendCodeUserId!=null){//业绩归属团队管理者userid(参照用户登录表主键)(封存时更新)
                        investBonus.setServiceBonus(service);//服务奖金(元)
                        investBonus.setTeamRecommendCodeUserId(teamRecommendCodeUserId);//业绩归属团队管理者userid(参照用户登录表主键)
                    }
                }
                logger.info("（dubbo服务定时任务：隔天跑批）奖励单数据investBonus："+investBonus.toString());
                investBonusList.add(investBonus);//将investBonus对象添加到集合中，用于一次性的保存
            }
            int result = investBonusMapper.insertInvestBonusList(investBonusList);//向t_invest_bonus(投资奖励表)添加数据
            logger.info("（dubbo服务定时任务：隔天跑批）向t_invest_bonus(投资奖励表)添加数据结果："+result);
        }else{
            logger.info("（dubbo服务定时任务：隔天跑批）查询要跑批的订单集合，订单集合为空");
        }
        logger.info("======（dubbo服务定时任务：隔天跑批）job-service-impl项目everyDayInvestLiquidation方法---------每天对历史订单跑批结算---------结束====");
    }


    /**
     * @Title: insertEveryDayLiquidationInvest
     * @Description:每月封存
     *              每月1号凌晨02:30:00对上一个月历史订单进行封存
     *	描述：每月1号凌晨02:30:00对上一个月历史订单进行封存。
            1、更新订单中的“superior_recommend_code_user_id（业绩归属上级合伙人userid）”“on_superior_recommend_code_user_id（业绩归属上上级合伙人userid）”
            “team_recommend_code_user_id（业绩归属团队管理者userid）”字段
            2、修改t_invest（投资表）中字段seal_status（封存状态:  0:未封存,   1:已封存）的值为1，表示订单已经封存
        场景：
            封存的订单不允许更改，要对历史订单进行结算，
        具体操作：
            1、更新订单中的“superior_recommend_code_user_id（业绩归属上级合伙人userid）”“on_superior_recommend_code_user_id（业绩归属上上级合伙人userid）”
            “team_recommend_code_user_id（业绩归属团队管理者userid）”字段
            2、修改t_invest（投资表）中字段seal_status（封存状态:  0:未封存,   1:已封存）的值为1，表示订单已经封存
        注意事项：
     * @throws
     */
    @Override
    public void updateEveryMonthSealInvest() throws ParseException{
        logger.info("======（dubbo服务定时任务：每月封存）job-service-impl项目updateEveryMonthSealInvest方法---------每月1号凌晨02:30:00对上一个月历史订单进行封存---------开始====");
        /**初始化参数**/
/************正确的代码*****************************************/
//        Date date = new Date();//当前时间
//        Map<String,Object> params=new HashMap<String,Object>();
//        Date startDate=DateUtil.getLastMonthFirstDayDate();//封存开始时间，对于订单而言。上一个月第一天
//        Date endDate=DateUtil.getLastMonthLastDayDate();//封存结束时间，对于订单而言。上一个月最后一天
//        params.put("startDate",startDate);
//        params.put("endDate",endDate);
//        params.put("sealStatus_1",Constants.SEAL_STATUS_1);//封存状态，0：未封存，1：已封存
//        params.put("liquidationMonth",DateUtil.getDateStringByApplyPattern(startDate,"yyyy-MM"));//封存结算月份，时间格式：yyyy-dd，如：2017-01
//        params.put("operateTime",date);//修改时间
/************为测试而写的代码*****************************************/
        Date date = new Date();//当前时间
        Map<String,Object> params=new HashMap<String,Object>();
        Date startDate=DateUtil.getCurrentMonthFirstDayDate();//封存开始时间，对于订单而言。上一个月第一天
        Date endDate=DateUtil.getCurrentMonthLastDayDate();//封存结束时间，对于订单而言。上一个月最后一天
        params.put("startDate",startDate);
        params.put("endDate",endDate);
        params.put("sealStatus_1",Constants.SEAL_STATUS_1);//封存状态，0：未封存，1：已封存
        params.put("liquidationMonth",DateUtil.getDateStringByApplyPattern(startDate,"yyyy-MM"));//封存结算月份，时间格式：yyyy-dd，如：2017-01
        params.put("operateTime",date);//修改时间
/*****************************************************/
        logger.info("（dubbo服务定时任务：每月封存）封存上一个月订单的请求参数params："+params.toString());
        int result=investMapper.updateEveryMonthSealedInvest(params);
        logger.info("（dubbo服务定时任务：每月封存）封存上一个月订单，操作结果，影响的行数result："+result);
        logger.info("======（dubbo服务定时任务：每月封存）job-service-impl项目updateEveryMonthSealInvest方法---------每月1号凌晨02:30:00对上一个月历史订单进行封存---------结束====");
    }
    /**
     * @Title: insertEveryMonthLiquidationInvest
     * @Description:每月跑批
     *              每月1号凌晨03:00:00对上一个月历史订单进行跑批结算
     *
     * @throws
     */
    @Override
    public void insertEveryMonthLiquidationInvest() throws ParseException{
        logger.info("======（dubbo服务定时任务：每月跑批）job-service-impl项目insertEveryMonthLiquidationInvest方法---------每月1号凌晨03:00:00对上一个月历史订单进行跑批结算---------开始====");
        /**初始化参数**/
/*****************正确的代码********************************/
//        Date date = new Date();//当前时间
//        Date startDate=DateUtil.getLastMonthFirstDayDate();//封存开始时间，对于订单而言。上一个月第一天
//        Date endDate=DateUtil.getLastMonthLastDayDate();//封存结束时间，对于订单而言。上一个月最后一天
//        String liquidationMonth=DateUtil.getDateStringByApplyPattern(startDate,"yyyy-MM");////结算月份，时间格式：yyyy-dd，如：2017-01
/*****************为测试而写的代码********************************/
        Date date = new Date();//当前时间
        Date startDate=DateUtil.getCurrentMonthFirstDayDate();//封存开始时间，对于订单而言。上一个月第一天
        Date endDate=DateUtil.getCurrentMonthLastDayDate();//封存结束时间，对于订单而言。上一个月最后一天
        String liquidationMonth=DateUtil.getDateStringByApplyPattern(startDate,"yyyy-MM");////结算月份，时间格式：yyyy-dd，如：2017-01
/*************************************************/
        Map<String,Object> params=new HashMap<String,Object>();
        BigDecimal bonusOneCoefficient1=null;//出借费系数
        BigDecimal bonusOneCoefficient2=null;//差额费系数
        BigDecimal bonusOneCoefficient3=null;//服务费系数
        //获取要计算“佣金”的用户，就是获取身份为合伙人的用户
        params.put("userRole",Constants.USER_ROLE_10);
        logger.info("（dubbo服务定时任务：每月跑批）查询要跑批的用户，请求参数："+params.toString());
        List<LoginUser> loginUserList=loginUserMapper.selectEveryMonthUser(params);
        if(loginUserList!=null && loginUserList.size()>0){
            List<CommissionLiquidation> commissionLiquidationList=new ArrayList<CommissionLiquidation>();//用于存放所有的“佣金清算信息”
            logger.info("（dubbo服务定时任务：每月跑批）查询要跑批的用户，用户数量："+loginUserList.size());
            //查询“出借费百分比系数”“差额百分比系数”“服务费百分比系数”
            params.put("bonusType1",Constants.BONUS_TYPE_10);//“出借费百分比系数”对应的字典编码
            params.put("bonusType2", Constants.BONUS_TYPE_20);//“差额百分比系数”对应的字典编码
            params.put("bonusType3",Constants.BONUS_TYPE_30);//“服务费百分比系数”对应的字典编码
            logger.info("（dubbo服务定时任务：每月跑批）查询“出借费系数”“差额费系数”“服务费系数”，请求参数："+params.toString());
            Map<String, Object> bonusMap= bonusConfigMapper.selectAllBonus(params);
            logger.info("（dubbo服务定时任务：每月跑批）查询“出借费系数”“差额费系数”“服务费系数”，返回结果："+bonusMap.toString());
            bonusOneCoefficient1=new BigDecimal(String.valueOf(bonusMap.get("bonusOneCoefficient1")));//出借费系数
            bonusOneCoefficient2=new BigDecimal(String.valueOf(bonusMap.get("bonusOneCoefficient2")));//差额费系数
            bonusOneCoefficient3=new BigDecimal(String.valueOf(bonusMap.get("bonusOneCoefficient3")));//服务费系数
            logger.info("（dubbo服务定时任务：每月跑批）（不带百分比）“出借费系数”bonusOneCoefficient1："+bonusOneCoefficient1+"，“差额费系数”bonusOneCoefficient2："+bonusOneCoefficient2+"，“服务费系数”bonusOneCoefficient3："+bonusOneCoefficient3);
            params.put("bonusOneCoefficient1",bonusOneCoefficient1);
            params.put("bonusOneCoefficient2",bonusOneCoefficient2);
            params.put("bonusOneCoefficient3",bonusOneCoefficient3);
            //获取要跑批订单的“开始时间”“结束时间”
            params.put("startDate",startDate);
            params.put("endDate",endDate);
            logger.info("（dubbo服务定时任务：每月跑批）开始循环查询每个用户的佣金");
            for (int i = 0; i <loginUserList.size() ; i++) {
                CommissionLiquidation commissionLiquidation=new CommissionLiquidation();//定义“佣金清算实体”
                LoginUser loginUser=loginUserList.get(i);//获取每个合伙人对象
                logger.info("（dubbo服务定时任务：每月跑批）要跑批的合伙人信息loginUser："+loginUser.toString());
                Integer userId=loginUser.getUserId();
                params.put("userId",userId);
                //查询单个合伙人的“投资总额”“折扣总额”“出借费”“差额费”“服务费”
                logger.info("（dubbo服务定时任务：每月跑批）查询合伙人上一个月的“出借费”“差额费”“服务费”，请求参数："+params.toString());
                Map<String, Object> liquidationMap=investMapper.selectEveryMonthLiquidationOfUser(params);
                logger.info("（dubbo服务定时任务：每月跑批）查询合伙人上一个月的“出借费”“差额费”“服务费”，返回结果："+liquidationMap.toString());
                JSONObject jsonLiquidation=(JSONObject) JSONObject.toJSON(liquidationMap);
                commissionLiquidation.setUserId(userId);//参照登录用户表主键
                commissionLiquidation.setUserType(loginUser.getUserType());//用户身份(参照字典表)
                commissionLiquidation.setInfoId(jsonLiquidation.getInteger("info_id"));//参照用户表主键
                commissionLiquidation.setLiquidationNum(TradeWaterNumUtil.genTradeWaterNumForOrder(TradeTypesEnum.JSDN));//结算单号
                commissionLiquidation.setLiquidationMonth(liquidationMonth);//结算月份
                commissionLiquidation.setInvestTotalAmount(jsonLiquidation.getBigDecimal("invest_total_amount"));//交易总额(万元)
                commissionLiquidation.setDiscountTotalAmount(jsonLiquidation.getBigDecimal("discount_total_amount"));//折标总额(万元)
                commissionLiquidation.setLendBonus(jsonLiquidation.getBigDecimal("lend_bonus"));//出借奖金(元)
                commissionLiquidation.setDiffBonus(jsonLiquidation.getBigDecimal("diff_bonus"));//差额奖金(元)
                commissionLiquidation.setServiceBonus(jsonLiquidation.getBigDecimal("service_bonus"));//服务奖金(元)
                commissionLiquidation.setTotalReward(jsonLiquidation.getBigDecimal("total_reward"));//奖励总额(元)
                commissionLiquidation.setLiquidationStatus(Constants.LIQUIDATION_STATUS_10);//结算状态(参照字典表)
//                commissionLiquidation.setLiquidationTime();//结算时间，注意：该时间是支付时生成
                commissionLiquidation.setCreateTime(date);//创建时间
                commissionLiquidationList.add(commissionLiquidation);//将跑批的数据保存到一个集合中
            }
            logger.info("（dubbo服务定时任务：每月跑批）一次性批量保存跑批数据commissionLiquidationList");
            int result=commissionLiquidationMapper.insertCommissionLiquidationList(commissionLiquidationList);//向t_commission_liquidation(佣金清算表)添加跑批数据
            logger.info("（dubbo服务定时任务：每月跑批）批量保存数据的结果："+result);
        }else{
            logger.info("（dubbo服务定时任务：每月跑批），没有要跑批的合伙人");
        }
        logger.info("======（dubbo服务定时任务：每月跑批）job-service-impl项目insertEveryMonthLiquidationInvest方法---------每月1号凌晨03:00:00对上一个月历史订单进行跑批结算---------结束====");
    }



}