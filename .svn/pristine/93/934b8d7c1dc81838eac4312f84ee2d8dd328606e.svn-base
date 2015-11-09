package omms;

import static org.junit.Assert.fail;

import java.util.Iterator;

import m.w.core.dto.DataGrid;
import m.w.frs.mgserver.service.snapup.ActivitySnappingUpService;

import org.junit.Test;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.annotation.AnnotationIocLoader;

public class ActivityProductTest {

	@Test
	public void test() {
		Ioc ioc = new NutIoc(new AnnotationIocLoader("m.w.frs.mgserver.service.snapup"));
		String[] str=ioc.getNames();
		for (int i = 0; i < str.length; i++) {
			System.out.println(str[i]);
		}
		System.out.println(str);
		ActivitySnappingUpService activitySnappingUpService = ioc.get(ActivitySnappingUpService.class, "activitySnappingUpService");
//		activitySnappingUpService.lusi();
		fail("Not yet implemented");
	}

}