package m.w.core.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class DbUtils {
	protected static Log logger = LogFactory.getLog(DbUtils.class);

	/**
	 * 获取数据库链接
	 * 
	 * @param driver
	 *            驱动类名
	 * @param url
	 *            数据库链接URL
	 * @param user
	 *            用户名
	 * @param pwd
	 *            密码
	 * @return 如果成功返回数据库链接，否则返回null.
	 */
	public static Connection getConnection(String driver, String url,
			String user, String pwd) {
		Connection conn = null;
		try {
			Class.forName(driver);
			DriverManager.setLoginTimeout(40);// 40秒连不上数据库则放弃
			conn = DriverManager.getConnection(url, user, pwd);
			conn.setAutoCommit(false);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			conn = null;
		}
		return conn;
	}

	public static Connection getConnection() {
		DataSource dataSource = Daos.getDataSource();
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return null;
	}

	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	public static void close(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
}
