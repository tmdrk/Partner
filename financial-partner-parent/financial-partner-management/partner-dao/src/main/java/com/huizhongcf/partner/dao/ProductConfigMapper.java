package com.huizhongcf.partner.dao;

import com.huizhongcf.partner.model.PartnerOrganization;
import com.huizhongcf.partner.model.ProductConfig;

public interface ProductConfigMapper extends BaseMapper<ProductConfig>{

	Integer insertProduct(ProductConfig productConfig);

}