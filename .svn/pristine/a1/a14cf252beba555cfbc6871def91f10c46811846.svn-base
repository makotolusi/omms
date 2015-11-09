package m.w.codegen;

/** 代码生成用的元信息 */
public @interface CodeGenFieldMeta {
    /** 是否可为查询条件，默认是 */
    boolean searchable() default true;
    
    /** 是否可编辑，非可编辑的为隐藏域，默认是 */
    boolean editable() default true;
    
    /** 指定特别的class类型 */
    String cssClass() default "";
    
    /** 树或选择框获取数据用url */
    String url() default "";
}
