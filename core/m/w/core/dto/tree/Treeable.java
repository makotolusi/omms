package m.w.core.dto.tree;

/**
 * 对象可以转换为树节点
 * @author WenWu
 *
 */
public interface Treeable {
    /**
     * 将本对象转换为一个树节点对象
     * @return
     */
    public TreeItem toTreeItem();
}
