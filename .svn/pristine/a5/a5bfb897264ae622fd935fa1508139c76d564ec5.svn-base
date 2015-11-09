package m.w.chart.fcf;

import java.util.Map;

import m.w.chart.fcf.util.XmlBuilder;

import com.google.common.collect.Maps;

public class Category implements Xmlable{
    private Map<String, Object> data = Maps.newLinkedHashMap();
    
    public static Category born(){
        Category self = new Category();
        return self;
    }
    
    public String toXml(){
        return XmlBuilder.toSimpleXml("category", data).toString();
    }
    
    public Category name(String name){
        data.put("name", name);
        return this;
    }

    public Category hoverText(String hoverText){
        data.put("hoverText", hoverText);
        return this;
    }

    public Category showName(Boolean showName){
        data.put("showName", showName);
        return this;
    }

}
