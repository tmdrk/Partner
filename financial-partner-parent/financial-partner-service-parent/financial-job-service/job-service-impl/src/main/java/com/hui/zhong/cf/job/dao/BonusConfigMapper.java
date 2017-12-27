package com.hui.zhong.cf.job.dao;


import com.hui.zhong.cf.job.model.BonusConfig;

import java.util.Map;

public interface BonusConfigMapper extends BaseMapper<BonusConfig>{
    /**
     * @Title: selectAllBonus
     * @Description:查询所有的奖金类型
     * @param @param params
     * @param @return
     * @return Map<String,Object>    返回类型
     * @throws
     */
    public Map<String,Object> selectAllBonus(Map<String,Object> params);
}