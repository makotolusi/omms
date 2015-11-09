package m.w.codegen;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.lang.Mirror;

public class FieldInfo {
    public FieldInfo(String name, Class<?> clazz, String comment, ColDefine define){
        this.name = name;
        this.comment = comment;
        this.clazz = clazz;
        this.define = define;
        this.mirror = Mirror.me(clazz);
    }

    private String name;
    private String comment;
    private Class<?> clazz;
    private ColDefine define;
    private Mirror<?> mirror;
    
    /** 运行的最大宽度 */
    public int width(){
        return define != null ? define.width() : 0;
    }
    
    /** 精度 */
    public int precision(){
        return define != null ? define.precision() : 0;
    }
    
    /** 是否允许为空 */
    public boolean isNnllable(){
        return define != null ? define.notNull() : true;
    }
    
    /** 是否日期类型 */
    public boolean isDate(){
        return java.sql.Date.class.equals(clazz);
    }
    
    /** 是否时间类型 */
    public boolean isTime(){
        return java.util.Date.class.equals(clazz);
    }
    
    /** 是否为枚举类型 */
    public boolean isEnum(){
        return mirror.isEnum();
    }
    
    /** 是否为整形 */
    public boolean isInt(){
        return mirror.isInt();
    }
    
    /** 是否为小数 */
    public boolean isDecimal(){
        return mirror.isDecimal();
    }
    
    /** 是否数值类型 */
    public boolean isNumber(){
        return mirror.isNumber();
    }
    
    /** 是否金额类型 */
    public boolean isCurrency(){
        return java.math.BigDecimal.class.equals(clazz);
    }
    
    public String name() {
        return name;
    }
    public String comment() {
        return comment;
    }
    public Class<?> clazz() {
        return clazz;
    }
}
