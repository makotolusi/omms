package m.w.sys.domain;

import java.util.Map;

import m.w.core.service.Wrapable;

public interface Constable extends Wrapable{
    /**
     * 常量项到常量显示的映射
     * @return
     */
    public Map<String, String> getConstFieldMap();
}
