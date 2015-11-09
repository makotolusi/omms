package m.w.sys.service;

import java.util.List;
import java.util.Set;

import m.w.core.service.WxIdService;
import m.w.core.util.Ids;
import m.w.sys.domain.Atta;
import m.w.sys.domain.AttachableEntity;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.lang.Mirror;
import org.nutz.log.Log;
import org.nutz.log.Logs;

public class AttachableService<T extends AttachableEntity> extends WxIdService<T>{
    private static Log log = Logs.get();

    @Override
    protected void afterSave(T obj) {
        super.afterSave(obj);
        updateAtta(obj);
    }

    @Override
    protected void afterInsert(T obj) {
        super.afterInsert(obj);
        updateAtta(obj);
    }

    @Override
    protected void afterUpdate(T obj) {
        super.afterUpdate(obj);
        updateAtta(obj);
    }

    private void updateAtta(T obj) {
        if (obj instanceof AttachableEntity) {
            AttachableEntity a = (AttachableEntity) obj;
            Mirror<AttachableEntity> m = Mirror.me(a);
            Set<String> attaIds = a.getAttaFields();
            for (String attaId : attaIds) {
                Object o = m.getValue(a, attaId);
                if (o != null && o instanceof String) {
                    String value = (String) o;
                    List<Long> ids = Ids.toIds(value);
                    if (ids != null) {
                        log.debugf("处理附件[%s - %d]的附件[ %s]", a.getClass().getSimpleName(), a.getId(), value);
                        dao().update(Atta.class, 
                                     Chain.make("entityName", a.getClass().getSimpleName()).add("entityId", a.getId()).add("entityAttaField", attaId),
                                     Cnd.where("id", "in", ids));
                    }
                }
            }

        }
    }

    @Override
    protected void afterDelete(T obj) {
        super.afterDelete(obj);
        if(obj instanceof AttachableEntity){
            AttachableEntity a = (AttachableEntity)obj;
            Mirror<AttachableEntity> m = Mirror.me(a);
            Set<String> attaIds = a.getAttaFields();
            for (String attaId : attaIds) {
                Object o = m.getValue(a, attaId);
                if (o != null && o instanceof String) {
                    String value = (String) o;
                    List<Long> ids = Ids.toIds(value);
                    if (ids != null) {
                        log.debugf("清空附件[%s - %d]的附件[ %s]", a.getClass().getSimpleName(), a.getId(), value);
                        dao().update(Atta.class,
                                     Chain.make("entityName", null).add("entityId", null),
                                     Cnd.where("id", "in", ids));
                    }
                }
            }
        }
    }
}
