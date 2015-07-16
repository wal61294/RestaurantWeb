/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.java_training.huzarevich.restaurant.custom_tag;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author huz
 */
public class CustomTag extends TagSupport {

    private String format;

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
            out.write("Done by Alexander Huzarevich 2015");
        } catch (Exception e) {
            throw new JspException(e.getMessage());
        }
        return EVAL_PAGE;
    }

    public int doEndTag() {
        return EVAL_PAGE;
    }

}
