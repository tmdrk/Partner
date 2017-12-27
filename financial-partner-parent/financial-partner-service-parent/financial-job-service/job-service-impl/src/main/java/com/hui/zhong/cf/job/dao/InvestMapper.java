package com.hui.zhong.cf.job.dao;


import com.hui.zhong.cf.job.model.Invest;

import java.util.List;
import java.util.Map;

public interface InvestMapper extends BaseMapper<Invest>{

    /**
     * @Title: selectEveryDayInvest
     * @Description:隔天跑批---查询要跑批的订单集合
     * @param @param params
     * @param @return
     * @return List<Map<String, Object>>    返回类型
     * @throws
     */
    public List<Map<String, Object>> selectEveryDayInvest(Map<String, Object> params);
    /**
     * @Title: updateEveryMonthSealedInvest
     * @Description:每月封存，每月1号凌晨00:05:00对上一个月历史订单进行封存
     * @param @param params
     * @param @return
     * @return int    返回类型
     * @throws
     */
    public int updateEveryMonthSealedInvest(Map<String, Object> params);
    /**
     * @Title: selectEveryMonthLiquidationOfUser
     * @Description:每月跑批，每月1号凌晨01:00:00对上一个月历史订单进行跑批
     *                  查询单个合伙人的“投资总额”“折扣总额”“出借费”“差额费”“服务费”
     * @param @param params
     * @param @return
     * @return Map<String,Object>    返回类型
     * @throws
     */
    public Map<String,Object> selectEveryMonthLiquidationOfUser(Map<String, Object> params);

}