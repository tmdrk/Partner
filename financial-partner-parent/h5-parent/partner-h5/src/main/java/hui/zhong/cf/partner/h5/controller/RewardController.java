package hui.zhong.cf.partner.h5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.huizhongcf.util.RegExpUtil;
import com.huizhongcf.util.StringUtil;

import hui.zhong.cf.partner.h5.constants.MobileApiUrls;
import hui.zhong.cf.partner.h5.framework.BaseController;
import hui.zhong.cf.partner.h5.utils.OkHttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
/**
 * 奖励
 * @author libai
 *
 */
@Controller
@RequestMapping("/reward")
public class RewardController  extends BaseController {
	private static final String TOKEN_PARAM = "{'token':'%s'}";
	/**
     * 跳转到奖励一般合伙人/奖励团队管理者列表页面
     * @param token		用户标识
	 * @throws IOException 
     * @return
     */
    @RequestMapping("/toReward")
    public String toReward() throws IOException {
    	logger.info("跳转到奖励一般合伙人/奖励团队管理者列表页面");
    	String token = getToken();
        if (StringUtil.isBlank(token)) {
        	logger.info("token为空");
            return "login";
        }
    	String url = String.format(MobileApiUrls.doReward, MOBILE_API_SERVER_DOMAIN);
        String params = String.format(TOKEN_PARAM, token);
        logger.info("入参："+params);
        JSONObject responseBody = parse(post(url, params));
        logger.info("出参："+responseBody);
        //判断返回状态是否登陆超时
        if (isUnLogin(responseBody)) {
            // token失效
        	logger.info("token失效");
            return "login";
        }
        if (OkHttpUtil.isSuccessful(responseBody)) {
            // 数据请求成功
        	getRequest().setAttribute("body", responseBody.get("body"));
            return "awards";
        }
        return "awards";
        /*return "跳转到失败页面";*/
    }
	
	/**
     * 跳转到出借详情列表页面
     *
     * @return
     */
    @RequestMapping("/toLend")
    public String toLend() {
    	logger.info("跳转到出借详情列表页面");
        return "loanDetail";
    }
    
    /**
     * 跳转到出借订单详情页面
     * @param investId		订单id(0我的出借，1我的下级出借，2其他下级出借)
     * @return
     * @throws IOException 
     */
    @RequestMapping("/toLendOrder")
    public String toLendOrder(@RequestParam(required = true) String investId) throws IOException {
    	logger.info("跳转到出借订单详情页面");
    	String token = getToken();
        if (StringUtil.isBlank(token)) {
        	logger.info("token为空");
            return "login";
        }
    	String url = String.format(MobileApiUrls.doLendOrder, MOBILE_API_SERVER_DOMAIN);
        String params = String.format("{'token':'%s','investId':'%s'}", token, investId);
        logger.info("入参："+params);
        JSONObject responseBody = parse(post(url, params));
        logger.info("出参："+responseBody);
        //判断返回状态是否登陆超时
        if (isUnLogin(responseBody)) {
            // token失效
        	logger.info("token失效");
            return "login";
        }
        if (OkHttpUtil.isSuccessful(responseBody)) {
            // 数据请求成功
        	getRequest().setAttribute("body", responseBody.get("body"));
            return "orderDetail";
        }
    	getRequest().setAttribute("money", "错误");
        return "orderDetail";
        /*return "跳转到失败页面";*/
    }
	
	/**
     * 出借详情列表
     * @param lender		出借方(0我的出借，1我的下级出借，2其他下级出借)
     * @param investStatus	出借状态（10预约中20收益中30已结清）不传显示所有
     * @param userId		用户id 查看某个特定用户时，传入
     * @param pageNo		第几页
     * @param pageSize		每页显示数量
	 * @throws IOException 
     */
    @RequestMapping("/doLendList")
    @ResponseBody
    public Object doLendList(@RequestParam(required = true) String lender,
                             @RequestParam(required = true) String investStatus,
                             @RequestParam(required = true) String userId,
                             @RequestParam(required = false) String pageNo,
                             @RequestParam(required = false) String pageSize) throws IOException {
    	String token = getToken();
        if (StringUtil.isBlank(token)) {
        	logger.info("token为空");
        	return renderError("100000", "token为空");
        }
    	String url = String.format(MobileApiUrls.doLend, MOBILE_API_SERVER_DOMAIN);
        Map<String, String> params = new HashMap<String, String>();
        params.put("lender", lender);
        params.put("investStatus", investStatus);
        params.put("userId", userId);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        params.put("token", token);
        logger.info("入参："+params);
        JSONObject responseBody = parse(post(url, params));
        logger.info("出参："+responseBody);
        //判断返回状态是否登陆超时
        if (isUnLogin(responseBody)) {
            // token失效
        	logger.info("登陆超时");
        	return renderError("100001", "登陆超时");
        }
        if (OkHttpUtil.isSuccessful(responseBody)) {
            // 数据请求成功
        	return renderSuccess(responseBody);
        }
        logger.info("接口调用错误");
        return renderError("100002", "接口调用错误");
    }
    
}
