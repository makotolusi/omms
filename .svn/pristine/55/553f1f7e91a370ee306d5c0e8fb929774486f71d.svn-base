package m.w.chart.fcf.util;

import java.util.List;

import m.w.chart.fcf.Set;

import com.google.common.collect.Lists;


public class SetBuilder {
    private List<Set> sets = Lists.newLinkedList();
    
    public static SetBuilder make(){
        return new SetBuilder();
    }
    
    public List<Set> toList(){
        return sets;
    }
    
    public SetBuilder add(Set set){
        sets.add(set);
        return this;
    }
    public SetBuilder add(String name, Number value){
        sets.add(Set.born().name(name).value(value));
        return this;
    }
    
    public SetBuilder add(String name, Number value, String color){
        sets.add(Set.born().name(name).value(value).color(color));
        return this;
    }
}
