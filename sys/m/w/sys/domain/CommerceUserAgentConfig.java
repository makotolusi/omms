package m.w.sys.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table("commerce_user_agent_config")
public class CommerceUserAgentConfig extends WrapSupport implements Serializable,IdEntity,
		Constable {
	private static final long serialVersionUID = -6909410061423195974L;

	@Override
	public Map<String, String> getConstFieldMap() {
		Map<String, String> fm = new HashMap<String, String>();
		// fm.put("editor", "editorText");
		return fm;
	}

	public CommerceUserAgentConfig() {
	}

	public CommerceUserAgentConfig(Long id) {
		this.id = id;
	}

	// =========================================================================
	// 数据库字段
	// =========================================================================

	public CommerceUserAgentConfig(Long agentId, BigDecimal profitRatio) {
		super();
		this.agentId = agentId;
		this.profitRatio = profitRatio;
	}

	@Id
	@Column
	protected Long id;

	@Column
	protected Long agentId;//经销商Id
	
	@One(target = CommerceUser.class, field = "agentId")
	public CommerceUser commerceUser;
	
	@Column
	protected BigDecimal profitRatio;//利润率
	
	protected BigDecimal profit;//总盈利
	
	protected BigDecimal grossIncome;//总
	
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

	public CommerceUser getCommerceUser() {
		return commerceUser;
	}

	public void setCommerceUser(CommerceUser commerceUser) {
		this.commerceUser = commerceUser;
	}

	public BigDecimal getProfitRatio() {
		return profitRatio;
	}

	public void setProfitRatio(BigDecimal profitRatio) {
		this.profitRatio = profitRatio;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	
	public BigDecimal getGrossIncome() {
		return grossIncome;
	}

	public void setGrossIncome(BigDecimal grossIncome) {
		this.grossIncome = grossIncome;
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
