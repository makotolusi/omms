package m.w.frs.mgserver.module;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import m.w.core.dto.DataGrid;
import m.w.core.dto.Result;
import m.w.frs.mgserver.service.AgentRelationService;
import m.w.frs.mgserver.service.AgentService;
import m.w.sys.domain.CommerceUser;
import m.w.sys.domain.CommerceUser.Role;
import m.w.sys.domain.CommerceUserAgentRelation;
import m.w.sys.service.CommerceUserService;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;

@At("/commerceuser")
@IocBean
public class CommerceUserModule {

	@Inject
	private CommerceUserService commerceUserService;

	@Inject
	private AgentService agentService;

	@Inject
	private AgentRelationService agentRelationService;
	
	/**
	 * list order info
	 * */
	@At({ "/items" })
	public DataGrid items(String phoneNum, Role role,Long agentId) {
		Cnd cnd = Cnd.where("1", "=", "1");
		if (role != null) {
			cnd.and("role", "=", Role.MEMBER.toString());
		}
		if (!StringUtils.isEmpty(phoneNum)) {
			cnd.and("phoneNum", "=", phoneNum);
		}
		if(agentId!=null){
			List<CommerceUserAgentRelation> relation=agentRelationService.query(Cnd.where("agentId","=", agentId), new Pager());
			long[] ids=new long[relation.size()];
			int i=0;
			for (Iterator iterator = relation.iterator(); iterator.hasNext();) {
				CommerceUserAgentRelation commerceUserAgentRelation = (CommerceUserAgentRelation) iterator
						.next();
				ids[i]=commerceUserAgentRelation.getOffLineId();
				i++;
			}
			cnd.where().andIn("id",ids);
		}
		return commerceUserService.datagrid(cnd);
	}

	@At({ "/updateRole" })
	public Result updateRole(Long id, CommerceUser.Role role) {
		CommerceUser user = commerceUserService.fetch(id);
		if (user != null) {
			user.setRole(role);
			commerceUserService.save(user);
			if (role == Role.MEMBER) {
				agentService.clear(Cnd.where("agentId", "=", id));
			}
		}
		return Result.ok();
	}
}
