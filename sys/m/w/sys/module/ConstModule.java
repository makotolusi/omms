package m.w.sys.module;

import m.w.BasicModule;
import m.w.sys.util.Consts;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;

@At("/sys/const")
@IocBean
public class ConstModule extends BasicModule {
    @At
    public void reload(){
        Consts.reload();
    }
    
    @At("/options/?/?")
    public Object options(String clazz, String fild){
        return Consts.options(clazz+"."+fild);
    }
}
