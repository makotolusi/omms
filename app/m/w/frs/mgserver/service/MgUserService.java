package m.w.frs.mgserver.service;

import m.w.core.service.WxIdService;
import m.w.frs.mgserver.domain.MgUser;

import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(fields = "dao")
public class MgUserService extends WxIdService<MgUser> {

}
