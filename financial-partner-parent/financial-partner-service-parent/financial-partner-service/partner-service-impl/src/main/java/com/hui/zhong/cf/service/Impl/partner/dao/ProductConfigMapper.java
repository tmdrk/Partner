package com.hui.zhong.cf.service.Impl.partner.dao;


import com.hui.zhong.cf.service.partner.model.ProductConfig;

public interface ProductConfigMapper extends BaseMapper<ProductConfig>{

    /**
     * Description: 根据productTerm(期限)获取折算系数
     * @param
     * @return ProductConfig
     * @throws
     * @Author peigaoxaing
     * Create Date: 2017年12月13日 上午12:04:32
     */
    public ProductConfig selectProductConfigByProductTerm(Integer productTerm);
}