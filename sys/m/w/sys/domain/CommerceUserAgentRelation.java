package m.w.sys.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import m.w.core.dao.IdEntity;
import m.w.sys.util.WrapSupport;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

/**
 * 商业用户
 * 
 * 
 */
@Table("commerce_user_agent_relation")
public class CommerceUserAgentRelation extends WrapSupport implements Serializable,IdEntity,
		Constable {
	private static final long serialVersionUID = -6909410061423195974L;

	@Override
	public Map<String, String> getConstFieldMap() {
		Map<String, String> fm = new HashMap<String, String>();
		// fm.put("editor", "editorText");
		return fm;
	}

	public CommerceUserAgentRelation() {
	}

	public CommerceUserAgentRelation(Long id) {
		this.id = id;
	}

	// =========================================================================
	// 数据库字段
	// =========================================================================

	@Id
	@Column
	protected Long id;

	@Column
	protected Long agentId;//经销商Id
	
	@One(target = CommerceUser.class, field = "agentId")
	public CommerceUser agent;
	
	@Column
	protected Long offLineId;//经销商Id
	
	
	@One(target = CommerceUser.class, field = "offLineId")
	public CommerceUser offLine;
	
	@Column
	protected Status status=Status.NORMAL;//逻辑删除
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public CommerceUser getAgent() {
		return agent;
	}

	public void setAgent(CommerceUser agent) {
		this.agent = agent;
	}

	public Long getOffLineId() {
		return offLineId;
	}

	public void setOffLineId(Long offLineId) {
		this.offLineId = offLineId;
	}

	public CommerceUser getOffLine() {
		return offLine;
	}

	public void setOffLine(CommerceUser offLine) {
		this.offLine = offLine;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public enum Status {
		STOP(0,"停用"), NORMAL(1,"启用");

		private final Integer index;
		private final String value;
		Status(Integer index,String value) {
			this.value = value;
			this.index=index;
		}

		public String getValue() {
			return value;
		}
		
		public Integer getIndex() {
			return index;
		}
	}
}
