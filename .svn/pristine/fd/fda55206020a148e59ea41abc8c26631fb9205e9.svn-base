package m.w.chart.fcf.ms;

import java.util.List;

import m.w.chart.fcf.Categories;
import m.w.chart.fcf.Chartable;
import m.w.chart.fcf.Dataset;
import m.w.chart.fcf.ss.SingleSeriesChart;
import m.w.chart.fcf.util.XmlBuilder;

@SuppressWarnings("unchecked")
public abstract class MultiSeriesChart<T extends Chartable> extends SingleSeriesChart<T> {
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
    
    public T animation(Boolean animation){
        attr.put("animation", animation);
        return (T)this;
    }

    public T canvasBgColor(String canvasBgColor){
        attr.put("canvasBgColor", canvasBgColor);
        return (T)this;
    }

    public T divLineAlpha(Integer divLineAlpha){
        attr.put("divLineAlpha", divLineAlpha);
        return (T)this;
    }

    public T divLineColor(String divLineColor){
        attr.put("divLineColor", divLineColor);
        return (T)this;
    }

    public T divLineDecimalPrecision(Integer divLineDecimalPrecision){
        attr.put("divLineDecimalPrecision", divLineDecimalPrecision);
        return (T)this;
    }

    public T divLineThickness(Integer divLineThickness){
        attr.put("divLineThickness", divLineThickness);
        return (T)this;
    }

    public T limitsDecimalPrecision(Integer limitsDecimalPrecision){
        attr.put("limitsDecimalPrecision", limitsDecimalPrecision);
        return (T)this;
    }

    public T numDivLines(Integer numDivLines){
        attr.put("numDivLines", numDivLines);
        return (T)this;
    }

    public T outCnvBaseFont(String outCnvBaseFont){
        attr.put("outCnvBaseFont", outCnvBaseFont);
        return (T)this;
    }

    public T outCnvBaseFontColor(String outCnvBaseFontColor){
        attr.put("outCnvBaseFontColor", outCnvBaseFontColor);
        return (T)this;
    }

    public T outCnvBaseFontSize(Integer outCnvBaseFontSize){
        attr.put("outCnvBaseFontSize", outCnvBaseFontSize);
        return (T)this;
    }

    public T showDivLineValue(Boolean showDivLineValue){
        attr.put("showDivLineValue", showDivLineValue);
        return (T)this;
    }

    public T showLegend(Boolean showLegend){
        attr.put("showLegend", showLegend);
        return (T)this;
    }

    public T showLimits(Boolean showLimits){
        attr.put("showLimits", showLimits);
        return (T)this;
    }

    public T showValues(Boolean showValues){
        attr.put("showValues", showValues);
        return (T)this;
    }

    public T xAxisName(String xAxisName){
        attr.put("xAxisName", xAxisName);
        return (T)this;
    }

    public T zeroPlaneAlpha(Integer zeroPlaneAlpha){
        attr.put("zeroPlaneAlpha", zeroPlaneAlpha);
        return (T)this;
    }

    public T zeroPlaneColor(String zeroPlaneColor){
        attr.put("zeroPlaneColor", zeroPlaneColor);
        return (T)this;
    }
}
