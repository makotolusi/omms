package m.w.frs.mgserver.service;

import m.w.core.service.WxIdService;
import m.w.sys.domain.CommerceUser;
import m.w.sys.domain.CommerceUserAgentConfig;
import m.w.sys.domain.CommerceUserAgentRelation;
import m.w.sys.service.CommerceUserService;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(fields = "dao")
public class AgentService extends WxIdService<CommerceUserAgentConfig> {

	@Inject
	private AgentRelationService agentRelationService;
	
	@Inject
	private CommerceUserService commerceUserService;
	
	/**
	 * 取得所属经销商
	 * @param offlineId
	 * @return
	 */
	public CommerceUser getAgentByOfflineId(Long offlineId){
		CommerceUserAgentRelation relation=agentRelationService.fetch(Cnd.where("offlineId","=",offlineId));
		CommerceUser agent=commerceUserService.fetch(relation.getAgentId());
		return agent;
	}
}
