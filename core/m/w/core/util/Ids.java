package m.w.core.util;

import java.util.Collections;
import java.util.List;

import org.nutz.lang.Lang;
import org.nutz.lang.Strings;

public abstract class Ids {
    /**
     * 将逗号（,）分割的字符串ID转换为Long类型的ID列表
     * @param strIds
     * @return 如果输入的为null或空串或转换失败，返回空列表，否则返回装换后的
     */
    @SuppressWarnings("unchecked")
    public static List<Long> toIds(String strIds){
        if(Strings.isBlank(strIds)){
            return Collections.EMPTY_LIST;
        }
        try {
            return Lang.array2list(Strings.splitIgnoreBlank(strIds, ","), Long.class);
        }
        catch (Exception e) {
            return Collections.EMPTY_LIST;
        }
    }
}
