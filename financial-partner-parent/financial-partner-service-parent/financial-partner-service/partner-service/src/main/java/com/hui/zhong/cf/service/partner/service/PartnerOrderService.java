package com.hui.zhong.cf.service.partner.service;

import com.huizhongcf.util.ReturnMsgData;

/**
 * <dl>
 * <dt>类名：${FILE_NAME}</dt>
 * <dd>描述: 节点业务逻辑实现</dd>
 * <dd>创建时间： 2017/12/12 18:08</dd>
 * <dd>创建人： Administrator</dd>
 * <dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2017/12/12 18:08    peigaoxiang       1.0        1.0 Version
 * </pre>
 * </dl>
 */
public interface PartnerOrderService {
    /**
     * Description: 汇中网向合伙人同步用户实名认证信息
     * @param
     * @return ReturnMsgData
     * @throws
     * @Author peigaoxiang
     * Create Date: 2017年12月11日 下午18:18:19
     */
    public ReturnMsgData updateUserRealInfo(String body);
    /**
     * Description: 汇中网向合伙人同步订单集合
     * @param
     * @return ReturnMsgData
     * @throws
     * @Author peigaoxiang
     * Create Date: 2017年12月11日 下午18:18:19
     */
    public ReturnMsgData insertOrderInfo(String body);

    /**
     * Description: 汇中网向合伙人同步订单状态集合
     * @param
     * @return ReturnMsgData
     * @throws
     * @Author peigaoxiang
     * Create Date: 2017年12月11日 下午18:18:19
     */
    public ReturnMsgData updateOrderStatus(String body);
}
