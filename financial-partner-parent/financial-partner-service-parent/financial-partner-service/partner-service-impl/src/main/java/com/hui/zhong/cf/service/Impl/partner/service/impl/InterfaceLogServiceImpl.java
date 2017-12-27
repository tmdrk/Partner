package com.hui.zhong.cf.service.Impl.partner.service.impl;

import com.hui.zhong.cf.service.Impl.partner.dao.InterfaceLogMapper;
import com.hui.zhong.cf.service.partner.model.InterfaceLog;
import com.hui.zhong.cf.service.partner.service.InterfaceLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <dl>
 * <dt>类名：${FILE_NAME}</dt>
 * <dd>描述: 节点业务逻辑实现</dd>
 * <dd>创建时间： 2017/12/13 18:28</dd>
 * <dd>创建人： Administrator</dd>
 * <dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2017/12/13 18:28    peigaoxiang       1.0        1.0 Version
 * </pre>
 * </dl>
 */
@Service
public class InterfaceLogServiceImpl implements InterfaceLogService {
    private Logger logger = LoggerFactory.getLogger(InterfaceLogServiceImpl.class);
    @Autowired
    private InterfaceLogMapper interfaceLogMapper;//日志service
    @Override
    public int insertSelective(InterfaceLog interfaceLog) {
        int result=interfaceLogMapper.insertSelective(interfaceLog);
        return result;
    }
}
