/**
 * 
 */
package com.wonders.web.tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * <p>
 * Copyright:2018-2100万达信息股份有限公司 版权所有
 * </p>
 * <p>
 * Company:万达股份有限公司
 * </p>
 * 
 * @author Wang si rui
 * @version 1.0
 * @Date 下午10:49:42
 * @Rewrite record： 1、
 */
public class MyIpTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		JspFragment jf = this.getJspBody();
		StringWriter sw = new StringWriter();
		jf.invoke(sw);
		String content = sw.getBuffer().toString();
		content = filter(content);
		this.getJspContext().getOut().write(content);
	}

	public static String filter(String message) {
		if (message == null) {
			return (null);
		}
		char content[] = new char[message.length()];
		message.getChars(0, message.length(), content, 0);
		StringBuilder result = new StringBuilder(content.length + 50);
		for (int i = 0; i < content.length; i++) {
			switch (content[i]) {
			case '<':
				result.append("&lt;");
				break;
			case '>':
				result.append("&gt;");
				break;
			case '&':
				result.append("&amp;");
				break;
			case '"':
				result.append("&quot;");
				break;
			default:
				result.append(content[i]);
			}
		}
		return (result.toString());
	}
}
