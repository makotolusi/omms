package m.w.frs.mgserver.service.snapup;

import m.w.core.service.WxIdService;
import m.w.frs.mgserver.domain.OrderDetail;

import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(fields = "dao")
public class OrderDetailService extends WxIdService<OrderDetail> {

}
