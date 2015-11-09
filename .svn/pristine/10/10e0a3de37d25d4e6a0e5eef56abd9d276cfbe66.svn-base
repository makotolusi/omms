package m.w.init.impl;

import m.w.App;
import m.w.Const;
import m.w.init.Initializer;

import org.nutz.dao.Dao;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.Mvcs;
import org.nutz.resource.Scans;

public class TableInitializer implements Initializer {
    private static Log log = Logs.get();

    @Override
    public void init() {
        log.info("开始初始化数据库表...");
        Dao dao = Mvcs.getIoc().get(Dao.class);
        for (Class<?> clazz : Scans.me().scanPackage(Const.BASE_PACKAGE)) {
            if (clazz.getAnnotation(Table.class) != null) {
                log.info("初始化数据库表：" + clazz.getName());
                dao.create(clazz, App.isForceCreatTable());
            }
        }
        log.info("数据库表初始化成功！");
    }

    @Override
    public String[] dependence() {
        return new String[]{"NoName"};
    }
}
