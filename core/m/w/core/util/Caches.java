package m.w.core.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

public abstract class Caches {
    private static CacheManager manager = CacheManager.create();

    public static Cache getCache(String name){
        return manager.getCache(name);
    }
}
