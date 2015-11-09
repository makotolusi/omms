package m.w.sys.domain;

import java.io.Serializable;
import java.sql.Date;

import m.w.core.dao.IdEntity;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("sys_workcalendar")
@Comment("工作日历")
public class WorkCalendar implements Serializable, IdEntity {
    private static final long serialVersionUID = -977261537620961742L;
    public static enum TYPE {
        Holiday,
        Workday,
        Normal
    }

    /** 主键 */
    @Id
    @Column
    @Comment("主键")
    private Long id;

    /** 日期 */
    @Column
    @Comment("日期")
    @ColDefine(notNull=true, type=ColType.DATE)
    private Date onDate;

    /** 日期类型 */
    @Column
    @Comment("日期类型")
    @ColDefine(notNull=true, width=7)
    private TYPE dateType = TYPE.Normal;

    /** 说明 */
    @Column
    @ColDefine(notNull=true, width=200)
    @Comment("说明")
    private String notes;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getOnDate() {
        return onDate;
    }
    public void setOnDate(Date onDate) {
        this.onDate = onDate;
    }
    public TYPE getDateType() {
        return dateType;
    }
    public void setDateType(TYPE dateType) {
        this.dateType = dateType;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
}
