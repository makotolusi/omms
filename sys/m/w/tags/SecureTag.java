package m.w.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 权限判断标签
 * @author WenWu
 *
 */
public abstract class SecureTag extends TagSupport {
    private static final long serialVersionUID = 2327254932216121773L;

    protected void verifyAttributes() throws JspException {}

    public int doStartTag() throws JspException {

        verifyAttributes();

        return onDoStartTag();
    }

    public abstract int onDoStartTag() throws JspException;
}
