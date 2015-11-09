package m.w.sys.dao;

import m.w.core.dao.IdEntity;
import m.w.sys.domain.User;
import m.w.sys.domain.WxLog;
import m.w.sys.util.Users;

import org.nutz.dao.impl.NutDao;

/**
 * 对基本的增加、修改、删除记录系统日志
 * @author WenWu
 *
 */
public class WxDao extends NutDao {

    @Override
    public <T> T insert(T obj) {
        T t = super.insert(obj);
        if(obj instanceof IdEntity && !obj.getClass().equals(WxLog.class)){
            Long eid = ((IdEntity)obj).getId();
            String ename = obj.getClass().getSimpleName();
            wxlog(WxLog.ACTION_INSERT, eid, ename);
        }
        return t;
    }

    @Override
    public int update(Object obj) {
        int cnt = super.update(obj);
        if(obj instanceof IdEntity && !obj.getClass().equals(WxLog.class)){
            Long eid = ((IdEntity)obj).getId();
            String ename = obj.getClass().getSimpleName();
            wxlog(WxLog.ACTION_UPDATE, eid, ename);
        }
        return cnt;
    }

    @Override
    public int delete(Class<?> classOfT, long id) {
        int cnt = super.delete(classOfT, id);
        if(!classOfT.equals(WxLog.class)){
            wxlog(WxLog.ACTION_DELETE, id, classOfT.getSimpleName());
        }
        return cnt;
    }

    @Override
    public int delete(Object obj) {
        int cnt = super.delete(obj);
        if(obj instanceof IdEntity && !obj.getClass().equals(WxLog.class)){
            Long eid = ((IdEntity)obj).getId();
            String ename = obj.getClass().getSimpleName();
            wxlog(WxLog.ACTION_DELETE, eid, ename);
        }
        return cnt;
    }
    
    private void wxlog(String action, Long eid, String ename){
        User u = Users.user();
        super.insert(new WxLog(action, u.getId(), u.getUsername(), eid, ename, null));
    }

}
