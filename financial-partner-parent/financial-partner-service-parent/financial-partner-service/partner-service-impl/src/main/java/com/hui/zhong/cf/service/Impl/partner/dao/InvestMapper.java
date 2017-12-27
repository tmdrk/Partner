package com.hui.zhong.cf.service.Impl.partner.dao;


import com.hui.zhong.cf.service.partner.model.Invest;

public interface InvestMapper extends BaseMapper<Invest>{

    /**
     * Description: 查询contractNo(合同编号)是否重复
     * @param
     * @return Long
     * @throws
     * @Author peigaoxaing
     * Create Date: 2017年12月13日 上午12:04:32
     */
    public Long selectCountEqualsContractNo(String contractNo);
    /**
     * Description: 根据contractNo(合同编号)更新订单
     * @param
     * @return Long
     * @throws
     * @Author peigaoxaing
     * Create Date: 2017年12月13日 上午12:04:32
     */
    public int updateInvestByContractNo(Invest invest);
}