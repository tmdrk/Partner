package hui.zhong.cf.partner.h5.constants;

/**
 * Description:
 *
 * @author LiuXianfa
 * @date 2017/12/21
 */
public class MobileApiUrls {

    public static final String checkUserName = "%s/user/checkUserName";

    public static final String modifyPassWord = "%s/user/modifyPassWord";

    public static final String indexStat = "%s/index/stat";

    public static final String doRegister = "%s/user/doRegister";

    public static final String forgetPassWord = "%s/user/forgetPassWord";

    public static final String sendMsg = "%s/user/sendMsg";

    public static final String logout = "%s/user/logout";

    public static final String doLogin = "%s/user/doLogin";

    public static final String userInfo = "%s/user/info";
    
    public static final String monthList = "%s/commission/unsettlement/month";
    
    public static final String unSettlementList = "%s/commission/unsettlement";

	public static final String loanBonusDetail = "%s/commission/loanBonus/detail";

	public static final String diffBonusDetail = "%s/commission/diffBonus/detail";
	
	public static final String serviceBonusDetail = "%s/commission/serviceBonus/detail";

    // =====================用户页签下的接口===================
    /**
     *我的客户
     */
	public static final String myCustomer = "%s/loginUser/myCustomer";
    /**
     *我的下级-合伙人
     */
	public static final String mySubordinate_partner = "%s/loginUser/mySubordinate/partner";
    /**
     *我的下级-合伙人客户
     */
	public static final String mySubordinate_partnerCustomer = "%s/loginUser/mySubordinate/partnerCustomer";
    /**
     *其他下级-合伙人
     */
	public static final String otherSubordinate_partner = "%s/loginUser/otherSubordinate/partner";
    /**
     *其他下级-合伙人客户
     */
	public static final String otherSubordinate_partnerCustomer = "%s/loginUser/otherSubordinate/partnerCustomer";
    /**
     *奖励
     */
	public static final String investBonus_init = "%s/investBonus/init";
    // =====================用户页签下的接口 end===============


    /**
     * 奖励一般合伙人/奖励团队管理者列表
     */
    public static final String doReward = "%s/investBonus/init";
    
    /**
     * 出借详情列表
     */
    public static final String doLend = "%s/invest/list";

    /**
     * 出借订单详情
     */
    public static final String doLendOrder = "%s/invest/detail";
    
}
