package m.w.frs.mg.domain;

import java.util.Map;

import m.w.core.service.Wrapable;

public interface InfoSysable extends Wrapable{
	/**
     * 信息系统ID到信息系统名称的映射
     * @return
     */
    public Map<String, String> getInfoSysFieldMap();
}
