package com.hui.zhong.cf.job.dao;


import com.hui.zhong.cf.job.model.InvestBonus;

import java.util.List;

public interface InvestBonusMapper extends BaseMapper<InvestBonus>{
    /**
     * 隔天跑批，一次性保存多条数据
     * @param list
     * @return
     */
    public int insertInvestBonusList(List<InvestBonus> list);
}