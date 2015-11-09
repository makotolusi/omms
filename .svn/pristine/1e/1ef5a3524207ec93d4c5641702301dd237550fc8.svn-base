package m.w.chart.fcf.util;

import java.util.List;
import java.util.Map;

import m.w.chart.fcf.Xmlable;

import org.nutz.lang.Lang;

public class XmlBuilder {
    public static StringBuilder toXml(String name, Map<String, Object> data, List<? extends Xmlable> nodes){
        StringBuilder sb = beginXml(name, data);
        appendXml(nodes, sb);
        endXml(name, sb);
        return sb;
    }
    
    public static StringBuilder toXml(String name, Map<String, Object> data){
        return toXml(name, data, null);
    }
    
    public static StringBuilder toSimpleXml(String name, Map<String, Object> data){
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(name);
        if(!Lang.isEmpty(data)){
            for(String key : data.keySet()){
                Object value = data.get(key);
                if(null != value){
                    sb.append(" ");
                    sb.append(key);
                    sb.append("=\"");
                    if(value instanceof Boolean){
                        sb.append(Boolean.TRUE.equals(value) ? "1" : "0");
                    }else{
                        sb.append(value.toString());
                    }
                    sb.append("\"");
                }
            }
        }
        sb.append("/>");
        return sb;
    }
    
    public static StringBuilder beginXml(String name, Map<String, Object> data){
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(name);
        if(!Lang.isEmpty(data)){
            for(String key : data.keySet()){
                Object value = data.get(key);
                if(null != value){
                    sb.append(" ");
                    sb.append(key);
                    sb.append("=\"");
                    if(value instanceof Boolean){
                        sb.append(Boolean.TRUE.equals(value) ? "1" : "0");
                    }else{
                        sb.append(value.toString());
                    }
                    sb.append("\"");
                }
            }
        }
        sb.append(">");
        return sb;
    }
    
    public static StringBuilder endXml(String name, StringBuilder sb){
        sb.append("</");
        sb.append(name);
        sb.append(">");
        return sb;
    }
    
    public static StringBuilder appendXml(Xmlable node, StringBuilder sb){
        if(node != null){
            sb.append(node.toXml());
        }
        return sb;
    }
    
    public static StringBuilder appendXml(List<? extends Xmlable> nodes, StringBuilder sb){
        if(!Lang.isEmpty(nodes)){
            for(Xmlable node : nodes){
                sb.append(node.toXml());
            }
        }
        return sb;
    }
}
