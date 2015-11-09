package m.w.core.util;

import java.net.URLEncoder;

import org.nutz.lang.Strings;

public abstract class Names {
    private static final String EMPTY_STRING = "";
    /**
     * 去除名称里面的回车、换行、制表符这样的字符
     * @param in
     * @return
     */
    public static String fmt(String in){
        return Strings.isBlank(in) ? EMPTY_STRING : in.replaceAll("\t|\r|\n", EMPTY_STRING);
    }
    
    /**
     * 将名称中非空的名称使用分隔符连接起来
     * @param separator
     * @param names
     * @return
     */
    public static String join(String separator, String... names){
        separator = Strings.sNull(separator);
        if(null == names){
            return "";
        }
        StringBuffer bf = new StringBuffer();
        for(String s : names){
            if(!Strings.isBlank(s)){
                bf.append(s);
                bf.append(separator);
            }
        }
        if(bf.length() > 0){
            return bf.substring(0, bf.length() - separator.length());
        }
        return "";
    }
    
    public static String encodeFileName(String fileName){
        try {
            StringBuffer s = new StringBuffer(URLEncoder.encode(fileName,"UTF-8"));
            return s.toString().replaceAll("[+]","%20");//替换"+"为"%20"
        }
        catch (Exception e) {}
        return "";
    }
}
