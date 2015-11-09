package m.w.frs.cms.service;

import m.w.core.service.WxIdService;
import m.w.frs.cms.domain.Dict;

import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(fields = "dao")
public class DictService extends WxIdService<Dict> {

}
