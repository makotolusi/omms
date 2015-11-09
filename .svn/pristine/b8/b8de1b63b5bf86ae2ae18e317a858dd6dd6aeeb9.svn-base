package m.w.core.dto.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.json.JsonField;
import org.nutz.lang.Strings;

public class TreeItem implements Serializable, Comparable<TreeItem> {
    private static final long serialVersionUID = -2213865915392800214L;

    private static final String OPEN = "open";
    private static final String CLOSED = "closed";

    @JsonField(ignore = true)
    private String pid;

    @JsonField(ignore = true)
    private Integer order = 0;

    @JsonField(ignore = true)
    private TreeItem parent;
    
    @JsonField(ignore = true)
    private Object data;

    @JsonField(ignore = true)
    boolean builded = false;

    private String id;
    private String text;
    private String iconCls;
    private String state = CLOSED;
    private boolean checked;
    private Map<String, Object> attributes;
    private List<TreeItem> children;

    public static TreeItem item() {
        return new TreeItem();
    }

    public static TreeItem item(Object id, String text) {
        return new TreeItem(id, text);
    }

    public static TreeItem item(Object id, String text, Object pid) {
        return new TreeItem(id, text, pid);
    }

    public static TreeItem item(Object id, String text, Object pid, Integer order) {
        return new TreeItem(id, text, pid, order);
    }

    public static TreeItem item(Object id, String text, Object pid, Integer order, boolean isOpen) {
        return new TreeItem(id, text, pid, order, isOpen);
    }
    
    public static TreeItem item(Object id, String text, Object pid, String attr, Integer order) {
        return new TreeItem(id, text, pid, attr, order);
    }
    
    public static TreeItem item(Object id, String text, Object pid, String icon, String attr) {
        return new TreeItem(id, text, pid, icon, attr);
    }

    public static TreeItem item(Object id,
                                String text,
                                Object pid,
                                Integer order,
                                boolean isOpen,
                                boolean isChecked) {
        return new TreeItem(id, text, pid, order, isOpen, isChecked);
    }
    
    

    public TreeItem() {};

    public TreeItem(Object id, String text) {
        this(id, text, null, 0, false, false);
    };

    public TreeItem(Object id, String text, Object pid) {
        this(id, text, pid, 0, false, false);
    };

    public TreeItem(Object id, String text, Object pid, Integer order) {
        this(id, text, pid, order, false, false);
    };

    public TreeItem(Object id, String text, Object pid, Integer order, boolean isOpen) {
        this(id, text, pid, order, isOpen, false);
    }

    public TreeItem(Object id,
                    String text,
                    Object pid,
                    Integer order,
                    boolean isOpen,
                    boolean isChecked) {
        this.id = Strings.sNull(id);
        this.text = text;
        this.pid = Strings.sNull(pid);
        this.order = order;
        if (isOpen) {
            this.state = OPEN;
        }
        this.checked = isChecked;
    }
    
    public TreeItem(Object id, String text, Object pid, String attr, Integer order) {
		this.id = Strings.sNull(id);
		this.text = text;
		this.pid = Strings.sNull(pid);
		this.addAttr("type", attr);
		this.order = order;
	}
    
	public TreeItem(Object id, String text, Object pid, String icon, String attr) {
		this.id = Strings.sNull(id);
		this.text = text;
		this.iconCls = icon;
		this.pid = Strings.sNull(pid);
		this.addAttr("type", attr);
	}

    public TreeItem addChild(TreeItem item) {
        if (children == null) {
            children = new ArrayList<TreeItem>();
        }
        children.add(item);
        return this;
    }

    public TreeItem addAttr(String key, Object value) {
        if (attributes == null) {
            attributes = new HashMap<String, Object>();
        }
        attributes.put(key, value);
        return this;
    }

    public TreeItem open() {
        state = OPEN;
        return this;
    }

    public TreeItem close() {
        state = CLOSED;
        return this;
    }

    public TreeItem check() {
        checked = true;
        return this;
    }

    public TreeItem uncheck() {
        checked = false;
        return this;
    }

    /**
     * 是否具有下级节点
     * 
     * @return
     */
    public boolean hasChildren() {
        return children != null && !children.isEmpty();
    }

    /**
     * 是否具有父节点
     * 
     * @return
     */
    public boolean hasParent() {
        return builded && pid != null;
    }

    @Override
    public int compareTo(TreeItem o) {
        return this.order != null ? this.order.compareTo(o.order) : 0;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public TreeItem getParent() {
        return parent;
    }

    public void setParent(TreeItem parent) {
        this.parent = parent;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isBuilded() {
        return builded;
    }

    void setBuilded(boolean builded) {
        this.builded = builded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public List<TreeItem> getChildren() {
        return children;
    }

    public void setChildren(List<TreeItem> children) {
        this.children = children;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public static String getOpen() {
        return OPEN;
    }

    public static String getClosed() {
        return CLOSED;
    }
}
