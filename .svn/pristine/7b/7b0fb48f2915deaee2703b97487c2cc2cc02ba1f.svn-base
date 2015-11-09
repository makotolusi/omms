package m.w.chart.fcf;

import org.nutz.ioc.Ioc;
import org.nutz.mvc.View;
import org.nutz.mvc.ViewMaker;

public class FusionChartsViewMaker implements ViewMaker {
    @Override
    public View make(Ioc ioc, String type, String value) {
        if ("fcf".equalsIgnoreCase(type)) {
            return new FusionChartsFreeView();
        }
        return null;
    }
}
