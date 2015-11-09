package m.w.frs.mgserver.service.snapup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import m.w.frs.mgserver.domain.Order;
import m.w.frs.mgserver.domain.OrderDetail;
import m.w.frs.mgserver.service.ActivityProductDataService;
import m.w.sys.util.RedisManager;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import redis.clients.jedis.Jedis;

/**
 * 1 支付验证 snapUpOrder 返回确认列表 2 支付确认 3.改变订单明细状态
 * 
 * @author lusi
 * 
 */
@IocBean(name = "activitySnappingUpService")
public class ActivitySnappingUpService {

	private static final Log log = Logs.getLog(ActivitySnappingUpService.class);

	private static final int ORDER_EXPIRE = 1800;

	@Inject
	private OrderService orderService;
	
	@Inject
	private OrderDetailService orderDetailService;

	@Inject
	private ActivityProductDataService activityProductDataService;

	/**
	 * 取得用户订单 返回订单明细
	 * 
	 * @param orders
	 */
	public Order snapUpOrder(Order order) {
		List<OrderDetail> orderDetails = order.getOrderDetails();
		String orderCode = order.createOrderCode();
		List<OrderDetail> results = new ArrayList();
		for (Iterator<OrderDetail> iterator = orderDetails.iterator(); iterator
				.hasNext();) {
			OrderDetail orderDetail = (OrderDetail) iterator.next();
			orderDetail.setOrderCode(orderCode);
			// 1.活动是否存在
			if (!existActivity(orderDetail)) {
				orderDetail.setCode(OrderDetail.CODE.NONE_ACTIVITY);
			} else
			// 2.产品是否存在
			if (!existProduct(orderDetail)) {
				orderDetail.setCode(OrderDetail.CODE.NONE_PRODUCT);
			} else {
				// 1返回抢购结果
				orderDetail = this.snapUpOne(orderDetail);
				// 2 记录有效订单 缓存超时时间为ORDER_EXPIRE
				this.saveOrderDetail(orderDetail);
			}
			// 3.只返回不正确的
			if (orderDetail.getCode() != OrderDetail.CODE.SUCCESS)
				results.add(orderDetail);
		}
		order.setOrderDetails(results);
		return order;
	}

	/**
	 * 单个订单抢购
	 * 
	 * @param order
	 * @return
	 */
	private synchronized OrderDetail snapUpOne(OrderDetail orderDetail) {
		Jedis jedis = null;
		try {
			jedis = RedisManager.getJedis();// cache
			log.info("stock key " + orderDetail.getStockKey());
			String sto = jedis.hget(orderDetail.getStockKey(),
					OrderDetail.STOCK);
			if (StringUtils.isEmpty(sto)) {
				orderDetail.setCode(OrderDetail.CODE.STOCK_NULL);
				return orderDetail;
			} else {
				Integer stockOrigin = Integer.parseInt(sto);// 库存
				log.info(Thread.currentThread().getName() + " 库存  "
						+ stockOrigin + "抢购数量 " + orderDetail.getCount());
				Integer stock = stockOrigin - orderDetail.getCount();
				log.info(Thread.currentThread().getName() + " 差值  " + stock);
				if (stock < 0) {
					log.info(Thread.currentThread().getName() + " key "
							+ orderDetail.getOrderDetailKey() + " 库存不足  ");
					// jedis.incr(Consts.REDIS_ORDER + "notenough");
					orderDetail.setCode(OrderDetail.CODE.STOCK_NOT_ENOUGH);
					orderDetail.setStock(stockOrigin);
					return orderDetail;
				} else {
					jedis.hset(orderDetail.getStockKey(), OrderDetail.STOCK,
							stock + "");
					log.info(Thread.currentThread().getName() + " key "
							+ orderDetail.getOrderDetailKey()
							+ " 该商品抢购成功 库存还剩 " + stock);
					orderDetail.setCode(OrderDetail.CODE.SUCCESS);
					orderDetail.setStock(stockOrigin);
					return orderDetail;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			orderDetail.setCode(OrderDetail.CODE.EXCEPTION);
			return orderDetail;
		} finally {
			RedisManager.returnJedis(jedis);
		}
	}

	/**
	 * 确认支付的明细 持久化内存 其余未确认项 过期
	 * 
	 * @param orders
	 * @param token
	 * @return
	 */
	public void confirmSnapupResult(Order order) {
		for (Iterator iterator = order.getOrderDetails().iterator(); iterator
				.hasNext();) {
			OrderDetail orderDetail = (OrderDetail) iterator.next();
			RedisManager.persist(orderDetail.getOrderDetailKey());// 取消订单过期时间
		}
	}

	/**
	 * 成功支付回调 更新订单状态 已支付
	 * 
	 * @param snapUp
	 */
	public boolean sanpupSuccess(String orderCode) {
		// 1.取得订单信息从mysql
		Order order = orderService.fetch(Cnd.where("orderCode", "=", orderCode));
		if(order==null){
			log.info("update mysql order info status fail! order is not exists " + orderCode);
			return false;
		}
		order.setStatus(Order.Status.PAID);	
		orderService.update(order);
		log.info("update mysql order info status {paid}" + orderCode);
		log.info("get orderdetails" );
		List<OrderDetail> orderDetails= orderDetailService.dao().query(OrderDetail.class, Cnd.where("orderId", "=", order.getId()));
		// 2 更新myslq库存
		for (Iterator iterator = orderDetails.iterator(); iterator
				.hasNext();) {
			OrderDetail orderDetail = (OrderDetail) iterator.next();
			String stock = RedisManager.hget(orderDetail.getStockKey(),
					OrderDetail.STOCK);
			log.info("redis productcode is " + orderDetail.getProductCode()+" stock is "+orderDetail.getStock());
			if (!StringUtils.isEmpty(stock)) {
				activityProductDataService.updateProductStock(orderDetail.getProductCode(),
						Integer.parseInt(stock));
			}

		}
		return true;
	}

	/**
	 * 恢复库存
	 * 
	 * @param detail
	 * @param count
	 */
	public synchronized void add(OrderDetail detail) {
		//恢复redis库存
		String stockNow = RedisManager.hget(detail.getStockKey(),
				OrderDetail.STOCK);
		if (!StringUtils.isEmpty(stockNow)) {
			stockNow = (Integer.parseInt(stockNow) + detail.getCount()) + "";
		}
		RedisManager.hset(detail.getStockKey(), OrderDetail.STOCK, stockNow);
		log.info(" key " + detail.getStockKey() + " 恢复库存  " + stockNow);
	}

	/**
	 * 记录用户有效订单
	 * 
	 * @param snapUp
	 */
	private void saveOrderDetail(OrderDetail detail) {
		if (detail.getCode() == OrderDetail.CODE.SUCCESS) {// 有效订单记录
			log.info("order key " + detail.getOrderDetailKey());
			Map<String, String> hash = new HashMap<>();
			hash.put("count", detail.getCount() + "");
			hash.put("activityId", detail.getActivityId() + "");
			hash.put("productCode", detail.getProductCode());
			RedisManager.hmset(detail.getOrderDetailKey(), hash);
			RedisManager.expire(detail.getOrderDetailKey(), ORDER_EXPIRE);
		}
	}

	/**
	 * 订单的活动是否有效
	 * 
	 * @param snapUp
	 */
	private boolean existActivity(OrderDetail snapUp) {
		log.info("activity key " + snapUp.getActivityKey());
		if (RedisManager.keys(snapUp.getActivityKey() + "*") == null
				|| RedisManager.keys(snapUp.getActivityKey() + "*").isEmpty()) {
			return false;
		} else
			return true;
	}

	/**
	 * 订单的产品是否有效
	 * 
	 * @param snapUp
	 */
	private boolean existProduct(OrderDetail snapUp) {
		log.info("activity_product key " + snapUp.getStockKey());
		return RedisManager.exists(snapUp.getStockKey());
	}

}
