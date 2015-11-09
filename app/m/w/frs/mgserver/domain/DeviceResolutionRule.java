package m.w.frs.mgserver.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Index;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.TableIndexes;

import m.w.core.dao.IdEntity;
import m.w.sys.domain.Constable;
import m.w.sys.util.WrapSupport;

@Table("commerce_user_device_resolution_rule")
@Comment("设备尺寸规格")
@TableIndexes({ @Index(name = "resolution_index", fields = { "resolution" }, unique = true) })
public class DeviceResolutionRule extends WrapSupport implements Serializable, IdEntity, Constable {

	public final static String RULE1="rule1";
	public final static String RULE2="rule2";
	public final static String RULE3="rule3";
	/**
	 * 
	 */
	private static final long serialVersionUID = -5541257497772248914L;

	@Override
	public Map<String, String> getConstFieldMap() {
		Map<String, String> fm = new HashMap<String, String>();
		return fm;
	}

	@Id
	@Column
	private Long id;

	/** resolution */
	@Column
	private String resolution;

	/** rule1 */
	@Column("rule1")
	protected String rule1;

	/** rule2 */
	@Column("rule2")
	protected String rule2;

	/** rule3 */
	@Column("rule3")
	protected String rule3;

	/** rule4 */
	@Column("rule4")
	protected String rule4;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getRule1() {
		return rule1;
	}

	public void setRule1(String rule1) {
		this.rule1 = rule1;
	}

	public String getRule2() {
		return rule2;
	}

	public void setRule2(String rule2) {
		this.rule2 = rule2;
	}

	public String getRule3() {
		return rule3;
	}

	public void setRule3(String rule3) {
		this.rule3 = rule3;
	}

	public String getRule4() {
		return rule4;
	}

	public void setRule4(String rule4) {
		this.rule4 = rule4;
	}

}
