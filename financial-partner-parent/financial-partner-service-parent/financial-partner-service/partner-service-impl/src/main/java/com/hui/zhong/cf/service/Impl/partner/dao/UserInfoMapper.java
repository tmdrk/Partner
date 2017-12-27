package com.hui.zhong.cf.service.Impl.partner.dao;


import com.hui.zhong.cf.service.partner.model.UserInfo;

import java.util.Map;

public interface UserInfoMapper extends BaseMapper<UserInfo>{

    /**
     * Description: 根据cardCode(身份证号)查询用户信息
     * @param
     * @return Map<String,Object>
     * @throws
     * @Author peigaoxiang
     * Create Date: 2017年12月11日 下午18:18:19
     */
    public Map<String,Object> selectUserByCardCode(String cardCode);
    /**
     * Description: 保存用户实名认证信息
     * @param
     * @return int
     * @throws
     * @Author peigaoxiang
     * Create Date: 2017年12月11日 下午18:18:19
     */
    public int insertUserInfo(Map<String,Object> params);

    public Map<String,Object> selectCountByUserIdAndCardCode(Map<String,Object> params);
}