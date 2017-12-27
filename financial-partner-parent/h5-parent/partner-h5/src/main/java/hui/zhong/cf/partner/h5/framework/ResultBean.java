package hui.zhong.cf.partner.h5.framework;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LiuXianfa
 */
public class ResultBean {
    private String errorCode;
    private String errorMsg;
    private String serverTime;
    private Object body;

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getServerTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }


}


	
	
	
