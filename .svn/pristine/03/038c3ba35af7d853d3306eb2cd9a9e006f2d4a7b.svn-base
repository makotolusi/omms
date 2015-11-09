package m.w.sys.domain;

import java.io.Serializable;
import java.util.Date;

import m.w.core.dao.IdEntity;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("Sys_WxLog")
public class WxLog implements Serializable, IdEntity {
    private static final long serialVersionUID = 693393492614433361L;
    
    public WxLog(){
        
    }
    
    public WxLog(String action, Long userId, String userName, Long entityId, String entityName, String msg){
        this.onTime = new Date();
        this.action = action; 
        this.userId = userId;
        this.userName = userName;
        this.entityId = entityId;
        this.entityName = entityName;
        this.msg = msg;
    }
    
    
    /** 插入 */
    public static final String ACTION_INSERT = "insert";
    /** 更新 */
    public static final String ACTION_UPDATE = "update";
    /** 删除 */
    public static final String ACTION_DELETE = "delete";
    /** 其它 */
    public static final String ACTION_OTHER = "other";
    
    // =========================================================================
    // 数据库字段
    // =========================================================================

    @Id
    @Column
    private Long id;

    @Column
    @ColDefine(notNull = true, type = ColType.DATETIME)
    private Date onTime;

    @Column
    @ColDefine(notNull = true, width = 50)
    private String action;

    @Column
    @ColDefine(notNull = true, type = ColType.INT, width = 16, precision = 0)
    private Long userId;

    @Column
    @ColDefine(notNull = true, width = 50)
    private String userName;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 8000)
    private String msg;
    
    @Column
    @ColDefine(type = ColType.INT, width = 16, precision = 0)
    private Long entityId;

    @Column
    @ColDefine(width = 100)
    private String entityName;

    // =========================================================================
    // 生成方法
    // =========================================================================
    @Override
    public Long getId() {
        return id;
    }

    public Date getOnTime() {
        return onTime;
    }

    public void setOnTime(Date onTime) {
        this.onTime = onTime;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
    
}
