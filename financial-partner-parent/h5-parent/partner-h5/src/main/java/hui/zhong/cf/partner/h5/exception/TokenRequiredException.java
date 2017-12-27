package hui.zhong.cf.partner.h5.exception;

/**
 * Description:
 *
 * @author LiuXianfa
 * @date 2017/12/23
 */
public class TokenRequiredException extends RuntimeException {

    public TokenRequiredException() {
        super("token不能为空");
    }

    public TokenRequiredException(String msg) {
        super(msg);
    }
}
