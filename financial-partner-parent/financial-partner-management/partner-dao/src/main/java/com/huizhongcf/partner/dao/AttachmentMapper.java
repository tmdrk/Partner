package com.huizhongcf.partner.dao;

import java.util.List;
import java.util.Map;

import com.huizhongcf.partner.model.Attachment;

/**
 * 
 *
 * Description: 附件Mapper
 *
 * @author yaodawei
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年6月15日    yaodawei       1.0        1.0 Version 
 * </pre>
 */
public interface AttachmentMapper extends BaseMapper<Attachment>{

	/**
     * 
     * Description: 根据多条件查询,返回Map
     *
     * @param 
     * @return Map<String,Object>
     * @throws 
     * @Author ydw
     * Create Date: 2014-11-24 下午08:06:54
     */
    public List<Map<String, Object>> findAttachments(Map<String, Object> paramsCondition);
}