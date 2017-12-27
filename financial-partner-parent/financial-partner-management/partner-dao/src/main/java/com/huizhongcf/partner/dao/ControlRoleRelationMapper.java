package com.huizhongcf.partner.dao;

import java.util.List;

import com.huizhongcf.partner.model.ControlRoleRelation;

public interface ControlRoleRelationMapper extends BaseMapper<ControlRoleRelation>{
	
    /**
     * 
     * Description: 根据控件id删除该控件相关的角色信息
     *
     * @param 
     * @return int
     * @throws 
     * @Author ydw
     * Create Date: 2014-12-9 下午04:35:50
     */
    public int deleteCtrlRoleRelationByCtrlId(Integer controlId);
    
    /**
     * 
     * Description: 查找这个组件拥有哪些角色
     *
     * @param 
     * @return List<String>
     * @throws 
     * @Author ydw
     * Create Date: 2014-12-11 上午10:21:33
     */
    public List<String> findRoleCodeByCtrlId(Integer controlId);
}