package com.hui.zhong.cf.service.partner.model;

import java.io.Serializable;

/**
 * 
 *
 * Description: 页面实体
 *
 * @author ydw
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014-12-11    ydw       1.0        1.0 Version 
 * </pre>
 */
public class Page implements Serializable {
	
    private Integer pageId;//页面id

    private String pageName;//页面中文名

    private String pageMark;//页面唯一标识

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName == null ? null : pageName.trim();
    }

    public String getPageMark() {
        return pageMark;
    }

    public void setPageMark(String pageMark) {
        this.pageMark = pageMark == null ? null : pageMark.trim();
    }
}