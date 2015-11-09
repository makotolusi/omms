package m.w.codegen;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import m.w.core.dao.IdEntity;
import m.w.core.util.Clazzs;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Lang;
import org.nutz.lang.Mirror;
import org.nutz.lang.Strings;

public class Config {
    private Config(){}

    /** 代码生成的类型 ：按实体类或按包 */
    public static enum TYPE{
        /** 按某个实体类生成代码 */
        CLASS,
        /** 主从表形式 */
        MD,
        /** 生成某个包下的所有代码 */
        PACKAGE
    }
    /**
     * 默认按类生成代码
     */
    private TYPE type = TYPE.CLASS;

    /** 实体类名 */
    private Class<?> domainClass;

    /** 代码生成包名 */
    private String basePackage;
    
    /** 访问的地址前缀 */
    private String uriPrefix;
    
    /** 实体中文名 */
    private String chineseName;
    
    private Map<String, String> fields;
    
    private Mirror<?> mirror;
    
    /**
     * 按单个实体类生成代码的配置
     * @param domainClass 实体类
     * @return
     */
    public static Config make(Class<?> domainClass){
        return make(domainClass, null);
    }
    
    /**
     * 按单个实体类生成代码的配置
     * @param domainClass 实体类
     * @param chineseName 实体类中文名
     * @return 如果为有效实体类则返回Config对象，否则返回null
     */
    public static Config make(Class<?> domainClass, String chineseName){
        Mirror<?> mirror = Mirror.me(domainClass);
        if(!Clazzs.hasInterface(domainClass, IdEntity.class)){
            System.err.println("指定的类["+domainClass.getName()+"]没有实现IdEntity接口！忽略其代码生成！");
            return null;
        }
        Table table = mirror.getAnnotation(Table.class);
        if(table == null){
            System.err.println("指定的类["+domainClass.getName()+"]没有@Table注解，不是一个实体类！忽略其代码生成！");
            return null;
        }
        if(Strings.isBlank(chineseName)){
            Comment comment = mirror.getAnnotation(Comment.class);
            if(comment != null){
                chineseName = comment.value();
            }
            if(Strings.isBlank(chineseName)){
                chineseName = domainClass.getSimpleName();
            }
        }
        
        Config cfg = new Config();
        cfg.mirror = mirror;
        cfg.type = TYPE.CLASS;
        cfg.domainClass = domainClass;
        cfg.fields = cfg.getDomainFields();
        if(cfg.fields == null){
            return null;
        }
        cfg.chineseName = Strings.sBlank(chineseName, domainClass.getSimpleName());
        cfg.basePackage = cfg.getBasePackageByClass();
        cfg.uriPrefix = cfg.getUriPrefixByClass();
        return cfg;
    }

    /**
     * 模板路径
     * @return
     */
    public String templatePath(){
        switch (this.type) {
        case MD:
            return "template/md";
        default:
            return "template/ppms";
        }
    }
    
    /**
     * 实体类
     * @return
     */
    public Class<?> domainClass(){
        return this.domainClass;
    }
    
    /**
     * 实体类全名
     * @return
     */
    public String domainName(){
        return this.domainClass.getSimpleName();
    }
    
    /**
     * 实体类所在的业务包名
     * @return
     */
    public String basePackage(){
        return this.basePackage;
    }
    
    /**
     * HTTP 访问地址的前缀
     * @return
     */
    public String uriPrefix(){
        return this.uriPrefix;
    }
    
    /**
     * 实体中文名
     * @return
     */
    public String chineseName(){
        return this.chineseName;
    }
    
    /**
     * 首字母小写的实体类名
     * @return
     */
    public String uncapDomainName(){
        return StringUtils.uncapitalize(domainName());
    }
    
    /**
     * 全部小写形式的实体类名
     * @return
     */
    public String lowerDomainName(){
        return domainName().toLowerCase();
    }
    
    /**
     * 获取给定类的所有字段映射
     * key   字段名
     * value 字段中文名或字段名
     * @return
     */
    public Map<String, String> domainFields(){
        return Collections.unmodifiableMap(fields);
    }
    
    private Map<String, String> getDomainFields(){
        Map<String, String> fields = new TreeMap<String, String>();
        Field[] fieldArr = mirror.getFields(Column.class);
        if(fieldArr == null || fieldArr.length==0){
            System.err.println("指定的类["+domainClass.getName()+"]没有@Column注解,尝试查找所有字段...");
            fieldArr = mirror.getFields();
        }
        if(fieldArr == null || fieldArr.length==0){
            System.err.println("指定的类["+domainClass.getName()+"]没有声明任何字段！忽略其代码生成！");
            return null;
        }
        for (Field field : fieldArr) {
            String fieldName = field.getName();
            String commentValue = fieldName;
            Comment comment  = field.getAnnotation(Comment.class);
            if(comment != null){
                commentValue = Strings.sBlank(comment.value(), fieldName);
            }else{
                System.err.println("指定的类["+domainClass.getName()+"]的字段["+fieldName+"]没有@Comment注解,直接使用字段名为中文显示");
            }
            fields.put(fieldName, commentValue);
        }
        return fields;
    }
    
    private String getBasePackageByClass(){
        String name = domainClass.getName();
        if(name.indexOf(".domain.") < 0){
            Lang.makeThrow("结构错误：包名  %s 中不包含'.domain.'，不能生成代码。", name);
        }
        if(!name.startsWith("m.w.")){
            Lang.makeThrow("结构错误：包名 %s 不是'm.w.'开头，不能生成代码。", name);
        }
        return name.substring(0, name.lastIndexOf(".domain."));
    }
    
    private String getUriPrefixByClass(){
        return basePackage.substring(3, basePackage.length()).replace('.', '/') + '/' + domainName().toLowerCase();
    }
    
}
