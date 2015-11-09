package m.w.frs.mg.domain;

import java.util.Map;

import m.w.core.service.Wrapable;

public interface InfoTypeable extends Wrapable{
	/**
     * 信息系统类型ID到信息系统类型名称的映射
     * @return
     */
    public Map<String, String> getInfoTypeFieldMap();
}
