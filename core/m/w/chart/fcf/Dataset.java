package m.w.chart.fcf;

import java.util.List;
import java.util.Map;

import m.w.chart.fcf.util.XmlBuilder;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Dataset implements Xmlable {
    private Map<String, Object> data = Maps.newLinkedHashMap();
    private List<Set> sets = Lists.newLinkedList();
    
    public static Dataset born(){
        return new Dataset();
    }
    
    public Dataset addSet(Set set){
        this.sets.add(set);
        return this;
    }
    
    public String toXml(){
        return XmlBuilder.toXml("dataset", data, sets).toString();
    }
    
    public Dataset parentYAxis(String parentYAxis){
        data.put("parentYAxis", parentYAxis);
        return this;
    }

    public Dataset seriesName(String seriesName){
        data.put("seriesName", seriesName);
        return this;
    }

    public Dataset color(String color){
        data.put("color", color);
        return this;
    }

    public Dataset showValues(Boolean showValues){
        data.put("showValues", showValues);
        return this;
    }

    public Dataset alpha(Integer alpha){
        data.put("alpha", alpha);
        return this;
    }

    public Dataset showAreaBorder(Boolean showAreaBorder){
        data.put("showAreaBorder", showAreaBorder);
        return this;
    }

    public Dataset areaBorderThickness(Integer areaBorderThickness){
        data.put("areaBorderThickness", areaBorderThickness);
        return this;
    }

    public Dataset areaBorderColor(String areaBorderColor){
        data.put("areaBorderColor", areaBorderColor);
        return this;
    }

    public Dataset areaAlpha(Integer areaAlpha){
        data.put("areaAlpha", areaAlpha);
        return this;
    }

    public Dataset numberPrefix(String numberPrefix){
        data.put("numberPrefix", numberPrefix);
        return this;
    }

    public Dataset numberSuffix(String numberSuffix){
        data.put("numberSuffix", numberSuffix);
        return this;
    }

    public Dataset showAnchors(Boolean showAnchors){
        data.put("showAnchors", showAnchors);
        return this;
    }

    public Dataset anchorSides(Integer anchorSides){
        data.put("anchorSides", anchorSides);
        return this;
    }

    public Dataset anchorRadius(Number anchorRadius){
        data.put("anchorRadius", anchorRadius);
        return this;
    }

    public Dataset anchorBorderColor(String anchorBorderColor){
        data.put("anchorBorderColor", anchorBorderColor);
        return this;
    }

    public Dataset anchorBorderThickness(Integer anchorBorderThickness){
        data.put("anchorBorderThickness", anchorBorderThickness);
        return this;
    }

    public Dataset anchorBgColor(String anchorBgColor){
        data.put("anchorBgColor", anchorBgColor);
        return this;
    }

    public Dataset anchorBgAlpha(Integer anchorBgAlpha){
        data.put("anchorBgAlpha", anchorBgAlpha);
        return this;
    }

    public Dataset anchorAlpha(Integer anchorAlpha){
        data.put("anchorAlpha", anchorAlpha);
        return this;
    }

    public Dataset lineThickness(Integer lineThickness){
        data.put("lineThickness", lineThickness);
        return this;
    }
}
