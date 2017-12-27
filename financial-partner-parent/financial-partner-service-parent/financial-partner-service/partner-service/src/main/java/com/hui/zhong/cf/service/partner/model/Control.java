package com.hui.zhong.cf.service.partner.model;

import java.io.Serializable;

/**
 * 
 *
 * Description: 按钮控件实体
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
public class Control implements Serializable {
	
    private Integer controlId;//控件id

    private String controlName;//控件名称

    private String controlMark;//控件唯一标识

    private Integer pageId;//所属页面id

    public Integer getControlId() {
        return controlId;
    }

    public void setControlId(Integer controlId) {
        this.controlId = controlId;
    }

    public String getControlName() {
        return controlName;
    }

    public void setControlName(String controlName) {
        this.controlName = controlName == null ? null : controlName.trim();
    }

    public String getControlMark() {
        return controlMark;
    }

    public void setControlMark(String controlMark) {
        this.controlMark = controlMark == null ? null : controlMark.trim();
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }
}