package com.hui.zhong.cf.job.api.controller;

import com.hui.zhong.cf.job.service.EveryDayMonthLiquidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

/**
 * <dl>
 * <dt>类名：${FILE_NAME}</dt>
 * <dd>描述: 节点业务逻辑实现</dd>
 * <dd>创建时间： 2017/12/9 10:48</dd>
 * <dd>创建人： Administrator</dd>
 * <dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2017/12/9 10:48    peigaoxiang       1.0        1.0 Version
 * </pre>
 * </dl>
 */
public class EveryMonthSealJobController {
    private Logger logger = LoggerFactory.getLogger(EveryMonthSealJobController.class);
    @Autowired
    private EveryDayMonthLiquidationService everyDayMonthLiquidationService;//订单跑批Servcice

    /**
     * @Title: everyMonthSealInvest
     * @Description: 每月封存
     *                 每月1号凌晨00:05:00对上一个月历史订单进行封存
     * @throws
     */
    public void everyMonthSealInvest() throws ParseException {
        logger.info("======（job服务定时任务：每月封存）financial-partner-job项目everyMonthSealInvest方法------每月1号凌晨00:05:00对上一个月历史订单进行封存-------开始====");
        try {
            everyDayMonthLiquidationService.updateEveryMonthSealInvest();
        }catch(Exception e){
            logger.error("（job服务定时任务：每月封存）发生异常，异常信息："+e.getMessage());
            e.printStackTrace();
        }
        logger.info("======（job服务定时任务：每月封存）financial-partner-job项目everyMonthSealInvest方法------每月1号凌晨00:05:00对上一个月历史订单进行封存-------结束====");
    }






}
