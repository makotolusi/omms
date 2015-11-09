package m.w.chart.fcf;

import java.util.Map;

import com.google.common.collect.Maps;

@SuppressWarnings("unchecked")
public abstract class Chart<T extends Chartable> {
    protected Map<String, Object> attr = Maps.newLinkedHashMap();
    
    protected Trendlines trendlines;
    
    /** 设置趋势线 */
    public T trendLines(Trendlines trendlines) {
        this.trendlines = trendlines;
        return (T)this;
    }
    
    /** 通用设置属性的方法 */
    public T attr(String key, Object value){
        attr.put(key, value);
        return (T)this;
    }

//    baseFont
//    baseFontColor
//    baseFontSize
//    bgAlpha
//    bgColor
//    bgSWF
//    caption
//    chartBottomMargin
//    chartLeftMargin
//    chartRightMargin
//    chartTopMargin
//    decimalPrecision
//    decimalSeparator
//    formatNumber
//    formatNumberScale
//    hoverCapBgColor
//    hoverCapBorderColor
//    hoverCapSepChar
//    numberPrefix
//    numberSuffix
//    showHoverCap
//    showNames
//    subCaption
//    thousandSeparator

    public T baseFont(String baseFont){
        attr.put("baseFont", baseFont);
        return (T)this;
    }

    public T baseFontColor(String baseFontColor){
        attr.put("baseFontColor", baseFontColor);
        return (T)this;
    }

    public T baseFontSize(Integer baseFontSize){
        attr.put("baseFontSize", baseFontSize);
        return (T)this;
    }

    public T bgAlpha(Integer bgAlpha){
        attr.put("bgAlpha", bgAlpha);
        return (T)this;
    }

    public T bgColor(String bgColor){
        attr.put("bgColor", bgColor);
        return (T)this;
    }

    public T bgSWF(String bgSWF){
        attr.put("bgSWF", bgSWF);
        return (T)this;
    }

    public T chartBottomMargin(Integer chartBottomMargin){
        attr.put("chartBottomMargin", chartBottomMargin);
        return (T)this;
    }

    public T chartLeftMargin(Integer chartLeftMargin){
        attr.put("chartLeftMargin", chartLeftMargin);
        return (T)this;
    }

    public T chartRightMargin(Integer chartRightMargin){
        attr.put("chartRightMargin", chartRightMargin);
        return (T)this;
    }

    public T chartTopMargin(Integer chartTopMargin){
        attr.put("chartTopMargin", chartTopMargin);
        return (T)this;
    }

    public T hoverCapBgColor(String hoverCapBgColor){
        attr.put("hoverCapBgColor", hoverCapBgColor);
        return (T)this;
    }

    public T hoverCapBorderColor(String hoverCapBorderColor){
        attr.put("hoverCapBorderColor", hoverCapBorderColor);
        return (T)this;
    }

    public T hoverCapSepChar(String hoverCapSepChar){
        attr.put("hoverCapSepChar", hoverCapSepChar);
        return (T)this;
    }

    public T showHoverCap(Boolean showHoverCap){
        attr.put("showHoverCap", showHoverCap);
        return (T)this;
    }
}
