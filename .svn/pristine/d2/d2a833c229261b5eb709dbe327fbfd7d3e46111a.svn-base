package m.w.core.util;

import javax.sql.DataSource;

import org.nutz.dao.Dao;
import org.nutz.mvc.Mvcs;

public abstract class Daos {
    /**
     * 获取Dao
     * @return
     */
    public static Dao dao(){
        return Mvcs.getIoc().get(Dao.class, "dao");
    }

    /**
     * 获取数据源
     * @return
     */
    public static DataSource getDataSource() {
        return Mvcs.getIoc().get(DataSource.class, "dataSource");
    }
}
