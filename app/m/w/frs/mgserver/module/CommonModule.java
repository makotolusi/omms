package m.w.frs.mgserver.module;

import java.util.ArrayList;
import java.util.List;

import m.w.BasicModule;
import m.w.core.dto.tree.TreeItem;
import m.w.frs.mgserver.domain.Product;
import m.w.frs.mgserver.service.ProductService;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;

@At("/mg/sso/common")
@IocBean
public class CommonModule extends BasicModule{
	
	@Inject
	private ProductService productService;
	
    /** 显示全部还是所属 */
    public static enum IS_ALL{
        /** 显示所有 */
        A,
        /** 显示本级与下级 */
        M
    }
    
    /** 节点的类型 */
    public static enum NODE_TYPE {
        /** 单位分类 */
        TP,

        /** 分公司 */
        BR,

        /** 项目部 */
        PD,

        /** 工程 */
        PJ,

        /** 部门 */
        DP,

        /** 岗位 */
        PS,

        /** 用户 */
        US
    }

    private static final String PRODUCT_CODE = "productCode";
    private static final String PRICE = "price";
    private static final String TYPE = "type";
    private static final String TYPE_ID = "typeId";
    private static final String COMPANY_ID = "companyId";
    private static final String COMPANY_CODE = "companyCode";
    
    private static final String PROJECT_ID = "projectId";
    private static final String PROJECT_CODE = "projectCode";
    
    private static final String DEPARTMENT_ID = "departmentId";
    private static final String POSITION_ID = "positionId";
    private static final String USER_ID = "userId";
    
    private static final String OPEN = "open";
    private static final String FULL_NAME = "fullName";
    private static final String FULL_PATH = "fullPath";
    
    private static final String POST_ALL = "POST_ALL";
    
    private static final String EMPTY = "";
    
    /**
     * 商品树
     * @param all    是否显示所有
     * @param max    树的最大基本
     * @param states 需要显示的工程的状态
     * <br>
     * 常用的树：<br>
     * 机构树      &lt;ul class=&quot;easyui-tree&quot; data-options=&quot;url:'&lt;c:url value='/ztjs/sso/common/tree/A/BR'/&gt;'&quot;&gt;&lt;/ul&gt;<br>
     * 项目部树  &lt;ul class=&quot;easyui-tree&quot; data-options=&quot;url:'&lt;c:url value='/ztjs/sso/common/tree/A/PD'/&gt;'&quot;&gt;&lt;/ul&gt;<br>
     * 工程树      &lt;ul class=&quot;easyui-tree&quot; data-options=&quot;url:'&lt;c:url value='/ztjs/sso/common/tree/A/PJ'/&gt;'&quot;&gt;&lt;/ul&gt;<br>
     * 岗位树      &lt;ul class=&quot;easyui-tree&quot; data-options=&quot;url:'&lt;c:url value='/ztjs/sso/common/tree/A/PS'/&gt;'&quot;&gt;&lt;/ul&gt;<br>
     * 用户树      &lt;ul class=&quot;easyui-tree&quot; data-options=&quot;url:'&lt;c:url value='/ztjs/sso/common/tree/A/US'/&gt;'&quot;&gt;&lt;/ul&gt;<br>
     * @return
     */
    @At({"/productTree/?/?","/productTree/?/?/?"})
    public Object productTree(IS_ALL all, NODE_TYPE max, String states){
        NODE_TYPE type = null;
        if(!Strings.isBlank(param("id"))){
            type = NODE_TYPE.TP;
        }

        ArrayList<TreeItem> items =  new ArrayList<TreeItem>();
        if(type != null){//展开节点
        	Cnd cnd = null;
        	cnd = (Cnd)Cnd.limit()
        			.and("price", ">=", 0)
        			.and("price", "<=", 1000);
        	List<Product> products = productService.query(cnd, null);
            if(products != null){
	            for(Product p : products){
	                items.add(makeProductNode(p));
	            }
	        }
        }else{//分类
            items = makeTypeNodes(all, max);
        }
        return items;
    }

    private ArrayList<TreeItem> makeTypeNodes(IS_ALL all, NODE_TYPE max) {
        ArrayList<TreeItem> items = new ArrayList<TreeItem>();
        
        TreeItem item = new TreeItem(NODE_TYPE.TP + "-00", "～10元");
        items.add(item);
        
        item = new TreeItem(NODE_TYPE.TP + "-01", "10～20元");
        items.add(item);
        
        item = new TreeItem(NODE_TYPE.TP + "-02", "20～50元");
        items.add(item);
        
        item = new TreeItem(NODE_TYPE.TP + "-03", "50～100元");
        items.add(item);
        
        item = new TreeItem(NODE_TYPE.TP + "-04", "100～200元");
        items.add(item);
        
        item = new TreeItem(NODE_TYPE.TP + "-05", "200～500元");
        items.add(item);
        
        item = new TreeItem(NODE_TYPE.TP + "-06", "500～1000元");
        items.add(item);
        
        item = new TreeItem(NODE_TYPE.TP + "-07", "1000元～");
        items.add(item);
        
        item = new TreeItem(NODE_TYPE.TP + "-08", "化妆品");
        items.add(item);
        
        item = new TreeItem(NODE_TYPE.TP + "-09", "电器");
        items.add(item);
        
        return items;
    }

    private TreeItem makeProductNode(Product p) {
        TreeItem item = new TreeItem(p.getId(), p.getName());
        item.addAttr(PRICE, p.getPrice().toString());
        item.addAttr(PRODUCT_CODE, p.getProductCode());
//        if(type.equals(max)){
            item.setState(OPEN);
//        }else{
//            item.addAttr(POST_ALL, true);
//        }
        return item;
    }
}
