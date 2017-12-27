package com.hui.zhong.cf.service.Impl.partner.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hui.zhong.cf.service.Impl.partner.dao.*;
import com.hui.zhong.cf.service.partner.model.*;
import com.hui.zhong.cf.service.partner.service.PartnerOrderService;
import com.huizhongcf.util.Constants;
import com.huizhongcf.util.ReturnMsgData;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;

/**
 * <dl>
 * <dt>类名：${FILE_NAME}</dt>
 * <dd>描述: 节点业务逻辑实现</dd>
 * <dd>创建时间： 2017/12/12 18:18</dd>
 * <dd>创建人： Administrator</dd>
 * <dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2017/12/12 18:18    peigaoxiang       1.0        1.0 Version
 * </pre>
 * </dl>
 */
public class PartnerOrderServiceImpl implements PartnerOrderService {
    private Logger logger = LoggerFactory.getLogger(PartnerOrderServiceImpl.class);

    @Autowired
    private UserInfoMapper userInfoMapper;//userInfo表Service
    @Autowired
    private InvestMapper investMapper;//投资表Service
    @Autowired
    private ProductConfigMapper productConfigMapper;//折算系数service
    @Autowired
    private InterfaceLogMapper interfaceLogMapper;//日志service
    @Autowired
    private LoginUserMapper loginUserMapper;//用户登录表service
    /**
     * Description: 汇中网向合伙人同步用户实名认证信息
     * @param
     * @return ReturnMsgData
     * @throws
     * @Author peigaoxiang
     * Create Date: 2017年12月11日 下午18:18:19
     */
    public ReturnMsgData updateUserRealInfo(String body){
        logger.info("(dubbo服务partner-service-impl，保存用户实名认证信息)updateUserRealInfo方法，接收到的body："+body);
        /**初始化参数**/
        ReturnMsgData result=new ReturnMsgData("1000","操作失败");
        Date date=new Date();//当前时间
        String returnMsg="操作失败，";
        /**解析参数，保存订单**/
        JSONArray jsonArray=JSONArray.parseArray(body);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        String username= jsonObject.getString("username");
        String realName= jsonObject.getString("realName");
        String cardCode= jsonObject.getString("cardCode");
        if(StringUtils.isBlank(username) || StringUtils.isBlank(realName) || StringUtils.isBlank(cardCode)){
            logger.info("(dubbo服务partner-service-impl，保存用户实名认证信息)请求参数为空");
            result.setReturnMsg("操作失败，请求参数为空");
        }else{
            Map<String,Object> params=new HashMap<String,Object>();
            params.put("username",username);
            params.put("realName",realName);
            params.put("cardCode",cardCode);
            //验证username(手机号)信息是否存在
            logger.info("(dubbo服务partner-service-impl，保存用户实名认证信息)根据username(手机号)查询用户信息，请求参数username："+username);
            LoginUser loginUser = loginUserMapper.selectLoginUserByUsername(username);
            if(loginUser!=null && loginUser.getUserId()!=null){
                params.put("userId", loginUser.getUserId());//设置“用户id”
                logger.info("(dubbo服务partner-service-impl，保存用户实名认证信息)根据username(手机号)查询用户信息，返回结果loginUser："+loginUser.toString());
                //验证“是否已经实名认证”“身份证号是否已经使用”
                logger.info("(dubbo服务partner-service-impl，保存用户实名认证信息)验证“是否已经实名认证”“身份证号是否已经使用”,请求参数params："+params.toString());
                Map<String, Object> countMap=userInfoMapper.selectCountByUserIdAndCardCode(params);
                Long countUserId=new Long(String.valueOf(countMap.get("countUserId")));
                Long countCardCode=new Long(String.valueOf(countMap.get("countCardCode")));
                logger.info("(dubbo服务partner-service-impl，保存用户实名认证信息)验证“是否已经实名认证”“身份证号是否已经使用”,返回结果countUserId："+countUserId+"，countCardCode："+countCardCode);
                String sexString = "";
                String sex = "";
                if(countUserId<=0 && countCardCode<=0){
                    //用户未实名，身份证号未被使用，开始解析用户性别
                    List<String> list = new ArrayList<String>();
                    list.add("1");
                    list.add("3");
                    list.add("5");
                    list.add("7");
                    list.add("9");
                    int length = cardCode.length();
                    if (length == 18) {
                        sexString = cardCode.substring(length - 2, length - 1);
                    } else {
                        sexString = cardCode.substring(length - 1);
                    }
                    if (list.contains(sexString)) {
                        sex=Constants.EMPLOYEE_SEX_NAN;//0:男
                    } else {
                        sex=Constants.EMPLOYEE_SEX_NV;//1:女
                    }
                    params.put("sex", sex);//设置“性别”
                    params.put("createTime",date);//设置“创建时间”
                    logger.info("(dubbo服务partner-service-impl，保存用户实名认证信息)保存用户信息，请求参数userInfoMap："+params.toString());
                    int resultUserInfo = userInfoMapper.insertUserInfo(params);
                    logger.info("(dubbo服务partner-service-impl，保存用户实名认证信息)保存用户信息，返回结果影响的行数：resultUserInfo："+resultUserInfo);
                    if (1 == resultUserInfo) {
                        result.setReturnCode("0000");
                        result.setReturnMsg("操作成功");
                    }
                }else{
                    if(countUserId>=1){
                        returnMsg+="username："+username+"用户已经实名，";
                    }
                    if(countCardCode>=1){
                        returnMsg+="cardCode："+cardCode+"该身份证号已经使用";
                    }
                    result.setReturnMsg(returnMsg);
                }
            }else{
                logger.info("(dubbo服务partner-service-impl，保存用户实名认证信息)根据username(手机号)查询用户信息，返回结果为null");
                result.setReturnMsg("操作失败，根据username(手机号)未找到用户信息");
            }
        }
        logger.info("(dubbo服务partner-service-impl，保存用户实名认证信息)dubbo服务返回结果："+result.toString());
        /**保存日志 */
        InterfaceLog interfaceLog=new InterfaceLog();
        interfaceLog.setTradeType("‘汇中网’向‘合伙人’推送用户实名认证信息");//接口类型
        interfaceLog.setReqData(body);// 请求报文
        interfaceLog.setRespResult(result.toString());//响应报文
        interfaceLog.setSystemDocking(Constants.SYSTEM_CODE_01);//系统标识，01：汇中网，20：合伙人
        interfaceLog.setCreateTime(date);//创建时间
        interfaceLogMapper.insertSelective(interfaceLog);//保存日志
        return result;
    }

    /**
     * Description: 汇中网向合伙人同步订单
     * @param
     * @return ReturnMsgData
     * @throws
     * @Author peigaoxiang
     * Create Date: 2017年12月11日 下午18:18:19
     */
    @Override
    public ReturnMsgData insertOrderInfo(String body) {
        logger.info("(dubbo服务partner-service-impl，保存订单集合)insertOrderInfo方法，接收到的body："+body);
        /**初始化参数**/
        ReturnMsgData result=new ReturnMsgData("1000","操作失败");
        List<String> successIds=new ArrayList<String>();//成功的集合
        List<String> failIds=new ArrayList<String>();//失败的集合
        Date date=new Date();//当前时间
        List<Integer> resultInvestList=new ArrayList<Integer>();//保存操作订单集合结果
        Map<String,Object> resultInvestMap=new HashMap<String,Object>();//保存操作订单集合详细信息
        /**解析参数，保存订单**/
        JSONArray investArray=JSONArray.parseArray(body);//订单集合
        if(investArray==null){
            logger.info("(dubbo服务partner-service-impl，保存订单集合)body转换成JSONArray对象，对象为null");
        }else{
            logger.info("(dubbo服务partner-service-impl，保存订单集合)body转换成JSONArray对象，开始循环遍历订单集合");
            for (int i = 0; i < investArray.size(); i++) {
                JSONObject investJson=investArray.getJSONObject(i);
                logger.info("(dubbo服务partner-service-impl，保存订单集合)开始保存第"+(i+1)+"个订单，订单对象investJson："+investJson.toJSONString());
                //定义一些局部变量
                String cardCode=investJson.getString("cardCode");//身份证号
                String contractNo=investJson.getString("contractNo");//合同编号
                //验证“合同编号”是否唯一
                logger.info("(dubbo服务partner-service-impl，保存订单集合)查询合同编号是否唯一，请求参数contractNo："+contractNo);
                Long contractNoCount=investMapper.selectCountEqualsContractNo(contractNo);
                logger.info("(dubbo服务partner-service-impl，保存订单集合)查询合同编号是否唯一，返回结果contractNoCount："+contractNoCount);
                if(contractNoCount>=1){
                    failIds.add(contractNo);
                    resultInvestList.add(-1);
                    resultInvestMap.put(contractNo,"订单保存失败，合同编号重复，contractNo："+contractNo);
                    logger.info("(dubbo服务partner-service-impl，保存订单集合)订单保存失败，合同编号重复，contractNo："+contractNo);
                    continue;
                }
                Invest invest=JSONObject.parseObject(investJson.toJSONString(), Invest.class);//转换成invest对象
                //根据cardCode(身份证号)获取用户信息
                logger.info("(dubbo服务partner-service-impl，保存订单集合)根据cardCode(身份证号)获取用户信息，请求参数cardCode："+cardCode);
                Map<String, Object> userMap=userInfoMapper.selectUserByCardCode(cardCode);
                logger.info("(dubbo服务partner-service-impl，保存订单集合)根据cardCode(身份证号)获取用户信息，返回结果userMap："+userMap.toString());
                Integer userId=Integer.parseInt(String.valueOf(userMap.get("user_id")));
                Integer infoId=Integer.parseInt(String.valueOf(userMap.get("info_id")));
                invest.setUserId(userId);//用户id
                invest.setUserRole(String.valueOf(userMap.get("user_role")));//用户角色
                invest.setInfoId(infoId);//用户信息表id
                //设置“折算系数分子”“折算系数分母”
                Integer productTerm = investJson.getInteger("productTerm");
                logger.info("(dubbo服务partner-service-impl，保存订单集合)获取折算系数，请求参数productTerm："+productTerm);
                //根据productTerm(期限)获取折算系数，不考虑productTerm(期限)对应的折算系数不存在，这种情况
                ProductConfig productConfig=productConfigMapper.selectProductConfigByProductTerm(productTerm);
                logger.info("(dubbo服务partner-service-impl，保存订单集合)获取折算系数，返回结果productConfig："+productConfig.toString());
                Integer discountMolecular = productConfig.getDiscountMolecular();//折算系数分子
                Integer discountDenominator = productConfig.getDiscountDenominator();//折算系数分母
                invest.setDiscountMolecular(discountMolecular);//设置“折算系数分子”
                invest.setDiscountDenominator(discountDenominator);//设置“折算系数分母”
                BigDecimal investAmount=investJson.getBigDecimal("investAmount");
                BigDecimal discountAmount=investAmount.multiply(new BigDecimal(discountMolecular.toString())).divide(new BigDecimal(discountDenominator.toString()),2,BigDecimal.ROUND_HALF_UP);//四舍五入
                logger.info("(dubbo服务partner-service-impl，保存订单集合)计算后的单笔折标额："+discountAmount.toString());
                invest.setDiscountAmount(discountAmount);//设置“单笔折标额(元)”
                invest.setSealStatus(Constants.SEAL_STATUS_0);//封存状态，0：未封存，1：已封存
                invest.setCreateTime(date);//创建时间
                logger.info("(dubbo服务partner-service-impl，保存订单集合)要保存的订单信息："+invest.toString());
                int resultInvest=investMapper.insertSelective(invest);//保存单笔订单
                logger.info("(dubbo服务partner-service-impl，保存订单集合)保存订单返回结果resultInvest："+resultInvest);
                if(resultInvest!=1){
                    failIds.add(contractNo);
                    resultInvestList.add(-1);
                    resultInvestMap.put(contractNo,"订单保存失败");
                }else{
                    successIds.add(contractNo);
                    resultInvestList.add(1);
                    resultInvestMap.put(contractNo,"订单保存成功");
                }
            }
        }
        if(!resultInvestList.contains(-1)){
            result.setReturnCode("0000");
            result.setReturnMsg("操作成功");
            logger.info("(dubbo服务partner-service-impl，保存订单集合)同步订单集合全部操作成功");
        }else{
            logger.info("(dubbo服务partner-service-impl，保存订单集合)同步订单集合有操作失败的");
            result.setReturnMsg("操作失败，失败信息："+resultInvestMap.toString());
        }
        result.setSuccessIds(successIds);//存储成功的集合
        result.setFailIds(failIds);//存储失败的集合
        logger.info("(dubbo服务partner-service-impl，保存订单集合)dubbo服务返回结果："+result.toString());
        /**保存日志 */
        InterfaceLog interfaceLog=new InterfaceLog();
        interfaceLog.setTradeType("‘汇中网’向‘合伙人’推送订单集合");//接口类型
        interfaceLog.setReqData(body);// 请求报文
        interfaceLog.setRespResult(result.toString());//响应报文
        interfaceLog.setSystemDocking(Constants.SYSTEM_CODE_01);//系统标识，01：汇中网，20：合伙人
        interfaceLog.setCreateTime(date);//创建时间
        interfaceLogMapper.insertSelective(interfaceLog);//保存日志
        return result;
    }



    /**
     * Description: 汇中网向合伙人同步订单状态集合
     * @param
     * @return ReturnMsgData
     * @throws
     * @Author peigaoxiang
     * Create Date: 2017年12月11日 下午18:18:19
     */
    @Override
    public ReturnMsgData updateOrderStatus(String body){
        logger.info("(dubbo服务partner-service-impl，更改订单状态)updateOrderStatus方法，接收到的body："+body);
        /**初始化参数**/
        ReturnMsgData result=new ReturnMsgData("1000","操作失败");
        List<String> successIds=new ArrayList<String>();//成功的集合
        List<String> failIds=new ArrayList<String>();//失败的集合
        Date date=new Date();//当前时间
        List<Integer> resultInvestList=new ArrayList<Integer>();//更新订单集合结果
        Map<String,Object> resultInvestMap=new HashMap<String,Object>();//更新操作订单集合详细信息
        /**解析参数，更新订单**/
        JSONArray investArray=JSONArray.parseArray(body);//订单状态集合
        if(investArray==null){
            logger.info("(dubbo服务partner-service-impl，更改订单状态)body转换成JSONArray对象，对象为null");
        }else{
            logger.info("(dubbo服务partner-service-impl，更改订单状态)body转换成JSONArray对象，开始循环遍历订单状态集合");
            for (int i = 0; i < investArray.size(); i++) {
                JSONObject investJson=investArray.getJSONObject(i);
                logger.info("(dubbo服务partner-service-impl，更改订单状态)开始更新第"+(i+1)+"个订单，订单对象investJson："+investJson.toJSONString());
                //定义一些局部变量
                String contractNo=investJson.getString("contractNo");//合同编号
                //检测“合同编号”是否存在
                logger.info("(dubbo服务partner-service-impl，更改订单状态)查询合同编号是否存在，请求参数contractNo："+contractNo);
                Long contractNoCount=investMapper.selectCountEqualsContractNo(contractNo);
                logger.info("(dubbo服务partner-service-impl，更改订单状态)查询合同编号是否存在，返回结果contractNoCount："+contractNoCount);
                if(contractNoCount<=0){
                    failIds.add(contractNo);
                    resultInvestList.add(-1);
                    resultInvestMap.put(contractNo,"订单更新失败，合同编号不存在，contractNo："+contractNo);
                    logger.info("(dubbo服务partner-service-impl，更改订单状态)订单更新失败，合同编号不存在，contractNo："+contractNo);
                    continue;
                }
                Invest invest=JSONObject.parseObject(investJson.toJSONString(), Invest.class);//转换成invest对象
                invest.setOperateTime(date);//修改时间
                logger.info("(dubbo服务partner-service-impl，更改订单状态)更新订单状态，请求参数invest："+invest.toString());
                int resultInvest=investMapper.updateInvestByContractNo(invest);//更新单笔订单
                logger.info("(dubbo服务partner-service-impl，更改订单状态)更新订单状态，返回结果resultInvest："+resultInvest);
                if(resultInvest!=1){
                    failIds.add(contractNo);
                    resultInvestList.add(-1);
                    resultInvestMap.put(contractNo,"订单更新失败");
                }else{
                    successIds.add(contractNo);
                    resultInvestList.add(1);
                    resultInvestMap.put(contractNo,"订单更新成功");
                }
            }
        }
        if(!resultInvestList.contains(-1)){
            result.setReturnCode("0000");
            result.setReturnMsg("操作成功");
            logger.info("(dubbo服务partner-service-impl，更改订单状态)更新订单状态，全部操作成功");
        }else{
            logger.info("(dubbo服务partner-service-impl，更改订单状态)更新订单状态，有操作失败的");
            result.setReturnMsg("操作失败，失败信息："+resultInvestMap.toString());
        }
        result.setSuccessIds(successIds);
        result.setFailIds(failIds);
        logger.info("(dubbo服务partner-service-impl，更改订单状态)dubbo服务返回结果："+result.toString());
        /**保存日志 */
        InterfaceLog interfaceLog=new InterfaceLog();
        interfaceLog.setTradeType("‘汇中网’向‘合伙人’推送订单状态集合");//接口类型
        interfaceLog.setReqData(body);// 请求报文
        interfaceLog.setRespResult(result.toString());//响应报文
        interfaceLog.setSystemDocking(Constants.SYSTEM_CODE_01);//系统标识，01：汇中网，20：合伙人
        interfaceLog.setCreateTime(date);//创建时间
        interfaceLogMapper.insertSelective(interfaceLog);//保存日志
        return result;
    }

}