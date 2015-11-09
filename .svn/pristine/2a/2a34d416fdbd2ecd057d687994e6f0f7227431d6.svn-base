package m.w.sys.util;

import m.w.core.util.Daos;
import m.w.sys.domain.AuthAdmin;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;

public class PositionNameHelper {

	public static void updatePositionName(Long positionId, String positionName){
		Dao dao = Daos.dao();
		dao.update(AuthAdmin.class, Chain.make("positionName", positionName), Cnd.where("positionId", "=", positionId));
//		dao.update(AuthFinancial.class, Chain.make("positionName", positionName), Cnd.where("positionId", "=", positionId));
//		dao.update(AuthTreeNode.class, Chain.make("positionName", positionName), Cnd.where("positionId", "=", positionId));
	}
}
