package com.huizhongcf.partner.dao;


import java.util.List;
import java.util.Map;

import com.huizhongcf.partner.model.BonusConfig;

public interface BonusConfigMapper extends BaseMapper<BonusConfig>{


	List<Map<String, Object>> selectBonusConfig();

	

	
}