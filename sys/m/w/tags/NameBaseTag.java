package m.w.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.nutz.lang.Strings;

/**
 * 基于名称判定的标签
 * 
 * @author WenWu
 * 
 */
public abstract class NameBaseTag extends SecureTag {
    private static final long serialVersionUID = -6979650056303439620L;

    private String name = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected void verifyAttributes() throws JspException {
        if (Strings.isBlank(name)) {
            throw new JspException("The 'name' tag attribute must be set.");
        }
    }

    public int onDoStartTag() throws JspException {
        boolean show = showTagBody(getName());
        if (show) {
            return TagSupport.EVAL_BODY_INCLUDE;
        } else {
            return TagSupport.SKIP_BODY;
        }
    }

    protected abstract boolean showTagBody(String roleName);
}
