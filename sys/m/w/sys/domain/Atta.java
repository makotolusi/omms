package m.w.sys.domain;

import java.io.Serializable;
import java.util.Date;

import m.w.core.dao.IdEntity;
import m.w.core.util.Files;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.json.JsonField;

/**
 * 附件表
 * @author WenWu
 *
 */
@Table("Sys_Atta")
public class Atta implements Serializable, IdEntity{
    private static final long serialVersionUID = 7653089243427528690L;

    /** 自动生成主键 */
    @Id
    @Column
    private Long id;

    /** 文件类型 */
    @Column
    @ColDefine(notNull = true, width = 5)
    private AttaType fileType;

    /** 文件大小，按字节数 */
    @Column
    @ColDefine(notNull = true, type = ColType.INT, width = 16, precision = 0)
    private Long fileSize;

    /** 原始文件名 */
    @Column
    @ColDefine(notNull = true, width = 512)
    private String rawName;

    /** 文件存放路径 */
    @Column
    @ColDefine(notNull = true, width = 1024)
    private String filePath;
    
    /** 扩展名 */
    @Column
    @ColDefine(width = 12)
    private String extName;
    
    /** MIME */
    @Column
    @ColDefine(width = 128)
    private String mime;
    
    /** 备注，说明 */
    @Column
    @ColDefine(width = 500)
    private String notes;
    
    /** 对应的实体对象类名 */
    @Column
    @ColDefine(width = 50)
    private String entityName;
    
    /** 对应的实体对象类中附件字段的名字 */
    @Column
    @ColDefine(width = 50)
    private String entityAttaField;
    
    /** 对应的实体对象ID */
    @Column
    private Long entityId;
    
    /** 上传用户ID */
    @Column
    private Long userId;
    
    /** 上传用户姓名 */
    @Column
    @ColDefine(width = 50)
    private String userName;
    
    /** 上传时间 */
    @Column
    private Date onTime;
    
    /** 显示文件大小 */
    @JsonField
    public String getLength(){
        return Files.byteCountToDisplaySize(fileSize);
    }
    
    public void setLength(String length) {
    }


    // =========================================================================
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AttaType getFileType() {
        return fileType;
    }

    public void setFileType(AttaType fileType) {
        this.fileType = fileType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getRawName() {
        return rawName;
    }

    public void setRawName(String rawName) {
        this.rawName = rawName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }


    public String getExtName() {
        return extName;
    }


    public void setExtName(String extName) {
        this.extName = extName;
    }


    public String getSize() {
        return Files.byteCountToDisplaySize(fileSize);
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getOnTime() {
        return onTime;
    }

    public void setOnTime(Date onTime) {
        this.onTime = onTime;
    }

    public String getEntityAttaField() {
        return entityAttaField;
    }

    public void setEntityAttaField(String entityAttaField) {
        this.entityAttaField = entityAttaField;
    }

}
