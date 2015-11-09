package m.w.frs.mgserver.service;

import m.w.core.service.WxIdService;
import m.w.frs.mgserver.domain.MgTribeInfo;

import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(fields = "dao")
public class MgTribelInfoService extends WxIdService<MgTribeInfo> {

}
