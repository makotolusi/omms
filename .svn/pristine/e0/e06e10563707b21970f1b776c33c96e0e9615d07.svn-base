package m.w.chart.fcf.ss;

import java.util.List;

import m.w.chart.fcf.Chart;
import m.w.chart.fcf.Chartable;
import m.w.chart.fcf.Set;
import m.w.chart.fcf.util.XmlBuilder;

@SuppressWarnings("unchecked")
public class SingleSeriesChart<T extends Chartable> extends Chart<T> {
    protected List<Set> sets;
    
    public T sets(List<Set> sets) {
        this.sets = sets;
        return (T)this;
    }

    public String toXml() {
        StringBuilder sb = XmlBuilder.beginXml("graph", attr);
        XmlBuilder.appendXml(sets, sb);
        XmlBuilder.appendXml(trendlines, sb);
        XmlBuilder.endXml("graph", sb);
        return sb.toString();
    }
    
    public T caption(String caption){
        attr.put("caption", caption);
        return (T)this;
    }
    
    public T decimalPrecision(Integer decimalPrecision){
        attr.put("decimalPrecision", decimalPrecision);
        return (T)this;
    }

    public T decimalSeparator(String decimalSeparator){
        attr.put("decimalSeparator", decimalSeparator);
        return (T)this;
    }

    public T formatNumber(Boolean formatNumber){
        attr.put("formatNumber", formatNumber);
        return (T)this;
    }

    public T formatNumberScale(Boolean formatNumberScale){
        attr.put("formatNumberScale", formatNumberScale);
        return (T)this;
    }
    

    public T numberPrefix(String numberPrefix){
        attr.put("numberPrefix", numberPrefix);
        return (T)this;
    }

    public T numberSuffix(String numberSuffix){
        attr.put("numberSuffix", numberSuffix);
        return (T)this;
    }
    

    public T showNames(Boolean showNames){
        attr.put("showNames", showNames);
        return (T)this;
    }

    public T subCaption(String subCaption){
        attr.put("subCaption", subCaption);
        return (T)this;
    }

    public T thousandSeparator(String thousandSeparator){
        attr.put("thousandSeparator", thousandSeparator);
        return (T)this;
    }
}
