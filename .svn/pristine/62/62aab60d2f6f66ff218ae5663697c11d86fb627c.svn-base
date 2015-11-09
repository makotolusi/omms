package m.w.frs.mgserver.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import m.w.core.dto.Result;
import m.w.core.service.WxIdService;
import m.w.frs.mgserver.domain.Reward;
import m.w.sys.util.DateUtils;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

@IocBean(fields = "dao")
public class RewardService extends WxIdService<Reward> {

	public static final int MAX_REWARD = 30;

	public Object sendrUserReward(String receivedObject) {
		Reward reward = null;
		Reward m = null;

		if (!StringUtils.isEmpty(receivedObject)) {
			Calendar cal = Calendar.getInstance();
			reward = Json.fromJson(Reward.class, receivedObject);
			m = this.fetch(Cnd
					.limit()
					.and("telNumber", "=", reward.getTelNumber())
					.and("effectiveData", ">=",
							DateUtils.toString(cal.getTime())));
			if (m != null) {
				// 最大值30元
				if (reward.getCoupon().add(m.getCoupon())
						.compareTo(new BigDecimal(MAX_REWARD)) > 0) {
					m.setCoupon(new BigDecimal(MAX_REWARD));
				} else {
					m.setCoupon(reward.getCoupon().add(m.getCoupon()));
				}

				return this.update(m, "成功", "失败");
			} else {
				Date date = new Date();
				cal.setTime(date);
				cal.add(cal.DATE, 20);
				date = cal.getTime();
				reward.setEffectiveData(date);
				return this.insert(reward, "成功", "失败");
			}

		}
		return Result.err("content is empty");
	}
}
