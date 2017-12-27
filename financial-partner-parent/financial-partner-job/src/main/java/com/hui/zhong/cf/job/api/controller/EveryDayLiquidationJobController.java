package com.hui.zhong.cf.job.api.controller;

import com.hui.zhong.cf.job.service.EveryDayMonthLiquidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

/**
 *<dl>
 *<dt>类名：EveryDayLiquidationJobController</dt>
 *<dd>描述: 每天凌晨00:10:00，执行定时任务，结算昨天的订单</dd>
 *<dd>创建时间： 2017.12.7</dd>
 *<dd>创建人： peigaoxiang</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2017.12.7    peigaoxiang       1.0        1.0 Version
 * </pre>
 *</dl>
 */
public class EveryDayLiquidationJobController {
    private Logger logger = LoggerFactory.getLogger(EveryDayLiquidationJobController.class);
    @Autowired
    private EveryDayMonthLiquidationService everyDayMonthLiquidationService;//订单跑批Servcice

    /**
     * @Title: everyDayLiquidationInvest
     * @Description: 隔天凌晨00:10:00对历史订单进行结算
     * @throws
     */
    public void everyDayLiquidationInvest(){
        logger.info("======（job服务定时任务：隔天跑批）financial-partner-job项目everyDayLiquidationInvest方法------隔天对历史订单跑批结算-------开始====");
        try {
            everyDayMonthLiquidationService.insertEveryDayLiquidationInvest();
        }catch(Exception e){
            logger.error("（job服务定时任务：隔天跑批）发生异常，异常信息："+e.getMessage());
            e.printStackTrace();
        }
        logger.info("======（job服务定时任务：隔天跑批）financial-partner-job项目everyDayLiquidationInvest方法------隔天对历史订单跑批结算-------结束====");
    }







}
