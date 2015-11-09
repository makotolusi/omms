package m.w.init.impl;

import m.w.Const;
import m.w.core.util.Clazzs;
import m.w.init.Initializer;
import m.w.sys.domain.Dictable;
import m.w.sys.util.Dicts;

import org.nutz.lang.Mirror;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * 扫描所有类，找出Dictable类型的类
 * @author WenWu
 *
 */
public class DictableLister implements Initializer{
    private static Log log = Logs.get();
    
    @Override
    public void init() {
        for(Class<?> clazz : Clazzs.findImplements(Const.BASE_PACKAGE, Dictable.class)){
            Dictable dict = (Dictable) Mirror.me(clazz).born();
            Dicts.reg(dict);
            log.debug("注册字典类型" + clazz.getName());
        }
    }

    @Override
    public String[] dependence() {
        return new String[]{TableInitializer.class.getName()};
    }

}
