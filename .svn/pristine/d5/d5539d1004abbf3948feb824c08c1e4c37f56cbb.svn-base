package m.w.core.dto.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Lang;

public class Tree implements Serializable {
    private static final long serialVersionUID = 2781162298272715466L;
    private static final String SEPARATOR = "/";
    private static final String EMPTY = "";

    private List<TreeItem> roots = new ArrayList<TreeItem>();
    private Map<String, TreeItem> map = new HashMap<String, TreeItem>();

    private boolean builded = false;
    private JsonFormat format = null;
    
    /**
     * 获取树的根节点
     * @param tree
     * @return
     */
    public static List<TreeItem> roots(Tree tree){
    	return Collections.unmodifiableList(tree.roots);
    }

    public static Tree tree() {
        return new Tree();
    }

    public static Tree tree(JsonFormat format) {
        return new Tree(format);
    }

    public static Tree tree(Collection<TreeItem> items) {
        return new Tree(items);
    }

    public static Tree tree(Collection<TreeItem> items, JsonFormat format) {
        return new Tree(items, format);
    }

    public static Tree toTree(Collection<? extends Treeable> items) {
        return toTree(items, null);
    }

    public static Tree toTree(Collection<? extends Treeable> items, JsonFormat format) {
        Tree tree = new Tree(format);
        if(items != null){
            for (Treeable item : items) {
                tree.addItem(item.toTreeItem());
            }
        }
        return tree;
    }

    public Tree() {
        this(null, null);
    }

    public Tree(JsonFormat format) {
        this(null, format);
    }

    public Tree(Collection<TreeItem> items) {
        this(items, null);
    }

    public Tree(Collection<TreeItem> items, JsonFormat format) {
        this.format = format;
        if(items != null){
            for (TreeItem item : items) {
                addItem(item);
            }
        }
    }

    /**
     * 转换为json串
     * 
     * @return
     */
    public synchronized String toJson() {
        build();
        return Json.toJson(roots, format);
    }

    /**
     * 向树增加一批节点
     * 
     * @param item
     * @return
     */
    public synchronized Tree addItems(Collection<TreeItem> items) {
        if(items != null){
            for (TreeItem item : items) {
                addItem(item);
            }
        }
        return this;
    }
    
    /**
     * 向树增加一批节点
     * 
     * @param item
     * @return
     */
    public synchronized Tree addTreeables(Collection<? extends Treeable> items) {
        if(items != null){
            for (Treeable item : items) {
                addItem(item.toTreeItem());
            }
        }
        return this;
    }

    /**
     * 向树增加一个节点
     * 
     * @param item
     * @return
     */
    public synchronized Tree addItem(TreeItem item) {
        if (item != null && !map.values().contains(item)) {
            map.put(item.getId(), item);
            builded = false;
        }
        return this;
    }

    /**
     * 通过id删除某个节点
     * 
     * @param id
     * @return
     */
    public synchronized Tree removeItem(String id) {
        if(id != null){
            TreeItem item = map.get(id);
            if (item != null) {
                removeItem(item);
            }
        }
        return this;
    }
    
    /**
     * 删除一批节点
     * @param ids
     * @return
     */
    public synchronized Tree removeItem(String[] ids){
        if(ids != null){
            for(String id : ids){
                removeItem(id);
            }
        }
        return this;
    }
    
    /**
     * 删除一批节点
     * @param ids
     * @return
     */
    public synchronized Tree removeItem(Collection<String> ids){
        if(ids != null){
            for(String id : ids){
                removeItem(id);
            }
        }
        return this;
    }

    /**
     * 删除某个节点
     * 
     * @param item
     * @return
     */
    public synchronized Tree removeItem(TreeItem item) {
        if (item != null && map.values().contains(item)) {
            map.remove(item.getId());
            if (roots.contains(item)) {
                roots.remove(item);
            }
        }
        return this;
    }

    /**
     * 打开所有树节点
     * 
     * @return
     */
    public synchronized Tree open() {
        build();
        for (TreeItem item : map.values()) {
            item.open();
        }
        return this;
    }

    /**
     * 将指定ID的树节点打开
     * 
     * @param ids
     * @return
     */
    public synchronized Tree open(Collection<String> ids) {
        if(ids != null){
            build();
            for (String id : ids) {
                TreeItem item = map.get(id);
                if (item != null) {
                    item.open();
                }
            }
        }
        return this;
    }

    /**
     * 打开到指定节点
     * 
     * @param id
     * @return
     */
    public synchronized Tree openTo(String id) {
        if(id != null){
            build();
            TreeItem item = map.get(id);
            while (item.getParent() != null) {
                item = item.getParent();
                item.open();
            }
        }
        return this;
    }

    /**
     * 关闭所有树节点
     * 
     * @return
     */
    public synchronized Tree close() {
        build();
        for (TreeItem item : map.values()) {
            item.close();
        }
        return this;
    }

    /**
     * 将指定ID的树节点关闭
     * 
     * @param ids
     * @return
     */
    public synchronized Tree close(Collection<String> ids) {
        if(ids != null){
            build();
            for (String id : ids) {
                TreeItem item = map.get(id);
                if (item != null) {
                    item.close();
                }
            }
        }
        return this;
    }

    /**
     * 选中所有树节点
     * 
     * @return
     */
    public synchronized Tree check() {
        build();
        for (TreeItem item : map.values()) {
            item.check();
        }
        return this;
    }

    /**
     * 将指定ID的树节点选中
     * 
     * @param ids
     * @return
     */
    public synchronized Tree check(Collection<String> ids) {
        if(ids != null){
            build();
            for (String id : ids) {
                TreeItem item = map.get(id);
                if (item != null) {
                    item.check();
                }
            }
        }
        return this;
    }

    /**
     * 选中到某节点
     * 
     * @param id
     * @return
     */
    public synchronized Tree checkTo(String id) {
        if(id != null){
            build();
            TreeItem item = map.get(id);
            while (item.getParent() != null) {
                item = item.getParent();
                item.check();
            }
        }
        return this;
    }

    /**
     * 不选中所有树节点
     * 
     * @return
     */
    public synchronized Tree uncheck() {
        build();
        for (TreeItem item : map.values()) {
            item.uncheck();
        }
        return this;
    }

    /**
     * 将指定ID的树节点不选中
     * 
     * @param ids
     * @return
     */
    public synchronized Tree uncheck(Collection<String> ids) {
        build();
        for (String id : ids) {
            TreeItem item = map.get(id);
            if (item != null) {
                item.uncheck();
            }
        }
        return this;
    }

    /**
     * 获取指定节点的所有父节点（不含自身，顶级节点在前面）
     * 
     * @param id
     * @return
     */
    public synchronized List<TreeItem> parents(String id) {
        List<TreeItem> ps = new ArrayList<TreeItem>();
        if(id == null){
            return ps;
        }
        
        build();
        TreeItem item = map.get(id);
        if (item != null) {
            while (item.getParent() != null) {
                item = item.getParent();
                ps.add(0, item);
            }
        }
        return ps;
    }

    /**
     * 获取指定节点的所有父节点显示名称（不含自身，顶级节点在前面）
     * 
     * @param id
     * @return
     */
    public synchronized String parentTexts(String id) {
        return parentTexts(id, SEPARATOR);
    }

    /**
     * 获取指定节点的所有父节点显示名称（不含自身，顶级节点在前面）
     * 
     * @param id
     * @param separator
     *            名称间的分隔符
     * @return
     */
    public synchronized String parentTexts(String id, String separator) {
        if(id == null){
            return EMPTY;
        }
        
        List<String> ps = new ArrayList<String>();
        build();
        TreeItem item = map.get(id);
        if (item != null) {
            while (item.getParent() != null) {
                item = item.getParent();
                ps.add(0, item.getText());
            }
        }
        return Lang.concat(separator, ps).toString();
    }

    /**
     * 获取指定节点的所有父节点（含自身，顶级节点在前面）
     * 
     * @param id
     * @return
     */
    public synchronized List<TreeItem> path(String id) {
        List<TreeItem> ps = new ArrayList<TreeItem>();
        if(id == null){
            return ps;
        }

        build();
        TreeItem item = map.get(id);
        if (item != null) {
            ps.add(item);
            while (item.getParent() != null) {
                item = item.getParent();
                ps.add(0, item);
            }
        }
        return ps;
    }

    /**
     * 获取指定节点的所有父节点显示名称（含自身，顶级节点在前面）
     * 
     * @param id
     * @return
     */
    public synchronized String pathText(String id) {
        return pathText(id, SEPARATOR);
    }

    /**
     * 获取指定节点的所有父节点显示名称（含自身，顶级节点在前面）
     * 
     * @param id
     * @param separator
     * @return
     */
    public synchronized String pathText(String id, String separator) {
        if(id == null){
            return EMPTY;
        }
        List<String> ps = new ArrayList<String>();
        build();
        TreeItem item = map.get(id);
        if (item != null) {
            ps.add(item.getText());
            while (item.getParent() != null) {
                item = item.getParent();
                ps.add(0, item.getText());
            }
        }
        return Lang.concat(separator, ps).toString();
    }

    public Tree build() {
        if (builded != true) {
            buildTree();
            builded = true;
        }
        return this;
    }

    private void buildTree() {
        roots.clear();
        //建树
        for (TreeItem item : map.values()) {
            if(item.getPid() == null){
                roots.add(item);
            }else{
                TreeItem parent = map.get(item.getPid());
                if (parent != null) {
                    item.setParent(parent);
                    parent.addChild(item);
                }else{//非完整数据的动态建树
                    roots.add(item);
                }
            }
        }
        //排序
        Collections.sort(roots);
        for (TreeItem item : map.values()) {
            if(item.hasChildren()){
                Collections.sort(item.getChildren());
            }
            item.setBuilded(true);
        }
    }
}
