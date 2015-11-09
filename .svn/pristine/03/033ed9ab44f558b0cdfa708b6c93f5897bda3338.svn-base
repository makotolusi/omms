package m.w.init.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import m.w.App;
import m.w.Const;
import m.w.core.util.Clazzs;
import m.w.init.Initializer;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.nutz.lang.Lang;
import org.nutz.lang.Mirror;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.resource.Scans;

/**
 * 扫描获取Shiro Annotation使用情况的工具
 * 
 * @author WenWu
 * 
 */
public class ShiroAnnotationLister implements Initializer {
    private static Log log = Logs.get();
    
    @Override
    public void init() {
        if (!App.isDev()) {
            return;
        }
        log.debug("\n开始扫描 Shiro Annotation...");

        Set<String> permissions = new TreeSet<String>();
        Set<String> roles = new TreeSet<String>();
        Set<String> permissionsCls = new TreeSet<String>();
        Set<String> rolesCls = new TreeSet<String>();

        for (Class<?> clazz : Scans.me().scanPackage(Const.BASE_PACKAGE)) {
            Mirror<?> mirror = Mirror.me(clazz);
            Method[] methods = mirror.getMethods();
            for (Method method : methods) {
                if (Clazzs.hasAnnotation(method, RequiresPermissions.class)) {
                    String[] value = method.getAnnotation(RequiresPermissions.class).value();
                    add(permissionsCls, clazz, method, value);
                    add(permissions, value);
                }
                if (Clazzs.hasAnnotation(method, RequiresRoles.class)) {
                    String[] value = method.getAnnotation(RequiresRoles.class).value();
                    add(rolesCls, clazz, method, value);
                    add(roles, value);
                }
            }
        }

        log.debug("\n程序中使用到的 Shiro Permission 有：");
        print(permissions);

        log.debug("\n使用 RequiresPermissions 的方法有：");
        print(permissionsCls);

        log.debug("\n程序中使用到的 Shiro Role 有：");
        print(roles);

        log.debug("\n使用 RequiresRoles 的方法有：");
        print(rolesCls);

        log.debug("\n开始扫描 Shiro Annotation 完成！\n");
    }

    private void add(Set<String> set, Class<?> clazz, Method method, String[] value) {
        Class<?>[] ps = method.getParameterTypes();
        List<String> pt = new ArrayList<String>();
        for (Class<?> p : ps) {
            pt.add(p.getSimpleName());
        }
        String params = pt.toString();
        params = params.substring(1, params.length() - 1);
        
        StringBuffer sb = new StringBuffer();
        sb.append(clazz.getName());
        sb.append(".");
        sb.append(method.getName());
        sb.append("(");
        sb.append(params);
        sb.append(")");
        String s = Strings.alignLeft(sb, 80, ' ');
        set.add(s + "\t" + Lang.array2list(value));
    }

    private void add(Set<String> set, String[] value) {
        for (String v : value) {
            set.add(v);
        }
    }

    private void print(Set<String> set) {
        for (String v : set) {
            log.debug(v);
        }
    }

    @Override
    public String[] dependence() {
        return null;
    }
}
