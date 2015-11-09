代码生成器使用说明
代码生成器m.w.codegen.CodeGen可根据定义的实体类来生成相关代码，包括：Module、
Service两个java文件；index.jsp、_form.jsp、add.jsp、update.jsp、query.jsp和
view.jsp六个文件。
调用方式为直接运行方法CodeGen.gen()传入一个或多个实体类为参数即可。

-------------------------------------------------------------------------------

关于实体类的定义
实体类必须定义@Table注释，如果实体类没有定义任何@Column注释，则整个实体类字段都
被视为数据库字段；如果定义有@Column，则仅包含@Column注释的字段被视为数据库字段。
实体类与数据库字段上定义的@Comment注释中的内容被视为类和字段对应的中文名，如果没有
定义@Comment则直接将类名与字段名视为中文名。

-------------------------------------------------------------------------------

实体类字段中避免出现的字段名(与分页查询相关)：
page
rows
order
sort

-------------------------------------------------------------------------------
实体类字段的可用类型：
【字符串】
String

【整数】
Long Integer long int Short short BigInteger Byte byte

【浮点数】
Double Float double float BigDecimal

【日期】
java.sql.Date

【日期时间】
java.util.Date

【Bool】
Boolean boolean

-------------------------------------------------------------------------------
实体类字段的可用查询方式：
【字符串】
eq  = EQUAL
lk  = LIKE
in  = IN
ne  = NOT EQUAL
ni  = NOT IN
nu  = IS NULL
nn  = IS NOT NULL
nl  = NOT LIKE
il  = LIKE IGNORE CASE
inl = NOTE LIKE IGNORE CASE

【整数】
eq  = EQUAL
in  = IN
bt  = BETWEEN
ge  = GREAT or EQUAL
le  = LESS or EQUAL
gt  = GREAT
lt  = LESS
ne  = NOT EQUAL
ni  = NOT IN
nu  = IS NULL
nn  = IS NOT NULL

【浮点数】
eq  = EQUAL
bt  = BETWEEN
ge  = GREAT or EQUAL
le  = LESS or EQUAL
gt  = GREAT
lt  = LESS
ne  = NOT EQUAL
nu  = IS NULL
nn  = IS NOT NULL

【日期】
eq  = EQUAL
bt  = BETWEEN
ge  = GREAT or EQUAL
le  = LESS or EQUAL
gt  = GREAT
lt  = LESS
ne  = NOT EQUAL
nu  = IS NULL
nn  = IS NOT NULL

【日期时间】
eq  = EQUAL
bt  = BETWEEN
ge  = GREAT or EQUAL
le  = LESS or EQUAL
gt  = GREAT
lt  = LESS
ne  = NOT EQUAL
nu  = IS NULL
nn  = IS NOT NULL

【Bool】
eq  = EQUAL
ne  = NOT EQUAL
nu  = IS NULL
nn  = IS NOT NULL

