package m.w.mg.sso.util;

import java.util.ArrayList;
import java.util.List;

import m.w.core.dto.tree.TreeItem;
import m.w.core.util.Daos;
import m.w.sys.domain.AuthTreeNode;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;


public class Auths {
	public static List<TreeItem> getTreeNodes(Long positionId, AuthTreeNode.AUTH_TYPE type){
		List<TreeItem> nodes = new ArrayList<TreeItem>();
		List<AuthTreeNode> ts = dao().query(AuthTreeNode.class, Cnd.where("positionId", "=", positionId).and("authType", "=", type.toString()));
		if(ts != null && ts.size() > 0){
			for(AuthTreeNode t : ts){
				nodes.add(new TreeItem(t.getNodeId(), getNodeName(t.getNodeId())));
			}
		}
		return nodes;
	}
	
	public static String getNodeName(String nodeId){
		TreeItem node = getNode(nodeId);
		if(node != null){
			return node.getText();
		}
		return "";
	}
	
	public static TreeItem getNode(String nodeId){

		return null;
	}
	
	private static Dao dao(){
		return Daos.dao();
	}
}
