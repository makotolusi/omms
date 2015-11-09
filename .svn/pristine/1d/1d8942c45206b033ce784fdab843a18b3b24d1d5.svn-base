package m.w.frs.mgserver.service.snapup;

import java.util.Iterator;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;

import m.w.core.dto.Result;
import m.w.core.service.WxIdService;
import m.w.frs.mgserver.domain.ActivityProductData;
import m.w.frs.mgserver.domain.Order;
import m.w.frs.mgserver.domain.OrderDetail;
import m.w.frs.mgserver.domain.UserToken;
import m.w.frs.mgserver.service.ActivityProductDataService;
import m.w.frs.mgserver.service.UserTokenService;
import m.w.sys.domain.CommerceUser;
import m.w.sys.service.CommerceUserService;
import m.w.sys.util.MirrorUtils;

@IocBean(name="orderService",fields = "dao")
public class OrderService extends WxIdService<Order> {
	
	@Inject
	private UserTokenService userTokenService;
	
	@Inject
	private CommerceUserService commerceUserService;
	
	@Inject
	private ActivityProductDataService activityProductDataService;
	/** 得到用户订单列表 */
	@At
	@GET
	public Result getUserOrderByToken(UserToken userTokenTmp) {
		Result r = Result.err();
		try {
			CommerceUser user=commerceUserService.getUserByToken(userTokenTmp);
			if(user==null){
				return r;
			}else{
				List<Order> orders= this.dao().fetchLinks(this.dao().query(Order.class, Cnd.where("receiverId", "=", user.getId())), "orderDetails");
				for (Iterator iterator = orders.iterator(); iterator.hasNext();) {
					Order order = (Order) iterator.next();
					List<OrderDetail> orderDetails=order.getOrderDetails();
					for (Iterator iterator2 = orderDetails.iterator(); iterator2.hasNext();) {
						OrderDetail orderDetail = (OrderDetail) iterator2.next();
						ActivityProductData activityProductData= activityProductDataService.fetch(Cnd.where("activityId", "=", orderDetail.getActivityId()).and("productCode", "=", orderDetail.getProductCode()));
						String url=activityProductDataService.getProductImageUrl(activityProductData.getPicUrl1(), null);
						activityProductData.setPicUrl1(url);
						orderDetail.setActivityProductData(activityProductData);
					}
				}
				r = new Result();
				r.setSuccess(true);
				r.setData(MirrorUtils.convertBeanToJson(orders));
				return r;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.err("确认订单失败");
		}
	}
}
