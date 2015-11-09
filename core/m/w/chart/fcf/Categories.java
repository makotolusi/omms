package m.w.chart.fcf;

import java.util.List;
import java.util.Map;

import m.w.chart.fcf.util.XmlBuilder;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Categories implements Xmlable{
    private Map<String, Object> data = Maps.newLinkedHashMap();
    private List<Category> categories = Lists.newLinkedList();
    
    public static Categories born(){
        Categories self = new Categories();
        return self;
    }
    
    public Categories addCategory(Category category){
        this.categories.add(category);
        return this;
    }
    
    public String toXml(){
        return XmlBuilder.toXml("categories", data, categories).toString();
    }
    
    public Categories font(String font){
        data.put("font", font);
        return this;
    }

    public Categories fontSize(Integer fontSize){
        data.put("fontSize", fontSize);
        return this;
    }

    public Categories fontColor(String fontColor){
        data.put("fontColor", fontColor);
        return this;
    }
}
