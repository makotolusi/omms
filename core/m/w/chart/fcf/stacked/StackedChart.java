package m.w.chart.fcf.stacked;

import java.util.List;

import m.w.chart.fcf.Categories;
import m.w.chart.fcf.Chartable;
import m.w.chart.fcf.Dataset;
import m.w.chart.fcf.ms.MultiSeriesChart;
import m.w.chart.fcf.util.XmlBuilder;

@SuppressWarnings("unchecked")
public abstract class StackedChart<T extends Chartable> extends MultiSeriesChart<T> {
    protected List<Dataset> datasets;
    protected Categories categories;

    public T datasets(List<Dataset> datasets) {
        this.datasets = datasets;
        return (T)this;
    }
    
    public T categories(Categories categories) {
        this.categories = categories;
        return (T)this;
    }
    
    public String toXml() {
        StringBuilder sb = XmlBuilder.beginXml("graph", attr);
        XmlBuilder.appendXml(categories, sb);
        XmlBuilder.appendXml(datasets, sb);
        XmlBuilder.appendXml(trendlines, sb);
        XmlBuilder.endXml("graph", sb);
        return sb.toString();
    }
    
    public T yAxisMaxValue(Number yAxisMaxValue){
        attr.put("yAxisMaxValue", yAxisMaxValue);
        return (T)this;
    }

    public T yAxisMinValue(Number yAxisMinValue){
        attr.put("yAxisMinValue", yAxisMinValue);
        return (T)this;
    }

    public T yAxisName(String yAxisName){
        attr.put("yAxisName", yAxisName);
        return (T)this;
    }

}
