package m.w.chart.fcf;

import java.util.Map;

import m.w.chart.fcf.util.XmlBuilder;

import com.google.common.collect.Maps;

public class Set implements Xmlable{
    private Map<String, Object> data = Maps.newLinkedHashMap();
    
    public static Set born(){
        Set self = new Set();
        return self;
    }
    
    public String toXml(){
        return XmlBuilder.toSimpleXml("set", data).toString();
    }
    
    public Set name(String name){
        data.put("name", name);
        return this;
    }

    public Set value(Number value){
        data.put("value", value);
        return this;
    }

    public Set color(String color){
        data.put("color", color);
        return this;
    }

    public Set hoverText(String hoverText){
        data.put("hoverText", hoverText);
        return this;
    }

    public Set link(String link){
        data.put("link", link);
        return this;
    }

    public Set alpha(Integer alpha){
        data.put("alpha", alpha);
        return this;
    }

    public Set isSliced(Boolean isSliced){
        data.put("isSliced", isSliced);
        return this;
    }

    public Set showName(Boolean showName){
        data.put("showName", showName);
        return this;
    }
}
