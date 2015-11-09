package m.w.sys.quartz.util;

import java.sql.Connection;
import java.sql.SQLException;

import m.w.sys.util.Webs;

import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.quartz.utils.ConnectionProvider;

public class WxQuartzConnectionProvider implements ConnectionProvider {
    private static final Log log = Logs.get();

    @Override
    public Connection getConnection() throws SQLException {
        return Webs.getDataSource().getConnection();
    }

    @Override
    public void shutdown() throws SQLException {
        log.debug("shutdown");
    }

}
