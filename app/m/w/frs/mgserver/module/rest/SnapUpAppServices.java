package m.w.frs.mgserver.module.rest;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.POST;

import m.w.core.dto.Result;
import m.w.frs.mgserver.domain.Order;
import m.w.frs.mgserver.domain.OrderDetail;
import m.w.frs.mgserver.service.AgentService;
import m.w.frs.mgserver.service.snapup.ActivitySnappingUpService;
import m.w.frs.mgserver.service.snapup.OrderService;
import m.w.sys.service.CommerceUserService;
import m.w.sys.util.MirrorUtils;
import m.w.sys.util.RedisManager;

@At("/ApCommonServices/SnapUpServices/")
@IocBean
public class SnapUpAppServices {

	@Inject
	private ActivitySnappingUpService activitySnappingUpService;

	@Inject
	private AgentService agentService;
	
	@Inject
	private OrderService orderService;

	@Inject
	private CommerceUserService commerceUserService;
	
	private static final Log log = Logs.getLog(SnapUpAppServices.class);

	/** 抢购 */
	@At
	@POST
	// @Filters({ @By(type = AuthFilter.class) })
	public Object snapup(HttpServletRequest request) {
		// 1 得到订单
		String receivedObject = request.getParameter("content");
		log.info(receivedObject);
		Order order = (Order) MirrorUtils.convertJsontoBean(receivedObject,
				Order.class);
//		//获取经销商信息
//		CommerceUser agent=agentService.getAgentByOfflineId(order.getReceiverId());
//		if(agent!=null){
//			order.setSubmitPerson(agent.getPhoneNum()==null?0L:Long.parseLong(agent.getPhoneNum()));
//		}else{
//			order.setSubmitPerson(0L);
//		}
//		if (order == null) {
//			return Result.err("订单解析错误");
//		}
		// orderService.dao().insertWith(order, "orderDetails");
		// 开始抢购 返回结果
		Order result = activitySnappingUpService.snapUpOrder(order);
		Result r = new Result();
		if (result.getOrderDetails().isEmpty()) {
			r.setSuccess(true);
			r.setData(MirrorUtils.convertBeanToJson(result));
			return r;
		} else {
			r.setData(MirrorUtils.convertBeanToJson(result));
			return r;
		}
	}

	/** 确认订单明细 支付 */
	@At
	@POST
	// @Filters({ @By(type = AuthFilter.class) })
	public Result confirm(HttpServletRequest request) {
		try {
			// 1 得到最终确认订单与明细 持久化到mysql
			String receivedObject = request.getParameter("content");
			Order order = (Order) MirrorUtils.convertJsontoBean(receivedObject,
					Order.class);
			orderService.dao().insertWith(order, "orderDetails");
			// 2 持久内存
			activitySnappingUpService.confirmSnapupResult(order);
		} catch (Exception e) {
			return Result.err("确认订单失败");
		}
		return Result.ok();
	}

	/** 取消订单 恢复库存 */
	@At
	@POST
	// @Filters({ @By(type = AuthFilter.class) })
	public Result cancel(HttpServletRequest request) {
		try {
			String receivedObject = request.getParameter("content");
			Order order = (Order) MirrorUtils.convertJsontoBean(receivedObject,
					Order.class);
			if (order == null) {
				return Result.err("订单解析错误");
			}
			order.setStatus(Order.Status.CANCEL);	
			orderService.dao().update(Order.class, Chain.make("status",Order.Status.CANCEL), Cnd.where("orderCode","=",order.getOrderCode()));
			List<OrderDetail> orderDetails = order.getOrderDetails();
			for (Iterator iterator = orderDetails.iterator(); iterator
					.hasNext();) {
				OrderDetail orderDetail = (OrderDetail) iterator.next();
				orderDetail.setOrderCode(order.getOrderCode());
				log.info("order key " + orderDetail.getOrderDetailKey());
				// 是否存在订单
				if (RedisManager.exists(orderDetail.getOrderDetailKey())) {
					// 恢复库存
					activitySnappingUpService.add(orderDetail);
					// 删除内存中的cancel订单
					RedisManager.del(orderDetail.getOrderDetailKey());
				}
			}

		} catch (Exception e) {
			return Result.err("确认订单失败");
		}
		return Result.ok();
	}

	/** 测试回调 */
	@At
	@GET
	// @Filters({ @By(type = AuthFilter.class) })
	public Result aliCallBack(HttpServletRequest request) {
		try {
			String orderCode = request.getParameter("orderCode");
			log.info("ali call back order number" + orderCode);
			boolean suc=activitySnappingUpService.sanpupSuccess(orderCode);
			if(suc){
				return Result.ok();
			}else{
				return Result.err("确认订单失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.err("确认订单失败");
		}
	}
	
	
}
