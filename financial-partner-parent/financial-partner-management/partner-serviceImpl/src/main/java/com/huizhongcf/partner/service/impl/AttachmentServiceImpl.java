/* 
 * Copyright (C) 2006-2017 普惠融通科技（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: AttachmentServiceImpl.java 
 *
 * Created: [2017年6月23日 下午5:59:54] by yaodawei
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
 * ProjectName: lender-service 
 * 
 * Description: 
 * 
 * ==========================================================*/

package com.huizhongcf.partner.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huizhongcf.partner.dao.AttachmentMapper;
import com.huizhongcf.partner.model.Attachment;
import com.huizhongcf.partner.service.AttachmentService;

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
@Service("attachmentService")
public class AttachmentServiceImpl implements  AttachmentService{
	
	@Autowired
	private AttachmentMapper attachmentMapper;
	
	@Override
	public Integer insertSelective(Attachment attachment) {
		attachmentMapper.insertSelective(attachment);
		return attachment.getAttachmentId();
	}

	@Override
	public void deleteByPrimaryKey(Integer id) {
		attachmentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Map<String, Object>> findAttachments(Map<String, Object> parms) {
		return attachmentMapper.findAttachments(parms);
	}

}
