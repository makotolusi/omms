package m.w.sys.util;

import m.w.core.service.Wrapable;
import m.w.core.util.Clazzs;
import m.w.frs.mg.domain.InfoSysable;
import m.w.frs.mg.domain.InfoTypeable;
import m.w.frs.mg.util.Infos;
import m.w.sys.domain.Constable;
import m.w.sys.domain.Dictable;
import m.w.sys.domain.Orgable;

public abstract class WrapSupport implements Wrapable {

    @Override
    public void wrap() {
        Class<?> clazz = getClass();
        if (Clazzs.hasInterface(clazz, Dictable.class)) {
            Dicts.wrap((Dictable)this);
        }
        if (Clazzs.hasInterface(clazz, Constable.class)) {
            Consts.wrap((Constable)this);
        }

        if (Clazzs.hasInterface(clazz, InfoTypeable.class)) {
            Infos.wrap((InfoTypeable)this);
        }
        if (Clazzs.hasInterface(clazz, InfoSysable.class)) {
            Infos.wrapSys((InfoSysable)this);
        }
    }
}
