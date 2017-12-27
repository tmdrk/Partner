package com.hui.zhong.cf.job.dao;


import com.hui.zhong.cf.job.model.LoginUser;

import java.util.List;
import java.util.Map;

public interface LoginUserMapper extends BaseMapper<LoginUser>{
    /**
     * @Title: updateEveryMonthSealedInvest
     * @Description:每月跑批，获取要计算“佣金”的用户，就是获取身份为合伙人的用户
     * @param @param params
     * @param @return
     * @return int    返回类型
     * @throws
     */
    public List<LoginUser> selectEveryMonthUser(Map<String,Object> params);






}