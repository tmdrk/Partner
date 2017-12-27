package com.hui.zhong.cf.job.model;

/**
 * 
 *<dl>
 *<dt>类名：User.java</dt>
 *<dd>描述: 用户实体</dd> 
 *<dd>创建时间： 2017年8月7日 上午10:40:39</dd>
 *<dd>创建人： 李强</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         	Author		Version		Description 
 * ------------------------------------------------------------------ 
 * 2017年8月7日		李强			1.0			1.0 Version 
 * </pre>
 *</dl>
 */
public class User {
    private Long userid;//主键id

    private String username;//用户名

    private String passwd;//密码

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}