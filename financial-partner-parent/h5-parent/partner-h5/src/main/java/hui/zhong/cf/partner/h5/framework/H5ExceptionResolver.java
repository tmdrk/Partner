package hui.zhong.cf.partner.h5.framework;

import com.alibaba.fastjson.JSON;
import hui.zhong.cf.partner.h5.exception.TokenRequiredException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * H5-全局异常处理器
 *
 * @author LiuXianfa
 */
public class H5ExceptionResolver extends SimpleMappingExceptionResolver {

    private static final Log loger = LogFactory.getLog(H5ExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler,
                                         Exception ex) {

        ResultBean resultBean = new ResultBean();

        if (ex instanceof TokenRequiredException) {
            loger.error("H5模块>未登录");
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");

            resultBean.setErrorCode("9999");
            resultBean.setErrorMsg("用户未登录/登录失效");
            try {
                String jsonString = JSON.toJSONString(resultBean);
                response.getWriter().print(jsonString);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        loger.error("H5模块>接口异常", ex);
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        resultBean.setErrorCode("500");
        resultBean.setErrorMsg("服务器异常");
        try {
            String jsonString = JSON.toJSONString(resultBean);
            response.getWriter().print(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
