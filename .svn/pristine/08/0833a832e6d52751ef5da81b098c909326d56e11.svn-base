package m.w.init;

import java.util.ArrayList;
import java.util.List;

import m.w.Const;
import m.w.core.util.Clazzs;

import org.nutz.lang.Mirror;
import org.nutz.log.Log;
import org.nutz.log.Logs;

public abstract class Initializers {
    private static Log log = Logs.get();

    /**
     * 执行所有初始化工作
     */
    public static void doInit() {
        List<String> done = new ArrayList<String>();
        List<String> err = new ArrayList<String>();
        List<String> all = new ArrayList<String>();
        List<Class<?>> inits = Clazzs.findImplements(Const.BASE_PACKAGE, Initializer.class);
        for (Class<?> clazz : inits) {
            all.add(clazz.getName());
        }
        boolean ok = true;
        while (ok) {
            ok = false;
            for (Class<?> clazz : inits) {
                if (!done.contains(clazz.getName())
                    && !err.contains(clazz.getName())
                    && canInit(all, done, clazz)) {
                    log.debug("开始执行初始化器：" + clazz.getName());
                    try {
                        Initializer initializer = (Initializer) Mirror.me(clazz).born();
                        initializer.init();
                        log.debug("初始化器：" + clazz.getName() + "执行成功！");
                        done.add(clazz.getName());
                        ok = true;
                    }
                    catch (Exception e) {
                        err.add(clazz.getName());
                        log.debug("初始化器：" + clazz.getName() + "执行失败！");
                    }
                }
            }
        }
    }

    /**
     * 判断当前是否可初始化某项工作
     * 
     * @param done
     * @param initializer
     * @return
     */
    private static boolean canInit(List<String> all, List<String> done, Class<?> clazz) {
        Initializer initializer = (Initializer) Mirror.me(clazz).born();
        String[] dependence = initializer.dependence();
        if (dependence == null) {
            return true;
        } else {
            for (String name : dependence) {
                if (!done.contains(name) && all.contains(name)) {
                    return false;
                }
            }
        }
        return true;
    }

}
