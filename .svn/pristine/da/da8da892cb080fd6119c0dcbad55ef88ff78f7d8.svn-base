package m.w.frs.mgserver.service.snapup;

import java.sql.Connection;
import java.sql.Statement;

import m.w.frs.mgserver.domain.Order;
import m.w.frs.mgserver.domain.OrderDetail;
import m.w.sys.util.DBConnection;

import org.apache.commons.lang3.StringUtils;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import redis.clients.jedis.JedisPubSub;

public class SnapupOrderExpireSubscribe extends JedisPubSub {

	private static final Log log = Logs
			.getLog(SnapupOrderExpireSubscribe.class);
	ActivitySnappingUpService activitySnappingUpService = new ActivitySnappingUpService();

	// 初始化按表达式的方式订阅时候的处理
	public void onPSubscribe(String pattern, int subscribedChannels) {
		log.info(pattern + "=" + subscribedChannels);
	}

	/**
	 * *=__keyevent@0__:expired=mg:snapup:order:1234561441527660857:12:A00001:1
	 * 取得按表达式的方式订阅的消息后的处理
	 */
	public void onPMessage(String pattern, String channel, String message) {
		try {
			log.info(pattern + "=" + channel + "=" + message + " ");
			if (!StringUtils.isEmpty(message)) {
				if (message.indexOf(":") >= 0) {
					String[] str = message.split(":");
					OrderDetail detail = new OrderDetail();
					detail.setActivityId(Long.parseLong(str[4]));
					detail.setProductCode(str[5]);
					detail.setCount(Integer.parseInt(str[6]));
					String orderCode=str[3];
					activitySnappingUpService.add(detail);
					//更新数据库 订单作废
					this.updateOrderStatus(orderCode);
				}
			}
		} catch (Exception e) {
			log.error("订单过期触发 抢购数量恢复时 异常 " + e.getMessage());
		}

	}

	public void updateOrderStatus(String orderCode){
		 DBConnection dbsource=null;
		 Statement stmt = null;
		 Connection conn =null;
	    try {  
            dbsource = new DBConnection();  
            conn = dbsource.getConnection();  
            stmt = conn.createStatement();
            String sql = "update tbl_order set status ='"+Order.Status.TIME_OUT+"' where orderCode='"+orderCode+"'";
            stmt.executeUpdate(sql);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }finally{
        	dbsource.closeConnection(conn);
        }
	}
}
