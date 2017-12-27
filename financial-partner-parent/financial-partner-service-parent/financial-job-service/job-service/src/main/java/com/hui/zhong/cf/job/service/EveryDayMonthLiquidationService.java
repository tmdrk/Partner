package com.hui.zhong.cf.job.service;

import java.text.ParseException;

/**
 * <dl>
 * <dt>类名：${FILE_NAME}</dt>
 * <dd>描述: 节点业务逻辑实现</dd>
 * <dd>创建时间： 2017/12/7 21:47</dd>
 * <dd>创建人： Administrator</dd>
 * <dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2017/12/7 21:47    peigaoxiang       1.0        1.0 Version
 * </pre>
 * </dl>
 */
public interface EveryDayMonthLiquidationService {
    /**
     * @Title: insertEveryDayLiquidationInvest
     * @Description: 每天凌晨00:10:00对历史订单进行结算
        描述：投资人下单投资，每天凌晨计算历史订单，对合伙人、团队管理者产生的奖金
        场景：
            情况1：到凌晨00:10:00时，若当前时间是[2号，31号]，那么结算“buyDate（订单购买时间）”处于当月的所有未结算的订单
            情况2：到凌晨00:10:00时，若当前时间是1号，那么结算“buyDate（订单购买时间）”上一个月所有未结算的订单
        业务逻辑操作：
            循环遍历每一个符合条件的订单，根据订单，订单关联的用户，计算“出借奖金”、“差额奖金”、“服务费”
        具体操作：
            对t_invest表中的符合条件的数据计算，并存储到t_invest_bonus表中，
        注意事项：
     *
     * @throws
     */
    public void insertEveryDayLiquidationInvest() throws ParseException;
    /**
     * @Title: updateEveryMonthSealInvest
     * @Description:每月封存
     *              每月1号凌晨00:05:00对上一个月历史订单进行封存
     *
     * @throws
     */
    public void updateEveryMonthSealInvest() throws ParseException;
    /**
     * @Title: insertEveryMonthLiquidationInvest
     * @Description:每月跑批
     *              每月1号凌晨01:00:00对上一个月历史订单进行跑批结算
     *
     * @throws
     */
    public void insertEveryMonthLiquidationInvest() throws ParseException;



}
