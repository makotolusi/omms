package m.w;

import m.w.core.util.Queries;

import org.nutz.castor.Castors;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.lang.Strings;
import org.nutz.mvc.Mvcs;

public abstract class BasicModule {
    private static final String ERR = "_ERR_";
    private static final String INFO = "_INFO_";
    private static final String BR = "<br/>";

    /**
     * 设置错误信息
     * 
     * @param msg
     */
    protected void err(String msg) {
        attr(ERR, msg);
    }

    /**
     * 增加错误信息
     * 
     * @param msg
     */
    protected void addErr(String msg) {
        addAttr(ERR, msg);
    }

    /**
     * 设置提示信息
     * 
     * @param msg
     */
    protected void info(String msg) {
        attr(INFO, msg);
    }

    /**
     * 增加提示信息
     * 
     * @param msg
     */
    protected void addInfo(String msg) {
        addAttr(INFO, msg);
    }

    /**
     * 设置属性
     * 
     * @param key
     * @param value
     */
    protected void attr(String key, Object value) {
        Mvcs.getReq().setAttribute(key, value);
    }

    /**
     * 增加属性
     * 
     * @param key
     * @param value
     */
    protected void addAttr(String key, Object value) {
        Object old = Mvcs.getReq().getAttribute(key);
        if (old != null) {
            Mvcs.getReq().setAttribute(key, (String) old + BR + value);
        } else {
            Mvcs.getReq().setAttribute(key, value);
        }
    }

    /**
     * 获取指定参数
     * 
     * @param name
     * @return
     */
    protected String param(String name) {
        return param(name, null);
    }

    /**
     * 获取指定参数
     * 
     * @param name
     * @param defaultValue
     * @return
     */
    protected String param(String name, String defaultValue) {
        String value = Mvcs.getReq().getParameter(name);
        return value == null ? defaultValue : value;
    }

    /**
     * 获取指定类型的参数
     * 
     * @param clazz
     * @param name
     * @return
     */
    protected <T> T param(Class<T> clazz, String name) {
        String value = Mvcs.getReq().getParameter(name);
        if (!Strings.isBlank(value)) {
            return Castors.me().castTo(value, clazz);
        }
        return null;
    }

    /**
     * 获取分页信息
     * 
     * @return
     */
    protected Pager pager() {
        return Queries.pager();
    }
    
    /**
     * 按规则生成查询条件与排序条件
     * @param clazz        要生成条件的实体类
     * @return
     */
    protected Cnd cnd(Class<?> clazz) {
        return Queries.make(clazz);
    }

    /**
     * 按规则生成查询条件与排序条件
     * @param clazz        要生成条件的实体类
     * @param defaultSort  无排序时默认字段
     * @return
     */
    protected Cnd cnd(Class<?> clazz, String defaultSort) {
        return Queries.make(clazz, defaultSort);
    }

    /**
     * 按规则生成查询条件与排序条件
     * @param clazz        要生成条件的实体类
     * @param defaultSort  无排序时默认字段
     * @param defaultOrder 无排序时默认排序顺序
     * @return
     */
    protected Cnd cnd(Class<?> clazz, String defaultSort, String defaultOrder) {
        return Queries.make(clazz, defaultSort, defaultOrder);
    }
    
    /**
     * 按规则生成查询条件与排序条件
     * @param cnd          初始条件
     * @param limited      是否受初始条件限制
     * @param clazz        要生成条件的实体类
     * @param defaultSort  无排序时默认字段
     * @param defaultOrder 无排序时默认排序顺序
     * @return
     */
    protected Cnd cnd(Cnd cnd, boolean limited, Class<?> clazz, String defaultSort, String defaultOrder) {
        return Queries.make(cnd, limited, clazz, defaultSort, defaultOrder);
    }

}
