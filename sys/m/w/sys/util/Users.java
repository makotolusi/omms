package m.w.sys.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import m.w.core.dto.tree.Tree;
import m.w.core.util.Daos;
import m.w.sys.domain.User;

import org.apache.shiro.SecurityUtils;
import org.nutz.dao.Dao;
import org.nutz.lang.Lang;

/**
 * 用户操作工具类
 * 
 * @author WenWu
 * 
 */
public abstract class Users {
    private static List<User> users = new ArrayList<User>();
    private static Map<Long, User> map = new ConcurrentHashMap<Long, User>();
    private static Tree tree;
    private static boolean inited = false;

    static{
        init();
    }
    
    private static void init() {
        if (inited) {
            return;
        }
        Dao dao = Daos.dao();
        List<User> list = dao.query(User.class, null);
        for(User user : list){
        	users.add(user);
            map.put(user.getId(), user);
        }
        Collections.sort(users);
//        tree = Tree.toTree(map.values()).open();;
        inited = true;
    }
    
    public synchronized static void reload(){
    	users.clear();
        map.clear();
        inited = false;
        init();
    }
    
    public synchronized static Tree tree() {
        return tree;
    }
	
    /**
     * 获取当前用户
     * 
     * @return 如果获取成功，则返回用户；否则抛出未登录的异常
     */
    public static User user() {
        User user = curUser();
        if(null != user){
            return user;
        }
        throw Lang.makeThrow("用户没有登录");
    }
    
    /**
     * 获取当前Session中的用户信息
     * @return 如果用户没登录则返回null
     */
    private static User curUser(){
        Object obj = SecurityUtils.getSubject().getPrincipal();
        if (obj != null && obj instanceof User) {
            return (User) obj;
        }else{
            return null;
        }
    }

    /**
     * 判断当前用户是否管理员
     * 
     * @return
     */
    public static boolean isAdmin() {
        return user().isAdmin();
    }
    
    /** 判断用户是否登录了 */
    public static boolean isLogged(){
        return null != curUser();
    }
}
