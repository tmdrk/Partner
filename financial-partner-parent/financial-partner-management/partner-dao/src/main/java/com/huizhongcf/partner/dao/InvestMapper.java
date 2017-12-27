package com.huizhongcf.partner.dao;

import java.util.List;
import java.util.Map;

import com.huizhongcf.partner.model.Invest;
import com.huizhongcf.partner.model.InvestBonus;

public interface InvestMapper extends BaseMapper<Invest>{
    /**
     * 
     * Description: 查询订单详情数据,返回Map
     *
     * @param 
     * @return Map<String,Object>
     * @throws 
     * @Author lixiaoshuai
     * Create Date: 2017年12月9日 18:27:10
     */
    public Map<String, Object> getInvestDetail(Map<String, Object> paramsCondition);

}