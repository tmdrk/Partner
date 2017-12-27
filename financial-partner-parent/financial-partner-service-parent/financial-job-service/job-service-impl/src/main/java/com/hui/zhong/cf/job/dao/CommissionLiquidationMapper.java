package com.hui.zhong.cf.job.dao;


import com.hui.zhong.cf.job.model.CommissionLiquidation;

import java.util.List;

public interface CommissionLiquidationMapper extends BaseMapper<CommissionLiquidation>{
    /**
     * 每月跑批，一次性保存多条数据
     * @param list
     * @return
     */
    public int insertCommissionLiquidationList(List<CommissionLiquidation> list);
}