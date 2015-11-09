package m.w.frs.mgserver.service;

import m.w.core.service.WxIdService;
import m.w.sys.domain.CommerceUserAgentRelation;

import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(fields = "dao")
public class AgentRelationService extends
		WxIdService<CommerceUserAgentRelation> {

}
