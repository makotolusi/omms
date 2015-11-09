package m.w;

import static m.w.Const.BASE_PACKAGE;
import m.w.chart.fcf.FusionChartsViewMaker;
import m.w.sys.shiro.ShiroActionFilter;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Encoding;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Localization;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.annotation.Views;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@Ok("json")
@Fail("json")
@Localization("msg")
@SetupBy(AppSetup.class)
@Modules(scanPackage = true)
@Encoding(input = "utf8", output = "utf8")
@IocBy(type = ComboIocProvider.class, args = {"*org.nutz.ioc.loader.json.JsonLoader",
                                              "ioc/",
                                              "*org.nutz.ioc.loader.annotation.AnnotationIocLoader",
                                              BASE_PACKAGE})
//@Filters({@By(type = CheckSession.class, args = {Const.USER_SESSION_KEY, "/login"}), @By(type = ShiroActionFilter.class, args = {"jsp:/login"})})
@Filters({@By(type = ShiroActionFilter.class)})
@Views({FusionChartsViewMaker.class})
public class MainModule {
    @At("/")
    @Ok("->:/index")
    public void index() {

    }

}
