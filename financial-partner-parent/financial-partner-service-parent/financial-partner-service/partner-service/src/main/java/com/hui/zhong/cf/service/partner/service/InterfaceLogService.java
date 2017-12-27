package com.hui.zhong.cf.service.partner.service;

import com.hui.zhong.cf.service.partner.model.InterfaceLog;

/**
 * <dl>
 * <dt>类名：${FILE_NAME}</dt>
 * <dd>描述: 节点业务逻辑实现</dd>
 * <dd>创建时间： 2017/12/13 18:27</dd>
 * <dd>创建人： Administrator</dd>
 * <dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2017/12/13 18:27    peigaoxiang       1.0        1.0 Version
 * </pre>
 * </dl>
 */
public interface InterfaceLogService {
    /**
     * @Title: insertSelective
     * @Description:保存日志信息
     * @param @param interfaceLog
     * @param @return
     * @return int    返回类型
     * @throws
     */
    public int insertSelective(InterfaceLog interfaceLog);

}
