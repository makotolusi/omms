package m.w.core.util;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.nutz.log.Log;
import org.nutz.log.Logs;

public abstract class Dbs {

    private static final Log log = Logs.get();
    
    public static Long count(String sql){
        return scalar(1, Long.class, sql);
    }
    
    public static <T> T scalar(String columnName, Class<T> clazz, String sql){
        return scalar(columnName, clazz, sql, getDataSource());
    }
    
    public static <T> T scalar(int columnIndex, Class<T> clazz, String sql){
        return scalar(columnIndex, clazz, sql, getDataSource());
    }
    
    public static <T> T fetch(Class<T> clazz, String sql){
        return fetch(clazz, sql, getDataSource());
    }
    
    public static <T> List<T> list(Class<T> clazz, String sql){
        return list(clazz, sql, getDataSource());
    }
    
    private static <T> T scalar(String columnName, Class<T> clazz, String sql, DataSource ds){
        QueryRunner q = getQueryRunner(ds);
        try {
            return q.query(sql, new ScalarHandler<T>(columnName));
        }
        catch (SQLException e) {
            log.error(e);
        }
        return null;
    }
    
    private static <T> T scalar(int columnIndex, Class<T> clazz, String sql, DataSource ds){
        QueryRunner q = getQueryRunner(ds);
        try {
            return q.query(sql, new ScalarHandler<T>(columnIndex));
        }
        catch (SQLException e) {
            log.error(e);
        }
        return null;
    }
    
    private static <T> T fetch(Class<T> clazz, String sql, DataSource ds){
        QueryRunner q = getQueryRunner(ds);
        try {
            return q.query(sql, new BeanHandler<T>(clazz));
        }
        catch (SQLException e) {
            log.error(e);
        }
        return null;
    }
   
    private static <T> List<T> list(Class<T> clazz, String sql, DataSource ds){
        QueryRunner q = getQueryRunner(ds);
        try {
            return q.query(sql, new BeanListHandler<T>(clazz));
        }
        catch (SQLException e) {
            log.error(e);
        }
        return null;
    }
    
    private static QueryRunner getQueryRunner(DataSource ds){
        return new QueryRunner(ds);
    }
    
    private static DataSource getDataSource(){
        return Daos.getDataSource();
    }
}
