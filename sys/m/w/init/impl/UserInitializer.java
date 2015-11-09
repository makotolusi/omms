package m.w.init.impl;

import m.w.init.Initializer;

import org.nutz.log.Log;
import org.nutz.log.Logs;

public class UserInitializer implements Initializer {
	private static Log log = Logs.get();

	@Override
	public void init() {
		log.debug("开始初始化用户...");
		// Dao dao = Mvcs.getIoc().get(Dao.class);
		// int count = dao.count(User.class);
		// if (count == 0) {
		// Org org = dao.fetch(Org.class, Cnd.where("id", ">",
		// -1).getOrderBy().asc("id"));
		// if (org != null) {
		// User admin = new User();
		// admin.setUsername("管理员");
		// admin.setPassword("1");
		// admin.setOrgId(org.getId());
		// admin.setAdmin(true);
		// admin.setEnable(true);
		// dao.insert(admin);
		// }
		// }
		log.debug("用户初始化成功！");
	}

	@Override
	public String[] dependence() {
		// TODO Auto-generated method stub
		return null;
	}

}
