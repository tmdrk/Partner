package com.hui.zhong.cf.service.Impl.partner.dao;


import java.util.List;

import com.hui.zhong.cf.service.partner.model.ReservedInviteCode;

public interface ReservedInviteCodeMapper extends BaseMapper<ReservedInviteCode>{
	
	public List<ReservedInviteCode> getReservedInviteCode(ReservedInviteCode reservedinvitecode);

	
}