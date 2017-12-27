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
 * <dd>创建时间： 2017/12/9 11:01</dd>
 * <dd>创建人： Administrator</dd>
 * <dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2017/12/9 11:01    peigaoxiang       1.0        1.0 Version
 * </pre>
 * </dl>
 */
public class EveryMonthLiquidationJobController {
    private Logger logger = LoggerFactory.getLogger(EveryMonthLiquidationJobController.class);
    @Autowired
    private EveryDayMonthLiquidationService everyDayMonthLiquidationService;//订单跑批Servcice

    /**
     * @Title: everyMonthLiquidationInvest
     * @Description: 每月跑批
     *                 每月凌晨01:00:00对历史订单进行跑批结算
     * @throws
     */
    public void everyMonthLiquidationInvest() throws ParseException {
        logger.info("======（job服务定时任务：每月跑批）financial-partner-job项目everyMonthLiquidationInvest方法------每月凌晨01:00:00对历史订单进行跑批结算-------开始====");
        try {
        everyDayMonthLiquidationService.insertEveryMonthLiquidationInvest();
        }catch(Exception e){
            logger.error("（job服务定时任务：每月跑批）发生异常，异常信息："+e.getMessage());
            e.printStackTrace();
        }
        logger.info("======（job服务定时任务：每月跑批）financial-partner-job项目everyMonthLiquidationInvest方法------每月凌晨01:00:00对历史订单进行跑批结算-------结束====");
    }

}
