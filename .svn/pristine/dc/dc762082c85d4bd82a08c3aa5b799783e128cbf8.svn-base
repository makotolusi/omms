package m.w.chart.fcf;

import java.util.Map;

import m.w.chart.fcf.util.XmlBuilder;

import com.google.common.collect.Maps;

public class Line implements Xmlable{
    private Map<String, Object> data = Maps.newLinkedHashMap();
    
    public static Line born(){
        Line self = new Line();
        return self;
    }
    
    public String toXml(){
        return XmlBuilder.toSimpleXml("line", data).toString();
    }
    
    public Line startValue(Number startValue){
        data.put("startValue", startValue);
        return this;
    }

    public Line endValue(Number endValue){
        data.put("endValue", endValue);
        return this;
    }

    public Line color(String color){
        data.put("color", color);
        return this;
    }

    public Line displayValue(String displayValue){
        data.put("displayValue", displayValue);
        return this;
    }

    public Line thickness(Integer thickness){
        data.put("thickness", thickness);
        return this;
    }

    public Line isTrendZone(Boolean isTrendZone){
        data.put("isTrendZone", isTrendZone);
        return this;
    }

    public Line showOnTop(Boolean showOnTop){
        data.put("showOnTop", showOnTop);
        return this;
    }

    public Line alpha(Integer alpha){
        data.put("alpha", alpha);
        return this;
    }
}
