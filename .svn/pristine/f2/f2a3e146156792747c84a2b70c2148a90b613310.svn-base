package m.w.chart.fcf;

import java.util.List;

import m.w.chart.fcf.util.XmlBuilder;

import com.google.common.collect.Lists;

public class Trendlines implements Xmlable {
    private List<Line> lines = Lists.newLinkedList();
    
    public static Trendlines born(){
        Trendlines self = new Trendlines();
        return self;
    }
    
    public Trendlines addLine(Line line){
        this.lines.add(line);
        return this;
    }
    
    public String toXml(){
        return XmlBuilder.toXml("trendlines", null, lines).toString();
    }
}
