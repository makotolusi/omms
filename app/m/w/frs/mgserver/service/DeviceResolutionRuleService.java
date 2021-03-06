package m.w.frs.mgserver.service;

import m.w.App;
import m.w.core.service.WxIdService;
import m.w.frs.mgserver.domain.DeviceResolutionRule;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(fields = "dao")
public class DeviceResolutionRuleService extends
		WxIdService<DeviceResolutionRule> {

	public DeviceResolutionRule calculateAndInsert(String resolution) {
		DeviceResolutionRule rule = this.calculateResolution(resolution);
		return this.insert(rule);
	}

	public DeviceResolutionRule getByResolution(String resolution) {
		return this.fetch(Cnd.where("resolution", "=", resolution));
	}

	public DeviceResolutionRule getDefaultResolution() {
		DeviceResolutionRule rule = new DeviceResolutionRule();
		rule.setResolution("720*1280");
		rule.setRule1("400*400");
		rule.setRule2("300*300");
		rule.setRule3("100*100");
		return rule;
	}

	public DeviceResolutionRule calculateResolution(String resolution) {
		if(StringUtils.isEmpty(resolution))
			return this.getDefaultResolution();
		DeviceResolutionRule newRule = new DeviceResolutionRule();
		newRule.setResolution(resolution);
		String resolutionRange=App.getResolutionRange();
		Integer lowRes=Integer.parseInt(resolutionRange.split(":")[0]);
		Integer highRes=Integer.parseInt(resolutionRange.split(":")[1]);
		Integer deviceRes=Integer.parseInt(resolution.split("\\*")[1]);
		if(deviceRes<=lowRes){
			String picLowRes=App.getPicLowResolution();
			newRule.setRule1(picLowRes.split(":")[2]);
			newRule.setRule2(picLowRes.split(":")[1]);
			newRule.setRule3(picLowRes.split(":")[0]);
		}
		if(deviceRes>lowRes&&deviceRes<=highRes){
			String picMidRes=App.getPicMidResolution();
			newRule.setRule1(picMidRes.split(":")[2]);
			newRule.setRule2(picMidRes.split(":")[1]);
			newRule.setRule3(picMidRes.split(":")[0]);
		}
		if(deviceRes>highRes){
			String picHighRes=App.getPicHighResolution();
			newRule.setRule1(picHighRes.split(":")[2]);
			newRule.setRule2(picHighRes.split(":")[1]);
			newRule.setRule3(picHighRes.split(":")[0]);
		}
		return newRule;
	}


}
