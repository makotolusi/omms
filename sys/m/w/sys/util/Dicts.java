package m.w.sys.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import m.w.core.util.Daos;
import m.w.sys.domain.DictItem;
import m.w.sys.domain.DictType;
import m.w.sys.domain.Dictable;

import org.apache.commons.beanutils.BeanUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * 字典操作工具类
 * @author WenWu
 *
 */
public class Dicts {
    private static Log log = Logs.get();
    
	private static List<DictType> types = new ArrayList<DictType>();
    private static Map<String, List<DictItem>> map = new ConcurrentHashMap<String, List<DictItem>>();
    private static boolean inited = false;
    
    private static Map<Long, String> id2Name = new HashMap<Long, String>();
    private static Map<Class<? extends Dictable>, Map<String, String>> fields = new ConcurrentHashMap<Class<? extends Dictable>, Map<String, String>>();
    
    static{
        init();
    }
    
    private synchronized static void init(){
        if (inited) {
            return;
        }
        log.debug("开始初始化字典缓存...");
        Dao dao = Daos.dao();
        List<DictType> dictTypes = dao.query(DictType.class, Cnd.orderBy().asc("orders"));
        for(DictType type : dictTypes){
            types.add(type);
            map.put(type.getCode(), Collections.unmodifiableList(dao.query(DictItem.class, Cnd.where("typeId", "=", type.getId()).and("state", "=", DictItem.STATE_1).asc("orders"), null)));
        }
        List<DictItem> items = dao.query(DictItem.class, null);
        for(DictItem item : items){
            id2Name.put(item.getId(), item.getText());
        }
        inited = true;
        log.debug("字典缓存初始化成功！");
    }
    
    /**
     * 重新加载字典缓存
     * @return
     */
    public synchronized static boolean reload(){
        types.clear();
        map.clear();
        inited = false;
        init();
        return true;
    }
    
    /**
     * 获取所有字典项类型
     * @return
     */
    public static List<DictType> types(){
    	return Collections.unmodifiableList(types);
    }
    
    /**
     * 获取某种类型的字典项
     * @param code 字典分类代码
     * @return
     */
    public static List<DictItem> dicts(String code){
    	return map.get(code);
    }
    
    /**
     * 根据字典ID获取对应的中文显示
     * @param id 字典项ID
     * @return
     */
    public static String getText(Long id){
        return id2Name.get(id);
    }
    
    /**
     * 根据字典ID获取对应的中文显示
     * @param id
     * @return
     */
    public static String getText(String id){
        try {
            return id2Name.get(Long.parseLong(id));
        }
        catch (Exception e) {
            return id;
        }
    }
    
    /**
     * 注册为字典项类
     * @param clazz
     */
    public static void reg(Dictable clazz){
        Map<String, String> fm = clazz.getDictFieldMap();
        if(fm != null){
            fields.put(clazz.getClass(), fm);
        }
    }
    
    /**
     * 包装字典项的显示
     * @param clazz
     */
    public static void wrap(Dictable clazz){
        Map<String, String> fm = fields.get(clazz.getClass());
        for(String key : fm.keySet()){
            try {
                String kv = BeanUtils.getProperty(clazz, key);
                BeanUtils.setProperty(clazz, fm.get(key), getText(kv));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
