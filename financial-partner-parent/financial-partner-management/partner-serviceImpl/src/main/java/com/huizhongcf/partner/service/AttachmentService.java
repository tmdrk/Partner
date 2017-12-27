/* 
 * Copyright (C) 2006-2017 普惠融通科技（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: AttachmentService.java 
 *
 * Created: [2017年6月23日 下午5:59:32] by yaodawei
 *
 * $Id$
 * 
 * $Revision$
 *
 * $Author$
 *
 * $Date$
 *
 * ============================================================ 
 * 
 * ProjectName: partner-service 
 * 
 * Description: 
 * 
 * ==========================================================*/

package com.huizhongcf.partner.service;

import java.util.List;
import java.util.Map;

import com.huizhongcf.partner.model.Attachment;

/** 
 *
 * Description: 
 *
 * @author yaodawei
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年6月23日    yaodawei       1.0        1.0 Version 
 * </pre>
 */

public interface AttachmentService {

	public Integer insertSelective(Attachment attachment);
	
	public void deleteByPrimaryKey(Integer id);
	
	/**
	 * 
	 * Description: 图片查询
	 *
	 * @param 
	 * @return PageModel 返回信息
	 * @throws 
	 * @Author guoyanwei
	 * Create Date: 2017年6月28日 下午3:37:43
	 */
	public List<Map<String, Object>> findAttachments(Map<String, Object> parms);

}
